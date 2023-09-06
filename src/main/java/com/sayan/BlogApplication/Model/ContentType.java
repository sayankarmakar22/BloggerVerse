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
@Table(name = "ContentType")
public class ContentType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String contentType;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;
}
