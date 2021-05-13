package org.sid.cenima;

import org.sid.cenima.entities.Film;
import org.sid.cenima.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;



/*
*
* To build the project for production use this command to build the jar
* cmd: mvnw clean package
* and tp run it on the server use, you can find the build jar on /target folder
* cmd : java -jar <theNameOfApplication.jar>
* */

@SpringBootApplication
public class CenimaApplication implements CommandLineRunner {

    @Autowired
    private ICinemaInitService cinemaInitService;

    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(CenimaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Film.class);
        cinemaInitService.initVilles();
        cinemaInitService.initCinemas();
        cinemaInitService.initSalles();
        cinemaInitService.initPlaces();
        cinemaInitService.initSeances();
        cinemaInitService.initCategories();
        cinemaInitService.initFilms();
        cinemaInitService.initProjections();
        cinemaInitService.initTickets();
    }
}
