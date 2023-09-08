package com.sayan.BlogApplication.Repository;

import com.sayan.BlogApplication.Model.BlogPost;
import com.sayan.BlogApplication.Model.BlogView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories

public interface BlogViewRepo extends JpaRepository<BlogView, String> {
    @Query(value="SELECT SUM(b.view_count) FROM blog_view b WHERE b.blog_id = :blog_id")
    long totalViews(@Param("blog_id")String blog_Id);
}
