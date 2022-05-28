package com.android.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.lab1.fragment.ListFragment;
import com.android.lab1.model.Covid19;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    private EditText nameEt, dateEt, slEt;
    private CheckBox cb1, cb2, cb3, cb4, cb5;
    private Button addBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameEt = findViewById(R.id.nameEt);
        dateEt = findViewById(R.id.dateEt);
        slEt = findViewById(R.id.slEt);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        cb5 = findViewById(R.id.cb5);
        addBtn = findViewById(R.id.addBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                        dateEt.setText(day + "/" + month + "/" + year);
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEt.getText().toString();
                String date = dateEt.getText().toString();
                String sl = slEt.getText().toString();

                String cauTruc = "";
                if (cb1.isChecked())
                    cauTruc += "arn ";
                if (cb2.isChecked())
                    cauTruc += "protein-s ";
                if (cb3.isChecked())
                    cauTruc += "protein-n ";

                String vacxin = "";
                if (cb4.isChecked())
                    vacxin += "co ";
                if (cb5.isChecked())
                    vacxin += "khong ";

                if (name.equals("") && date.equals("") && sl.equals(""))
                    Toast.makeText(AddActivity.this, "Yeu cau dien day du cac truong.", Toast.LENGTH_SHORT).show();
                else if (!date.matches("[\\d/]+") || !sl.matches("\\d+"))
                    Toast.makeText(AddActivity.this, "Du lieu khong hop le.", Toast.LENGTH_SHORT).show();
                else {
                    SqliteHelper sqlite = new SqliteHelper(getApplicationContext());
                    sqlite.add(new Covid19(1, name, cauTruc, date, vacxin, Integer.parseInt(sl)));
                    ListFragment.updateUI();
                    Toast.makeText(AddActivity.this, "Them thanh cong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}