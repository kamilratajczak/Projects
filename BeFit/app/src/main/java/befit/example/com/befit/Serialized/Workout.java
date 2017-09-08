package befit.example.com.befit.Serialized;

import com.google.gson.annotations.SerializedName;

public class Workout {

    @SerializedName("id_cwiczenia")
    public int id_cwiczenia;

    @SerializedName("exercise")
    public String exercise;

    @SerializedName("photo")
    public String photo;

}