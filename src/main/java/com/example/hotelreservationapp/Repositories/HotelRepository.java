package com.example.hotelreservationapp.Repositories;

import com.example.hotelreservationapp.Models.Hotel;
import com.example.hotelreservationapp.Models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}