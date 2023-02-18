package com.example.hostel.dto;

import lombok.Data;
import org.springframework.stereotype.Service;


@Service
@Data
public class RoomDto {

    private Integer number;
    private Integer floor;
    private Double size;
    private Integer hotel_id;
}
