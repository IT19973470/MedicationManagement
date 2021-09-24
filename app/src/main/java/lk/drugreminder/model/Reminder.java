package lk.drugreminder.model;

public class Reminder {

    private String medicationHeader;
    private String medication;
    private String dose;
    private String dueTime;
    private String tookTime;
    private String nextTime;
    private String remaining;
    private String endAt;
    private boolean missed;
    private String reason;

    public Reminder(String medicationHeader, String medication, String dose, String dueTime, String nextTime, String remaining, String endAt) {
        this.medicationHeader = medicationHeader;
        this.medication = medication;
        this.dose = dose;
        this.dueTime = dueTime;
        this.nextTime = nextTime;
        this.remaining = remaining;
        this.endAt = endAt;
    }

    public Reminder(String medicationHeader, String medication, String dose, String dueTime, String tookTime, String nextTime, String remaining, String endAt, boolean missed) {
        this.medicationHeader = medicationHeader;
        this.medication = medication;
        this.dose = dose;
        this.dueTime = dueTime;
        this.tookTime = tookTime;
        this.nextTime = nextTime;
        this.remaining = remaining;
        this.endAt = endAt;
        this.missed = missed;
    }

    public Reminder(String medicationHeader, String medication, String dose, String dueTime, String tookTime, String nextTime, String remaining, String endAt, boolean missed, String reason) {
        this.medicationHeader = medicationHeader;
        this.medication = medication;
        this.dose = dose;
        this.dueTime = dueTime;
        this.tookTime = tookTime;
        this.nextTime = nextTime;
        this.remaining = remaining;
        this.endAt = endAt;
        this.missed = missed;
        this.reason = reason;
    }

    public String getMedicationHeader() {
        return medicationHeader;
    }

    public void setMedicationHeader(String medicationHeader) {
        this.medicationHeader = medicationHeader;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String getNextTime() {
        return nextTime;
    }

    public void setNextTime(String nextTime) {
        this.nextTime = nextTime;
    }

    public String getRemaining() {
        return remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public String getTookTime() {
        return tookTime;
    }

    public void setTookTime(String tookTime) {
        this.tookTime = tookTime;
    }

    public boolean isMissed() {
        return missed;
    }

    public void setMissed(boolean missed) {
        this.missed = missed;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
