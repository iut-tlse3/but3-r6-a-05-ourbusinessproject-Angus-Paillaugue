package ourbusinessproject;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class Bootstrap {

    @Autowired
    private InitializationService initService;

    public Bootstrap(InitializationService initService) {
        this.initService = initService;
    }

    @PostConstruct
    public void init() {
        try {
            this.initService.initProjects();
            this.initService.initPartnerships();
        } catch (RuntimeException e) {
            Logger.getLogger("bootstrap").warning("An error has been thrown when initializing bootstrap :\n"+ Arrays.toString(e.getStackTrace()));
        }
    }

    public InitializationService getInitializationService() {
        return this.initService;
    }
}
