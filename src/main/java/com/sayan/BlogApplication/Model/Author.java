package com.sayan.BlogApplication.Model;

import jakarta.persistence.*;
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
@Entity
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 255)
    private String name;

    private Date registrationDateTime;

    @Column(length = 255)
    private int username;

    @Column(length = 1500)
    private String password;

    @Column(length = 100)
    private String email;

    @Column(length = 15)
    private String phoneNumber;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<ContentType> contentType;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<BlogPost> blogPostList;


}
