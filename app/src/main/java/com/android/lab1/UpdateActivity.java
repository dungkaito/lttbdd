package com.android.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.lab1.fragment.ListFragment;
import com.android.lab1.model.Covid19;

import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {
    private EditText nameEt, dateEt, slEt;
    private CheckBox cb1, cb2, cb3, cb4, cb5;
    private Button okBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();
        Covid19 nv = (Covid19) intent.getExtras().getSerializable("nv");

        nameEt = findViewById(R.id.nameEt);
        dateEt = findViewById(R.id.dateEt);
        slEt = findViewById(R.id.slEt);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        cb5 = findViewById(R.id.cb5);
        okBtn = findViewById(R.id.okBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        nameEt.setText(nv.getName());
        dateEt.setText(nv.getNgayXuatHien());
        slEt.setText(String.valueOf(nv.getSoLuong()));
        String cauTruc = nv.getCauTruc();
        if (cauTruc.contains("arn"))
            cb1.setChecked(true);
        if (cauTruc.contains("protein-s"))
            cb2.setChecked(true);
        if (cauTruc.contains("protein-n"))
            cb3.setChecked(true);

        String vacxin = nv.getVacxin();
        if (vacxin.contains("co"))
            cb4.setChecked(true);
        if (vacxin.contains("khong"))
            cb5.setChecked(true);


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
                DatePickerDialog dialog = new DatePickerDialog(UpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                        dateEt.setText(day + "/" + month + "/" + year);
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(UpdateActivity.this, "Yeu cau dien day du cac truong.", Toast.LENGTH_SHORT).show();
                else if (!date.matches("[\\d/]+") || !sl.matches("\\d+"))
                    Toast.makeText(UpdateActivity.this, "Du lieu khong hop le.", Toast.LENGTH_SHORT).show();
                else {
                    SqliteHelper sqlite = new SqliteHelper(getApplicationContext());
                    sqlite.update(new Covid19(nv.getId(), name, cauTruc, date, vacxin, Integer.parseInt(sl)));
                    ListFragment.updateUI();
                    Toast.makeText(UpdateActivity.this, "Sua thanh cong!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}