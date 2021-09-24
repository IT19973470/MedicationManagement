package lk.drugreminder.model;

public class Reason {
    private String reasonId;
    private String text;

    public Reason(){}

    public Reason(String id, String toString) {
        this.reasonId = id;
        this.text = toString;
    }

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
