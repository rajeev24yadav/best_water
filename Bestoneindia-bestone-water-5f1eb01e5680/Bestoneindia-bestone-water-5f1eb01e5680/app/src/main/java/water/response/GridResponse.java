package water.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GridResponse implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("picture")
    private String picture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
