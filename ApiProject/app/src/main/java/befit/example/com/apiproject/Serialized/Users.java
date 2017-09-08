package befit.example.com.apiproject.Serialized;

import com.google.gson.annotations.SerializedName;

public class Users{

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("username")
    public String username;

    @SerializedName("email")
    public String email;

    @SerializedName("phone")
    public String phone;

    @SerializedName("website")
    public String website;

}