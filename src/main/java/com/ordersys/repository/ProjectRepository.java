package com.ordersys.repository;

import com.ordersys.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    Page<Project> findAllByProjectStatus(Project.Status inProgress, Pageable pageable);
    Page<Project> findByProjectNameLike(String keyword, Pageable pageable);
}

