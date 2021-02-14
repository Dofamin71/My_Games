package com.example.tic_tac_toe.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.example.tic_tac_toe.R;
import com.example.tic_tac_toe.Room;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.tic_tac_toe.MainActivity.APP_PREFERENCES;
import static com.example.tic_tac_toe.MainActivity.context;
import static com.example.tic_tac_toe.MainActivity.dp1;
import static com.example.tic_tac_toe.MainActivity.ref;

public class LobbyFragment extends Fragment {
    public LobbyFragment() {
        super(R.layout.fragment_game);
    }

    String name = "Default name", back = null;
    int amount = 0;
    ArrayList<Room> roomsList = new ArrayList<>();
    LinearLayout cardLayout;
    TextView title, players;
    LinearLayout.LayoutParams layoutparams;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_lobby, container, false);
        final Button create = root.findViewById(R.id.create_lobby);
        final EditText editName = root.findViewById(R.id.set_name_of_room);
        final LinearLayout scrollLayout = root.findViewById(R.id.scroll_layout);
        final ArrayList<Integer> matrix = new ArrayList<>();

        final SharedPreferences preferences = this.requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        back = preferences.getString("back", null);

        matrix.clear();
        for (int i = 0; i < 9; ++i) matrix.add(i, 0);

        ref.child("roomsList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<Room>> t = new GenericTypeIndicator<ArrayList<Room>>() {};
                if (dataSnapshot.getValue(t) != null) roomsList = dataSnapshot.getValue(t);
                scrollLayout.removeAllViews();
                if (!roomsList.isEmpty()) {
                    for (int i = 0; i < roomsList.size(); ++i) {
                        name = roomsList.get(i).getName();
                        amount = roomsList.get(i).getAmount();
                        if (back != null && back.equals(name)) {
                            editor.putString("back", null);
                            editor.apply();
                            back = null;
                            amount--;
                        }
                        roomsList.get(i).setAmount(amount);
                        create(matrix, scrollLayout, i, preferences, editor);
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
                if (!name.equals("Default name") && !contains(roomsList, name)) {
                    Room room = new Room(name, 0);
                    roomsList.add(room);
                    create(matrix, scrollLayout, roomsList.size()-1, preferences, editor);
                }
            }
        });
        return root;
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n", "RtlHardcoded"})
    private void create(ArrayList<Integer> matrix, LinearLayout scrollLayout, final int index, SharedPreferences preferences, final SharedPreferences.Editor editor) {
        ref.child(name).setValue(matrix);
        ref.child("roomsList").setValue(roomsList);

        final CardView cardView = new CardView(context);
        cardLayout = new LinearLayout(context);
        layoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutparams.setMargins(dp1 * 30, dp1 * 30, dp1 * 30, dp1 * 30);
        cardView.setLayoutParams(layoutparams);

        layoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutparams.setMargins(dp1 * 10, dp1 * 10, dp1 * 10, dp1 * 10);
        title = new TextView(context);
        title.setText(name);
        title.setLayoutParams(layoutparams);
        title.setTextSize(40);

        layoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutparams.setMargins(dp1 * 10, dp1 * 10, dp1 * 10, dp1 * 10);
        players = new TextView(context);
        players.setText(roomsList.get(index).getAmount() + "/2");
        players.setTextSize(20);
        players.setLayoutParams(layoutparams);
        players.setGravity(Gravity.RIGHT);

        cardLayout.addView(title);
        cardLayout.addView(players);
        cardView.setBackgroundColor(Color.RED);
        cardLayout.setGravity(Gravity.CENTER);

        cardView.addView(cardLayout);
        cardView.setId(index);

        scrollLayout.addView(cardView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (roomsList.get(cardView.getId()).getAmount() < 2) {
                    editor.putInt("id", cardView.getId());
                    editor.putString("name", roomsList.get(cardView.getId()).getName());
                    editor.apply();
                    Navigation.findNavController(v).navigate(R.id.action_lobby_to_game);
                }
            }
        });
    }

    private boolean contains(ArrayList<Room> roomsList, String name) {
        for (int i = 0; i < roomsList.size(); ++i) {
            if (roomsList.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
