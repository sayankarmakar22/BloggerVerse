package com.sayan.BlogApplication.Repository;

import com.sayan.BlogApplication.Model.BlogPost;
import com.sayan.BlogApplication.Model.BlogView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogViewRepo extends JpaRepository<BlogView, String> {
}
