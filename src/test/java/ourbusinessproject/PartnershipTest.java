package ourbusinessproject;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ourbusinessproject.domain.Enterprise;
import ourbusinessproject.domain.Partnership;
import ourbusinessproject.domain.Project;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class PartnershipTest {

    private Validator validator;

    @Mock
    private Project project;

    @Mock
    private Enterprise enterprise;

    private Partnership partnership;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        partnership = new Partnership();
        partnership.setCreationDate(new Date());
        partnership.setEnterprise(enterprise);
        partnership.setProject(project);
    }

    @Test
    public void testValidPartnershipValidation() {

        // given a valid partnership
        // expect validating the partnership instance returns no constraints violation
        assertTrue(validator.validate(partnership).isEmpty());

    }

    @Test
    public void testPartnerShipWithNoDateSetIsInvalid() {
        // given a partnership without creation date set
        partnership.setCreationDate(null);

        // expect validating the partnership instance returns  constraint violation
        assertFalse(validator.validate(partnership).isEmpty());
    }

    @Test
    public void testPartnerShipWithNoEnterpriseIsInvalid() {
        // given a partnership without enterprise
        partnership.setEnterprise(null);

        // expect validating the partnership instance returns  constraint violation
        assertFalse(validator.validate(partnership).isEmpty());
    }

    @Test
    public void testPartnerShipWithNoProjectIsInvalid() {
        // given a partnership without project
        partnership.setProject(null);

        // expect validating the partnership instance returns  constraint violation
        assertFalse(validator.validate(partnership).isEmpty());
    }

}