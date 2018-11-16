package com.example.wunna.firebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nameEdit;
    Spinner genereSpinner;
    ListView listViewArtists;
    List<Artist> artistList;
    DatabaseReference databaseArtists;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEdit=findViewById(R.id.editText);
        genereSpinner=findViewById(R.id.spinner);
        databaseArtists=FirebaseDatabase.getInstance().getReference("Artists");
        listViewArtists=findViewById(R.id.list_view_artists);

        artistList=new ArrayList<>();
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

    @Override
    protected void onStart() {
        super.onStart();
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                artistList.clear();
                for(DataSnapshot artistSnapshot:dataSnapshot.getChildren()){
  //                  Log.d("onDataChange: ", artistSnapshot.toString());
                    String artistName=(String) artistSnapshot.child("artistName").getValue();
                    String artistGenere=(String) artistSnapshot.child("artistGenere").getValue();
                    String artistTd=(String) artistSnapshot.child("artistTd").getValue();
                    Artist artist=new Artist(artistTd,artistName,artistGenere);
                    artistList.add(artist);

                }
                ArtistList adapter=new ArtistList(MainActivity.this,artistList);
                listViewArtists.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
