package lk.drugreminder.db;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import lk.drugreminder.LoginActivity;

public class FirebaseDB {

    public static DatabaseReference getDBSickness() {
        return getFirebaseDatabaseRef().child("sickness");
    }

    public static DatabaseReference getDBReminder() {
        return getFirebaseDatabaseRef().child("reminder");
    }

    public static DatabaseReference getDBMedicine() {
        return getFirebaseDatabaseRef().child("medicine");
    }

    public static DatabaseReference getDBUser() {
        return getFirebaseDatabaseRef().child("user");
    }

    public static DatabaseReference getFirebaseDatabaseRef() {
        return FirebaseDatabase.getInstance().getReference("MedicationReminder").child(LoginActivity.getUser().getEmail());
    }

    public static DatabaseReference getFirebaseDatabase() {
        return FirebaseDatabase.getInstance().getReference("MedicationReminder");
    }
}
