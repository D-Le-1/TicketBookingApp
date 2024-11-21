package com.example.ticketbooking.Model;

import java.io.Serializable;

public class Flight implements Serializable {
    private String airlineLogo;
    private String airlineName;
    private String arriveTime;
    private String classSeat;
    private String date;
    private String from;
    private String fromShort;
    private String to;
    private String toShort;
    private Integer numberSeat;
    private Double price;
    private String passager;
    private String seats;
    private String reservedSeat;
    private String time;

    public Flight() {
    }

    @Override
    public String toString() {
        return from;
    }

    public String getAirlineLogo() {
        return airlineLogo;
    }

    public void setAirlineLogo(String airlineLogo) {
        this.airlineLogo = airlineLogo;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getClassSeat() {
        return classSeat;
    }

    public void setClassSeat(String classSeat) {
        this.classSeat = classSeat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromShort() {
        return fromShort;
    }

    public void setFromShort(String fromShort) {
        this.fromShort = fromShort;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getToShort() {
        return toShort;
    }

    public void setToShort(String toShort) {
        this.toShort = toShort;
    }

    public Integer getNumberSeat() {
        return numberSeat;
    }

    public void setNumberSeat(Integer numberSeat) {
        this.numberSeat = numberSeat;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPassager() {
        return passager;
    }

    public void setPassager(String passager) {
        this.passager = passager;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getReservedSeat() {
        return reservedSeat;
    }

    public void setReservedSeat(String reservedSeat) {
        this.reservedSeat = reservedSeat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
