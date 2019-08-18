package com.example.instagramalex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.instagramalex.fragment.ComposeFragment;
import com.example.instagramalex.fragment.Logout;
import com.example.instagramalex.fragment.PostsFragment;
import com.example.instagramalex.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

  private final String TAG="MainActivity";
  private BottomNavigationView bottomNavigatonView;
  ProgressBar progressBarb;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final FragmentManager fragmentManager = getSupportFragmentManager();

    bottomNavigatonView = findViewById(R.id.bottom_navigation);
    progressBarb = (ProgressBar) findViewById(R.id.pbLoading);

    bottomNavigatonView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
          case R.id.action_post:
            fragment = new PostsFragment();
            break;
          case R.id.action_compose:
            fragment = new ComposeFragment();
            break;
          case R.id.action_logout:
            fragment = new Logout();

            break;

            case R.id.action_user:
              fragment = new ProfileFragment();
          default:
            break;
        }
        fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
        return true;
      }
    });
    //set default selection
    bottomNavigatonView.setSelectedItemId(R.id.action_post);
  }
}