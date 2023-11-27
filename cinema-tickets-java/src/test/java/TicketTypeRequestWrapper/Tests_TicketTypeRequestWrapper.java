package TicketTypeRequestWrapper;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;

@DisplayName("A Test Class for testing methods in the TicketTypeRequestWrapper class")
public class Tests_TicketTypeRequestWrapper {
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.TicketTypeRequestWrapper_TestCases#Method_TestCases")
    void Test_GetTotalTicketPrice(
            TicketTypeRequestWrapper ticketTypeRequestWrapper, 
            Integer expectedCost, 
            Integer expectedSeats,
            Integer expectedTickets
    ) {
        Assertions.assertEquals(expectedCost, ticketTypeRequestWrapper.GetTotalTicketPrice());
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.TicketTypeRequestWrapper_TestCases#Method_TestCases")
    void Test_GetRequiredSeats(
            TicketTypeRequestWrapper ticketTypeRequestWrapper, 
            Integer expectedCost, 
            Integer expectedSeats,
            Integer expectedTickets
    ) {
        Assertions.assertEquals(expectedSeats, ticketTypeRequestWrapper.GetTotalRequiredSeats());
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TestCases.TicketTypeRequestWrapper_TestCases#Method_TestCases")
    void Test_GetTotalTickets(
            TicketTypeRequestWrapper ticketTypeRequestWrapper, 
            Integer expectedCost, 
            Integer expectedSeats,
            Integer expectedTickets
    ) {
        Assertions.assertEquals(expectedTickets, ticketTypeRequestWrapper.GetTotalTickets());
    }
}
