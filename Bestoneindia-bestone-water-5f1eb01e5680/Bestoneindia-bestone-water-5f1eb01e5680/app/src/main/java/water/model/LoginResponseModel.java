package water.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponseModel {
    @SerializedName("mobile")
    private int mobile;
    @SerializedName("otp")
    private int otp;
    @SerializedName("msg")
    private String message;


    @SerializedName("status")
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }



    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
