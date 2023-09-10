package com.sayan.BlogApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentBlogRequest {
    private String commentId;
    private String authorId;
    private String blogId;
    private String viewerId;
    private String comment;
}
