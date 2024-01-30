package water.model;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;

public class RecentListModel extends BaseObservable {
    private Context context;
    private String id;
    private String name;

    private String image;

    public RecentListModel(Context context, String id, String name, String image) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void onItemClick(View view) {

    }

}

