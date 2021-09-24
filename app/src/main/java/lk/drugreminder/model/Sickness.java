package lk.drugreminder.model;

public class Sickness {

    private String sicknessId;
    private String sicknessName;

    public Sickness() {
    }

    public Sickness(String sicknessId, String sicknessName) {
        this.sicknessId = sicknessId;
        this.sicknessName = sicknessName;
    }

    public String getSicknessId() {
        return sicknessId;
    }

    public void setSicknessId(String sicknessId) {
        this.sicknessId = sicknessId;
    }

    public String getSicknessName() {
        return sicknessName;
    }

    public void setSicknessName(String sicknessName) {
        this.sicknessName = sicknessName;
    }

    @Override
    public String toString() {
        return sicknessName;
    }
}
