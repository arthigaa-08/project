package com.examly.springapp.model;

import jakarta.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    private String roomNumber;
    private int pricePerNight;
    private boolean available;

    @ManyToOne
    private RoomCategory roomCategory;

    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public int getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(int pricePerNight) { this.pricePerNight = pricePerNight; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public RoomCategory getRoomCategory() { return roomCategory; }
    public void setRoomCategory(RoomCategory roomCategory) { this.roomCategory = roomCategory; }
}

