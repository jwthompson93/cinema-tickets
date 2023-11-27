/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.gov.dwp.uc.pairtest.validation.service;

import java.util.function.Predicate;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;
import uk.gov.dwp.uc.pairtest.validation.predicate.ticket.*;

/**
 *
 * @author James Thompson
 */
public class TicketValidationServiceImpl implements TicketValidationService {
    
    private Predicate<TicketTypeRequestWrapper>[] checks = new Predicate[] {
        new AccountIdMoreThanZeroPredicate(),
        new AtLeastOneTicketSubmittedPredicate(),
        new TicketsTwentyOrLessPredicate(),
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
