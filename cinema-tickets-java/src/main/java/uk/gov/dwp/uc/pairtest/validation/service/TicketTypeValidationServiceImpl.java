/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.gov.dwp.uc.pairtest.validation.service;

import java.util.function.Predicate;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;
import uk.gov.dwp.uc.pairtest.validation.ticket.AdultMustAccompanyChildOrInfantPredicate;
import uk.gov.dwp.uc.pairtest.validation.ticket.InfantsMustNotOutnumberAdultsPredicate;
import uk.gov.dwp.uc.pairtest.validation.ticket.NoTicketsSubmittedPredicate;
import uk.gov.dwp.uc.pairtest.validation.ticket.TicketsLessThanTwentyPredicate;

/**
 *
 * @author James Thompson
 */
public class TicketTypeValidationServiceImpl implements TicketTypeValidationService {
    
    private Predicate<TicketTypeRequestWrapper>[] checks = new Predicate[] {
        new NoTicketsSubmittedPredicate(),
        new TicketsLessThanTwentyPredicate(),
        new AdultMustAccompanyChildOrInfantPredicate(),
        new InfantsMustNotOutnumberAdultsPredicate()
    };

    @Override
    public boolean validate(TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        for(var check : checks) {
            check.test(ticketTypeRequestWrapper);
        }
        
        return true;
    }   
}
