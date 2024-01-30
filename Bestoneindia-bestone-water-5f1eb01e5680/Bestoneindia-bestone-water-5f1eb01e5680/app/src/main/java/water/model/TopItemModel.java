package water.model;

import androidx.databinding.BaseObservable;

public class TopItemModel extends BaseObservable {
    private String id;
    private String name;
    private String image;

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
