package com.example.myadmin.testing101;

public class Form {
    private int id;
    private String type;
    private String description;
    private String email;
    private double latitude;
    private double longitude;
    private String timestamp;
    private String url;
    private int count_up;
    private int count_down;
    private double xronos_ekt;


    public Form(){

    }

    public Form(int id, String type, String description, String email,double latitude,double longitude,String timestamp,String url,int count_down,int count_up) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.url = url;
        this.count_down = count_down;
        this.count_up = count_up;
    }

    public Form(int id, String type,double latitude,double longitude,String description,String timestamp,double xronos_ekt) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.xronos_ekt = xronos_ekt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getXronos_ekt() {
        return xronos_ekt;
    }

    public void setXronos_ekt(double xronos_ekt) {
        this.xronos_ekt = xronos_ekt;
    }

    public int getCount_up() {
        return count_up;
    }

    public void setCount_up(int count_up) {
        this.count_up = count_up;
    }

    public int getCount_down() {
        return count_down;
    }

    public void setCount_down(int count_down) {
        this.count_down = count_down;
    }

    @Override
    public String toString(){
        return getType()+" : " + getDescription() +" : "+getTimestamp();
    }
}
