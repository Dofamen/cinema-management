package org.sid.cenima.controllers;

import javassist.NotFoundException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.sid.cenima.dao.FilmRepository;
import org.sid.cenima.dao.TicketRepository;
import org.sid.cenima.entities.Film;
import org.sid.cenima.entities.Ticket;
import org.sid.cenima.response.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class CinemaRestController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;


    /*produces we tell the Browser that your getting a media file type JPEG*/
    @GetMapping(path = "/imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") Long id) throws IOException, NotFoundException {
        Optional<Film> film = filmRepository.findById(id);
        if (!film.isPresent()) {
            throw new NotFoundException("id="+id.toString()+"not found");
        }
        String imageName = film.get().getImage();
        File file = new File(System.getProperty("user.home") + "/Desktop/Images/" + imageName);
        Path path = Paths.get(file.toURI());
        // Convert Image To Bytes
        return Files.readAllBytes(path);
    }

    @PostMapping(path = "/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm)throws Exception{
        List<Ticket> tickets = new ArrayList<>();
        System.out.println(ticketForm.toString());
        ticketForm.getTicket_id().forEach(ticketId ->{
                Ticket ticket = ticketRepository.findById(ticketId).get();
                if (ticket.isReserve()) throw new IllegalArgumentException();

                ticket.setReserve(true);
                ticket.setNomClient(ticketForm.getClientName());
                ticket.setCodePayment(ticketForm.getCodePayment());
                ticketRepository.save(ticket);
                tickets.add(ticket);
            });

        return tickets;
    }

    @Data @NoArgsConstructor @ToString
    static class TicketForm{
        private String clientName;
        private List<Long> ticket_id = new ArrayList<>();
        private Integer codePayment;
    }
}
