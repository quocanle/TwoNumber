package com.example.codinglayout_twonumbers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.codinglayout_twonumbers.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected ArrayList<String> listItems = new ArrayList<String>();
    protected ArrayAdapter<String> adapter;
    public MyViewModel model;
    protected ActivityMainBinding binding;
    private static MainActivity instance;

    public MainActivity(){
        instance = this;
    }

    public static MainActivity getInstance() {
        return instance;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        model = new ViewModelProvider(this).get(MyViewModel.class);
        model.getArrayList().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> arrayList) {
                listItems.clear();
                listItems.addAll(arrayList);
            }
        });

        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        binding.listResult.setAdapter(adapter);

        binding.textResult.setEnabled(false);

        binding.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plus();
            }
        });
        binding.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minus();
            }
        });
        binding.multiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multi();
            }
        });
        binding.divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                divide();
            }
        });
        binding.listResult.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                model.removeListItems(position);
                model.getArrayList().observe(MainActivity.this, new Observer<ArrayList<String>>() {
                    @Override
                    public void onChanged(ArrayList<String> arrayList) {
                        listItems.clear();
                        listItems.addAll(arrayList);
                    }
                });
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        binding.listResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsListActivity.class);
                intent.putExtra("string", listItems.get(position).toString());
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    private double[] getNumber() {
        try {
            double a = Double.parseDouble(binding.textA.getText().toString());
            double b = Double.parseDouble(binding.textB.getText().toString());
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
        binding.textResult.setText("" + result);
    }

    private void addListResult(String stringResult) {
        String item = stringResult;
        listItems.add(item);
        model.addListItems(item);
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