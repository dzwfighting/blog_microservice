package com.evan.commentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        name = "CommentDTO Model information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    @Schema(
            description = "Comment Id"
    )
    private Long commentId;

    @Schema(
            description = "Comment's reviewer"
    )
    @Email
//    @NotEmpty(message = "comment's reviewer should not be empty")
    private String reviewer;

    @Schema(
            description = "Comment title"
    )
    @NotEmpty(message = "title should not be empty")
    private String title;

    @Schema(
            description = "Comment body"
    )
    @NotEmpty(message = "body should not be empty")
    private String body;

    @Schema(
            name = "Comment's postId"
    )
    @NotNull(message = "postId should not be null")
    private Long postId;

    public CommentDTO(String reviewer, String title, String body, Long postId) {
        this.reviewer = reviewer;
        this.title = title;
        this.body = body;
        this.postId = postId;
    }
}
