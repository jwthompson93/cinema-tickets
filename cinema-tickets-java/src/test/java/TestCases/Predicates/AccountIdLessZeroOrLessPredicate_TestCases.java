/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestCases.Predicates;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;

/**
 *
 * @author James Thompson
 */
public class AccountIdLessZeroOrLessPredicate_TestCases {
    public static Stream<TicketTypeRequestWrapper> PassesSuccessfully_TestCases() {
        return Stream.of(
            new TicketTypeRequestWrapper(
                1L, 
                new TicketTypeRequest[] {
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1)
                }
            ),
            new TicketTypeRequestWrapper(
                156L, 
                new TicketTypeRequest[] {
                    new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1)
                }
            )
        );
    }
    
    public static Stream<TicketTypeRequestWrapper> ThrowInvalidPurchaseException_TestCases() {
        return Stream.of(
            new TicketTypeRequestWrapper(
                0L, 
                new TicketTypeRequest[1]
            ),
            new TicketTypeRequestWrapper(
                -1L, 
                new TicketTypeRequest[1]
            )
        );
    }
}
