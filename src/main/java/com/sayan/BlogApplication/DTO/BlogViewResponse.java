package com.sayan.BlogApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class BlogViewResponse {
    private String authorName;
    private String blogTitle;
    private String blogContent;
    private long blogViews;
    private List<String> blogComments = new ArrayList<>();
}
