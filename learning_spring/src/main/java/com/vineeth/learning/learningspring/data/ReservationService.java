package com.vineeth.learning.learningspring.data;

import com.vineeth.learning.learningspring.RoomReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Service
public class ReservationService {

    @Autowired
    private  RoomRepository roomRepository;

    @Autowired
    private  GuestRepository guestRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public List<RoomReservation> getRoomReservationsForDate(Date date){
        Iterable<Reservation> reservations = reservationRepository.findReservationByReservationDate(new Date(date.getTime()));
        List<RoomReservation> roomReservations = new ArrayList();
        reservations.forEach( reservation -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setDate(reservation.getReservationDate());
            roomReservation.setGuestId(reservation.getGuestId());
            roomReservation.setRoomId(reservation.getRoomId());
            populateRoomDetails(roomReservation, reservation.getRoomId());
            populateGuestDetails(roomReservation, reservation.getGuestId());
            roomReservations.add(roomReservation);
        });
        return roomReservations;
    }

    private void populateRoomDetails(RoomReservation roomReservation, long roomId){
        Room room = roomRepository.findById(roomId).orElseGet(null);
        if (null != room){
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
        }
    }

    private void populateGuestDetails(RoomReservation roomReservation, long guestId){
        Guest guest = guestRepository.findById(guestId).orElseGet(null);
        if (null != guest){
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
        }
    }
}
