package ourbusinessproject.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Partnership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Date creationDate;

    @NotNull
    @ManyToOne
    private Enterprise enterprise;

    @NotNull
    @OneToOne
    private Project project;

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Enterprise getEnterprise() {
        return this.enterprise;
    }

    public Project getProject() {
        return this.project;
    }

    public Long getId() {
        return id;
    }
}
