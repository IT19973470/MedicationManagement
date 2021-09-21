package lk.drugreminder.db;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDB {

    public static DatabaseReference getFirebaseDatabase() {
        return FirebaseDatabase.getInstance().getReference("imalka").child("sickness");
    }
}
