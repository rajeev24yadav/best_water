package water.model;

import com.google.gson.annotations.SerializedName;

public class PickupModelList {

    @SerializedName("product_id")
    private String product_id;

    @SerializedName("category_id")
    private String category_id;

    @SerializedName("product_name")
    private String product_name;

    @SerializedName("description")
    private String description;

    @SerializedName("video_url")
    private String video_url;

    @SerializedName("image_url")
    private String image_url;

    @SerializedName("banner_show")
    private String banner_show;

    @SerializedName("price")
    private String price;

    @SerializedName("created_date")
    private String created_date;


    @SerializedName("Product_Unit_InStock")
    private String Product_Unit_InStock;

    @SerializedName("Product_Availability_Status")
    private String Product_Availability_Status;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getBanner_show() {
        return banner_show;
    }

    public void setBanner_show(String banner_show) {
        this.banner_show = banner_show;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getProduct_Unit_InStock() {
        return Product_Unit_InStock;
    }

    public void setProduct_Unit_InStock(String product_Unit_InStock) {
        Product_Unit_InStock = product_Unit_InStock;
    }

    public String getProduct_Availability_Status() {
        return Product_Availability_Status;
    }

    public void setProduct_Availability_Status(String product_Availability_Status) {
        Product_Availability_Status = product_Availability_Status;
    }
}
