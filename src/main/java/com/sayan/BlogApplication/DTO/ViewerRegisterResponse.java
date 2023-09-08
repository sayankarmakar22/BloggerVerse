package com.sayan.BlogApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class ViewerRegisterResponse {
    private String viewerId;
    private String name;
    private String email;
    private String username;
    private Date registrationDateTime;
}
