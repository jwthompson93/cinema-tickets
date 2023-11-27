/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestCases;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

/**
 *
 * @author James Thompson
 */
public class TicketService_TestCases {
    public static Stream<Arguments> PassesSuccessfully_TestCases() {
        return Stream.of(
            Arguments.of(
                1L, 
                new TicketTypeRequest[] {
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1)
                }
            )
        );
    }
    
    public static Stream<Arguments> ThrowInvalidPurchaseException_TestCases() {
        return Stream.of(
            // Invalid Id
            Arguments.of(
                0L, 
                new TicketTypeRequest[] {
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1)
                }
            ),
            Arguments.of(
                -1L, 
                new TicketTypeRequest[] {
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1)
                }
            ),
            // No tickets submitted
            Arguments.of(
                1L, 
                new TicketTypeRequest[0]
            ),
            // Tickets more than 20 total
            Arguments.of(
                1L,
                new TicketTypeRequest[] {
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 21)
                }
            ),
            Arguments.of(
                1L,
                new TicketTypeRequest[] {
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 10),
                    new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5),
                    new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 6)
                }
            ),
            // Adults must accompany Children or Infants
            Arguments.of(
                1L,
                new TicketTypeRequest[] {
                    new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5)
                }
            ),
            Arguments.of(
                1L,
                    
                new TicketTypeRequest[] {
                    new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
                }
            ),
            Arguments.of(
                1L,
                new TicketTypeRequest[] {
                    new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 10),
                    new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
                }
            ),
            // Adults must outnumber Infants
            Arguments.of(
                1L,
                new TicketTypeRequest[] {
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5),
                    new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 6)
                }
            ),
            Arguments.of(
                1L,
                new TicketTypeRequest[] {
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 9),
                    new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 10)
                }
            ),
            Arguments.of(
                1L,
                new TicketTypeRequest[] {
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 4),
                    new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 10),
                    new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
                }
            )
        );
    }
}
