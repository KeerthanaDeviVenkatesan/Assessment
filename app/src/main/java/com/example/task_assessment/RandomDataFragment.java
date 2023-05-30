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
import android.widget.TextView;


import com.example.task_assessment.model.CategoriesItem;
import com.example.task_assessment.model.RandomDataResponse;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RandomDataFragment extends Fragment {
    private Api api;
    private RecyclerView recyclerView;
  private LinearLayoutManager layoutManager;
  private List<CategoriesItem> randomDataList;
    RandomDataAdapter randomDataAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_random_data, container, false);

     recyclerView=view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();

    }
private void getData(){
    Retrofit retrofit=new Retrofit.Builder().baseUrl("http://www.themealdb.com/api/json/").addConverterFactory(GsonConverterFactory.create()).build();
    api=retrofit.create(Api.class);
    Call<RandomDataResponse> call=api.randomDataCall();
    call.enqueue(new Callback<RandomDataResponse>() {
        @Override
        public void onResponse(Call<RandomDataResponse> call, Response<RandomDataResponse> response) {
            randomDataList = response.body().getCategories();

            if (response.isSuccessful()) {

                Gson gson=new Gson();
                String jsonData=gson.toJson(randomDataList);

                SharedPreferences sharedPreferences= requireContext().getSharedPreferences("share",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putString("data",jsonData);
                editor.apply();

                 randomDataAdapter=new RandomDataAdapter(randomDataList);
                layoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(randomDataAdapter);
                randomDataAdapter.setOnClickListener(new RandomDataAdapter.OnClickListener() {
                    @Override
                    public void onClick(int position, List<CategoriesItem> randomDataList) {
                        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(getActivity());
                        View bottomSheetView=LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet,null);
                        bottomSheetDialog.setContentView(bottomSheetView);
                        TextView descriptionTextView=bottomSheetView.findViewById(R.id.text);

                        String description=randomDataList.get(position).getStrCategoryDescription();
                        descriptionTextView.setText(description);
                        bottomSheetDialog.show();
                    }
                });
            }else{

                System.out.println("Not Success ");
            }

        }

        @Override
        public void onFailure(Call<RandomDataResponse> call, Throwable t) {
            System.out.println("Failure "+ t.getMessage());

        }
    });

}



}



