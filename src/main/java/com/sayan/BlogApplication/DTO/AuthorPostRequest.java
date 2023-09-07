package com.sayan.BlogApplication.DTO;

import com.sayan.BlogApplication.Model.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class AuthorPostRequest {
    private String authorId;
    private String blogId;
    private String blogContent;
    private String blogTitle;
}
