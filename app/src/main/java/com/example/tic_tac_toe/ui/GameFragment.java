package com.example.tic_tac_toe.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tic_tac_toe.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.tic_tac_toe.MainActivity.APP_PREFERENCES;
import static com.example.tic_tac_toe.MainActivity.mDatabase;
import static com.example.tic_tac_toe.MainActivity.ref;

public class GameFragment extends Fragment {
    public GameFragment() {
        super(R.layout.fragment_game);
    }
    public int online, count, num, player;
    ArrayList<Integer> array = new ArrayList<Integer>(), asd = new ArrayList<Integer>();
    ArrayList imageList = new ArrayList();
    ImageView imageView;
    TextView title;
    String text = "", name;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_game, container, false);
        TextView textView = root.findViewById(R.id.textView2);

        final SharedPreferences preferences = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        online = preferences.getInt("online", 2);
        name = preferences.getString("name", null);
        player = preferences.getInt("amount", 2);
        if (player == 2) ref.child(name).child("amount").setValue(2);

        switch (online) {
            case 0:
                textView.setText("Offline mode");
                break;
            case 1:
                textView.setText("Online mode");
                break;
            default:
                textView.setText("Something is wrong...");
                break;
        }

        imageList.clear();
        final ImageView image1 = root.findViewById(R.id.imageView1);
        imageList.add(image1);
        final ImageView image2 = root.findViewById(R.id.imageView2);
        imageList.add(image2);
        final ImageView image3 = root.findViewById(R.id.imageView3);
        imageList.add(image3);
        final ImageView image4 = root.findViewById(R.id.imageView4);
        imageList.add(image4);
        final ImageView image5 = root.findViewById(R.id.imageView5);
        imageList.add(image5);
        final ImageView image6 = root.findViewById(R.id.imageView6);
        imageList.add(image6);
        final ImageView image7 = root.findViewById(R.id.imageView7);
        imageList.add(image7);
        final ImageView image8 = root.findViewById(R.id.imageView8);
        imageList.add(image8);
        final ImageView image9 = root.findViewById(R.id.imageView9);
        imageList.add(image9);
        title = root.findViewById(R.id.textView);
        imageList.add(title);
        final Button restart = root.findViewById(R.id.restart);

        restart();

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();
                title.setText("Ход первого игрока");
            }
        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num == player && online == 1 || online == 0) {
                    array.set(0, num);
                    if (online == 1) mDatabase.child("rooms").child(name).child("matrix").child("0").setValue(num);
                    next(0);
                }
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num == player && online == 1 || online == 0) {
                    array.set(1, num);
                    if (online == 1) mDatabase.child("rooms").child(name).child("matrix").child("1").setValue(num);
                    next(1);
                }
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num == player && online == 1 || online == 0) {
                    array.set(2, num);
                    if (online == 1) mDatabase.child("rooms").child(name).child("matrix").child("2").setValue(num);
                    next(2);
                }
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num == player && online == 1 || online == 0) {
                    array.set(3, num);
                    if (online == 1) mDatabase.child("rooms").child(name).child("matrix").child("3").setValue(num);
                    next(3);
                }
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num == player && online == 1 || online == 0) {
                    array.set(4, num);
                    if (online == 1) mDatabase.child("rooms").child(name).child("matrix").child("4").setValue(num);
                    next(4);
                }
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num == player && online == 1 || online == 0) {
                    array.set(5, num);
                    if (online == 1) mDatabase.child("rooms").child(name).child("matrix").child("5").setValue(num);
                    next(5);
                }
            }
        });

        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num == player && online == 1 || online == 0) {
                    array.set(6, num);
                    if (online == 1) mDatabase.child("rooms").child(name).child("matrix").child("6").setValue(num);
                    next(6);
                }
            }
        });

        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num == player && online == 1 || online == 0) {
                    array.set(7, num);
                    if (online == 1) mDatabase.child("rooms").child(name).child("matrix").child("7").setValue(num);
                    next(7);
                }
            }
        });

        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num == player && online == 1 || online == 0) {
                    array.set(8, num);
                    if (online == 1) mDatabase.child("rooms").child(name).child("matrix").child("8").setValue(num);
                    next(8);
                }
            }
        });

        if (online == 1) {
            ref.child(name).child("matrix").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    GenericTypeIndicator<ArrayList<Integer>> t = new GenericTypeIndicator<ArrayList<Integer>>() {};
                    asd = dataSnapshot.getValue(t);
                    update(asd);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
        }
        return root;
    }

    private void next(int index) {
        count++;
        imageView = (ImageView) imageList.get(index);
        if (array.get(index) == 1) {
            text = "Ход второго игрока";
            num = 2;
            imageView.setImageResource(R.drawable.x);
        }
        else {
            text = "Ход первого игрока";
            num = 1;
            imageView.setImageResource(R.drawable.o);
        }
        imageView.setClickable(false);
        title.setText(text);
        resume(array, num);
    }

    private void update(ArrayList<Integer> asd) {
        int index = 0;
        boolean change = false;
        if (!asd.contains(1) && !asd.contains(2)) {
            restart();
            title.setText("Ход первого игрока");
        }
        else {
            for (int i = 0; i < 9; ++i) {
                if (!array.get(i).equals(asd.get(i))) {
                    array.set(i, asd.get(i));
                    index = i;
                    change = true;
                    break;
                }
            }
            if (change) {
                next(index);
            }
        }
    }

    private void resume(ArrayList<Integer> array, int num) {
        if (array.get(0).equals(array.get(1)) && array.get(1).equals(array.get(2)) && array.get(1) != 0 ||
                array.get(3).equals(array.get(4)) && array.get(4).equals(array.get(5)) && array.get(4) != 0 ||
                array.get(6).equals(array.get(7)) && array.get(7).equals(array.get(8)) && array.get(7) != 0 ||
                array.get(0).equals(array.get(3)) && array.get(3).equals(array.get(6)) && array.get(3) != 0 ||
                array.get(1).equals(array.get(4)) && array.get(4).equals(array.get(7)) && array.get(4) != 0 ||
                array.get(2).equals(array.get(5)) && array.get(5).equals(array.get(8)) && array.get(5) != 0 ||
                array.get(0).equals(array.get(4)) && array.get(4).equals(array.get(8)) && array.get(4) != 0 ||
                array.get(2).equals(array.get(4)) && array.get(4).equals(array.get(6)) && array.get(4) != 0) {

            for (int i = 0; i < 9; ++i) {
                imageView = (ImageView) imageList.get(i);
                imageView.setClickable(false);
            }

            title = (TextView) imageList.get(9);

            if (num == 2) {
                title.setText("Победил первый игрок! /_(1-1)_\\");
            } else {
                title.setText("Победил второй игрок! \\_(2-2)_/");
            }
        } else if (count > 9) {
            title.setText("Ничья |_(0-0)_|");
        }
    }

    private void restart() {
        num = 1;
        count = 1;

        asd.clear();
        array.clear();
        for (int i = 0; i < 9; ++i) array.add(i, 0);

        if (online == 1) mDatabase.child("rooms").child(name).child("matrix").setValue(array);

        for (int i = 0; i < 9; ++i) {
            imageView = (ImageView) imageList.get(i);
            imageView.setClickable(true);
        }

        for (int i = 0; i < 9; ++i) {
            imageView = (ImageView) imageList.get(i);
            imageView.setImageResource(R.drawable.texture);
        }
    }
}
