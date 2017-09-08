package befit.example.com.befit.Serialized;

import com.google.gson.annotations.SerializedName;

public class Atlas {

    @SerializedName("id_atlas")
    public int id_atlas;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

}