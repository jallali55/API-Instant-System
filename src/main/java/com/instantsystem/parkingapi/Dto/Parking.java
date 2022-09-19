package com.instantsystem.parkingapi.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.instantsystem.parkingapi.Enum.IsFree;

import java.awt.geom.Point2D;

public class Parking {
    private String name;
    private int capacity;
    private int availablePlace;
    @JsonProperty("geo_point_2d")
    private Point2D point2D;

    private int distance;

    private IsFree isFree;

    public Parking(String name, int capacity, int availablePlace, Point2D point2D, int distance) {
        this.name = name;
        this.capacity = capacity;
        this.availablePlace = availablePlace;
        this.point2D = point2D;
        this.distance = distance;
    }

    public Parking(String name, int capacity, int availablePlace, Point2D point2D, int distance, IsFree isFree) {
        this.name = name;
        this.capacity = capacity;
        this.availablePlace = availablePlace;
        this.point2D = point2D;
        this.distance = distance;
        this.isFree = isFree;
    }

    public Parking() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailablePlace() {
        return availablePlace;
    }

    public void setAvailablePlace(int availablePlace) {
        this.availablePlace = availablePlace;
    }

    public Point2D getPoint2D() {
        return point2D;
    }

    public void setPoint2D(Point2D point2D) {
        this.point2D = point2D;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public IsFree getIsFree() {
        return isFree;
    }

    public void setIsFree(IsFree isFree) {
        this.isFree = isFree;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", availablePlace=" + availablePlace +
                ", point2D=" + point2D +
                ", distance=" + distance +
                '}';
    }
}
