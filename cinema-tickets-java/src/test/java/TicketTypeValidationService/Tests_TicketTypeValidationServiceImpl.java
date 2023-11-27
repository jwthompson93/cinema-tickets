/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TicketTypeValidationService;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.validation.service.TicketTypeValidationService;
import uk.gov.dwp.uc.pairtest.validation.service.TicketTypeValidationServiceImpl;

/**
 *
 * @author James Thompson
 */
public class Tests_TicketTypeValidationServiceImpl {
    
    private final TicketTypeValidationService ticketTypeValidationService;
    
    public Tests_TicketTypeValidationServiceImpl() {
        this.ticketTypeValidationService = new TicketTypeValidationServiceImpl();
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("Tests_TicketTypeValidationServiceImpl_PassesSuccessfully_Arguments")
    void Tests_TicketTypeValidationServiceImpl_PassesSuccessfully(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertDoesNotThrow( () -> {
            ticketTypeValidationService.validate(ticketTypeRequestWrapper);
        });
    }
    
    static Stream<TicketTypeRequestWrapper> Tests_TicketTypeValidationServiceImpl_PassesSuccessfully_Arguments() {
        return Stream.of(
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 20)
            ),
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 10),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
            ),
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
            ),
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
    @MethodSource("Tests_TicketTypeValidationServiceImpl_TicketsLessThanTwenty_ThrowInvalidPurchaseException_Arguments")
    void Tests_TicketTypeValidationServiceImpl_TicketsLessThanTwenty_ThrowInvalidPurchaseException(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertEquals(
            Assertions.assertThrows(InvalidPurchaseException.class, () -> {
                ticketTypeValidationService.validate(ticketTypeRequestWrapper);
            }).getMessage(), 
            "Cannot order more than 20 tickets"
        );
    }
    
    static Stream<TicketTypeRequestWrapper> Tests_TicketTypeValidationServiceImpl_TicketsLessThanTwenty_ThrowInvalidPurchaseException_Arguments() {
        return Stream.of(
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 21)
            ),
            new TicketTypeRequestWrapper(
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 10),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 6)
            )
        );
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("Tests_TicketTypeValidationServiceImpl_AdultMustAccompanyChildOrInfant_ThrowInvalidPurchaseException_Arguments")
    void Tests_TicketTypeValidationServiceImpl_AdultMustAccompanyChildOrInfant_ThrowInvalidPurchaseException(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertEquals(
            Assertions.assertThrows(InvalidPurchaseException.class, () -> {
                ticketTypeValidationService.validate(ticketTypeRequestWrapper);
            }).getMessage(), 
            "Children or Infants must be accompanied by an Adult"
        );
    }
    
    static Stream<TicketTypeRequestWrapper> Tests_TicketTypeValidationServiceImpl_AdultMustAccompanyChildOrInfant_ThrowInvalidPurchaseException_Arguments() {
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
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("Tests_TicketTypeValidationServiceImpl_InfantsMustNotOutnumberAdults_ThrowInvalidPurchaseException_Arguments")
    void Tests_TicketTypeValidationServiceImpl_InfantsMustNotOutnumberAdults_ThrowInvalidPurchaseException(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertEquals(
            Assertions.assertThrows(InvalidPurchaseException.class, () -> {
                ticketTypeValidationService.validate(ticketTypeRequestWrapper);
            }).getMessage(), 
            "Infants must be accompanied by at least an equal amount of Adults"
        );
    }
    
    static Stream<TicketTypeRequestWrapper> Tests_TicketTypeValidationServiceImpl_InfantsMustNotOutnumberAdults_ThrowInvalidPurchaseException_Arguments() {
        return Stream.of(
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
