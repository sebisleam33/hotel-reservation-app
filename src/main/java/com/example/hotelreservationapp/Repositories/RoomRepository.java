package com.example.hotelreservationapp.Repositories;

import com.example.hotelreservationapp.Models.Room;
import com.example.hotelreservationapp.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHotelIdAndIsAvailable(Long hotelId, boolean isAvailable);
}