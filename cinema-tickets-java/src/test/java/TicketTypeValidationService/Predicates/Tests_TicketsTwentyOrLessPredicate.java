package TicketTypeValidationService.Predicates;


import java.util.function.Predicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.validation.predicate.ticket.TicketsTwentyOrLessPredicate;

public class Tests_TicketsTwentyOrLessPredicate {
    
    private Predicate<TicketTypeRequestWrapper> predicate;
    
    public Tests_TicketsTwentyOrLessPredicate() {
        predicate = new TicketsTwentyOrLessPredicate();
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.Predicates.TicketsTwentyOrLessPredicate_TestCases#PassesSuccessfully_TestCases")
    void Tests_TicketsTwentyOrLessPredicate_PassesSuccessfully(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertTrue(
            predicate.test(ticketTypeRequestWrapper)
        );
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.Predicates.TicketsTwentyOrLessPredicate_TestCases#ThrowInvalidPurchaseException_TestCases")
    void Tests_TicketsTwentyOrLessPredicate_ThrowInvalidPurchaseException(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertThrows(InvalidPurchaseException.class, () -> {
            predicate.test(ticketTypeRequestWrapper);
        });
    }
}
