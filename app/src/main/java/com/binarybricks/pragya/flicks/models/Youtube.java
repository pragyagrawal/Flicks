package com.binarybricks.pragya.flicks.models;

/**
 * Created by PRAGYA on 10/15/2016.
 */
public class Youtube {
    private String source;

    private String name;

    private String type;

    private String size;

    public String getSource ()
    {
        return source;
    }

    public void setSource (String source)
    {
        this.source = source;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getSize ()
    {
        return size;
    }

    public void setSize (String size)
    {
        this.size = size;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [source = "+source+", name = "+name+", type = "+type+", size = "+size+"]";
    }
}
