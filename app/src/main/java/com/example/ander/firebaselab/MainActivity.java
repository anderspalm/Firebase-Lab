package com.example.ander.firebaselab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText mEditText;
    Button mButton;
    DatabaseReference mNameRef;
    DatabaseReference mUser;
    DatabaseReference mArrayRef;
    ArrayList<String> arrayList;
    ListView mListView;
    private ArrayAdapter mArrayAdapter;
    private static final String TAG = "MainActivity";
    static String mUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.send_button);
        mEditText = (EditText) findViewById(R.id.edit_text);
        mListView = (ListView) findViewById(R.id.list_view_one);

        mArrayRef = FirebaseDatabase.getInstance().getReference().child("comments");
        mUser = mArrayRef.child("user");
        mNameRef = mArrayRef.child("text");
        arrayList = new ArrayList<>();

        getUserName();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemObject itemObject = new ItemObject(mUsername, mEditText.getText().toString());
                mArrayRef.push().setValue(itemObject);
                Log.i(TAG, "onClick: " + itemObject.getmUser());
                Log.i(TAG, "onClick: " + mArrayRef.child("text").getKey());

            }
        });

        FirebaseListAdapter firebaseListAdapter = new FirebaseListAdapter<ItemObject>(this, ItemObject.class, R.layout.list_items, mArrayRef) {
            @Override
            protected void populateView(View view, ItemObject itemObject, int position) {
                TextView textView1 = (TextView) view.findViewById(R.id.text_view_one);
                TextView textView2 = (TextView) view.findViewById(R.id.text_view_two);
                textView1.setText(itemObject.getmUser());
                textView2.setText(itemObject.getMtext());
            }
        };
        mListView.setAdapter(firebaseListAdapter);
    }

    public static void getUserName() {
        Random random = new Random();
        int num = random.nextInt(300) + 1;
        String userName = "User" + num;
        mUsername = userName;
    }
}
