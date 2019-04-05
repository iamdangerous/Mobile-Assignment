
package com.rahul.sd2test.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsersResponse {

    @SerializedName("status")
    @Expose
    public boolean status;
    @SerializedName("message")
    @Expose
    public Object message;
    @SerializedName("data")
    @Expose
    public Data data;

}
