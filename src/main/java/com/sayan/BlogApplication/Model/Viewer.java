package com.sayan.BlogApplication.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
@Entity
@Table(name = "Viewer")
public class Viewer {
    @Id
    private int viewerId;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private int username;

    @Column(length = 1500)
    private String password;
}
