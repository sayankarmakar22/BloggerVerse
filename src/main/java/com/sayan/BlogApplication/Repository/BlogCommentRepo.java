package com.sayan.BlogApplication.Repository;

import com.sayan.BlogApplication.Model.Author;
import com.sayan.BlogApplication.Model.BlogComment;
import com.sayan.BlogApplication.Model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogCommentRepo extends JpaRepository<BlogComment, BlogPost> {
}
