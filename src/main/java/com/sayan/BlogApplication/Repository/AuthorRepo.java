package com.sayan.BlogApplication.Repository;

import com.sayan.BlogApplication.Model.Author;
import com.sayan.BlogApplication.Model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AuthorRepo extends JpaRepository<Author,String> {
    Author findByid(String id);

    Author findByusername(String username);
    @Query(value="select * from blog_post where author_id = :id",nativeQuery = true)
    List<Map<String,Object>> getAllBlog(@Param("id") String id);
    @Query(value="select sum(view_count) from blog_viewer where blog_id = :id",nativeQuery = true)
    Long totalViews(@Param("id")String id);

    @Query(value="SELECT comments FROM blog_comments where blog_id = :id",nativeQuery = true)
    List<String> getAllBlogComments(@Param("id")String id);
}
