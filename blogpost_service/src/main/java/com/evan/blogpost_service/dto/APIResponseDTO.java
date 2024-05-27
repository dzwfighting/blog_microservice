package com.evan.blogpost_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIResponseDTO {
    private PostDTO postDTO;
    private UserDTO userDTO;
    private CategoryDTO categoryDTO;

    public APIResponseDTO(PostDTO postDTO, UserDTO userDTO) {
        this.postDTO = postDTO;
        this.userDTO = userDTO;
    }

    public APIResponseDTO(PostDTO postDTO, CategoryDTO categoryDTO) {
        this.postDTO = postDTO;
        this.categoryDTO = categoryDTO;
    }

    public APIResponseDTO(UserDTO userDTO, CategoryDTO categoryDTO) {
        this.userDTO = userDTO;
        this.categoryDTO = categoryDTO;
    }
}
