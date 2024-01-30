package water.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartItemModel {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("order_id")
        @Expose
        private String orderId;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("address_id")
        @Expose
        private String addressId;
        @SerializedName("discount_id")
        @Expose
        private Object discountId;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("create_date")
        @Expose
        private String createDate;
        @SerializedName("order_status")
        @Expose
        private String orderStatus;
        @SerializedName("order_item")
        @Expose
        private List<OrderItem> orderItem = null;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getAddressId() {
            return addressId;
        }

        public void setAddressId(String addressId) {
            this.addressId = addressId;
        }

        public Object getDiscountId() {
            return discountId;
        }

        public void setDiscountId(Object discountId) {
            this.discountId = discountId;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public List<OrderItem> getOrderItem() {
            return orderItem;
        }

        public void setOrderItem(List<OrderItem> orderItem) {
            this.orderItem = orderItem;
        }

        public class OrderItem {

            @SerializedName("order_item_id")
            @Expose
            private String orderItemId;
            @SerializedName("order_id")
            @Expose
            private String orderId;
            @SerializedName("product_id")
            @Expose
            private String productId;
            @SerializedName("quantity")
            @Expose
            private String quantity;
            @SerializedName("price")
            @Expose
            private String price;
            @SerializedName("product_detail_id")
            @Expose
            private String productDetailId;
            @SerializedName("size")
            @Expose
            private String size;
            @SerializedName("brand_id")
            @Expose
            private String brandId;
            @SerializedName("color")
            @Expose
            private Object color;
            @SerializedName("created_date")
            @Expose
            private String createdDate;
            @SerializedName("category_id")
            @Expose
            private String categoryId;
            @SerializedName("product_name")
            @Expose
            private String productName;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("video_url")
            @Expose
            private Object videoUrl;
            @SerializedName("image_url")
            @Expose
            private String imageUrl;
            @SerializedName("banner_show")
            @Expose
            private String bannerShow;
            @SerializedName("Product_Unit_InStock")
            @Expose
            private String productUnitInStock;
            @SerializedName("Product_Availability_Status")
            @Expose
            private String productAvailabilityStatus;

            public String getOrderItemId() {
                return orderItemId;
            }

            public void setOrderItemId(String orderItemId) {
                this.orderItemId = orderItemId;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getProductDetailId() {
                return productDetailId;
            }

            public void setProductDetailId(String productDetailId) {
                this.productDetailId = productDetailId;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getBrandId() {
                return brandId;
            }

            public void setBrandId(String brandId) {
                this.brandId = brandId;
            }

            public Object getColor() {
                return color;
            }

            public void setColor(Object color) {
                this.color = color;
            }

            public String getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Object getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(Object videoUrl) {
                this.videoUrl = videoUrl;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getBannerShow() {
                return bannerShow;
            }

            public void setBannerShow(String bannerShow) {
                this.bannerShow = bannerShow;
            }

            public String getProductUnitInStock() {
                return productUnitInStock;
            }

            public void setProductUnitInStock(String productUnitInStock) {
                this.productUnitInStock = productUnitInStock;
            }

            public String getProductAvailabilityStatus() {
                return productAvailabilityStatus;
            }

            public void setProductAvailabilityStatus(String productAvailabilityStatus) {
                this.productAvailabilityStatus = productAvailabilityStatus;
            }
        }
    }
}
