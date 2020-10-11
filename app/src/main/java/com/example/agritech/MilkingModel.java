package com.example.agritech;

public class MilkingModel {

    private String date;
    private String name;
    private int morning;
    private int evening;

    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name= name;
    }

    public int getMorning() {
        return morning;
    }

    public void setMorning(int morning  ) {
        this.morning= morning;
    }

    public int getEvening() {
        return evening;
    }


    public void setEvening(int evening) {
        this.evening= evening;
    }


}


