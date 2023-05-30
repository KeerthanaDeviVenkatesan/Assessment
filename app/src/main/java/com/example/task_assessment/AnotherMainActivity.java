package com.example.task_assessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;

public class AnotherMainActivity extends AppCompatActivity {
    private TabLayout tabLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_main);

        tabLayout=findViewById(R.id.tab_layout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position=tab.getPosition();
                switch (position){
                    case 0:
                        Fragment(new RandomDataFragment());
                        break;
                    case 1:
                        Fragment(new LocalStorageFragment());
                        break;
                    case 2:
                        Bundle bundle=getIntent().getExtras();
                        if (bundle!=null){
                            UserDetailsFragment userDetailsFragment=new UserDetailsFragment();
                            userDetailsFragment.setArguments(bundle);
                            Fragment(userDetailsFragment);
                        }
                        break;

                }
            }




            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.getTabAt(0).setIcon(R.drawable.baseline_data_array_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.baseline_sd_storage_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.baseline_storage_24);

        Fragment(new RandomDataFragment());


    }
    private void Fragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment).commit();
    }
}

