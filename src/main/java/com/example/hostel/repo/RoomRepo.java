package com.example.hostel.repo;

import com.example.hostel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepo extends JpaRepository<Room, Integer> {

    List<Room> getRoomsByHotelId(Integer hotel_id);
}
