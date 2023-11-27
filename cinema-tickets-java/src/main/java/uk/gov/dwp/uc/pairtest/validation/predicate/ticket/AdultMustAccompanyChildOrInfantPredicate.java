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
public class AdultMustAccompanyChildOrInfantPredicate extends AbstractTicketTypeRequestWrapperPredicate {
    @Override
    public boolean test(TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        return validate(
                ticketTypeRequestWrapper, 
                "Children or Infants must be accompanied by an Adult"
        );
    }
    
    @Override
    protected boolean condition(TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        return !ticketTypeRequestWrapper.getTicketTypeRequestMap().isEmpty() && 
                ticketTypeRequestWrapper.getTicketTypeRequestMap().containsKey(TicketTypeRequest.Type.ADULT);
    }
    
}
