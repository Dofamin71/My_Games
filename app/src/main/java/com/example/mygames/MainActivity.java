package com.example.mygames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int count, num;
    int[] array = new int[9];
    String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView image1 = findViewById(R.id.imageView1);
        final ImageView image2 = findViewById(R.id.imageView2);
        final ImageView image3 = findViewById(R.id.imageView3);
        final ImageView image4 = findViewById(R.id.imageView4);
        final ImageView image5 = findViewById(R.id.imageView5);
        final ImageView image6 = findViewById(R.id.imageView6);
        final ImageView image7 = findViewById(R.id.imageView7);
        final ImageView image8 = findViewById(R.id.imageView8);
        final ImageView image9 = findViewById(R.id.imageView9);
        final TextView title = findViewById(R.id.textView);
        final Button restart = findViewById(R.id.restart);

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
                array[0] = num;
                count++;
                resume(array, num);
                if (count%2!=0) {
                    text = "Ход первого игрока";
                    num = 1;
                    image1.setImageResource(R.drawable.o);
                } else {
                    text = "Ход второго игрока";
                    num = 2;
                    image1.setImageResource(R.drawable.x);
                }
                title.setText(text);
                image1.setClickable(false);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array[1] = num;
                count++;
                resume(array, num);
                if (count%2!=0) {
                    text = "Ход первого игрока";
                    num = 1;
                    image2.setImageResource(R.drawable.o);
                } else {
                    text = "Ход второго игрока";
                    num = 2;
                    image2.setImageResource(R.drawable.x);
                }
                title.setText(text);
                image2.setClickable(false);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array[2] = num;
                count++;
                resume(array, num);
                if (count%2!=0) {
                    text = "Ход первого игрока";
                    num = 1;
                    image3.setImageResource(R.drawable.o);
                } else {
                    text = "Ход второго игрока";
                    num = 2;
                    image3.setImageResource(R.drawable.x);
                }
                title.setText(text);
                image3.setClickable(false);
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array[3] = num;
                count++;
                resume(array, num);
                if (count%2!=0) {
                    text = "Ход первого игрока";
                    num = 1;
                    image4.setImageResource(R.drawable.o);
                } else {
                    text = "Ход второго игрока";
                    num = 2;
                    image4.setImageResource(R.drawable.x);
                }
                title.setText(text);
                image4.setClickable(false);
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array[4] = num;
                count++;
                resume(array, num);
                if (count%2!=0) {
                    text = "Ход первого игрока";
                    num = 1;
                    image5.setImageResource(R.drawable.o);
                } else {
                    text = "Ход второго игрока";
                    num = 2;
                    image5.setImageResource(R.drawable.x);
                }
                title.setText(text);
                image5.setClickable(false);
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array[5] = num;
                count++;
                resume(array, num);
                if (count%2!=0) {
                    text = "Ход первого игрока";
                    num = 1;
                    image6.setImageResource(R.drawable.o);
                } else {
                    text = "Ход второго игрока";
                    num = 2;
                    image6.setImageResource(R.drawable.x);
                }
                title.setText(text);
                image6.setClickable(false);
            }
        });

        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array[6] = num;
                count++;
                resume(array, num);
                if (count%2!=0) {
                    text = "Ход первого игрока";
                    num = 1;
                    image7.setImageResource(R.drawable.o);
                } else {
                    text = "Ход второго игрока";
                    num = 2;
                    image7.setImageResource(R.drawable.x);
                }
                title.setText(text);
                image7.setClickable(false);
            }
        });

        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array[7] = num;
                count++;
                resume(array, num);
                if (count%2!=0) {
                    text = "Ход первого игрока";
                    num = 1;
                    image8.setImageResource(R.drawable.o);
                } else {
                    text = "Ход второго игрока";
                    num = 2;
                    image8.setImageResource(R.drawable.x);
                }
                title.setText(text);
                image8.setClickable(false);
            }
        });

        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array[8] = num;
                count++;
                resume(array, num);
                if (count%2!=0) {
                    text = "Ход первого игрока";
                    num = 1;
                    image9.setImageResource(R.drawable.o);
                } else {
                    text = "Ход второго игрока";
                    num = 2;
                    image9.setImageResource(R.drawable.x);
                }
                title.setText(text);
                image9.setClickable(false);
            }
        });
    }

    private void resume(int[] array, int num) {
        final ImageView image1 = findViewById(R.id.imageView1);
        final ImageView image2 = findViewById(R.id.imageView2);
        final ImageView image3 = findViewById(R.id.imageView3);
        final ImageView image4 = findViewById(R.id.imageView4);
        final ImageView image5 = findViewById(R.id.imageView5);
        final ImageView image6 = findViewById(R.id.imageView6);
        final ImageView image7 = findViewById(R.id.imageView7);
        final ImageView image8 = findViewById(R.id.imageView8);
        final ImageView image9 = findViewById(R.id.imageView9);

        if (array[0] == array[1] && array[1] == array[2] && array[1] != 0 ||
                array[3] == array[4] && array[4] == array[5] && array[4] != 0 ||
                array[6] == array[7] && array[7] == array[8] && array[7] != 0 ||
                array[0] == array[3] && array[3] == array[6] && array[3] != 0 ||
                array[1] == array[4] && array[4] == array[7] && array[4] != 0 ||
                array[2] == array[5] && array[5] == array[8] && array[5] != 0 ||
                array[0] == array[4] && array[4] == array[8] && array[4] != 0 ||
                array[2] == array[4] && array[4] == array[6] && array[4] != 0) {
            image1.setClickable(false);
            image2.setClickable(false);
            image3.setClickable(false);
            image4.setClickable(false);
            image5.setClickable(false);
            image6.setClickable(false);
            image7.setClickable(false);
            image8.setClickable(false);
            image9.setClickable(false);

            if (num == 1) {
                Toast.makeText(this, "Победил первый игрок! /_(1-1)_\\", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Победил второй игрок! \\_(2-2)_/", Toast.LENGTH_SHORT).show();
            }
        } else if (count > 9) {
            Toast.makeText(MainActivity.this, "Ничья |_(0-0)_|", Toast.LENGTH_SHORT).show();
        }
    }

    private void restart() {
        final ImageView image1 = findViewById(R.id.imageView1);
        final ImageView image2 = findViewById(R.id.imageView2);
        final ImageView image3 = findViewById(R.id.imageView3);
        final ImageView image4 = findViewById(R.id.imageView4);
        final ImageView image5 = findViewById(R.id.imageView5);
        final ImageView image6 = findViewById(R.id.imageView6);
        final ImageView image7 = findViewById(R.id.imageView7);
        final ImageView image8 = findViewById(R.id.imageView8);
        final ImageView image9 = findViewById(R.id.imageView9);

        count = 1;
        num = 1;

        for (int i = 0; i < 9; i++) array[i] = 0;

        image1.setClickable(true);
        image2.setClickable(true);
        image3.setClickable(true);
        image4.setClickable(true);
        image5.setClickable(true);
        image6.setClickable(true);
        image7.setClickable(true);
        image8.setClickable(true);
        image9.setClickable(true);

        image1.setImageResource(R.drawable.texture);
        image2.setImageResource(R.drawable.texture);
        image3.setImageResource(R.drawable.texture);
        image4.setImageResource(R.drawable.texture);
        image5.setImageResource(R.drawable.texture);
        image6.setImageResource(R.drawable.texture);
        image7.setImageResource(R.drawable.texture);
        image8.setImageResource(R.drawable.texture);
        image9.setImageResource(R.drawable.texture);
    }
}