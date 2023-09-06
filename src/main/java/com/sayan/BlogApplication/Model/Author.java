package com.sayan.BlogApplication.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Column(length = 150)
    private String id;

    @Column(length = 255)
    private String name;

    private Date registrationDateTime;

    @Column(length = 255)
    private String username;

    @Column(length = 1500)
    private String password;

    @Column(length = 100)
    private String email;

    @Column(length = 15)
    private String phoneNumber;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<ContentType> contentType = new ArrayList<>();

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<BlogPost> blogPostList = new ArrayList<>();;


}
