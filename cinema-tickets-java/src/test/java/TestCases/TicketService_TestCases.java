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
            ),
            Arguments.of(
                156L, 
                new TicketTypeRequest[] {
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1)
                }
            )
        );
    }
    
    public static Stream<Arguments> ThrowInvalidPurchaseException_TestCases() {
        return Stream.of(
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
            )
        );
    }
}
