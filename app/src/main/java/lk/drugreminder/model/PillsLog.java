package lk.drugreminder.model;

public class PillsLog {
    private String pillsLogId;
    private Medication medication;
    private boolean tookPills;
    private String reason;
    private int tookTimeH;
    private int tookTimeM;
    private String tookDate;

    public PillsLog() {
    }

    public PillsLog(String pillsLogId, Medication medication, boolean tookPills, String reason, int tookTimeH, int tookTimeM, String tookDate) {
        this.pillsLogId = pillsLogId;
        this.medication = medication;
        this.tookPills = tookPills;
        this.reason = reason;
        this.tookTimeH = tookTimeH;
        this.tookTimeM = tookTimeM;
        this.tookDate = tookDate;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getTookTimeH() {
        return tookTimeH;
    }

    public void setTookTimeH(int tookTimeH) {
        this.tookTimeH = tookTimeH;
    }

    public int getTookTimeM() {
        return tookTimeM;
    }

    public void setTookTimeM(int tookTimeM) {
        this.tookTimeM = tookTimeM;
    }

    public String getTookDate() {
        return tookDate;
    }

    public void setTookDate(String tookDate) {
        this.tookDate = tookDate;
    }

    public String getPillsLogId() {
        return pillsLogId;
    }

    public void setPillsLogId(String pillsLogId) {
        this.pillsLogId = pillsLogId;
    }
}
