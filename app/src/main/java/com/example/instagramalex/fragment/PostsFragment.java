package com.example.instagramalex.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramalex.Post;
import com.example.instagramalex.PostAdapter;
import com.example.instagramalex.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {
    protected final String TAG ="PostsFragment";
    private RecyclerView rvPost;
    protected PostAdapter adapter;
    protected List<Post> mposts;
    private Button btnLogout;
    ProgressBar progressBarb;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvPost = view.findViewById(R.id.rvPost);

        //create the data source
        mposts = new ArrayList<>();
        //create the adapter
        adapter = new PostAdapter(getContext(), mposts);
        //set the adapter on the recycleVew
        rvPost.setAdapter(adapter);
        //set the layout manager on the recycleVew
        rvPost.setLayoutManager(new LinearLayoutManager(getContext()));
        requetePost();
    }

    protected void requetePost() {
        // Define the class we would like to query
        final ParseQuery<Post> postquery = ParseQuery.getQuery(Post.class);
        postquery.include(Post.KEY_USER);
        postquery.setLimit(20);
        postquery.addDescendingOrder(Post.KEY_CREATED_AT);
        postquery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Error with Query");
                    e.printStackTrace();
                }
                mposts.addAll(objects);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < objects.size(); i++){
                    Log.d(TAG, "POST: " + objects.get(i).getDescrption() + " USERNAME: " + objects.get(i).getUser().getUsername() +" TIME" +objects.get(i).getcreatedAt());
                }
            }
        });
    }
}