package com.sayan.BlogApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class AuthorResponse {
    private String id;
    private String name;
    private String username;
    private String email;
    private String phoneNumber;
    private String contentType;
    private String registrationDateTime;
}
