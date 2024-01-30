package water.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Observable;

public class CartResponse extends Observable {
    @SerializedName("data")
    private List<CartModel> data;

    public List<CartModel> getData() {
        return data;
    }
}
