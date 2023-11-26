package TicketTypeRequestWrapper;


import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;

@DisplayName("A Test Class for testing methods in the TicketTypeRequestWrapper class")
public class Tests_TicketTypeRequestWrapper {
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TicketTypeRequestWrapperArguments")
    void Test_GetTotalTicketPrice(
            TicketTypeRequestWrapper ticketTypeRequestWrapper, 
            Integer expectedCost, 
            Integer expectedSeats,
            Integer expectedTickets
    ) {
        Assertions.assertEquals(expectedCost, ticketTypeRequestWrapper.GetTotalTicketPrice());
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TicketTypeRequestWrapperArguments")
    void Test_GetRequiredSeats(
            TicketTypeRequestWrapper ticketTypeRequestWrapper, 
            Integer expectedCost, 
            Integer expectedSeats,
            Integer expectedTickets
    ) {
        Assertions.assertEquals(expectedSeats, ticketTypeRequestWrapper.GetTotalRequiredSeats());
    }
    
    @ParameterizedTest(name = "Expected: {1}, Actual: {2}")
    @MethodSource("TicketTypeRequestWrapperArguments")
    void Test_GetTotalTickets(
            TicketTypeRequestWrapper ticketTypeRequestWrapper, 
            Integer expectedCost, 
            Integer expectedSeats,
            Integer expectedTickets
    ) {
        Assertions.assertEquals(expectedTickets, ticketTypeRequestWrapper.GetTotalTickets());
    }
    
    static Stream<Arguments> TicketTypeRequestWrapperArguments() {
        return Stream.of(
            Arguments.of(
                new TicketTypeRequestWrapper(),
                0, 0, 0),
            Arguments.of(
                new TicketTypeRequestWrapper(
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5)
                ),
                100, 5, 5),
            Arguments.of(
                new TicketTypeRequestWrapper(
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5),
                    new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5)
                ),
                150, 10, 10),
            Arguments.of(
                new TicketTypeRequestWrapper(
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5),
                    new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5),
                    new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
                ),
                150, 10, 15),
            Arguments.of(
                new TicketTypeRequestWrapper(
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 15),
                    new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
                ),
                300, 15, 20)
        );
    }
}
