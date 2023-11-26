/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.gov.dwp.uc.pairtest.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author James Thompson
 */
public class TicketTypeRequestWrapper {
    private Map<TicketTypeRequest.Type, TicketTypeRequest> ticketTypeRequestMap;
    
    public TicketTypeRequestWrapper(TicketTypeRequest... ticketTypeRequests) {
        ticketTypeRequestMap = 
                Arrays
                .stream(ticketTypeRequests)
                .collect(Collectors.toMap(TicketTypeRequest::getTicketType, Function.identity()));
    }
    
    public Map<TicketTypeRequest.Type, TicketTypeRequest> getTicketTypeRequestMap() {
        return ticketTypeRequestMap;
    }
    
    public int GetTotalTickets() {
        return ticketTypeRequestMap.entrySet()
                .stream()
                .mapToInt((request) -> 
                        request.getValue().getNoOfTickets()
                )
                .sum();
    }
    
    public int GetTotalTicketPrice() {
        return ticketTypeRequestMap.entrySet()
                .stream()
                .mapToInt((request) -> 
                        request.getKey().cost * request.getValue().getNoOfTickets()
                )
                .sum();
    }
    
    public int GetTotalRequiredSeats() {
        return ticketTypeRequestMap.entrySet()
                .stream()
                .filter((request) -> 
                        request.getKey() != TicketTypeRequest.Type.INFANT
                )
                .mapToInt((request) -> 
                        request.getValue().getNoOfTickets()
                )
                .sum();
    }
}
