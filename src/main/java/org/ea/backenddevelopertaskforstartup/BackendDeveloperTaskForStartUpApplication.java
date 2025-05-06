package org.ea.backenddevelopertaskforstartup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BackendDeveloperTaskForStartUpApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendDeveloperTaskForStartUpApplication.class, args);
    }

}
