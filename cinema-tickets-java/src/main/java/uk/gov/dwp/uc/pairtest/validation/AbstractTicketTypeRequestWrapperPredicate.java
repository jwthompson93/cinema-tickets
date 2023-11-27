/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.gov.dwp.uc.pairtest.validation;

import java.util.function.Predicate;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

/**
 *
 * @author James Thompson
 */
public abstract class AbstractTicketTypeRequestWrapperPredicate implements Predicate<TicketTypeRequestWrapper> {
    
    protected boolean validate(
        TicketTypeRequestWrapper ticketTypeRequestWrapper, String errorMessage
    ) {
        if(condition(ticketTypeRequestWrapper)) {
            return true;
        }
            
        throw new InvalidPurchaseException(errorMessage);
    }
    
    protected abstract boolean condition(TicketTypeRequestWrapper ticketTypeRequestWrapper);
}
