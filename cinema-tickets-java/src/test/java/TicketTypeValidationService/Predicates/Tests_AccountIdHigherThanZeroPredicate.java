/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TicketTypeValidationService.Predicates;

import java.util.function.Predicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.validation.predicate.ticket.AccountIdMoreThanZeroPredicate;

/**
 *
 * @author James Thompson
 */
public class Tests_AccountIdHigherThanZeroPredicate {

    private Predicate<TicketTypeRequestWrapper> predicate;

    public Tests_AccountIdHigherThanZeroPredicate() {
        predicate = new AccountIdMoreThanZeroPredicate();
    }

    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.Predicates.AccountIdHigherThanZeroPredicate_TestCases#PassesSuccessfully_TestCases")
    void Tests_AccountIdHigherThanZeroPredicate_PassesSuccessfully(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertTrue(
                predicate.test(ticketTypeRequestWrapper)
        );
    }

    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.Predicates.AccountIdHigherThanZeroPredicate_TestCases#ThrowInvalidPurchaseException_TestCases")
    void Tests_AccountIdHigherThanZeroPredicate_ThrowInvalidPurchaseException(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertThrows(InvalidPurchaseException.class, () -> {
            predicate.test(ticketTypeRequestWrapper);
        });
    }
}
