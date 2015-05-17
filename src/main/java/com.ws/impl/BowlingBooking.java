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
        ObjectMapper mapper            = new ObjectMapper();
        List<Reservation> reservations = new ReservationDAO().getAll();
        return Response.status(200).entity(mapper.writeValueAsString(reservations)).build();
    }

    @Override
    public Response getBookingById(int id) throws JsonProcessingException {
        ObjectMapper mapper     = new ObjectMapper();
        Reservation reservation = new ReservationDAO().getReservationById(id);
        return Response.status(200).entity(mapper.writeValueAsString(reservation)).build();
    }

    @Override
    public Response createBooking(String json) throws IOException {
        /**
         * We need at least :
         * - voir tarif bowling : tarif par personne et par partie
         *-> donc calcul du temps qu'il faut pour le nombre de personne et partie
         * - 60 min / 8 personnes -> 7min 50 s / personne
         *
         * -> Pouvoir creer une resa juste avec le NOMBRE de personne et non les noms
         * IN :
         {
             "name_reservation":"dupont",
             "count_player":"4",
             "count_game":"1"
         }

         */

        ObjectMapper mapper = new ObjectMapper();

        String nameReservation = mapper.readTree(json).get("name_reservation").asText();
        int countPlayer        = mapper.readTree(json).get("count_player").asInt();
        int countGame          = mapper.readTree(json).get("count_game").asInt();

        Reservation reservation = new Reservation(nameReservation, countGame, countPlayer);

        createBookingInBdd(reservation);

        return Response.status(200).entity(mapper.writeValueAsString(reservation)).build();
    }

   /**
    * public static Date ajouterSeconde(Date date, int nbSeconde) {
        Calendar cal = Calendar.getlnstance();
        cal.setTime(date.getTime());
        cal.add(Calendar.SECOND, nbSeconde);
        return cal.getTime();
    }
    */



    public void createBookingInBdd(Reservation reservation){
        new ReservationDAO().save(reservation);
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
