package com.example.codinglayout_twonumbers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;

import com.example.codinglayout_twonumbers.databinding.ActivityDetailsListBinding;
import com.example.codinglayout_twonumbers.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class DetailsListActivity extends AppCompatActivity {
    private ActivityDetailsListBinding binding;
    private DetailViewModel model;
    private String string;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_list);

        binding = ActivityDetailsListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        model = new ViewModelProvider(this).get(DetailViewModel.class);
        model.getString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                string = s;
                binding.evDetail.setText(string);
            }
        });

        Intent reciveIntent = getIntent();
        if (reciveIntent != null) {
            string = reciveIntent.getStringExtra("string");
            position = reciveIntent.getIntExtra("position",0);
            binding.evDetail.setText(string);
            model.setString(string);
        }
        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main = MainActivity.getInstance();
                main.model.editListItems(position, binding.evDetail.getText().toString());
                main.model.getArrayList().observe(main, new Observer<ArrayList<String>>() {
                    @Override
                    public void onChanged(ArrayList<String> arrayList) {
                        main.listItems.clear();
                        main.listItems.addAll(arrayList);
                    }
                });
                main.adapter.notifyDataSetChanged();
                finish();
            }
        });
    }
}