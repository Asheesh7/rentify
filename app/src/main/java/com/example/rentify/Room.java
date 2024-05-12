package com.example.rentify;
public class Room {
    String name;
    String image;
    int UserID;
    String price;
    String location;
    int RoomID;
    String status;

    public Room() {
    }

    public Room(String name, int roomID, int userID, String location, String price, String status, String image) {
        this.name = name;
        RoomID = roomID;
        UserID = userID;
        this.location = location;
        this.price = price;
        this.status = status;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRoomID() {
        return RoomID;
    }

    public void setRoomID(int roomID) {
        RoomID = roomID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
