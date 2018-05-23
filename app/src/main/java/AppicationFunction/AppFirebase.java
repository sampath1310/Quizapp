package AppicationFunction;

import android.os.SystemClock;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AppFirebase {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth firebaseAuth;
    public String username;
    private String uuid;

    private  String user;


    public AppFirebase(FirebaseDatabase database,FirebaseAuth firebaseAuth){
        this.database=database;
        this.firebaseAuth=firebaseAuth;
    }

    public AppFirebase(FirebaseDatabase database,FirebaseAuth firebaseAuth,DatabaseReference myRef){
        this.database=database;
        this.firebaseAuth=firebaseAuth;
        this.myRef=myRef;
    }

    public AppFirebase(FirebaseDatabase database){
        this.database=database;
    }



    public void registerUser(String uuid,String username_text,String email_text,String password_text,String phno_text,String catogery){
        myRef = database.getReference().child("UserDetails");
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("user_name", username_text);
        userData.put("email", email_text);
        userData.put("password", password_text);
        userData.put("pno",phno_text);
        userData.put("catogery",catogery);
        myRef.child(uuid).setValue(userData);
    }

    public void subjectDetails(String email_text){
        myRef=database.getReference().child("SubjectDetails");
        Map<String, String> userData = new HashMap<String, String>();

        userData.put("email", email_text);
        myRef.push().setValue(userData);



    }

    public String getEmail(){
        return firebaseAuth.getCurrentUser().getEmail();
    }

    public String getUserName(String  uuid){
        myRef = database.getReference().child("UserDetails").child(uuid).child("user_name");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                username=new String(dataSnapshot.getValue().toString());
                user=dataSnapshot.getValue(String.class);
                Log.d("TAGuser",user);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return username;
    }

}
