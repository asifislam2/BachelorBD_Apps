package com.bachelor.bachelorbd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Dashbord extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FrameLayout fram_lay;
    DrawerLayout drawer_lay;

    MaterialToolbar materialToolbar;

    NavigationView navigation_view;
    Spinner spinner;
    ArrayAdapter<String> adapter;

    MeowBottomNavigation bottomNavigation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        drawer_lay = findViewById(R.id.drawer_lay);
        materialToolbar = findViewById(R.id.materialToolbar);
        navigation_view = findViewById(R.id.navigation_view);
        fram_lay = findViewById(R.id.fram_lay);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fram_lay, new HomeFragment());
        transaction.commit();
        ((FrameLayout) findViewById(R.id.fram_lay)).removeAllViews();


        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_location_on_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_settings_24));

        bottomNavigation.show(1, true);

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {

                if (model.getId()==1){

                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.add(R.id.fram_lay, new HomeFragment());
                    transaction.commit();
                    ((FrameLayout) findViewById(R.id.fram_lay)).removeAllViews();

                } else if (model.getId()==2) {

                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.add(R.id.fram_lay, new Fragment_mother());
                    transaction.commit();
                    ((FrameLayout) findViewById(R.id.fram_lay)).removeAllViews();

                } else if (model.getId()==3) {

                    Toast.makeText(Dashbord.this, "id three", Toast.LENGTH_SHORT).show();

                }

                return null;
            }
        });




        // spinner code start here
        spinner = findViewById(R.id.spinner);
        String[] pName = {"ঢাকা","ধানমন্ডি","মোহাম্মদপুর","কল্যানপুর","শ্যামলী", "কলাবাগান"};
        adapter = new ArrayAdapter<>(Dashbord.this, android.R.layout.simple_list_item_1, pName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //spinner code end here



        //actionbar toogle code start here

        ActionBarDrawerToggle toggle  = new ActionBarDrawerToggle(
                Dashbord.this, drawer_lay, materialToolbar,R.string.Close_Drawer,R.string.Open_Drawer
        );
        drawer_lay.addDrawerListener(toggle);

        // toogle code end here





        firebaseAuth = FirebaseAuth.getInstance();

        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId()==R.id.log_out){
                    firebaseAuth.signOut();
                    Intent intent = new Intent(Dashbord.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                return false;
            }
        });



    }
}