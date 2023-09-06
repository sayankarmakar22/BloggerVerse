package com.sayan.BlogApplication.Repository;

import com.sayan.BlogApplication.Model.Author;
import com.sayan.BlogApplication.Model.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentTypeRepo extends JpaRepository<ContentType, Integer> {
    void deleteByauthorId(String id);
    ContentType findByauthorId(String id);
}
