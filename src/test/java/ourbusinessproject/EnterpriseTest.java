package ourbusinessproject;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnterpriseTest {
    private Validator validator;
    private Enterprise enterprise;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        // given: an enterprise with all properties correctly set
        enterprise = new Enterprise();
        enterprise.setName("Company & Co");
        enterprise.setDescription("Comp description");
        enterprise.setContactName("Paul Durand");
        enterprise.setContactEmail("paul@compco.com");
    }

    @Test
    @DisplayName("Test the validation of a valid enterprise")
    public void testValidEnterpriseValidation() {
        // given a valid enterprise

        // the given enterprise is valid
        assertTrue(validator.validate(enterprise).isEmpty(), "Expected no constraint violation");
        // and has no projects associated
        assertNull(enterprise.getProjects());
    }

    @Test
    @DisplayName("Test the name of an enterprise cannot be empty or null")
    public void testEnterpriseNameValidation() {

        // when: the name is empty
        enterprise.setName("");

        // then: enterprise is no more valid
        assertFalse(validator.validate(enterprise).isEmpty(), "Expected constraint violation");

        // when: the name is null
        enterprise.setName(null);

        // then: enterprise is no more valid
        assertFalse(validator.validate(enterprise).isEmpty(), "Expected constraint violation");

    }

    @Test
    @DisplayName("Test the description of an enterprise cannot be too short")
    public void testEnterpriseDescriptionValidation() {

        // when the description is too short
        enterprise.setDescription("too short");

        // then: enterprise is no more valid
        assertFalse(validator.validate(enterprise).isEmpty(),"Expected constraint violation");

    }

    @Test
    @DisplayName("Test the contact name of an enterprise cannot be empty or null")
    public void testEnterpriseContactNameValidation() {

        // when the contact name is empty
        enterprise.setContactName("");

        // then: enterprise is no more valid
        assertFalse(validator.validate(enterprise).isEmpty(),"Expected constraint violation");

        // when the contact name is null
        enterprise.setContactName(null);

        // then: enterprise is no more valid
        assertFalse(validator.validate(enterprise).isEmpty(),"Expected constraint violation");

    }

    @Test
    @DisplayName("Test the contact email of an enterprise cannot be empty, null or invalid")
    public void testEnterpriseContactEmailValidation() {

        // when the contact email is empty
        enterprise.setContactEmail("");

        // then: enterprise is no more valid
        assertFalse(validator.validate(enterprise).isEmpty(),"Expected constraint violation");

        // when the contact email is null
        enterprise.setContactEmail(null);

        // then: enterprise is no more valid
        assertFalse(validator.validate(enterprise).isEmpty(),"Expected constraint violation");

        // when the contact email is not an email
        enterprise.setContactEmail("not an email");

        // then: enterprise is no more valid
        assertFalse(validator.validate(enterprise).isEmpty(), "Expected constraint violation");

        // tip : use @Email annotation
    }

}