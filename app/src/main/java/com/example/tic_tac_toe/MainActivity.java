package com.example.tic_tac_toe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES = "mySettings";
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference mDatabase = database.getReference();
    public static DatabaseReference ref = database.getReference().child("rooms");
    public static int dp1;
    @SuppressLint("StaticFieldLeak")
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        dp1 = dip2pix(getApplicationContext(), 1);
        setContentView(R.layout.activity_main);
    }

    public static int dip2pix(@NonNull Context context, int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
    }
}