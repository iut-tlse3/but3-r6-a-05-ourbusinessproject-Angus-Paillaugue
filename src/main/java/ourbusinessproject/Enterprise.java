package ourbusinessproject;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Enterprise {

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
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

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
}
