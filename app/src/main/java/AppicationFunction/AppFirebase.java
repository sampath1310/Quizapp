package AppicationFunction;

import android.os.SystemClock;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AppFirebase {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    public AppFirebase(FirebaseDatabase database){
        this.database=database;
    }
    public void registerUser(String username_text,String email_text,String password_text,String phno_text,String catogery){
        myRef = database.getReference().child("UserDetails");
        Map<String, String> userData = new HashMap<String, String>();



        userData.put("user_name", username_text);
        userData.put("email", email_text);
        userData.put("password", password_text);
        userData.put("pno",phno_text);
        userData.put("catogery",catogery);
        myRef.push().setValue(userData);
    }
}
