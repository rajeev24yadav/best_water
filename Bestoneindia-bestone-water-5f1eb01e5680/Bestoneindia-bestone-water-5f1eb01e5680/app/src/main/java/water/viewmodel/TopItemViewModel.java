package water.viewmodel;

import water.model.TopItemModel;

public class TopItemViewModel {

    public TopItemModel topItemModel;
    public String image;

    public TopItemViewModel(TopItemModel topItemModel) {
        this.topItemModel = topItemModel;
        this.image = topItemModel.getImage();
    }

    public String getImageurl() {
        return topItemModel.getImage();
    }
}
