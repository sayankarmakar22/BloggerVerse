package com.sayan.BlogApplication.Repository;

import com.sayan.BlogApplication.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface AuthorRepo extends JpaRepository<Author,String> {
    Author findByid(String id);
    @Query(value="select sum(view_count) from blog_viewer where blog_id = :id",nativeQuery = true)
    Long totalViews(@Param("id")String id);
}
