package com.android.lab1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.lab1.SqliteHelper;
import com.android.lab1.model.Covid19;
import com.android.lab1.R;

import java.util.ArrayList;

public class AdapterListNv extends ArrayAdapter<Covid19> {
    private Context context;
    private ArrayList<Covid19> list;

    public AdapterListNv(@NonNull Context context, ArrayList<Covid19> list) {
        super(context, R.layout.item, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, parent, false);

        TextView nameTv = view.findViewById(R.id.nameTv);
        TextView cauTrucTv = view.findViewById(R.id.cauTrucTv);
        TextView dateTv = view.findViewById(R.id.dateTv);
        TextView vacxinTv = view.findViewById(R.id.vacxinTv);
        TextView numTv = view.findViewById(R.id.numTv);
        TextView idTv = view.findViewById(R.id.idTv);
        Button removeBtn = view.findViewById(R.id.removeBtn);

        Covid19 nv = list.get(position);
        nameTv.setText(nv.getName());
        cauTrucTv.setText(nv.getCauTruc());
        dateTv.setText(nv.getNgayXuatHien());
        vacxinTv.setText(nv.getVacxin() + " vacxin");
        numTv.setText(String.valueOf(nv.getSoLuong()) + " quoc gia");
        idTv.setText(String.valueOf(nv.getId()));

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SqliteHelper sql = new SqliteHelper(getContext());
                sql.delete(nv);

                list.remove(nv);

                notifyDataSetChanged();
            }
        });

        return view;
    }
}
