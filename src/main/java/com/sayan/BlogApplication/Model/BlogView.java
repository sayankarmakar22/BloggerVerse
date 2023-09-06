package com.sayan.BlogApplication.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
@Entity
@Table(name = "BlogViewer")
public class BlogView {
    @Id
    private int viewSerialId;
    private int viewerId;
    private int authorId;
    private long viewCount;

    @ManyToOne
    @JoinColumn(name = "blogId")
    private BlogPost blogId;
}
