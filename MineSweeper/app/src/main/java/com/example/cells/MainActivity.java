package com.example.cells;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //global variables
    boolean[] EXIT_CODE = {false};
    public static final int LENGTH = 10, WIDTH = 10, MAX_MINES = 20;
    //================
    public int counter = 0, minesCurrent, minesCorrectlyIdentified = 0;
    //field and its' mask initialization
    Button[][] Cells;
    int[][] MinesCells, WasFlagged;
    TextView mines;
    //================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minesCurrent = MAX_MINES;
        mines = findViewById(R.id.Mines);
        mines.setText("" + minesCurrent + " / " + MAX_MINES);

        GenerateMines();
        Generate();
        GameLogic();
    }
    public void GenerateMines() {
        MinesCells = new int[WIDTH][LENGTH];
        Random random = new Random();
        counter = 0;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (random.nextInt(3) % 2 == 1 && counter != MAX_MINES) {
                    MinesCells[i][j] = 1;
                    ++counter;
                } else {
                    MinesCells[i][j] = 0;
                }

            }
        }
    }
    public int Modulo(int a, int b) {
        if (a >= 0) {
            return a % b;
        } else {
            return b + a % b;
        }
    }
    public int GetMineCells(int x, int y) {
        int mines = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (y + i >= 0 && y + i < WIDTH && x + j >= 0 && x + j < LENGTH) {
                    if (MinesCells[y + i][x + j] == 1) {
                        ++mines;
                    }
                }
            }
        }
        return mines;
    }

    public void StopAppRunning() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                Cells[i][j].setEnabled(false);
            }
        }
    }

    public void ExitApp() {
        TextView TV = findViewById(R.id.TV);
        if (EXIT_CODE[0] /*|| (minesCurrent == 0 && minesCorrectlyIdentified != MAX_MINES)*/) {
            TV.setText("YOU LOST");
        }
        if (minesCurrent == 0 && minesCorrectlyIdentified == MAX_MINES) {
            TV.setText("YOU WON");
        }

    }

    public void Generate(){
        GridLayout layout = findViewById(R.id.Grid);

        layout.removeAllViews();
        layout.setColumnCount(LENGTH);
        Cells = new Button[WIDTH][LENGTH];
        WasFlagged = new int[WIDTH][LENGTH];
        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < LENGTH; j++){
                Cells[i][j] = (Button) inflater.inflate(R.layout.cell, layout, false);
                WasFlagged[i][j] = 0;
            }
        }
    }

    public void GameLogic() {
        GridLayout layout = findViewById(R.id.Grid);
        layout.removeAllViews();
        layout.setColumnCount(LENGTH);
        for (int i = 0; i < WIDTH; i++){
            for (int j = 0; j < LENGTH; j++){
                Button button = Cells[i][j];
                int buttonMask = MinesCells[i][j], x = j, y = i;
                button.setBackgroundColor(Color.GRAY);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (WasFlagged[y][x] == 0) {
                            int nearMines = GetMineCells(x, y);
                            if (nearMines == 0) {
                                button.setBackgroundColor(Color.parseColor("#AAABAE"));
                            } else {
                                button.setBackgroundColor(Color.parseColor("#E1E3E5"));
                                button.setText(Integer.toString(nearMines));
                            }
                        }
                        if (buttonMask == 1) {
                            button.setEnabled(false);
                            EXIT_CODE[0] = true;
                            StopAppRunning();
                            ExitApp();
                        }

                    }
                });

                button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        if (WasFlagged[y][x] == 0) {
                            if (minesCurrent > 0) {
                                if (buttonMask == 1) {
                                    ++minesCorrectlyIdentified;
                                }
                                WasFlagged[y][x] = 1;
                                view.setBackgroundResource(R.drawable.flag);
                                button.setText("");
                                mines.setText("" + --minesCurrent + " / " + MAX_MINES);
                            }
                        } else {
                            if (buttonMask == 1) {
                                --minesCorrectlyIdentified;
                            }
                            WasFlagged[y][x] = 0;
                            int nearMines = GetMineCells(x, y);
                            if (nearMines == 0) {
                                button.setBackgroundColor(Color.parseColor("#AAABAE"));
                            } else {
                                button.setBackgroundColor(Color.parseColor("#E1E3E5"));
                                button.setText(Integer.toString(nearMines));
                            }
                            mines.setText("" + ++minesCurrent + " / " + MAX_MINES);

                        }
                        ExitApp();
                        return false;
                    }
                });
                Cells[i][j].setTag(Integer.toString(GetMineCells(j, i)));
                layout.addView(Cells[i][j]);

            }
        }
    }

}