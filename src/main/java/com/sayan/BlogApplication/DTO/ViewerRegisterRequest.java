package com.sayan.BlogApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class ViewerRegisterRequest {
    private String viewerId;
    private String name;
    private String email;
    private String username;
    private String password;
    private String role;
}
