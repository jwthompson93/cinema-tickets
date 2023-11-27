/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TestCases.Predicates;

import java.util.stream.Stream;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;

/**
 *
 * @author James Thompson
 */
public class InfantsMustNotOutnumberAdultsPredicate_TestCases {
    public static Stream<TicketTypeRequestWrapper> PassesSuccessfully_TestCases() {
        return Stream.of(
            new TicketTypeRequestWrapper(
                1L,
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
            ),
            new TicketTypeRequestWrapper(
                1L,
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 4)
            ),
            new TicketTypeRequestWrapper(
                1L,
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 10),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 10)
            ),
            new TicketTypeRequestWrapper(
                1L,
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 10),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
            )
        );
    }
    
    public static Stream<TicketTypeRequestWrapper> ThrowInvalidPurchaseException_TestCases() {
        return Stream.of(
            new TicketTypeRequestWrapper(
                1L,
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 6)
            ),
            new TicketTypeRequestWrapper(
                1L,
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 9),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 10)
            ),
            new TicketTypeRequestWrapper(
                1L,
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 4),
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 10),
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 5)
            )
        );
    }
}
