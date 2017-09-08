package befit.example.com.favouriteplaces.Serialized;

import com.google.gson.annotations.SerializedName;

public class Miejsca{

    @SerializedName("id")
    public int id;

    @SerializedName("login")
    public String login;

    @SerializedName("adres")
    public String adres;

    @SerializedName("x")
    public double x;

    @SerializedName("y")
    public double y;

    @SerializedName("description")
    public String description;

}