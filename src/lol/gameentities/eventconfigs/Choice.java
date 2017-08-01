package lol.gameentities.eventconfigs;

import com.google.gson.annotations.SerializedName;

public class Choice {
    @SerializedName("label")
    public String label;
    @SerializedName("text")
    public String text;
    @SerializedName("result")
    public int resultIndex;

    public String displayString() {
        return String.format("%s. %s", label, text);
    }

    @Override
    public String toString() {
        return "Choice{" +
                "label='" + label + '\'' +
                ", text='" + text + '\'' +
                ", resultIndex=" + resultIndex +
                '}';
    }
}
