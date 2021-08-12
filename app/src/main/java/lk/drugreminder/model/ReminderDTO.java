package lk.drugreminder.model;

public class ReminderDTO {

    private String medicineHeader;
    private String medicine;
    private String dose;
    private String dueTime;
    private String tookTime;
    private String nextTime;
    private String remaining;
    private String endAt;
    private boolean missed;

    public ReminderDTO(String medicineHeader, String medicine, String dose, String dueTime, String nextTime, String remaining, String endAt) {
        this.medicineHeader = medicineHeader;
        this.medicine = medicine;
        this.dose = dose;
        this.dueTime = dueTime;
        this.nextTime = nextTime;
        this.remaining = remaining;
        this.endAt = endAt;
    }

    public ReminderDTO(String medicineHeader, String medicine, String dose, String dueTime, String tookTime, String nextTime, String remaining, String endAt, boolean missed) {
        this.medicineHeader = medicineHeader;
        this.medicine = medicine;
        this.dose = dose;
        this.dueTime = dueTime;
        this.tookTime = tookTime;
        this.nextTime = nextTime;
        this.remaining = remaining;
        this.endAt = endAt;
        this.missed = missed;
    }

    public String getMedicineHeader() {
        return medicineHeader;
    }

    public void setMedicineHeader(String medicineHeader) {
        this.medicineHeader = medicineHeader;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
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
}
