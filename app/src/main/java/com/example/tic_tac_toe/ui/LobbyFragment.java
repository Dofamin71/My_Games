package com.example.tic_tac_toe.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.tic_tac_toe.Lobby;
import com.example.tic_tac_toe.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.tic_tac_toe.MainActivity.APP_PREFERENCES;
import static com.example.tic_tac_toe.MainActivity.mDatabase;
import static com.example.tic_tac_toe.MainActivity.ref;

public class LobbyFragment extends Fragment {
    public LobbyFragment() {
        super(R.layout.fragment_game);
    }

    String name = "Default name";
    int amount = 0, dp1;
    ArrayList<String> roomsList = new ArrayList<>();
    LinearLayout cardLayout;
    TextView title, players;
    LinearLayout.LayoutParams layoutparams;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_lobby, container, false);
        final Button create = root.findViewById(R.id.create_lobby);
        final EditText editName = root.findViewById(R.id.set_name_of_room);
        final LinearLayout scrollLayout = root.findViewById(R.id.scroll_layout);

        final SharedPreferences preferences = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        ref.child("roomsList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
                if (dataSnapshot.getValue(t) != null) roomsList = dataSnapshot.getValue(t);
                scrollLayout.removeAllViews();
                if (!roomsList.isEmpty()) {
                    for (int i = 0; i < roomsList.size(); ++i) {
                        name = roomsList.get(i);
                        Lobby lobby = new Lobby(name, 0);
                        create(lobby, scrollLayout);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                name = String.valueOf(editName.getText());
                editor.putString("name", name);
                editor.apply();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.equals("Default name")) {
                    Lobby lobby = new Lobby(name, 0);
                    if (!roomsList.contains(name)) roomsList.add(name);
                    create(lobby, scrollLayout);
                }
            }
        });
        return root;
    }

    public static int dip2pix(@NonNull Context context, int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip,
                context.getResources().getDisplayMetrics());
    }

    @SuppressLint("ResourceAsColor")
    private void create(Lobby lobby, LinearLayout scrollLayout) {
        final SharedPreferences preferences = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        mDatabase.child("rooms").child(name).setValue(lobby);
        mDatabase.child("rooms").child("roomsList").setValue(roomsList);
        dp1 = dip2pix(getContext(), 1);

        final CardView cardView = new CardView(getContext());
        cardLayout = new LinearLayout(getContext());
        layoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutparams.setMargins(dp1 * 30, dp1 * 30, dp1 * 30, dp1 * 30);
        cardView.setLayoutParams(layoutparams);
        cardView.setBackgroundColor(R.color.colorPrimary);

        layoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutparams.setMargins(dp1 * 10, dp1 * 10, dp1 * 10, dp1 * 10);
        title = new TextView(getContext());
        title.setText(name);
        title.setLayoutParams(layoutparams);
        title.setTextSize(40);

        layoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutparams.setMargins(dp1 * 10, dp1 * 10, dp1 * 10, dp1 * 10);
        players = new TextView(getContext());
        players.setText("0/2");
        players.setTextSize(20);
        players.setLayoutParams(layoutparams);
        players.setGravity(Gravity.RIGHT);

        ref.child(name).child("amount").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Integer> t = new GenericTypeIndicator<Integer>() {};
                amount = dataSnapshot.getValue(t);
                players.setText(amount + "/2");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        cardLayout.addView(title);
        cardLayout.addView(players);
        cardLayout.setGravity(Gravity.CENTER);

        cardView.addView(cardLayout);
        cardView.setId(roomsList.indexOf(name));

        scrollLayout.addView(cardView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("name", roomsList.get(cardView.getId()));
                editor.apply();
                Navigation.findNavController(v).navigate(R.id.action_lobby_to_game);
            }
        });
    }
}
