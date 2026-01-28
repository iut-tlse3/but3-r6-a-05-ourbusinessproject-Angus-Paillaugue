package ourbusinessproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BootstrapTest {

    private Bootstrap bootstrap;

    @Mock
    private InitializationService initializationService;


    @BeforeEach
    public void setUp() {
        bootstrap = new Bootstrap(initializationService);
    }

    @Test
    public void testInitMethodInvokeInitializationService() {

        // given a bootstrap and its initialization service
        assertNotNull(bootstrap);

        // when the init method is triggered
        bootstrap.init();

        // then the initialization of projects is triggered on the initialization service
        verify(initializationService).initProjects();
    }

    @Test
    public void testIniBootstrapMethodCatchRuntimeExceptionComingFromInitProjects() {
        // given a bootstrap and its initialization service throwing an exception
        willThrow(RuntimeException.class).given(initializationService).initProjects();

        // when the init method is triggered
        bootstrap.init();

        // then no exception is thrown

    }

}