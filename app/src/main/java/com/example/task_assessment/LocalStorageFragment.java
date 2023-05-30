package com.example.task_assessment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.task_assessment.model.CategoriesItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class LocalStorageFragment extends Fragment {
    private RecyclerView recyclerView;
    RandomDataAdapter randomDataAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_storage, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences= requireContext().getSharedPreferences("share", Context.MODE_PRIVATE);
        String jsonData=sharedPreferences.getString("data","");

        Gson gson=new Gson();
        Type listType=new TypeToken<List<CategoriesItem>>() {}.getType();
        List<CategoriesItem> storedData=gson.fromJson(jsonData,listType);

        randomDataAdapter=new RandomDataAdapter(storedData);
        recyclerView.setAdapter(randomDataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));



    }
}