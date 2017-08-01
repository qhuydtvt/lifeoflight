package lol.gameentities.eventconfigs;

import com.google.gson.annotations.SerializedName;

import java.util.Random;

public class Action {
    @SerializedName("type")
    public String type;
    @SerializedName("rate")
    public int rate;
    @SerializedName("max_rate")
    public int maxRate;

    Random random = new Random();

    @Override
    public String toString() {
        return "Action{" +
                "type='" + type + '\'' +
                ", rate=" + rate +
                ", maxRate=" + maxRate +
                '}';
    }

    public boolean roll() {
        return random.nextInt(maxRate + 1) < rate;
    }
}
