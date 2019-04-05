
package com.rahul.sd2test.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {

    @SerializedName("users")
    @Expose
    public ArrayList<User> users = new ArrayList<>();
    @SerializedName("has_more")
    @Expose
    public boolean hasMore;

}
