package TicketTypeValidationService.Predicates;


import java.util.function.Predicate;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.validation.ticket.NoTicketsSubmittedPredicate;

public class Tests_NoTicketsSubmittedPredicate {
    
    private Predicate<TicketTypeRequestWrapper> predicate;
    
    public Tests_NoTicketsSubmittedPredicate() {
        predicate = new NoTicketsSubmittedPredicate();
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.Predicates.NoTicketsSubmittedPredicate_TestCases#PassesSuccessfully_TestCases")
    void Tests_NoTicketsSubmittedPredicate_PassesSuccessfully(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertTrue(
                predicate.test(ticketTypeRequestWrapper)
        );
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.Predicates.NoTicketsSubmittedPredicate_TestCases#ThrowInvalidPurchaseException_TestCases")
    void Tests_NoTicketsSubmittedPredicate_ThrowInvalidPurchaseException(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertThrows(InvalidPurchaseException.class, () -> {
            predicate.test(ticketTypeRequestWrapper);
        });
    }
}
