package learn.mehadihossain.agecalculator;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DatePickerFragment.DateSetCommunicator,View.OnTouchListener{
    private EditText dateOfbirth;
    private EditText todaysDate;
    private boolean isDatOfBirthEditTextActive = false;
    private AgeCalculate ageCalculate;
    private TextView yearsTextView,monthsTextView,daysTextView;
    private String days,months,years;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState==null){
            days = "00";
            months = "00";
            years = "00";
        }else {
            days = savedInstanceState.getString("days","00");
            months = savedInstanceState.getString("months","00");
            years = savedInstanceState.getString("years","00");
        }

        dateOfbirth = (EditText) findViewById(R.id.dateOfBirth);
        todaysDate = (EditText) findViewById(R.id.todaysDate);
        dateOfbirth.setOnTouchListener(this);
        todaysDate.setOnTouchListener(this);
        //dateOfbirth.addTextChangedListener(new DateFormatWatcher(dateOfbirth));
        //todaysDate.addTextChangedListener();

        yearsTextView = (TextView) findViewById(R.id.yearsTextView);
        monthsTextView = (TextView) findViewById(R.id.monthsTextView);
        daysTextView = (TextView) findViewById(R.id.daysTextView);

        yearsTextView.setText(years);
        monthsTextView.setText(months);
        daysTextView.setText(days);

        ageCalculate = new AgeCalculate();

    }

    public void test(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");

    }

    public void getDate(View view) {
        if(view.getId()==R.id.dateOfBirth){
            isDatOfBirthEditTextActive = true;
        }
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    public void setDate(int year, int month, int day) {
        if(isDatOfBirthEditTextActive){
            isDatOfBirthEditTextActive = false;
            dateOfbirth.setText(day+"/"+month+"/"+year);
        }else{
            todaysDate.setText(day+"/"+month+"/"+year);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
                view.onTouchEvent(motionEvent);
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm!=null){
                    imm.hideSoftInputFromWindow(view.getWindowToken(),0);
                }
                return true;
            }

    public void calculateAge(View view) {
        String birth[] = dateOfbirth.getText().toString().split("/");
        String today[] = todaysDate.getText().toString().split("/");
        ageCalculate.setBirth(Integer.parseInt(birth[0]),Integer.parseInt(birth[1]),Integer.parseInt(birth[2]));
        ageCalculate.setTo(Integer.parseInt(today[0]),Integer.parseInt(today[1]),Integer.parseInt(today[2]));

        int[] age = ageCalculate.CalculateAge();
        if(age[0]==0 && age[1]==0 && age[2]==0) {
            Toast.makeText(this,"Incorrect date for calculation",Toast.LENGTH_SHORT).show();
        }else {
            days = Integer.toString(age[0]);
            months = Integer.toString(age[1]);
            years = Integer.toString(age[2]);
            yearsTextView.setText(years);
            monthsTextView.setText(months);
            daysTextView.setText(days);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("days",days);
        outState.putString("months",months);
        outState.putString("years",years);
    }
}
