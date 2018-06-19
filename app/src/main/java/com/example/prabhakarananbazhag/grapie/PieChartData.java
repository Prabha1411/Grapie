package com.example.prabhakarananbazhag.grapie;

import java.io.Serializable;
import java.util.ArrayList;

public class PieChartData implements Serializable{

    public Integer leftval;
    public Integer topval;
    public Integer height;
    public Integer width;
    public Integer trans;
    public ArrayList<String> name;
    public ArrayList<Float> matches;
    public ArrayList<String> color;



    public PieChartData(Integer leftval, Integer topval,Integer height, Integer width, Integer trans, ArrayList<String> name, ArrayList<Float> matches, ArrayList<String> color) {
        this.leftval = leftval;
        this.topval = topval;
        this.height = height;
        this.width = width;
        this.trans = trans;
        this.name = name;
        this.matches = matches;
        this.color = color;
    }
    public Integer getLeftval() {
        return leftval;
    }

    public void setLeftval(Integer leftval) {
        this.leftval = leftval;
    }

    public Integer getTopval() {
        return topval;
    }

    public void setTopval(Integer topval) {
        this.topval = topval;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getTrans() {
        return trans;
    }

    public void setTrans(Integer trans) {
        this.trans = trans;
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
