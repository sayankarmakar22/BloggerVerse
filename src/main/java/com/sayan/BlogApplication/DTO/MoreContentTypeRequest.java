package com.sayan.BlogApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MoreContentTypeRequest {
    private String contentType;
    private String authorId;
}
