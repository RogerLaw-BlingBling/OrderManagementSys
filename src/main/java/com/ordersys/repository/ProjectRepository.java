package com.ordersys.repository;

import com.ordersys.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
}

