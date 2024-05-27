package com.evan.commentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String email;
    private String username;
//    private String password;
    private int role;
    private Set<Long> posts;
    private Set<Long> comments;

}
