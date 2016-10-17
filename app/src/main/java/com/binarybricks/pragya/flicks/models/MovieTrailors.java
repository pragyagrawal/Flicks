package com.binarybricks.pragya.flicks.models;

import java.util.List;

/**
 * Created by PRAGYA on 10/15/2016.
 */

public class MovieTrailors {
    private String id;

    private List<Youtube> youtube;

    private String[] quicktime;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public List<Youtube> getYoutube() {
        return youtube;
    }

    public void setYoutube(List<Youtube> youtube) {
        this.youtube = youtube;
    }

    public String[] getQuicktime ()
    {
        return quicktime;
    }

    public void setQuicktime (String[] quicktime)
    {
        this.quicktime = quicktime;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", youtube = "+youtube+", quicktime = "+quicktime+"]";
    }
}
