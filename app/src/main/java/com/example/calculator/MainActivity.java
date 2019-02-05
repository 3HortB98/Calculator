package com.example.calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etNumberOne;
    EditText etNumberTwo;
    EditText etName;
    Button btnAdd;
    TextView tvResult;
    Button btnLogs;
    Button btnSub;
    Button btnDiv;
    Button btnMult;
    Button btnSave;
    Group nameGrp;

    private List<String> log = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etNumberOne = findViewById(R.id.etNumber1);
        etNumberTwo = findViewById(R.id.etNumber2);
        btnAdd = findViewById(R.id.btnAdd);
        tvResult = findViewById(R.id.tvResult);
        btnLogs = findViewById(R.id.btnLog);
        btnSub = findViewById(R.id.btnSubtract);
        btnDiv = findViewById(R.id.btnDivide);
        btnMult = findViewById(R.id.btnMultiply);
        btnSave = findViewById(R.id.btnSave);
        nameGrp = findViewById(R.id.groupName);

        readNameFromSharedPrefences();

        btnSave.setOnClickListener(this);

        btnAdd.setOnClickListener(this);

        btnSub.setOnClickListener(this);

        btnDiv.setOnClickListener(this);

        btnMult.setOnClickListener(this);

        btnLogs.setOnClickListener(this);

    }

    private String add(String numberOne, String numberTwo){
        if (numberOne.equals("") || numberTwo.isEmpty()) {
            Toast.makeText(this,"Please enter a valid number", Toast.LENGTH_SHORT).show();
            return null;
        }

        double a = Double.parseDouble(numberOne);
        double b = Double.parseDouble(numberTwo);
        //int b = Integer.parseInt(numberTwo);
        double result = a+b;
        return String.valueOf(result);
        //return Integer.toString(result); same way as above
    }

    private String subtract(String numberOne, String numberTwo){
        if (numberOne.equals("") || numberTwo.isEmpty()) {
            Toast.makeText(this,"Please enter a valid number", Toast.LENGTH_SHORT).show();
            return null;
        }
        double a = Double.parseDouble(numberOne);
        double b = Double.parseDouble(numberTwo);
        double result = a-b;
        return String.valueOf(result);
    }

    private String divide(String numberOne, String numberTwo){
        if (numberOne.equals("") || numberTwo.isEmpty()) {
            Toast.makeText(this,"Please enter a valid number", Toast.LENGTH_SHORT).show();
            return null;
        }
        double a = Double.parseDouble(numberOne);
        double b = Double.parseDouble(numberTwo);
        double result = a/b;
        return String.valueOf(result);

    }

    private String multiply(String numberOne, String numberTwo){
        if (numberOne.equals("") || numberTwo.isEmpty()) {
            Toast.makeText(this,"Please enter a valid number", Toast.LENGTH_SHORT).show();
            return null;
        }
        double a = Double.parseDouble(numberOne);
        double b = Double.parseDouble(numberTwo);
        double result = a*b;
        return String.valueOf(result);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSave:
                handleSetName();
                break;
            case R.id.btnAdd:
                handleAddClick();
                break;
            case R.id.btnSubtract:
                handleSubClick();
                break;
            case R.id.btnDivide:
                handleDivClick();
                break;
            case R.id.btnMultiply:
                handleMultClick();
                break;
            case R.id.btnLog:
                handleLogClick();
                break;
        }

    }

    private void handleSetName(){
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPreference",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",etName.getText().toString());
        editor.apply();
    }

    private void handleAddClick(){
        String num1 = etNumberOne.getText().toString();
        String num2 = etNumberTwo.getText().toString();
        String result = add(num1,num2);
        tvResult.setText(result);
        //log.add("Result of Addition: " + result);
        log.add("Add result of: "+num1 +" + " + num2 +" = " + result);
    }

    private void handleSubClick(){
        String num1 = etNumberOne.getText().toString();
        String num2 = etNumberTwo.getText().toString();
        String result = subtract(num1, num2);
        tvResult.setText(result);
        log.add("Subtract result of: "+num1 +" - " + num2 +" = " + result);
    }

    private void handleDivClick(){
        String num1 = etNumberOne.getText().toString();
        String num2 = etNumberTwo.getText().toString();
        String result = divide(num1, num2);
        tvResult.setText(result);
        log.add("Divide result of: "+num1 +" / " + num2 +" = " + result);
    }

    private void handleMultClick(){
        String num1 = etNumberOne.getText().toString();
        String num2 = etNumberTwo.getText().toString();
        String result = multiply(num1, num2);
        tvResult.setText(result);
        log.add("Multiply result of: "+num1 +" * " + num2 +" = " + result);
    }

    private void handleLogClick(){
        Intent intent = new Intent(MainActivity.this, LogsActivity.class);
        intent.putStringArrayListExtra("LogsResult", (ArrayList<String>)log);
        Room room = new Room("Training Room", 6);
        intent.putExtra("Room", room);
        startActivity(intent);
    }

    private void readNameFromSharedPrefences(){
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPreference",Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        if(!name.isEmpty()){
            nameGrp.setVisibility(View.GONE);

        }
        tvResult.setText(name);
    }
}
