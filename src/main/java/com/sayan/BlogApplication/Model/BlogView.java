package com.sayan.BlogApplication.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
@Entity
@Table(name = "BlogViewer")
public class BlogView {
    @Id
    @ManyToOne()
    @JoinColumn(name = "blogId")
    private BlogPost blogId;
    private int viewerId;
    private int authorId;
    private long viewCount;
}
