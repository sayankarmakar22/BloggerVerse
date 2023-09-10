package com.sayan.BlogApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class AuthorPostResponse {
    private String authorId;
    private String blogId;
    private String blogContent;
    private String blogTitle;
    private Date publishDateTime;
    private long views;
    private List<String> comments;
}
