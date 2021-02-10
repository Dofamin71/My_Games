package com.example.tic_tac_toe.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.tic_tac_toe.R;

import static com.example.tic_tac_toe.MainActivity.APP_PREFERENCES;

public class StartFragment extends Fragment {
    public StartFragment() {
        super(R.layout.fragment_start);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_start, container, false);
        Button singleplayer = root.findViewById(R.id.singleplayer_button);
        Button multiplayer = root.findViewById(R.id.multiplayer_button);
        Button exit = root.findViewById(R.id.exit_button);

        final SharedPreferences preferences = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        singleplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("online", 0);
                editor.apply();
                Navigation.findNavController(v).navigate(R.id.action_start_to_game);
            }
        });

        multiplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("online", 1);
                editor.apply();
                Navigation.findNavController(v).navigate(R.id.action_start_to_lobby);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        return root;
    }
}
