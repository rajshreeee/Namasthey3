package com.example.rajshree.namasthey3;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.SearchView;



public class Home extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
      private com.google.firebase.auth.FirebaseAuth mAuth;
    private String userID;

    public com.miguelcatalan.materialsearchview.MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
ViewPager viewPager = (ViewPager)findViewById(R.id.vpPager) ;
TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
tabsPager tabsPager = new tabsPager(getSupportFragmentManager());
viewPager.setAdapter(tabsPager);
tabLayout.setupWithViewPager(viewPager);
            searchView = findViewById(R.id.search_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId()){
                            case R.id.edit_profile:
                                Intent ep = new Intent(getApplicationContext(), profiletodb.class);
                                startActivity(ep);
                                break;

                            case R.id.view_profile:
                                Intent ep1 = new Intent(getApplicationContext(),profileview.class);
                                startActivity(ep1);
                                break;

                            case R.id.add_contact:
                                Intent ep2 = new Intent(getApplicationContext(),addContact.class);
                                startActivity(ep2);
                                break;
                        }
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);



    }

     @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        android.view.MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        android.widget.SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


 android.widget.Toast.makeText(getApplicationContext(), query, android.widget.Toast.LENGTH_LONG).show();
            Intent ep1 = new Intent(getApplicationContext(),seacrh.class);
              ep1.putExtra("search_value", query.toString());
               startActivity(ep1);
                return true;


            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return true;

            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}

