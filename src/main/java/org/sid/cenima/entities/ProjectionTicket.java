package org.sid.cenima.entities;

import org.sid.cenima.entities.Place;
import org.sid.cenima.entities.Ticket;
import org.springframework.data.rest.core.config.Projection;


@Projection(name = "ticketProj", types = Ticket.class)
public interface ProjectionTicket {
    public Long getId();
    public String getNomClient();
    public double getPrix();
    public Integer getCodePayment();
    public boolean isReserve();
    public Place getPlace();

}
