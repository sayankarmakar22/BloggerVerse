package com.sayan.BlogApplication.Repository;

import com.sayan.BlogApplication.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AuthorRepo extends JpaRepository<Author,String> {
    Author findByid(String id);
}
