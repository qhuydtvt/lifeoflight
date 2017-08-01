package lol.gameentities.eventconfigs;

import com.google.gson.annotations.SerializedName;

public class Choice {
    @SerializedName("label")
    public String label;
    @SerializedName("text")
    public String text;
    @SerializedName("result")
    public int result;

    @Override
    public String toString() {
        return "Choice{" +
                "label='" + label + '\'' +
                ", text='" + text + '\'' +
                ", result=" + result +
                '}';
    }
}
