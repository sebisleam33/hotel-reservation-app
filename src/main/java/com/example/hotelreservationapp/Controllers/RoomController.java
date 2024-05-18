package com.example.hotelreservationapp.Controllers;

import com.example.hotelreservationapp.Models.Room;
import com.example.hotelreservationapp.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/hotel/{hotelId}/rooms")
    public List<Room> getHotelRooms(@PathVariable Long hotelId) {
        return roomService.getAvailableRooms(hotelId);
    }

    @PostMapping("/book")
    public Room bookRoom(@RequestParam Long roomId) {
        return roomService.bookRoom(roomId);
    }

    @PostMapping("/cancel")
    public Room cancelBooking(@RequestParam Long roomId) {
        return roomService.cancelBooking(roomId);
    }
}