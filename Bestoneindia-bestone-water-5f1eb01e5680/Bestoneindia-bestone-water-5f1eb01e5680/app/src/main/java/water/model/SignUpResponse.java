package water.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpResponse {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private long mobile;
    @SerializedName("aadhaarNumber")
    @Expose
    private long aadhaarNumber;
    @SerializedName("age")
    @Expose
    private int age;
    @SerializedName("nationality")
    @Expose
    private String nationality;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobile() { return mobile; }

    public long getAadhaarNumber() { return aadhaarNumber; }

    public void setAadhaarNumber(long aadhaarNumber) { this.aadhaarNumber = aadhaarNumber; }

    public void setMobile(long mobile) { this.mobile = mobile; }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




}
