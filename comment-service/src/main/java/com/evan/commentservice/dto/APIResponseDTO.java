package com.evan.commentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class APIResponseDTO {
    private CommentDTO commentDTO;
    private UserDTO userDTO;
    private PostDTO postDTO;

    public APIResponseDTO(CommentDTO commentDTO, UserDTO userDTO) {
        this.commentDTO = commentDTO;
        this.userDTO = userDTO;
    }

    public APIResponseDTO(CommentDTO commentDTO, PostDTO postDTO) {
        this.commentDTO = commentDTO;
        this.postDTO = postDTO;
    }

    public APIResponseDTO(UserDTO userDTO, PostDTO postDTO) {
        this.userDTO = userDTO;
        this.postDTO = postDTO;
    }
}
