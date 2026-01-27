package ourbusinessproject;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
    private Collection<Project> roles;

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
}
