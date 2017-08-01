package lol.gameentities.eventconfigs;

import com.google.gson.annotations.SerializedName;

public class Choice {
    @SerializedName("label")
    public String label;
    @SerializedName("text")
    public String text;
    @SerializedName("result")
    public int result;
}
