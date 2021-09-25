package lk.drugreminder.model;

public class PillsLog {
    private Medication medication;
    private boolean tookPills;

    public PillsLog() {
    }

    public PillsLog(Medication medication, boolean tookPills) {
        this.medication = medication;
        this.tookPills = tookPills;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public boolean isTookPills() {
        return tookPills;
    }

    public void setTookPills(boolean tookPills) {
        this.tookPills = tookPills;
    }
}
