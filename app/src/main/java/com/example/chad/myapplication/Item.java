package com.example.chad.myapplication;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

/**
 * Created by chad on 2018/3/15.
 */

public class Item implements Serializable {

//    private long id;
    private long datetime;
    private Colors color;
    private String title;
    private String content;
    private String filename;
    private double latitude;
    private double longitude;
    private long lastModify;
    private boolean selected;

    public Item(){
        title="";
        content="";
    }

    public Item(long datetime, Colors color, String title, String content, String filename, double latitude, double longitude, long lastModify){

//        this.id=id;
        this.datetime=datetime;
        this.color=color;
        this.title=title;
        this.content=content;
        this.filename=filename;
        this.latitude=latitude;
        this.longitude=longitude;
        this.lastModify=lastModify;
    }

//    public long getId(){return id;}
//    public void setId(int id){this.id=id;}

    public long getDatetime(){return datetime;}
    public void setDatetime(long datetime){this.datetime=datetime;}
    public String getLocaleDate(){return String.format(Locale.getDefault(),"%tF %<tR",new Date(datetime));}


    public Colors getColor(){return color;}
    public void setColor(Colors color){this.color=color;}

    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title;}

    public String getContent(){return content;}
    public void setContent(String content){this.content=content;}

    public String getFilename(){return filename;}
    public void setFilename(String filename){this.filename=filename;}

    public double getLatitude(){return latitude;}
    public void setLatitude(double latitude){this.latitude=latitude;}

    public double getLongitude(){return longitude;}
    public void setLongitude(double longitude){this.longitude=longitude;}

    public long getLastModify(){return lastModify;}
    public void setLastModify(long lastModify){this.lastModify=lastModify;}

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
