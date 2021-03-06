package com.app.mystore.dto;

/*
* Author : Parth Panchal
* B00845025
* The model used to handle object response generated on availiablity submission from front end.
* */

public class avail {


    public String getMonStart() {
        return monStart;
    }

    public void setMonStart(String monStart) {
        this.monStart = monStart;
    }

    public String getMonEnd() {
        return monEnd;
    }

    public void setMonEnd(String monEnd) {
        this.monEnd = monEnd;
    }

    public String getTuesStart() {
        return tuesStart;
    }

    public void setTuesStart(String tuesStart) {
        this.tuesStart = tuesStart;
    }

    public String getTuesEnd() {
        return tuesEnd;
    }

    public void setTuesEnd(String tuesEnd) {
        this.tuesEnd = tuesEnd;
    }

    public String getWedStart() {
        return wedStart;
    }

    public void setWedStart(String wedStart) {
        this.wedStart = wedStart;
    }

    public String getWedEnd() {
        return wedEnd;
    }

    public void setWedEnd(String wedEnd) {
        this.wedEnd = wedEnd;
    }

    public String getThrusStart() {
        return thrusStart;
    }

    public void setThrusStart(String thrusStart) {
        this.thrusStart = thrusStart;
    }

    public String getThrusEnd() {
        return thrusEnd;
    }

    public void setThrusEnd(String thrusEnd) {
        this.thrusEnd = thrusEnd;
    }

    public String getFriStart() {
        return friStart;
    }

    public void setFriStart(String friStart) {
        this.friStart = friStart;
    }

    public String getFriEnd() {
        return friEnd;
    }

    public void setFriEnd(String friEnd) {
        this.friEnd = friEnd;
    }

    public String getSatStart() {
        return satStart;
    }

    public void setSatStart(String satStart) {
        this.satStart = satStart;
    }

    public String getSatEnd() {
        return satEnd;
    }

    public void setSatEnd(String satEnd) {
        this.satEnd = satEnd;
    }

    public String getSunStart() {
        return sunStart;
    }

    public void setSunStart(String sunStart) {
        this.sunStart = sunStart;
    }

    public String getSunEnd() {
        return sunEnd;
    }

    public void setSunEnd(String sunEnd) {
        this.sunEnd = sunEnd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWeekDays(Availability availability){
        String day = availability.getDay();
        System.out.println("weekdays :: Log"+day);
        switch (day){
            case "Monday":
                this.setMonEnd(availability.getEnd());
                this.setMonStart(availability.getStart());
                break;

            case "Tuesday":
                this.setTuesStart(availability.getStart());
                this.setMonStart(availability.getEnd());
                break;

            case "Wednesday":
                this.setWedStart(availability.getStart());
                this.setWedEnd(availability.getEnd());
                break;

            case "Thursday":
                this.setThrusStart(availability.getStart());
                this.setThrusEnd(availability.getEnd());
                break;
            case "Friday":
                this.setFriStart(availability.getStart());
                this.setSatEnd(availability.getEnd());
                break;
            case "Saturday":
                this.setSatStart(availability.getStart());
                this.setSatEnd(availability.getEnd());
                break;
            case "Sunday":
                this.setSunStart(availability.getStart());
                this.setSunEnd(availability.getEnd());
                break;
        }
    }

    String username;
    String monStart, monEnd;
    String tuesStart, tuesEnd;
    String wedStart, wedEnd;
    String  thrusStart, thrusEnd;
    String  friStart , friEnd;
    String satStart, satEnd;
    String sunStart,sunEnd;

}
