package com.example.hotelreservationapp.Services;

import com.example.hotelreservationapp.Models.Hotel;
import com.example.hotelreservationapp.Repositories.HotelRepository;
import com.example.hotelreservationapp.Models.Hotel;
import com.example.hotelreservationapp.Repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}