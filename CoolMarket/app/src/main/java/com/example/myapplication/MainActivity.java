package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.databinding.ElementsBinding;

import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int TotalSum = 0;
    ElementsBinding binding;
    public void clr(){
        if(binding.CB1.isChecked() == true){
            binding.CB1.setChecked(false);
            binding.CB1.setEnabled(true);
        }
        if (binding.CB2.isChecked() == true) {
            binding.CB2.setChecked(false);
            binding.CB2.setEnabled(true);
        }
        if (binding.CB3.isChecked() == true) {
            binding.CB3.setChecked(false);
            binding.CB3.setEnabled(true);
        }
        if (binding.CB4.isChecked() == true) {
            binding.CB4.setChecked(false);
            binding.CB4.setEnabled(true);
        }
        if (binding.CB5.isChecked() == true) {
            binding.CB5.setEnabled(true);
            binding.CB5.setChecked(false);
        }
        TotalSum = 0;
        binding.RG.clearCheck();
    }

    public boolean ProductDeliveryChosen(){
        return (binding.CB1.isChecked() || binding.CB2.isChecked() || binding.CB3.isChecked() || binding.CB4.isChecked() || binding.CB5.isChecked()) &&
                (binding.RB1.isChecked() || binding.RB2.isChecked() || binding.RB3.isChecked());
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ElementsBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());


        binding.RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (binding.RB1.isChecked()) {
                    binding.TV1.setText("Выбран способ доставки: самовывоз");
                    binding.TV1.setText("111");
                }
                if (binding.RB2.isChecked()) {
                    binding.TV1.setText("Выбран способ доставки: курьер");
                }
                if (binding.RB3.isChecked()) {
                    binding.TV1.setText("Выбран способ доставки: забрать на кассе");
                }
                if (binding.RB4.isChecked()) {
                    clr();
                }
            }
        });
        binding.CB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.CB1.isChecked()) {
                    binding.CB1.setEnabled(false);
                    TotalSum += 100;
                }
            }
        });

        binding.CB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.CB2.isChecked()) {
                    binding.CB2.setEnabled(false);
                    TotalSum += 50;
                }
            }
        });

        binding.CB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.CB3.isChecked()) {
                    binding.CB3.setEnabled(false);
                    TotalSum += 150;
                }
            }
        });
        binding.CB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.CB4.isChecked()) {
                    binding.CB4.setEnabled(false);
                    TotalSum += 75;
                }
            }
        });

        binding.CB5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.CB5.isChecked()) {
                    binding.CB5.setEnabled(false);
                    TotalSum += 25;
                }
            }
        });


        binding.BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.BT.isPressed()) {
                    if (ProductDeliveryChosen()) {
                        binding.TV1.setText("Сумма покупки: " + Integer.toString(TotalSum));
                        binding.ET.setText("");
                        clr();
                    }
                }
            }
        });


    }

}