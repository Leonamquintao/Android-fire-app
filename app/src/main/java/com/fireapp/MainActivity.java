package com.fireapp;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText mNameField;
    private EditText mSurNameField;
    private Button mAddBtn;
    private TextView mValueView;

    private Firebase mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cria o nó principal no firebase 'fire-app/Users'
        mRootRef = new Firebase("https://fire-app-a4105.firebaseio.com/Users");

        mValueView = (TextView) findViewById(R.id.greetUser);

        mNameField = (EditText) findViewById(R.id.nameField);
        mSurNameField = (EditText) findViewById(R.id.surNameField);

        //cria um event listener que observa alterações no banco mRootRef.
        mRootRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String valOf = String.valueOf(dataSnapshot.getValue(String.class));

                Log.v("Teste >>>>>>>>",valOf);
                //mValueView.setText("Leonam");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mAddBtn = (Button) findViewById(R.id.addBtn);

        mAddBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String value = mNameField.getText().toString();
                String subValue = mSurNameField.getText().toString();

                String key = value.concat(subValue);

                Firebase childRef = mRootRef.child(key);

                Firebase FirstName = childRef.child("Nome");
                Firebase LastName = childRef.child("Sobrenome");

                FirstName.setValue(value);
                LastName.setValue(subValue);

            }
        });

    }
}
