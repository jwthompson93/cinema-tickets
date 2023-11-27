/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TicketService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationServiceImpl;
import uk.gov.dwp.uc.pairtest.TicketService;
import uk.gov.dwp.uc.pairtest.TicketServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.validation.service.TicketTypeValidationServiceImpl;

/**
 *
 * @author James Thompson
 */
public class Tests_TicketService {
    
    private TicketService ticketService;
    
    public Tests_TicketService() {
        ticketService = new TicketServiceImpl(
            new TicketTypeValidationServiceImpl(), 
            new TicketPaymentServiceImpl(), 
            new SeatReservationServiceImpl()
        );
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.TicketService_TestCases#PassesSuccessfully_TestCases")
    void Test_PurchaseTickets_PassesSuccessfully(Long accountId, TicketTypeRequest... ticketTypeRequests) {
        Assertions.assertDoesNotThrow(() -> {
            ticketService.purchaseTickets(accountId, ticketTypeRequests);
        });
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.TicketService_TestCases#ThrowInvalidPurchaseException_TestCases")
    void Test_PurchaseTickets_ThrowsInvalidPurchaseException(Long accountId, TicketTypeRequest... ticketTypeRequests) {
        Assertions.assertThrows(InvalidPurchaseException.class, () -> {
            ticketService.purchaseTickets(accountId, ticketTypeRequests);
        });
    }
}
