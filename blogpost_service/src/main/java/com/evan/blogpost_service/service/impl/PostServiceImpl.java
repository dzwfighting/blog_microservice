package com.evan.blogpost_service.service.impl;

import com.evan.blogpost_service.dto.APIResponseDTO;
import com.evan.blogpost_service.dto.CategoryDTO;
import com.evan.blogpost_service.dto.PostDTO;
import com.evan.blogpost_service.dto.UserDTO;
import com.evan.blogpost_service.entity.Post;
import com.evan.blogpost_service.exception.ResourceNotFoundException;
import com.evan.blogpost_service.mapper.PostMapper;
import com.evan.blogpost_service.repository.PostRepository;
import com.evan.blogpost_service.service.CategoryClient;
import com.evan.blogpost_service.service.UserClient;
import com.evan.blogpost_service.service.PostService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);
    private UserClient userClient;
    private CategoryClient categoryClient;
    private PostRepository postRepository;

    @Override
    public APIResponseDTO savePost(PostDTO post) {
        LOGGER.info("in savePost()");
        // to get current user email
        String author = "user1@gmail.com";
        post.setAuthor(author);
        UserDTO userDTO = userClient.getUser(author);
        if (userDTO == null) throw new ResourceNotFoundException("Author", "email", author);

        Post convertPost = PostMapper.mapToPost(post);
        Post newPost = postRepository.save(convertPost);
        PostDTO postDto = PostMapper.mapToPostDTO(newPost);

        // TODO: append postId into current user
        Set<Long> addPost = userDTO.getPosts();
        addPost.add(postDto.getPostId());
        userDTO.setPosts(addPost);
        LOGGER.info("after add post in userDTO: " + userDTO.getPosts() + " the user email is: " + userDTO.getEmail());
        userClient.updateUser(userDTO);
        UserDTO newUserDTO = userClient.getUser(author);
        LOGGER.info("the newest user: " + newUserDTO.getPosts() + " the email is: : " + newUserDTO.getEmail());

//        Append category which current post belong into category database
        CategoryDTO categoryDTO = categoryClient.getCategoryByName(newPost.getCategory());
        if (categoryDTO == null) throw new ResourceNotFoundException("Category", "name", newPost.getCategory());
        Set<Long> addPostToCategory = categoryDTO.getPostIds();
        addPostToCategory.add(newPost.getPostId());
        categoryDTO.setPostIds(addPostToCategory);
        categoryClient.updateCategory(categoryDTO);
        CategoryDTO newCategoryDTO = categoryClient.getCategoryByName(newPost.getCategory());
        LOGGER.info("add current postId into category and update success: " + newCategoryDTO.getPostIds());

        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setUserDTO(newUserDTO);
        apiResponseDTO.setPostDTO(postDto);
        apiResponseDTO.setCategoryDTO(newCategoryDTO);
        LOGGER.info("success store response into apiResponse, the userDTO is: " + apiResponseDTO.getUserDTO().getPosts() + " the categoryDTO is: " + apiResponseDTO.getCategoryDTO().getPostIds());
        return apiResponseDTO;
    }

    @Override
    public PostDTO getPostById(Long postId) {
        if (postId == null) throw new ResourceNotFoundException("Post", "id", postId);
        LOGGER.info("in getPostById, the postId is: " + postId);
        Post post = postRepository.findById(postId).get();
        if (post == null) throw new ResourceNotFoundException("Post", "id", postId);

        PostDTO postDto = PostMapper.mapToPostDTO(post);

        return postDto;
    }

    @Override
    public List<PostDTO> getPostsByTitle(String title) {
        LOGGER.info("get posts by title");
        List<Post> posts = postRepository.findByTitle(title);
        List<PostDTO> postsDto = posts.stream().map(PostMapper::mapToPostDTO).collect(Collectors.toList());
        return postsDto;
    }

    @Override
    public List<PostDTO> getPostsByAuthor(String author) {
        LOGGER.info("get posts by author");
        List<Post> posts = postRepository.findByAuthor(author);
        List<PostDTO> postsDto = posts.stream().map(PostMapper::mapToPostDTO).collect(Collectors.toList());
        return postsDto;
    }

    @Override
    public List<PostDTO> getAllPosts() {
        LOGGER.info("get all posts");
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOS = posts.stream().map(PostMapper::mapToPostDTO).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO) {
        LOGGER.info("in updatePost(), the postId is: " + postDTO.getPostId());
        Post oldPost = postRepository.findById(postDTO.getPostId()).get();
        if (oldPost == null) throw new ResourceNotFoundException("Post", "id", postDTO.getPostId());
        Post newPost = PostMapper.mapToPost(postDTO);
        oldPost.setAuthor(newPost.getAuthor());
        oldPost.setTitle(newPost.getTitle());
        oldPost.setDescription(newPost.getDescription());
        oldPost.setCategory(newPost.getCategory());
        oldPost.setComments(newPost.getComments());
        Post updatePost = postRepository.save(oldPost);
        PostDTO updatePostDto = PostMapper.mapToPostDTO(updatePost);
        return updatePostDto;
    }

    @Override
    public APIResponseDTO deletePost(Long postId) {
        LOGGER.info("in deletePost()");
        Post delPost = postRepository.findById(postId).get();
        PostDTO delPostDTO = PostMapper.mapToPostDTO(delPost);
        LOGGER.info("the post which will be deleted is: " + delPostDTO.getPostId(), " the correspond category name is: " + delPostDTO.getCategory());

//        check the post if is existed:
        if (delPost == null) throw new ResourceNotFoundException("Post", "id", postId);

//        get author information, so that we can delete postId from the author
        String author = delPostDTO.getAuthor();
        UserDTO userDTO = userClient.getUser(author);

        if (userDTO == null) throw new ResourceNotFoundException("Author", "email", author);
        postRepository.delete(delPost);

        // TODO: delete postId which from user
//        delete post from user then update
        Set<Long> delPostFromUser = userDTO.getPosts();
        if (delPostFromUser.contains(postId)) delPostFromUser.remove(postId);
        userDTO.setPosts(delPostFromUser);
//        get new user information
        UserDTO newUserDTO = userClient.updateUser(userDTO);
        LOGGER.info("success update user data: " + newUserDTO.getPosts());

//        delete postId from category
        CategoryDTO categoryDTO = categoryClient.getCategoryByName(delPostDTO.getCategory());
        if (categoryDTO == null) throw new ResourceNotFoundException("Category", "name", delPostDTO.getCategory());
        Set<Long> delPostFromCategory = categoryDTO.getPostIds();
        LOGGER.info("the original category's postIds is: " + delPostFromCategory);

        if (delPostFromCategory.contains(delPostDTO.getPostId())) delPostFromCategory.remove(postId);
        categoryDTO.setPostIds(delPostFromCategory);
        CategoryDTO newCategoryDTO = categoryClient.updateCategory(categoryDTO);
        LOGGER.info("after delete the correspond postId, the category's postIds is: " + newCategoryDTO.getPostIds());

        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setUserDTO(newUserDTO);
        apiResponseDTO.setCategoryDTO(newCategoryDTO);
        LOGGER.info("Post deleted successfully: newUser: " + newUserDTO.getPosts() + " newCategoryDTO: " + newCategoryDTO.getPostIds());

        return apiResponseDTO;
    }

}
