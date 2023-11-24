/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.gov.dwp.uc.pairtest.validation.service;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestWrapper;

/**
 *
 * @author James Thompson
 */
public interface TicketTypeValidationService {
    public boolean validate(TicketTypeRequestWrapper ticketTypeRequestWrapper);
}
