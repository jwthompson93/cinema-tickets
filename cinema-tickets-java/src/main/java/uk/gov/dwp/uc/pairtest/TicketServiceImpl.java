package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */
    private TicketPaymentService ticketPaymentService;
    private SeatReservationService seatReservationService;
    
    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        // Create TicketTypeRequest Wrapper Object
        TicketTypeRequestWrapper ticketTypeRequestWrapper = new TicketTypeRequestWrapper(ticketTypeRequests);
        
        // Perform Validation Requests
        // Reserve Tickets
        ticketPaymentService.makePayment(accountId, ticketTypeRequestWrapper.GetTotalTicketPrice());
        // Reserve Seats
        seatReservationService.reserveSeat(accountId, ticketTypeRequestWrapper.GetTotalRequiredSeats());
    }

}
