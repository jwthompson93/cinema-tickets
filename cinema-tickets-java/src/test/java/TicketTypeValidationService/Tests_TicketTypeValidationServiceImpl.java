/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TicketTypeValidationService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
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
    @MethodSource({
        "TestCases.Predicates.NoTicketsSubmittedPredicate_TestCases#PassesSuccessfully_TestCases",
        "TestCases.Predicates.TicketsLessThanTwentyPredicate_TestCases#PassesSuccessfully_TestCases",
        "TestCases.Predicates.AdultMustAccompanyChildOrInfantPredicate_TestCases#PassesSuccessfully_TestCases", 
        "TestCases.Predicates.InfantsMustNotOutnumberAdultsPredicate_TestCases#PassesSuccessfully_TestCases"
    })
    void Tests_validate_PassesSuccessfully(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertDoesNotThrow( () -> {
            ticketTypeValidationService.validate(ticketTypeRequestWrapper);
        });
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.Predicates.NoTicketsSubmittedPredicate_TestCases#ThrowInvalidPurchaseException_TestCases")
    void Tests_validate_NoTicketsSubmitted_ThrowInvalidPurchaseException(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertEquals(            
            "No tickets submitted", 
            Assertions.assertThrows(InvalidPurchaseException.class, () -> {
                ticketTypeValidationService.validate(ticketTypeRequestWrapper);
            }).getMessage()
        );
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.Predicates.TicketsLessThanTwentyPredicate_TestCases#ThrowInvalidPurchaseException_TestCases")
    void Tests_validate_TicketsLessThanTwenty_ThrowInvalidPurchaseException(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertEquals(            
            "Cannot order more than 20 tickets", 
            Assertions.assertThrows(InvalidPurchaseException.class, () -> {
                ticketTypeValidationService.validate(ticketTypeRequestWrapper);
            }).getMessage()
        );
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.Predicates.AdultMustAccompanyChildOrInfantPredicate_TestCases#ThrowInvalidPurchaseException_TestCases")
    void Tests_validate_AdultMustAccompanyChildOrInfant_ThrowInvalidPurchaseException(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertEquals(
            "Children or Infants must be accompanied by an Adult", 
            Assertions.assertThrows(InvalidPurchaseException.class, () -> {
                ticketTypeValidationService.validate(ticketTypeRequestWrapper);
            }).getMessage()
        );
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.Predicates.InfantsMustNotOutnumberAdultsPredicate_TestCases#ThrowInvalidPurchaseException_TestCases")
    void Tests_TicketTypeValidationServiceImpl_InfantsMustNotOutnumberAdults_ThrowInvalidPurchaseException(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertEquals(
            "Infants must be accompanied by at least an equal amount of Adults", 
            Assertions.assertThrows(InvalidPurchaseException.class, () -> {
                ticketTypeValidationService.validate(ticketTypeRequestWrapper);
            }).getMessage()
        );
    }
}
