package com.sayan.BlogApplication.Repository;

import com.sayan.BlogApplication.Model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepo extends JpaRepository<BlogPost,String> {
}
