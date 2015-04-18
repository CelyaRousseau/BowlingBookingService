package com.ws.impl;

import com.bowling.dao.ReservationDAO;
import com.bowling.entity.Reservation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ws.itf.IBowlingBooking;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by Akronys on 18/04/2015.
 */
public class BowlingBooking implements IBowlingBooking {

    @Override
    public Response getBookings() throws JsonProcessingException {
        ObjectMapper mapper  = new ObjectMapper();
        List<Reservation> reservation   = new ReservationDAO().getAll();
        return Response.status(200).entity(mapper.writeValueAsString(reservation)).build();
    }

    @Override
    public Response getBookingById(int id) throws JsonProcessingException {
        ObjectMapper mapper  = new ObjectMapper();
        Reservation reservation   = new ReservationDAO().getReservationById(id);
        return Response.status(200).entity(mapper.writeValueAsString(reservation)).build();
    }

    @Override
    public Response createBooking(String json) throws IOException {
        return null;
    }

    @Override
    public Response updateBooking(String json, int id) throws IOException {
        return null;
    }

    @Override
    public Response deleteBooking(int id) throws IOException {
        return null;
    }
}
