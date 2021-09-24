package lk.drugreminder.model;

public class Medication {

    private String medicationId;
    private String medicationName;
    private String sicknessId;

    public Medication() {
    }

    public Medication(String medicationId, String medicationName, String sicknessId) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.sicknessId = sicknessId;
    }

    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getSicknessId() {
        return sicknessId;
    }

    public void setSicknessId(String sicknessId) {
        this.sicknessId = sicknessId;
    }
}
