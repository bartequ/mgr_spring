package pl.edu.agh.kt.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RepositoryUtils {

    private static RepositoryUtils instance;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void fillInstance() {
        instance = this;
    }

    public static UserRepository getUserRepository() {
        return instance.userRepository;
    }
}