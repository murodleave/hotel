package com.example.hostel.controller;

import com.example.hostel.entity.Hotel;
import com.example.hostel.repo.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController{

    @Autowired
    HotelRepo hotelRepo;

    @GetMapping("/getHotels")
    public List<Hotel> getHotels() {
        return hotelRepo.findAll();
    }

    @GetMapping("/getHotels/{id}")
    public Hotel getHotelById(@PathVariable Integer id) {
        return hotelRepo.findById(id).orElse(null);
    }

    @PostMapping
    public String addHotel(@RequestBody Hotel hotel) {
        hotelRepo.save(hotel);
        return "Hotel saved";
    }

    @PutMapping("/{id}")
    public String editHotel (@PathVariable Integer id, @RequestBody Hotel hotel) {
        Optional<Hotel> byId = hotelRepo.findById(id);
        if (!byId.isPresent()) return "Hotel not found";
        Hotel hotel1 = byId.get();
        hotel1.setName(hotel.getName());

        hotelRepo.save(hotel1);
        return "Hotel edited";
    }

    @DeleteMapping("/{id}")
    public String dropHotel (@PathVariable Integer id) {
        hotelRepo.deleteById(id);
        return "Hotel Deleted";
    }
}
