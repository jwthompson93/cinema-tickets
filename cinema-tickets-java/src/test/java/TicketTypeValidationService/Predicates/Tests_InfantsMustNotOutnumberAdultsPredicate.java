package TicketTypeValidationService.Predicates;


import java.util.function.Predicate;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.validation.ticket.InfantsMustNotOutnumberAdultsPredicate;

public class Tests_InfantsMustNotOutnumberAdultsPredicate {
    
    private Predicate<TicketTypeRequestWrapper> predicate;
    
    public Tests_InfantsMustNotOutnumberAdultsPredicate() {
        predicate = new InfantsMustNotOutnumberAdultsPredicate();
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("Tests_InfantsMustNotOutnumberAdultsPredicate_PassesSuccessfully_Arguments")
    void Tests_InfantsMustNotOutnumberAdultsPredicate_PassesSuccessfully(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertTrue(
            predicate.test(ticketTypeRequestWrapper)
        );
    }
    
    static Stream<TicketTypeRequestWrapper> Tests_InfantsMustNotOutnumberAdultsPredicate_PassesSuccessfully_Arguments() {
        return Stream.of(
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
            ),
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 4)
            ),
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 10),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 10)
            ),
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 10),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
            )
        );
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("Tests_InfantsMustNotOutnumberAdultsPredicate_ThrowInvalidPurchaseException_Arguments")
    void Tests_InfantsMustNotOutnumberAdultsPredicate_ThrowInvalidPurchaseException(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertThrows(InvalidPurchaseException.class, () -> {
            predicate.test(ticketTypeRequestWrapper);
        });
    }
    
    static Stream<TicketTypeRequestWrapper> Tests_InfantsMustNotOutnumberAdultsPredicate_ThrowInvalidPurchaseException_Arguments() {
        return Stream.of(
            new TicketTypeRequestWrapper(),
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 6)
            ),
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 9),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 10)
            ),
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 4),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 10),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
            )
        );
    }
}
