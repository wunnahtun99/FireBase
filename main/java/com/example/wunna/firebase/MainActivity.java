package com.example.wunna.firebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText nameEdit;
    Spinner genereSpinner;

    DatabaseReference databaseArtists;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEdit=findViewById(R.id.editText);
        genereSpinner=findViewById(R.id.spinner);
        databaseArtists=FirebaseDatabase.getInstance().getReference("Artists");


    }

    public void Add(View view) {
        String name=nameEdit.getText().toString().trim();
        String genere=genereSpinner.getSelectedItem().toString();
        if (!TextUtils.isEmpty(name)){
            String id=  databaseArtists.push().getKey();
            Artist artist=new Artist(id,name,genere);
            databaseArtists.child(id).setValue(artist);
            Toast.makeText(this,"Done !",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"Fail !",Toast.LENGTH_LONG).show();
        }

    }
}
