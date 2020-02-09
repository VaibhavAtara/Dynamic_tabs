package com.example.dynamic_tabs;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dynamic_tabs.ui.main.PlaceholderFragment;

public class DeviceRecyclerViewAdapter extends RecyclerView.Adapter<DeviceRecyclerViewAdapter.DeviceViewHolder>{

    private Context context;
    private List<DeviceObject> Devices;
    View view;

    public DeviceRecyclerViewAdapter(Context context, List<DeviceObject> devices) {
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

        holder.textView.setText(Devices.get(position).getTopic());
        holder.imageView.setImageResource(Devices.get(position).getThumbnail());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                /* Change made by Anirudh Ramani
                Here I am going to create an alert dialog which will ask the user which room he wants
                to add the device to. I will use Spinner for this purpose
                 */
                 //********************************************************************
                Button asd=(Button)view1;
                asd.setText("Clicked");
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Select room");
                final EditText input = new EditText(view.getContext());
                final Spinner spinner=new Spinner(view.getContext());
                ArrayList<String>room_options=new ArrayList<>();
                input.setInputType(InputType.TYPE_CLASS_TEXT );
                builder.setView(spinner);
                Database_test database_test=new Database_test(view.getContext());
                Cursor fetch_all_rooms=database_test.fetch_all_rooms();
                while (fetch_all_rooms.moveToNext())
                {
                    room_options.add(fetch_all_rooms.getString(1));
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(view.getContext(),
                      R.layout.support_simple_spinner_dropdown_item,room_options);
                spinner.setAdapter(adapter);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String room_name= spinner.getSelectedItem().toString();
                        Database_test  database_test = new Database_test(view.getContext());
                        database_test.insert_in_room(Devices.get(position),room_name);
                        Toast.makeText(view.getContext(),"device inserted",Toast.LENGTH_SHORT).show();



                    }});
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();



                //**************************************************************





                /*Database_test  database_test = new Database_test(view.getContext());
                database_test.insert_in_room(Devices.get(position).getTitle(),
                        Devices.get(position).getDes(),Devices.get(position).getCategory(),
                        Devices.get(position).getThumbnail(),"hall");
                Toast.makeText(view.getContext(),"device inserted",Toast.LENGTH_SHORT).show();*/
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
