import React from "react";
import {useNavigate} from "react-router-dom"
import {Box, Button, Heading, Text} from "@chakra-ui/react";
import axios from "axios";
import BlogpostService from "../../service/BlogpostService";

const PostCard = ({postId, author, title, description, category, comments}) => {
  const navigate = useNavigate();
  const handleClickTitle = () => {
    console.log("in postCard, the comments is: " + comments)
    navigate("/product/detail", {
      state: {postId, author, title, description, category, comments}
    });
  }

  const handleDeletePost = async () => {
    let res = await BlogpostService.deletePostById(postId);
    console.log(res);
    navigate(0);
  }

  return <Box>
    <Heading size='xs' textTransform='uppercase'><Button onClick={handleClickTitle}>{title}</Button></Heading>
    <Text pt='2' fontSize='sm'>Author: {author}</Text>
    <Text pt='2' fontSize='sm'>Description: {description}</Text>
    <Button marginTop='8px' colorScheme='red' size='sm' variant='outline' onClick={handleDeletePost}>Delete</Button>
  </Box>
}

export default PostCard;