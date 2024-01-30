package water.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Observable;

public class PickUpResponse extends Observable {

    @SerializedName("status")
    public boolean status;

    @SerializedName("data")
    public List<PickupModelList> list;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<PickupModelList> getList() {
        return list;
    }
}
