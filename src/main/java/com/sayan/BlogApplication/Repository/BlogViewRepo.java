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
    @Query(value="select sum(view_count) from blog_viewer where blog_id = :id",nativeQuery = true)
    Long totalViews(@Param("id")String id);
}
