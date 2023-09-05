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
@Table(name = "BlogComments")
public class BlogComment {
    @Id
    @ManyToOne()
    @JoinColumn(name = "blogId")
    private BlogPost blogId;
    private int viewerId;
    private String comments;
    private Date commentDateTime;

}
