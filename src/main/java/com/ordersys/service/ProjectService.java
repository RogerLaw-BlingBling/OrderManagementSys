package com.ordersys.service;

import com.ordersys.model.Project;
import com.ordersys.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Page<Project> findAll(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public Optional<Project> findOne(Integer id) {
        return projectRepository.findById(id);
    }

    public Page<Project> findAllByProgress(Project.Status inProgress, Pageable pageable) {
        return projectRepository.findAllByProjectStatus(inProgress, pageable);
    }

    public Page<Project> queryByName(String keyword, Pageable pageable) {
        return projectRepository.findByProjectNameLike("%" + keyword + "%", pageable);
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }
}
