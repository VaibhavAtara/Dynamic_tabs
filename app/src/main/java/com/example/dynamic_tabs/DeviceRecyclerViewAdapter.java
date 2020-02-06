package com.example.dynamic_tabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DeviceRecyclerViewAdapter extends RecyclerView.Adapter<DeviceRecyclerViewAdapter.DeviceViewHolder>{

    private Context context;
    private List<Book> Devices;
    View view;

    public DeviceRecyclerViewAdapter(Context context, List<Book> devices) {
        this.context = context;
        Devices = devices;
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.add_device_layout,parent,false);
        return new DeviceViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, final int position) {

        holder.textView.setText(Devices.get(position).getTitle());
        holder.imageView.setImageResource(Devices.get(position).getThumbnail());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database_test  database_test = new Database_test(view.getContext());
                Toast.makeText(view.getContext(),"HELLO",Toast.LENGTH_SHORT).show();
                database_test.insert_in_room(Devices.get(position).getTitle(),
                        Devices.get(position).getDes(),Devices.get(position).getCategory(),
                        Devices.get(position).getThumbnail(),"hall");
            }
        });

    }

    @Override
    public int getItemCount() {
        return Devices.size();
    }

    public static  class DeviceViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        Button button;
        CardView cardView;

        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = (TextView)itemView.findViewById(R.id.bookTitle);
            imageView = (ImageView)itemView.findViewById(R.id.bookImg);
            button = (Button)itemView.findViewById(R.id.addDevice);
            cardView = (CardView)itemView.findViewById(R.id.devicecard);
        }
    }
}
