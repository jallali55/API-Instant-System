package com.instantsystem.parkingapi.Service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instantsystem.parkingapi.Dto.Parking;
import com.instantsystem.parkingapi.Service.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


@Service
public class ParkingServiceImpl implements ParkingService {
    Logger logger = LoggerFactory.getLogger(ParkingServiceImpl.class);
    @Value("${uri.available}")
    private String uriAvailable;
    @Value("${uri.base}")
    private String uriBase;

    @Override
    public List<Parking> getAvailableParking(int diffDistance, Double xPosition, Double yPosition) throws JsonProcessingException {

        Point2D clientPosition = new Point2D.Double(xPosition, yPosition);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uriAvailable, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(result);
        JsonNode recordsNode = actualObj.get("records");
        List<Parking> parkingList = new ArrayList<>();
        for (JsonNode objNode : recordsNode) {
            parkingList.add(parseNodeToParking(objNode, clientPosition));
        }

        return parkingList.stream().filter(Objects::nonNull).sorted(Comparator.comparingInt(Parking::getDistance)).toList();
    }

    public Parking parseNodeToParking(JsonNode jsonNode, Point2D clientPosition) {
        JsonNode fieldsNode = jsonNode.get("fields");
        Point2D point2D = new Point2D.Double(0, 0);
        double distance = 0;

        try {
            JsonNode geoPoint2dNode = fieldsNode.get("geo_point_2d");
            double x = geoPoint2dNode.get(0).asDouble();
            double y = geoPoint2dNode.get(1).asDouble();
            point2D = new Point2D.Double(x, y);
            distance = point2D.distanceSq(clientPosition);
        } catch (NullPointerException nullPointerException) {
            logger.info("Cannot read location field");
        }
        Boolean isFree = false;
        int placesRestantes = 0;
        try {
            isFree = fieldsNode.get("gratuit").asBoolean();
        } catch (NullPointerException nullPointerException) {
            logger.info("Cannot read gratuit field");
        }
        try {
            placesRestantes = fieldsNode.get("places_restantes").asInt();
        } catch (NullPointerException nullPointerException) {
            logger.info("Cannot read places_restantes field");
        }
        //calculate the distance between the client position and parking position
        return new Parking(fieldsNode.get("nom").asText(), fieldsNode.get("capacite").asInt(), placesRestantes, point2D, (int) distance, isFree);
    }
}

