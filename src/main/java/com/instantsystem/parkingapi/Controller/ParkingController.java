package com.instantsystem.parkingapi.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.instantsystem.parkingapi.Dto.Parking;
import com.instantsystem.parkingapi.Service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @GetMapping("/get-available-park/{diffDistance}")
    public ResponseEntity<List<Parking>> getListOfAvailablePark(@PathVariable("diffDistance") int diffDistance, @RequestParam("xPosition") Double xPosition, @RequestParam("yPosition") Double yPosition) throws JsonProcessingException {
        //xPosition and yPosition are two variables that we will use to identify the client position
        return ResponseEntity.ok().body(parkingService.getAvailableParking(diffDistance,xPosition,yPosition));
    }
}
