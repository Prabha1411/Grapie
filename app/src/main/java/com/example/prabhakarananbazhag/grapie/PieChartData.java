package com.example.prabhakarananbazhag.grapie;

import java.io.Serializable;
import java.util.ArrayList;

public class PieChartData implements Serializable{
    public ArrayList<String> name;
    public ArrayList<Float> matches;
    public ArrayList<String> color;
    public PieChartData( ArrayList<String> name, ArrayList<Float> matches, ArrayList<String> color) {
        this.name = name;
        this.matches = matches;
        this.color = color;
    }
    public ArrayList<String> getName() {
        return name;
    }
    public ArrayList<Float> getMatches() {
        return matches;
    }
    public ArrayList<String> getColor() {
        return color;
    }
    public void setName(ArrayList<String> name) {
        this.name = name;
    }
    public void setMatches(ArrayList<Float> matches) {
        this.matches = matches;
    }
    public void setColor(ArrayList<String> color) {
        this.color = color;
    }







}
