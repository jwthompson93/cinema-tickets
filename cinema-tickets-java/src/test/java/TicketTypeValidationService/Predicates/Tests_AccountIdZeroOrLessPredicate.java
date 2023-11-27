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
import uk.gov.dwp.uc.pairtest.validation.predicate.ticket.AccountIdLessZeroOrLessPredicate;

/**
 *
 * @author James Thompson
 */
public class Tests_AccountIdZeroOrLessPredicate {

    private Predicate<TicketTypeRequestWrapper> predicate;

    public Tests_AccountIdZeroOrLessPredicate() {
        predicate = new AccountIdLessZeroOrLessPredicate();
    }

    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.Predicates.AccountIdLessZeroOrLessPredicate_TestCases#PassesSuccessfully_TestCases")
    void Tests_AccountIdLessZeroOrLessPredicate_PassesSuccessfully(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertTrue(
                predicate.test(ticketTypeRequestWrapper)
        );
    }

    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.Predicates.AccountIdLessZeroOrLessPredicate_TestCases#ThrowInvalidPurchaseException_TestCases")
    void Tests_AccountIdLessZeroOrLessPredicate_ThrowInvalidPurchaseException(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertThrows(InvalidPurchaseException.class, () -> {
            predicate.test(ticketTypeRequestWrapper);
        });
    }
}
