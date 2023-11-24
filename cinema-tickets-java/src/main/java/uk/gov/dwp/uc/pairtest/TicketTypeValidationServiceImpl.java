/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.gov.dwp.uc.pairtest;

import java.util.function.Predicate;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;

/**
 *
 * @author James Thompson
 */
public class TicketTypeValidationServiceImpl implements TicketTypeValidationService {
    
    Predicate<TicketTypeRequestWrapper> TicketsLessThanTwenty = wrapper -> (wrapper.GetTotalTickets() <= 20);
    Predicate<TicketTypeRequestWrapper> AdultMustAccompanyChildOrInfant = wrapper -> (
            !wrapper.getTicketTypeRequestMap().isEmpty() && 
            wrapper.getTicketTypeRequestMap().containsKey(TicketTypeRequest.Type.ADULT));
    Predicate<TicketTypeRequestWrapper> InfantsMustNotOutnumberAdults = wrapper -> (
            !wrapper.getTicketTypeRequestMap(). && 
            wrapper.getTicketTypeRequestMap().containsKey(TicketTypeRequest.Type.ADULT));

    @Override
    public boolean validate(TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        
    }
    
}
