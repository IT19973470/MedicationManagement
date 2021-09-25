package lk.drugreminder.model;

public class Medication {

    private String medicationId;
    private String medicationName;
    private String sicknessId;
    private String sicknessName;
    private int totalPills;
    private int lastAddedPills;
    private int dose;
    private int intervalH;
    private int intervalM;
    private int lastMedicationH;
    private int lastMedicationM;
    private int nextDueTimeH;
    private int nextDueTimeM;

    public Medication() {
    }

    public Medication(String medicationId, String medicationName, String sicknessId, String sicknessName) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.sicknessId = sicknessId;
        this.sicknessName = sicknessName;
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

    public int getLastMedicationH() {
        return lastMedicationH;
    }

    public void setLastMedicationH(int lastMedicationH) {
        this.lastMedicationH = lastMedicationH;
    }

    public int getLastMedicationM() {
        return lastMedicationM;
    }

    public void setLastMedicationM(int lastMedicationM) {
        this.lastMedicationM = lastMedicationM;
    }

    public int getLastAddedPills() {
        return lastAddedPills;
    }

    public void setLastAddedPills(int lastAddedPills) {
        this.lastAddedPills = lastAddedPills;
    }

    public int getNextDueTimeH() {
        return nextDueTimeH;
    }

    public void setNextDueTimeH(int nextDueTimeH) {
        this.nextDueTimeH = nextDueTimeH;
    }

    public int getNextDueTimeM() {
        return nextDueTimeM;
    }

    public void setNextDueTimeM(int nextDueTimeM) {
        this.nextDueTimeM = nextDueTimeM;
    }

    public String getSicknessName() {
        return sicknessName;
    }

    public void setSicknessName(String sicknessName) {
        this.sicknessName = sicknessName;
    }

}
