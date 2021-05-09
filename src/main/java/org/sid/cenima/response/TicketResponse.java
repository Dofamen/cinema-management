package org.sid.cenima.response;

import lombok.Data;

@Data
public class TicketResponse {
    private Long id;
    private double prix;
    private Integer codePayement;
    private String salle;
    private String Film;
    private Long place;
    private String nomClient;
}
