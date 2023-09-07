package com.sayan.BlogApplication.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
@Entity
@Table(name = "BlogComments")
public class BlogComment {
    @Id
    private String blogSerialNumber;
    private String viewerId;
    private String comments;
    private Date commentDateTime;

    @ManyToOne
    @JoinColumn(name = "blogId")
    private BlogPost blogId;


}
