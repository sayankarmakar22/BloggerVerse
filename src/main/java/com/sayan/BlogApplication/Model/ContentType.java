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
@Table(name = "ContentType")
public class ContentType {
    @Id
    @ManyToOne()
    @JoinColumn(name = "authorId")
    private Author author;
    private String contentType;
}
