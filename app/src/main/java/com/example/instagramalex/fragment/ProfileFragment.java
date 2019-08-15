package com.example.instagramalex.fragment;

import android.util.Log;

import com.example.instagramalex.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {
    @Override
    protected void requetePost() {
        // Define the class we would like to query
        final ParseQuery<Post> postquery = ParseQuery.getQuery(Post.class);
        postquery.include(Post.KEY_USER);
        postquery.setLimit(20);
        postquery.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
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
                    Log.d(TAG, "POST: " + objects.get(i).getDescrption() + " USERNAME: " + objects.get(i).getUser().getUsername());
                }
            }
        });
    }
}
