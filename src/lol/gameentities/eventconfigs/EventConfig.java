package lol.gameentities.eventconfigs;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lol.bases.Utils;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by huynq on 8/2/17.
 */
public class EventConfig {

    @SerializedName("id")
    public String id;
    @SerializedName("id_number")
    public int idNumber;
    @SerializedName("type")
    public String type;
    @SerializedName("begin_text")
    public String beginText;
    @SerializedName("action")
    public Action action;
    @SerializedName("choices")
    public List<Choice> choices;
    @SerializedName("affect")
    public List<String> affect;
    @SerializedName("result")
    public List<Integer> results;
    @SerializedName("end_text_success")
    public List<String> endTextSuccess;
    @SerializedName("end_text_fail")
    public List<String> endTextFail;

    public Choice findChoice(String label) {
        for (Choice choice : choices) {
            if (choice.label.toUpperCase().equals(label.toUpperCase())) {
                return choice;
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBeginText() {
        return beginText;
    }

    public void setBeginText(String beginText) {
        this.beginText = beginText;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public List<String> getAffect() {
        return affect;
    }

    public void setAffect(List<String> affect) {
        this.affect = affect;
    }

    public List<Integer> getResults() {
        return results;
    }

    public void setResults(List<Integer> results) {
        this.results = results;
    }

    public List<String> getEndTextSuccess() {
        return endTextSuccess;
    }

    public void setEndTextSuccess(List<String> endTextSuccess) {
        this.endTextSuccess = endTextSuccess;
    }

    public List<String> getEndTextFail() {
        return endTextFail;
    }

    public void setEndTextFail(List<String> endTextFail) {
        this.endTextFail = endTextFail;
    }

    @Override
    public String toString() {
        return "EventConfig{" +
                "id='" + id + '\'' +
                ", idNumber=" + idNumber +
                ", type='" + type + '\'' +
                ", beginText='" + beginText + '\'' +
                ", action=" + action +
                ", choices=" + choices +
                ", affect=" + affect +
                ", results=" + results +
                ", endTextSuccess=" + endTextSuccess +
                ", endTextFail=" + endTextFail +
                '}';
    }

    private static List<EventConfig> eventConfigs;
    public static void load() {
        String eventConfigString = Utils.loadFileContent("assets/event/event.json");
        if (eventConfigString != null) {
            Type configListType = new TypeToken<List<EventConfig>>(){}.getType();
            eventConfigs = new Gson().fromJson(eventConfigString, configListType);
            System.out.println(eventConfigs);
        }
    }

    private static final String RESULT_PLACEHOLDER = "{result}";

    public String renderTextSuccess(int index, Object result) {
        return this.endTextSuccess
                .get(index)
                .replace(RESULT_PLACEHOLDER, result.toString());
    }

    public String renderTextFailed(int index, Object result) {
        return this.endTextFail
                .get(index)
                .replace(RESULT_PLACEHOLDER, result.toString());
    }

    public String renderRndTextFailed(Object result) {
        return Utils.choice(this.endTextFail)
                .replace(RESULT_PLACEHOLDER, result.toString());
    }

    public static EventConfig random() {
        return Utils.choice(eventConfigs);
    }
}
