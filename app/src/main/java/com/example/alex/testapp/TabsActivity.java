package com.example.alex.testapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.alex.testapp.activity.TabsAdapter;
import com.example.alex.testapp.activity.fragments.SaveProductFragment;
import com.example.alex.testapp.activity.fragments.SearchFragment;

public class TabsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);


        initViewPager();
        initResources();
    }

    private void getUri() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                "https://openapi.etsy.com/v2/listings/active"
                        + "?api_key=" + Constants.KEY ));
        startActivity(intent);
    }

    private void initResources(){


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
