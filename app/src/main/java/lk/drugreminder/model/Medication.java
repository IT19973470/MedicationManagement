package lk.drugreminder.model;

import java.time.LocalTime;

public class Medication {

    private String medicationId;
    private String medicationName;
    private String sicknessId;
    private int totalPills;
    private int lastAddedPills;
    private int dose;
    private int intervalH;
    private int intervalM;
    private int firstMedicationH = -1;
    private int firstMedicationM = -1;

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

    public int getTotalPills() {
        return totalPills;
    }

    public void setTotalPills(int totalPills) {
        this.totalPills = totalPills;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public int getIntervalH() {
        return intervalH;
    }

    public void setIntervalH(int intervalH) {
        this.intervalH = intervalH;
    }

    public int getIntervalM() {
        return intervalM;
    }

    public void setIntervalM(int intervalM) {
        this.intervalM = intervalM;
    }

    public int getFirstMedicationH() {
        return firstMedicationH;
    }

    public void setFirstMedicationH(int firstMedicationH) {
        this.firstMedicationH = firstMedicationH;
    }

    public int getFirstMedicationM() {
        return firstMedicationM;
    }

    public void setFirstMedicationM(int firstMedicationM) {
        this.firstMedicationM = firstMedicationM;
    }

    public int getLastAddedPills() {
        return lastAddedPills;
    }

    public void setLastAddedPills(int lastAddedPills) {
        this.lastAddedPills = lastAddedPills;
    }
}
