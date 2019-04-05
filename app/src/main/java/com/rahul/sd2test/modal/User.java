
package com.rahul.sd2test.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("items")
    @Expose
    public ArrayList<String> items = new ArrayList<>();

    @SerializedName("loadMore")
    @Expose
    public boolean loadMore  = false;


}
