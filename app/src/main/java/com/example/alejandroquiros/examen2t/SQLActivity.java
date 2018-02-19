package com.example.alejandroquiros.examen2t;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;



public class SQLActivity extends AppCompatActivity {

    //private FirebaseAuth mAuth;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mLugaresRef = mRootRef.child("Lugares");
    DatabaseHandler databaseHandler = new DatabaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       //Descarga de datos de firebase
        mLugaresRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.e("Count " ,""+snapshot.getChildrenCount());


                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Log.v("FirebaseData", ""+postSnapshot.child("Lat").getValue());


                    Lugar lugar = new Lugar();
                    lugar.nombre = (String) postSnapshot.child("nombre").getValue();
                    lugar.Lat = (Double) postSnapshot.child("Lat").getValue();
                    lugar.Lon = (Double) postSnapshot.child("Lon").getValue();
                    Toast.makeText(SQLActivity.this, ""+lugar.Lon, Toast.LENGTH_SHORT).show();

                    databaseHandler.addContact(lugar);
                    Log.v("ContactoSQL", " Contactos: "+databaseHandler.getAllContacts());

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
