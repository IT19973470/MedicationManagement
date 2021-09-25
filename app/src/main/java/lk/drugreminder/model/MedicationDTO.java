package lk.drugreminder.model;

public class MedicationDTO {

    private String medicationHeader;
    private String sicknessName;
    private String dose;
    private String nextDueTime;
    private String secondNextDueTime;
    private String endAt;
    private String remaining;

    public MedicationDTO(String medicationHeader, String sicknessName, String dose, String nextDueTime, String remaining, String endAt) {
        this.medicationHeader = medicationHeader;
        this.sicknessName = sicknessName;
        this.dose = dose;
        this.nextDueTime = nextDueTime;
        this.remaining = remaining;
        this.endAt = endAt;
    }

    public MedicationDTO(String medicationHeader, String sicknessName, String dose, String nextDueTime, String remaining, String endAt, String secondNextDueTime) {
        this.medicationHeader = medicationHeader;
        this.sicknessName = sicknessName;
        this.dose = dose;
        this.nextDueTime = nextDueTime;
        this.remaining = remaining;
        this.endAt = endAt;
        this.secondNextDueTime = secondNextDueTime;
    }

    public String getMedicationHeader() {
        return medicationHeader;
    }

    public void setMedicationHeader(String medicationHeader) {
        this.medicationHeader = medicationHeader;
    }

    public String getSicknessName() {
        return sicknessName;
    }

    public void setSicknessName(String sicknessName) {
        this.sicknessName = sicknessName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getNextDueTime() {
        return nextDueTime;
    }

    public void setNextDueTime(String nextDueTime) {
        this.nextDueTime = nextDueTime;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public String getRemaining() {
        return remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }

    public String getSecondNextDueTime() {
        return secondNextDueTime;
    }

    public void setSecondNextDueTime(String secondNextDueTime) {
        this.secondNextDueTime = secondNextDueTime;
    }
}
