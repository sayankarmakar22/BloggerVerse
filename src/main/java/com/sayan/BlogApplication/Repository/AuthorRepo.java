package com.sayan.BlogApplication.Repository;

import com.sayan.BlogApplication.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author,Integer> {
}
