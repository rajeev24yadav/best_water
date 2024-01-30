package water.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyAccountResponse {

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

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("date_of_birth")
        @Expose
        private String dateOfBirth;
        @SerializedName("phone_number")
        @Expose
        private String phoneNumber;
        @SerializedName("Adhar_card_no")
        @Expose
        private Object adharCardNo;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("my_wallet")
        @Expose
        private String myWallet;
        @SerializedName("verifiedStatus")
        @Expose
        private String verifiedStatus;
        @SerializedName("create_date")
        @Expose
        private String createDate;
        @SerializedName("modified_date")
        @Expose
        private String modifiedDate;
        @SerializedName("lastlogin")
        @Expose
        private String lastlogin;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("deleted")
        @Expose
        private String deleted;
        @SerializedName("online_status")
        @Expose
        private String onlineStatus;
        @SerializedName("country")
        @Expose
        private Object country;
        @SerializedName("latitude")
        @Expose
        private Object latitude;
        @SerializedName("longitude")
        @Expose
        private Object longitude;
        @SerializedName("device_token")
        @Expose
        private String deviceToken;
        @SerializedName("token")
        @Expose
        private String token;
        @SerializedName("identity")
        @Expose
        private String identity;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Object getAdharCardNo() {
            return adharCardNo;
        }

        public void setAdharCardNo(Object adharCardNo) {
            this.adharCardNo = adharCardNo;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMyWallet() {
            return myWallet;
        }

        public void setMyWallet(String myWallet) {
            this.myWallet = myWallet;
        }

        public String getVerifiedStatus() {
            return verifiedStatus;
        }

        public void setVerifiedStatus(String verifiedStatus) {
            this.verifiedStatus = verifiedStatus;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(String modifiedDate) {
            this.modifiedDate = modifiedDate;
        }

        public String getLastlogin() {
            return lastlogin;
        }

        public void setLastlogin(String lastlogin) {
            this.lastlogin = lastlogin;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public String getOnlineStatus() {
            return onlineStatus;
        }

        public void setOnlineStatus(String onlineStatus) {
            this.onlineStatus = onlineStatus;
        }

        public Object getCountry() {
            return country;
        }

        public void setCountry(Object country) {
            this.country = country;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public String getDeviceToken() {
            return deviceToken;
        }

        public void setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

    }
}
