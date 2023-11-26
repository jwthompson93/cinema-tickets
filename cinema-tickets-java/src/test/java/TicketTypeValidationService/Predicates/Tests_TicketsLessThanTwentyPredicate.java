package TicketTypeValidationService.Predicates;


import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.validation.ticket.AdultMustAccompanyChildOrInfantPredicate;

public class Tests_TicketsLessThanTwentyPredicate {
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("Tests_TicketsLessThanTwentyPredicate_PassesSuccessfully_Arguments")
    void Tests_TicketsLessThanTwentyPredicate_PassesSuccessfully(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertTrue(
                new AdultMustAccompanyChildOrInfantPredicate().test(ticketTypeRequestWrapper)
        );
    }
    
    static Stream<TicketTypeRequestWrapper> Tests_TicketsLessThanTwentyPredicate_PassesSuccessfully_Arguments() {
        return Stream.of(
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5)
            ),
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5)
            ),
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
            ),
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 15),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
            )
        );
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("Tests_TicketsLessThanTwentyPredicate_ThrowInvalidPurchaseException_Arguments")
    void Tests_TicketsLessThanTwentyPredicate_ThrowInvalidPurchaseException(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertThrows(InvalidPurchaseException.class, () -> {
            new AdultMustAccompanyChildOrInfantPredicate().test(ticketTypeRequestWrapper);
        });
    }
    
    static Stream<TicketTypeRequestWrapper> Tests_TicketsLessThanTwentyPredicate_ThrowInvalidPurchaseException_Arguments() {
        return Stream.of(
            new TicketTypeRequestWrapper(),
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5)
            ),
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
            ),
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 10),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
            )
        );
    }
}
