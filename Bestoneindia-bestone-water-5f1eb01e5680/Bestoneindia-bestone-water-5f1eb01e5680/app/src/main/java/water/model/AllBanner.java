package water.model;

import com.google.gson.annotations.SerializedName;

public class AllBanner {
    @SerializedName("bannerFile")
    private String bannerFile;
    @SerializedName("bannerType")
    private String bannerType;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("banner")
    private Banner banner;
    @SerializedName("id")
    private Object id;

    public String getBannerFile() {
        return bannerFile;
    }

    public String getBannerType() {
        return bannerType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Banner getBanner() {
        return banner;
    }

    public Object getId() {
        return id;
    }


}
