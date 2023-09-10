package com.sayan.BlogApplication.Repository;

import com.sayan.BlogApplication.Model.BlogComment;
import com.sayan.BlogApplication.Model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogCommentRepo extends JpaRepository<BlogComment, String> {

    @Query(value="SELECT comments FROM blog_comments where blog_id = :id",nativeQuery = true)
    List<String> getAllBlogComments(@Param("id")String id);
}
