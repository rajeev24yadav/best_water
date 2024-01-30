package water.model;

import android.content.Context;

import androidx.databinding.BaseObservable;

import water.response.GridResponse;

public class GridListModel extends BaseObservable {

    private final Context context;
    private GridResponse gridResponse;

    public GridListModel(Context context, GridResponse gridResponse) {
        this.context = context;
        this.gridResponse = gridResponse;
    }

    private String id;
    private String name;
    private String image;

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setGridListModel(GridResponse gridResponse) {
        this.gridResponse = gridResponse;
        notifyChange();
    }
}

