package com.exemplo.meucarro;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            //calling the method displayselectedscreen and passing the id of selected menu
            displaySelectedScreen(item.getItemId());
            //make this method blank
            return true;
        }

        private void displaySelectedScreen(int itemId) {

            //creating fragment object
            Fragment fragment = null;

            //initializing the fragment object which is selected
            switch (itemId) {
                case com.exemplo.meucarro.R.id.menu1:
                    fragment = new FragmentCarro();
                    break;
                case com.exemplo.meucarro.R.id.menu2:
                    fragment = new FragmentSos();
                    break;
                case com.exemplo.meucarro.R.id.menu3:
                    fragment = new FragmentCombustivel();
                    break;
            }

            //replacing the fragment
            if (fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(com.exemplo.meucarro.R.id.container, fragment);
                ft.commit();
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.exemplo.meucarro.R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(com.exemplo.meucarro.R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}