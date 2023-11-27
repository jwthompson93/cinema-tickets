/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestCases;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;

/**
 *
 * @author James Thompson
 */
public class TicketTypeRequestWrapper_TestCases {
    
    /*
    Arguments:
        TicketTypeRequestWrapper, 
        Expected Cost - Integer, 
        Expected Seats - Integer,
        Expected Tickets - Integer
    */
    
    public static Stream<Arguments> Method_TestCases() {
        return Stream.of(
            Arguments.of(
                new TicketTypeRequestWrapper(1L),
                0, 
                0, 
                0
            ),
            Arguments.of(
                new TicketTypeRequestWrapper(
                    1L,
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5)
                ),
                100, 
                5, 
                5
            ),
            Arguments.of(
                new TicketTypeRequestWrapper(
                    1L,
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5),
                    new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5)
                ),
                150, 
                10, 
                10
            ),
            Arguments.of(
                new TicketTypeRequestWrapper(
                    1L,
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5),
                    new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5),
                    new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
                ),
                150, 
                10, 
                15
            ),
            Arguments.of(
                new TicketTypeRequestWrapper(
                    1L,
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 15),
                    new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
                ),
                300, 
                15, 
                20
            )
        );
    }
}
