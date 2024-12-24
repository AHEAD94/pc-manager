package com.example.pcmanager;

import android.media.Image;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.PrintStream;

public class Module extends AppCompatActivity {

    public String snapshot = null;

    private String moduleNo;

    private String state = "진행중";
    private String date_rep;
    private String item_rep;
    private String repairer;
    private String history;

    private String depth;
    private String thickness;
    private String length;
    private String width;
    private String diagonal;
    private String height;

    private String concrete;
    private String covering;
    private String size;
    private String strength;
    private String crackDamage;
    private String rebar;
    private String mold;
    private String time;

    private String finishing;
    private String starting;
    private String producer;

    File certificate;
    File abrogation;
    File preliminary;

    Image photo;

    public Module(String md) {
        this.snapshot = md;
        this.moduleNo = md.substring(md.indexOf("No")+3, 12);
    }

    public Module() {    }

    public void setTime(String md) {
        this.snapshot = md;
        this.time = md.substring(md.indexOf("request")+8, md.indexOf("request")+8+16);
    }

    public void setRequestedInfo() {
        String tempSt = this.snapshot;
        String cutSt;

        cutSt = tempSt.substring(tempSt.indexOf("finishing")+10, tempSt.indexOf(","));
        this.finishing = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("starting")+9, tempSt.indexOf(",")-1);
        this.starting = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("producer")+9, tempSt.indexOf(",")-1);
        this.producer = cutSt;
    }

    public void setModuleInfo() {
        String tempSt = this.snapshot;
        String cutSt;

        /*
        while (tempSt.contains("=") != true) {
            if ()
            cutSt = tempSt.substring(tempSt.indexOf("=")+1, tempSt.indexOf(","));
            this.moduleNo = cutSt;
            tempSt = tempSt.substring(tempSt.indexOf(",")+2);
        }
        */

        cutSt = tempSt.substring(tempSt.indexOf("No")+3, tempSt.indexOf(","));
        this.moduleNo = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("date")+5, tempSt.indexOf(","));
        this.date_rep = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("item")+5, tempSt.indexOf(","));
        this.item_rep = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("repairer")+9, tempSt.indexOf(","));
        this.repairer = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("history")+8, tempSt.indexOf(",")-1);
        this.history = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("depth")+6, tempSt.indexOf(","));
        this.depth = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("thickness")+10, tempSt.indexOf(","));
        this.thickness = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("length")+7, tempSt.indexOf(","));
        this.length = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("width")+6, tempSt.indexOf(","));
        this.width = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("diagonal")+9, tempSt.indexOf(","));
        this.diagonal = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("height")+7, tempSt.indexOf(",")-1);
        this.height = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("concrete")+9, tempSt.indexOf(","));
        this.concrete = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("covering")+9, tempSt.indexOf(","));
        this.covering = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("size")+5, tempSt.indexOf(","));
        this.size = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("strength")+9, tempSt.indexOf(","));
        this.strength = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("damage")+7, tempSt.indexOf(","));
        this.crackDamage = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("rebar")+6, tempSt.indexOf(","));
        this.rebar = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("mold")+5, tempSt.indexOf(",")-1);
        this.mold = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("finishing")+10, tempSt.indexOf(","));
        this.finishing = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("starting")+9, tempSt.indexOf(",")-1);
        this.starting = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("producer")+9, tempSt.indexOf(","));
        this.producer = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        cutSt = tempSt.substring(tempSt.indexOf("state")+6, tempSt.indexOf(",")-1);
        this.state = cutSt;
        tempSt = tempSt.substring(tempSt.indexOf(",")+2);

        // report, photo 작업 추후 추가
        Log.d("tempSt", tempSt);
    }

    public String getModuleNo() {
        return this.moduleNo;
    }

    public String getTime() {
        return this.time;
    }

    public String getState() { return state; }

    public String getDate_rep() {
        return date_rep;
    }

    public String getItem_rep() {
        return item_rep;
    }

    public String getRepairer() {
        return repairer;
    }

    public String getHistory() {
        return history;
    }

    public String getDepth() {
        return depth;
    }

    public String getThickness() {
        return thickness;
    }

    public String getLength() {
        return length;
    }

    public String getWidth() {
        return width;
    }

    public String getDiagonal() {
        return diagonal;
    }

    public String getHeight() {
        return height;
    }

    public String getConcrete() {
        return concrete;
    }

    public String getCovering() {
        return covering;
    }

    public String getSize() {
        return size;
    }

    public String getStrength() {
        return strength;
    }

    public String getCrackDamage() {
        return crackDamage;
    }

    public String getRebar() {
        return rebar;
    }

    public String getMold() {
        return mold;
    }

    public String getFinishing() {
        return finishing;
    }

    public String getStarting() {
        return starting;
    }

    public String getProducer() {
        return producer;
    }
}
