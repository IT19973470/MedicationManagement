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

    public static DatabaseReference getDBReason() {
        //return FirebaseDatabase.getInstance().getReference("gayan").child("reason");
        return getFirebaseDatabaseRef().child("reason");
    }

    public static DatabaseReference getDBMedication() {
        return getFirebaseDatabaseRef().child("medication");
    }

    public static DatabaseReference getDBUser() {
        return getFirebaseDatabaseRef().child("user");
    }

    public static DatabaseReference getFirebaseDatabaseRef() {
        return FirebaseDatabase.getInstance().getReference("MedicationReminder").child(LoginActivity.getUsername());
    }

    public static DatabaseReference getFirebaseDatabase() {
        return FirebaseDatabase.getInstance().getReference("MedicationReminder");
    }
}
