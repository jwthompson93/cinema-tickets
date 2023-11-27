package TicketTypeValidationService.Predicates;


import java.util.function.Predicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.validation.predicate.ticket.AtLeastOneTicketSubmittedPredicate;

public class Tests_AtLeastOneTicketSubmittedPredicate {
    
    private Predicate<TicketTypeRequestWrapper> predicate;
    
    public Tests_AtLeastOneTicketSubmittedPredicate() {
        predicate = new AtLeastOneTicketSubmittedPredicate();
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.Predicates.AtLeastOneTicketSubmittedPredicate_TestCases#PassesSuccessfully_TestCases")
    void Tests_AtLeastOneTicketSubmittedPredicate_PassesSuccessfully(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertTrue(
                predicate.test(ticketTypeRequestWrapper)
        );
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.Predicates.AtLeastOneTicketSubmittedPredicate_TestCases#ThrowInvalidPurchaseException_TestCases")
    void Tests_AtLeastOneTicketSubmittedPredicate_ThrowInvalidPurchaseException(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertThrows(InvalidPurchaseException.class, () -> {
            predicate.test(ticketTypeRequestWrapper);
        });
    }
}
