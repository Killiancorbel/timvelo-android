package com.timvelo.app.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.timvelo.app.R;
import com.timvelo.app.ui.base.BaseActivity;
import com.timvelo.app.ui.raceList.RaceFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation) BottomNavigationView nav;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fm = getSupportFragmentManager();
                for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                switch (item.getItemId()) {
                    case R.id.action_races:
                        fm.beginTransaction().replace(R.id.main_content, RaceFragment.newInstance())
                                .commit();
                        break;
                    case R.id.action_pronos:
                        break;
                    case R.id.action_classement:
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });

        nav.setSelectedItemId(R.id.action_races);
        getSupportFragmentManager().beginTransaction().add(R.id.main_content, RaceFragment.newInstance()).commit();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }
}
