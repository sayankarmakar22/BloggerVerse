package com.sayan.BlogApplication.Repository;

import com.sayan.BlogApplication.Model.Author;
import com.sayan.BlogApplication.Model.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentTypeRepo extends JpaRepository<ContentType, Author> {
}
