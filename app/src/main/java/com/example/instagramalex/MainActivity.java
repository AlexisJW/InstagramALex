package com.example.instagramalex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG="MainActivity";
    private EditText etDescription;
    private ImageView ivPostImage;
    private Button btnCaptureImage;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDescription = findViewById(R.id.etDescription);
        ivPostImage = findViewById(R.id.ivPostImage);
        btnCaptureImage = findViewById(R.id.btnCaptureImage);
        btnSubmit = findViewById(R.id.btnSubmit);

        requetePost();
    }

    private void requetePost() {
        // Define the class we would like to query
        final ParseQuery<Post> postquery = ParseQuery.getQuery(Post.class);
        postquery.include(Post.KEY_USER);
        postquery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Error with Query");
                    e.printStackTrace();
                }
                for (int i = 0; i < objects.size(); i++){
                    Log.d(TAG, "POST: " + objects.get(i).getDescrption() + " USERNAME: " + objects.get(i).getUser().getUsername());
                }
            }
        });
    }
}
