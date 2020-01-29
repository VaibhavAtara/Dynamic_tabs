package com.example.dynamic_tabs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Book> mydata;

    public RecyclerViewAdapter(Context context, List<Book> mydata) {
        this.context = context;
        this.mydata = mydata;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

            holder.textView.setText(mydata.get(position).getTitle());
            holder.imageView.setImageResource(mydata.get(position).getThumbnail());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,book_act.class);
                    intent.putExtra("Title",mydata.get(position).getTitle());
                    intent.putExtra("Category",mydata.get(position).getCategory());
                    intent.putExtra("Description",mydata.get(position).getDes());
                    intent.putExtra("Thumbnail",mydata.get(position).getThumbnail());
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return mydata.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = (TextView)itemView.findViewById(R.id.book_title);
            imageView = (ImageView)itemView.findViewById(R.id.book_card);
            cardView = (CardView)itemView.findViewById(R.id.card);
        }
    }
}
