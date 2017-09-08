package befit.example.com.befit.Serialized;

import com.google.gson.annotations.SerializedName;

public class Products{

    @SerializedName("id_produktu")
    public int id_produktu;

    @SerializedName("name")
    public String name;

    @SerializedName("calories")
    public float calories;

    @SerializedName("fat")
    public float fat;

    @SerializedName("carbs")
    public float carbs;

    @SerializedName("protein")
    public float protein;

    @SerializedName("quantity")
    public int quantity;

}