package com.example.instagramalex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.viewHolder>{
  private Context context;
  private List<Post> posts;

  public PostAdapter(Context context, List<Post> posts) {
    this.context = context;
    this.posts = posts;
  }

  @NonNull
  @Override
  public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.items, parent, false);
    return new viewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    Post post = posts.get(position);
    holder.bind(post);
  }

  @Override
  public int getItemCount() {
    return posts.size();
  }

  class viewHolder extends RecyclerView.ViewHolder{

    private TextView tvHandle;
    private ImageView ivImage;
    private TextView tvDescription;
    public viewHolder(@NonNull View itemView) {
      super(itemView);
      tvHandle = itemView.findViewById(R.id.tvHandle);
      ivImage = itemView.findViewById(R.id.ivImage);
      tvDescription = itemView.findViewById(R.id.tvDescription);
    }

    public void bind(Post post){
      // bind the view element to the post
      tvHandle.setText(post.getUser().getUsername());
      ParseFile image = post.getImage();
      if (image != null){
        Glide.with(context).load(image.getUrl()).into(ivImage);
      }
      tvDescription.setText(post.getDescrption());
    }
  }
}
