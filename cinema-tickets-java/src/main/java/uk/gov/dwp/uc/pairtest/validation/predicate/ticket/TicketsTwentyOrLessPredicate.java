/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.gov.dwp.uc.pairtest.validation.predicate.ticket;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;
import uk.gov.dwp.uc.pairtest.validation.AbstractTicketTypeRequestWrapperPredicate;

/**
 *
 * @author James Thompson
 */
public class TicketsTwentyOrLessPredicate extends AbstractTicketTypeRequestWrapperPredicate{

    @Override
    public boolean test(TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        return this.validate(
            ticketTypeRequestWrapper,
            "Cannot order more than 20 tickets"
        );
    }

    @Override
    protected boolean condition(TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        return ticketTypeRequestWrapper.GetTotalTickets() <= 20;
    }
}
