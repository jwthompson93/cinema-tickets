/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.gov.dwp.uc.pairtest.domain;

import java.util.Arrays;

/**
 *
 * @author James Thompson
 */
public class TicketTypeRequestWrapper {
    private TicketTypeRequest[] ticketTypeRequests;
    
    public TicketTypeRequestWrapper(TicketTypeRequest... ticketTypeRequests) {
        this.ticketTypeRequests = ticketTypeRequests;
    }
    
    public int GetTotalTickets() {
        return Arrays
                .stream(ticketTypeRequests)
                .mapToInt((value) -> value.getNoOfTickets())
                .sum();
    }
    
    public int GetTotalTicketPrice() {
        return Arrays
                .stream(ticketTypeRequests)
                .mapToInt((value) -> 
                        value.getTicketType().cost * value.getNoOfTickets())
                .sum();
    }
    
    public int GetTotalRequiredSeats() {
        return Arrays
                .stream(ticketTypeRequests)
                .filter((request) -> 
                        request.getTicketType() != TicketTypeRequest.Type.INFANT)
                .mapToInt((request) -> request.getNoOfTickets())
                .sum();
    }
}
