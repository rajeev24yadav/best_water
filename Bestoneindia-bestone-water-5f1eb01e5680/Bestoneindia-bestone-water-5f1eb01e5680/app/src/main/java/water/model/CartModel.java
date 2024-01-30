package water.model;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

public class CartModel extends BaseObservable {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
