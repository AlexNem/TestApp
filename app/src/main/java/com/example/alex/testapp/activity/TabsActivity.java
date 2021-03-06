package com.example.alex.testapp.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.alex.testapp.R;
import com.example.alex.testapp.activity.fragments.SaveProductFragment;
import com.example.alex.testapp.activity.fragments.SearchFragment;

public class TabsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);


        initViewPager();
    }


    private void initViewPager(){
        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout =  findViewById(R.id.tab_layout);
        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
        adapter.addFragment(new SearchFragment(), "Search");
        adapter.addFragment(new SaveProductFragment(), "Save Product");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
