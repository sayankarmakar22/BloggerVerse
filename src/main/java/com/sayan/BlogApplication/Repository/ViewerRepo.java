package com.sayan.BlogApplication.Repository;

import com.sayan.BlogApplication.Model.Viewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewerRepo extends JpaRepository<Viewer, String> {
}
