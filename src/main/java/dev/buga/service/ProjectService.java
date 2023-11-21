package dev.buga.service;

import dev.buga.data.ProjectRepository;
import dev.buga.entity.Project;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> readAll() {
        return projectRepository.findAll();
    }

    public Optional<Project> readById(long id) {
        return projectRepository.findById(id);
    }

    public void update(Project project) {
        projectRepository.save(project);
    }

    public void add(Project project) {
        projectRepository.save(project);
    }

    public void remove(long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.removeEmployeeAssociations();
            projectRepository.delete(project);
        }
    }
}
