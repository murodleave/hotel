package com.example.hostel.controller;

import com.example.hostel.dto.RoomDto;
import com.example.hostel.entity.Hotel;
import com.example.hostel.entity.Room;
import com.example.hostel.repo.HotelRepo;
import com.example.hostel.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")



public class RoomController {

    @Autowired
    RoomRepo roomRepo;
    @Autowired
    HotelRepo hotelRepo;

    @GetMapping
    public List<Room> getRooms () {
        return roomRepo.findAll();
    }

    @GetMapping("/{id}")
    public Room getRoom (@PathVariable Integer id) {

        Optional<Room> room = roomRepo.findById(id);
        return room.orElse(null);
    }

    @PostMapping
    public String addRoom (@RequestBody RoomDto roomDto) {
        Optional<Hotel> byId = hotelRepo.findById(roomDto.getHotel_id());
        if (!byId.isPresent()) return "Hotel not found";

        Room room = new Room();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        room.setHotel(byId.get());

        roomRepo.save(room);
        return "Room saved";
    }

    @PutMapping("/{id}")
    public String editRoom (@PathVariable Integer id, @RequestBody RoomDto roomDto) {
        Optional<Hotel> byId = hotelRepo.findById(roomDto.getHotel_id());
        if (!byId.isPresent()) return "Hotel not found";

        Optional<Room> byId1 = roomRepo.findById(id);
        if (!byId1.isPresent()) return "Room not found";

        Room room = byId1.get();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        room.setHotel(byId.get());
        roomRepo.save(room);
        return "Room edited";
    }

    @GetMapping("/byhotelid/{id}")
    public List<Room> getRoomByHotelId(@PathVariable Integer id) {
        return roomRepo.getRoomsByHotelId(id);
    }
}
