package com.android.lab1.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.lab1.AddActivity;
import com.android.lab1.model.Covid19;
import com.android.lab1.R;
import com.android.lab1.SqliteHelper;
import com.android.lab1.adapter.AdapterListNv;

import java.util.ArrayList;
import java.util.Calendar;

public class SearchFragment extends Fragment {
    private ArrayList<Covid19> listCovid = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);

        EditText et1 = view.findViewById(R.id.startEt);
        EditText et2 = view.findViewById(R.id.endEt);
        ListView  listView = view.findViewById(R.id.listView);
        Button searchBtn = view.findViewById(R.id.searchBtn);
//        TextView sumTv = view.findViewById(R.id.sumTv);
        TextView thongKe = view.findViewById(R.id.resultTv);

        AdapterListNv adapter = new AdapterListNv(getContext(), listCovid);
        listView.setAdapter(adapter);

        SqliteHelper sql = new SqliteHelper(getContext());

        et1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                        et1.setText(day + "/" + month + "/" + year);
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        et2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                        et2.setText(day + "/" + month + "/" + year);
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String start = et1.getText().toString();
                String end = et2.getText().toString();
                listCovid.clear();
                listCovid.addAll(sql.getByDate(start, end));

                for (Covid19 c : listCovid) {
                    System.out.println(c.getNgayXuatHien());
                }

                AdapterListNv adapter1 = new AdapterListNv(getContext(), listCovid);
                listView.setAdapter(adapter1);
                adapter1.notifyDataSetChanged();


//                adapter.notifyDataSetChanged();
//                adapter.notifyDataSetChanged();
//                listCovid.clear();


            }
        });

        //tk
        listCovid.clear();
        listCovid = sql.getAll();

        ArrayList<Covid19> tmp = new ArrayList<>();

        for (Covid19 c : listCovid) {
//            System.out.println(c.getNgayXuatHien().split("/")[0]);

            if (c.getNgayXuatHien().split("/")[0].equals("2022")) {
//                System.out.println(c.getNgayXuatHien());
                tmp.add(c);
            }
        }

        int[] res = new int[13];

        for (Covid19 c : tmp) {
            int thang = Integer.parseInt(c.getNgayXuatHien().split("/")[1]);

            res[thang]++;
        }

        String s = "";
        for (int i=1; i<13; i++) {
            s += "Tháng " + i + ": " + res[i] + " chủng mới\n";
        }

        thongKe.setText(s);

        listCovid.clear();

        return view;
    }

}
