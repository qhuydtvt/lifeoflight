package lol.gameentities.eventconfigs;

import com.google.gson.annotations.SerializedName;

public class Action {
    @SerializedName("type")
    public String type;
    @SerializedName("rate")
    public int rate;
    @SerializedName("max_rate")
    public int maxRate;
}
