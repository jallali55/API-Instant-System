package com.instantsystem.parkingapi.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.instantsystem.parkingapi.Dto.Parking;

import java.util.List;

public interface ParkingService {

    List<Parking> getAvailableParking(int diffDistance,Double xPosition,Double yPosition) throws JsonProcessingException;
}
