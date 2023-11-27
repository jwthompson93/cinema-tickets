/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TicketValidationService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.validation.service.TicketValidationServiceImpl;
import uk.gov.dwp.uc.pairtest.validation.service.TicketValidationService;

/**
 *
 * @author James Thompson
 */
public class Tests_TicketValidationServiceImpl {
    
    private final TicketValidationService ticketTypeValidationService;
    
    public Tests_TicketValidationServiceImpl() {
        this.ticketTypeValidationService = new TicketValidationServiceImpl();
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource({
        "TestCases.Predicates.AccountIdHigherThanZeroPredicate_TestCases#PassesSuccessfully_TestCases",
        "TestCases.Predicates.AtLeastOneTicketSubmittedPredicate_TestCases#PassesSuccessfully_TestCases",
        "TestCases.Predicates.TicketsTwentyOrLessPredicate_TestCases#PassesSuccessfully_TestCases",
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
    @MethodSource("TestCases.Predicates.AccountIdHigherThanZeroPredicate_TestCases#ThrowInvalidPurchaseException_TestCases")
    void Tests_validate_AccountIdHigherThanZeroPredicate_ThrowInvalidPurchaseException(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertEquals(            
            "Account ID not valid", 
            Assertions.assertThrows(InvalidPurchaseException.class, () -> {
                ticketTypeValidationService.validate(ticketTypeRequestWrapper);
            }).getMessage()
        );
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.Predicates.AtLeastOneTicketSubmittedPredicate_TestCases#ThrowInvalidPurchaseException_TestCases")
    void Tests_validate_AtLeastOneTicketSubmitted_ThrowInvalidPurchaseException(
            TicketTypeRequestWrapper ticketTypeRequestWrapper) {
        Assertions.assertEquals(            
            "No tickets submitted", 
            Assertions.assertThrows(InvalidPurchaseException.class, () -> {
                ticketTypeValidationService.validate(ticketTypeRequestWrapper);
            }).getMessage()
        );
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.Predicates.TicketsTwentyOrLessPredicate_TestCases#ThrowInvalidPurchaseException_TestCases")
    void Tests_validate_TicketsTwentyOrLess_ThrowInvalidPurchaseException(
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
