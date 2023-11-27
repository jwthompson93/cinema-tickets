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
    private final TicketTypeValidationService ticketTypeValidationService;
    private final TicketPaymentService ticketPaymentService;
    private final SeatReservationService seatReservationService;
    
    public TicketServiceImpl(
            TicketTypeValidationService ticketTypeValidationService, 
            TicketPaymentService ticketPaymentService, 
            SeatReservationService seatReservationService
    ) {
        this.ticketTypeValidationService = ticketTypeValidationService;
        this.ticketPaymentService = ticketPaymentService;
        this.seatReservationService = seatReservationService;
    }
    
    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        // Create TicketTypeRequest Wrapper Object
        TicketTypeRequestWrapper ticketTypeRequestWrapper = new TicketTypeRequestWrapper(accountId, ticketTypeRequests);
        
        // Perform Validation. Throws InvalidPurchaseException if any of the business rules are failed
        ticketTypeValidationService.validate(ticketTypeRequestWrapper);
        // Reserves Tickets
        ticketPaymentService.makePayment(accountId, ticketTypeRequestWrapper.GetTotalTicketPrice());
        // Reserves Seats
        seatReservationService.reserveSeat(accountId, ticketTypeRequestWrapper.GetTotalRequiredSeats());
    }

}
