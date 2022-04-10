package com.vineeth.learning.learningspring.webservice;


import com.vineeth.learning.learningspring.data.Room;
import com.vineeth.learning.learningspring.data.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class RoomRestController {

    @Autowired
    private RoomRepository roomRepository;


    @RequestMapping("/rooms")
    @ResponseBody
    public Iterable<Room> getRooms() {
        Iterable<Room> rooms = roomRepository.findAll();
        return rooms;
    }
}
