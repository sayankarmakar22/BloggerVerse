package com.sayan.BlogApplication.Repository;

import com.sayan.BlogApplication.Model.BlogPost;
import com.sayan.BlogApplication.Model.BlogView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogViewRepo extends JpaRepository<BlogView, BlogPost> {
}
