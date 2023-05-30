package com.example.task_assessment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserDetailsFragment extends Fragment {
    TextView textView1,textView2,textView3;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_user_details, container, false);

        Bundle bundle=getArguments();
        if (bundle!=null){
            String name=bundle.getString("name");
          textView1=view.findViewById(R.id.name1);
          textView1.setText(name);
          String email=bundle.getString("email");
          textView2=view.findViewById(R.id.email1);
          textView2.setText(email);
          String mobile=bundle.getString("mobile");
          textView3=view.findViewById(R.id.mobile1);
          textView3.setText(mobile);
        }


        return view;
    }


}