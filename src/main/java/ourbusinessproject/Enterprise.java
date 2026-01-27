package ourbusinessproject;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=1)
    private String name;

    @NotNull
    @Size(min=10)
    private String description;

    @NotNull
    @Size(min=1)
    private String contactName;

    @NotNull
    @Email
    private String email;

    @OneToMany(mappedBy = "enterprise")
    private Collection<Project> projects;

    public Enterprise(){}

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getContactName() {
        return this.contactName;
    }

    public String getContactEmail() {
        return this.email;
    }

    public Collection<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        if(this.projects == null) {
            this.projects = new ArrayList<>();
        }
        this.projects.add(project);
    }
}
