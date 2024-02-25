package com.example.codinglayout_twonumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText textA;
    private EditText textB;
    private EditText textResult;
    private Button plusButton;
    private Button minusButton;
    private Button multiButton;
    private Button divideButton;
    private ListView listResult;
    private ArrayList<String> listItems = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textA = findViewById(R.id.text_a);
        textB = findViewById(R.id.text_b);
        textResult = findViewById(R.id.text_result);
        plusButton = findViewById(R.id.plus_button);
        minusButton = findViewById(R.id.minus_button);
        multiButton = findViewById(R.id.multi_button);
        divideButton = findViewById(R.id.divide_button);
        listResult = findViewById(R.id.list_result);

        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        listResult.setAdapter(adapter);

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plus();
            }
        });
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minus();
            }
        });
        multiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multi();
            }
        });
        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                divide();
            }
        });
    }

    private double[] getNumber() {
        try {
            double a = Double.parseDouble(textA.getText().toString());
            double b = Double.parseDouble(textB.getText().toString());
            double[] array = new double[2];
            array[0] = a;
            array[1] = b;
            return array;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new double[2];
    }

    private void showResult(double result) {
        textResult.setText("" + result);
    }

    private void addListResult(String stringResult) {
        String item = stringResult;
        listItems.add(item);
        adapter.notifyDataSetChanged();
    }

    private void plus() {
        try {
            double[] arrayNum = getNumber();
            double a = arrayNum[0];
            double b = arrayNum[1];
            double result = a + b;
            showResult(result);
            addListResult(a + " + " + b + " = " + result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void minus() {
        try {
            double[] arrayNum = getNumber();
            double a = arrayNum[0];
            double b = arrayNum[1];
            double result = a - b;
            showResult(result);
            addListResult(a + " - " + b + " = " + result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void multi() {
        try {
            double[] arrayNum = getNumber();
            double a = arrayNum[0];
            double b = arrayNum[1];
            double result = a * b;
            showResult(result);
            addListResult(a + " * " + b + " = " + result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void divide() {
        try {
            double[] arrayNum = getNumber();
            double a = arrayNum[0];
            double b = arrayNum[1];
            double result = a / b;
            showResult(result);
            addListResult(a + " / " + b + " = " + result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}