package water.model;

import com.google.gson.annotations.SerializedName;

public class Banner {
    @SerializedName("bannerImageUrl")
    private String bannerImageUrl;

    public String getBannerImageUrl() {
        return bannerImageUrl;
    }

    public void setBannerImageUrl(String bannerImageUrl) {
        this.bannerImageUrl = bannerImageUrl;
    }
}
