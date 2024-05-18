package com.example.hotelreservationapp.Services;

import com.example.hotelreservationapp.Repositories.RoomRepository;
import com.example.hotelreservationapp.Models.Room;
import com.example.hotelreservationapp.Repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAvailableRooms(Long hotelId) {
        return roomRepository.findByHotelIdAndIsAvailable(hotelId, true);
    }

    public Room bookRoom(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        if (room.isAvailable()) {
            room.setAvailable(false);
            return roomRepository.save(room);
        }
        throw new IllegalStateException("Room is not available");
    }

    public Room cancelBooking(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        if (!room.isAvailable()) {
            room.setAvailable(true);
            return roomRepository.save(room);
        }
        throw new IllegalStateException("Room is not booked");
    }
}