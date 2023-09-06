package com.sayan.BlogApplication.Repository;

import com.sayan.BlogApplication.Model.BlogComment;
import com.sayan.BlogApplication.Model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogCommentRepo extends JpaRepository<BlogComment, Integer> {
}
