package com.android.lab1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.lab1.UpdateActivity;
import com.android.lab1.model.Covid19;
import com.android.lab1.R;
import com.android.lab1.SqliteHelper;
import com.android.lab1.adapter.AdapterListNv;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    private static ArrayList<Covid19> list;
    private static AdapterListNv adapter;
    private static SqliteHelper sql;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        sql = new SqliteHelper(getContext());
        list = sql.getAll();

        ListView listView = view.findViewById(R.id.listView);
        adapter = new AdapterListNv(getContext(), list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), UpdateActivity.class);
                intent.putExtra("nv", list.get(i));
                startActivity(intent);

                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    public static void updateUI() {
        list.clear();
        list.addAll(sql.getAll());

        adapter.notifyDataSetChanged();
    }
}
