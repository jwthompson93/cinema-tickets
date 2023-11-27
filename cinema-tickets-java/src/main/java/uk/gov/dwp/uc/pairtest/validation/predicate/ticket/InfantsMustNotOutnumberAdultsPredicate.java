/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.gov.dwp.uc.pairtest.validation.predicate.ticket;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;
import uk.gov.dwp.uc.pairtest.validation.AbstractTicketTypeRequestWrapperPredicate;

/**
 *
 * @author James Thompson
 */
public class InfantsMustNotOutnumberAdultsPredicate extends AbstractTicketTypeRequestWrapperPredicate {
    @Override
    public boolean test(TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        return validate(
                ticketTypeRequestWrapper, 
                "Infants must be accompanied by at least an equal amount of Adults"
        );
    }
    
    @Override
    protected boolean condition(TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        
        if(ticketTypeRequestWrapper.getTicketTypeRequestMap().containsKey(TicketTypeRequest.Type.ADULT) && 
                ticketTypeRequestWrapper.getTicketTypeRequestMap().containsKey(TicketTypeRequest.Type.INFANT)) {
            if(ticketTypeRequestWrapper.getTicketTypeRequestMap().get(TicketTypeRequest.Type.ADULT).getNoOfTickets() >= 
               ticketTypeRequestWrapper.getTicketTypeRequestMap().get(TicketTypeRequest.Type.INFANT).getNoOfTickets())
            {
                return true;
            }
            
            return false;
        }
        
        return true;
    }
    
}
