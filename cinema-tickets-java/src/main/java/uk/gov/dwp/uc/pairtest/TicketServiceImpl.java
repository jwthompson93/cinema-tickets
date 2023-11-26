package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.validation.service.TicketTypeValidationService;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */
    private TicketTypeValidationService ticketTypeValidationService;
    private TicketPaymentService ticketPaymentService;
    private SeatReservationService seatReservationService;
    
    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        if(accountId > 0) {
            throw new InvalidPurchaseException("Account not valid");
        }
        
        // Create TicketTypeRequest Wrapper Object
        TicketTypeRequestWrapper ticketTypeRequestWrapper = new TicketTypeRequestWrapper(ticketTypeRequests);
        
        // Perform Validation Requests
        ticketTypeValidationService.validate(ticketTypeRequestWrapper);
        // Reserve Tickets
        ticketPaymentService.makePayment(accountId, ticketTypeRequestWrapper.GetTotalTicketPrice());
        // Reserve Seats
        seatReservationService.reserveSeat(accountId, ticketTypeRequestWrapper.GetTotalRequiredSeats());
    }

}
