package com.example.instagramalex.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.instagramalex.LoginActivity;
import com.example.instagramalex.R;
import com.parse.ParseUser;

import static android.view.View.VISIBLE;

public class Logout extends Fragment {
    private Button btnLogout;
    ProgressBar progressBarb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.logout_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnLogout = (Button) view.findViewById(R.id.btnLogout);
        progressBarb = (ProgressBar) view.findViewById(R.id.pbLoading);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBarb.setVisibility(VISIBLE);
                logout();
            }
        });
    }

    public void logout(){
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }
}
