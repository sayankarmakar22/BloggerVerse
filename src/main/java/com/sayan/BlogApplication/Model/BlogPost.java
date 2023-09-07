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
@Table(name = "BlogPost")
public class BlogPost {
    @Id
    private String blogId;
    @Column(length = 5000)
    private String blogContent;
    private String blogTitle;
    private Date blogDateTime;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;

    @OneToMany(mappedBy = "blogId",cascade = CascadeType.ALL)
    private List<BlogComment> blogComments  = new ArrayList<>();;

    @OneToMany(mappedBy = "blogId",cascade = CascadeType.ALL)
    private List<BlogView> blogViewCount = new ArrayList<>();;
}
