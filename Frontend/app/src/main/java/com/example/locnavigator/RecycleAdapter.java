package com.example.locnavigator;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleHolder> {

    private ArrayList<location> mEa;
    public class RecycleHolder extends RecyclerView.ViewHolder
    {
        private TextView turn,duration,distance,area;
        private ImageView t2;
        public RecycleHolder( View itemView) {
            super(itemView);
            turn=  (TextView)itemView.findViewById(R.id.direction);
            duration=  (TextView)itemView.findViewById(R.id.duration);
            distance=  (TextView)itemView.findViewById(R.id.distance);
            area=  (TextView)itemView.findViewById(R.id.area);

            t2 = (ImageView) itemView.findViewById(R.id.op);
        }
    }
    public RecycleAdapter(ArrayList<location> a)
    {
        mEa=a;
    }

    @Override
    public RecycleHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_received,parent,false);
        RecycleHolder recycleHolder = new RecycleHolder(view);
        return recycleHolder;
    }

    @Override
    public void onBindViewHolder(RecycleHolder holder, int position) {
        location b = mEa.get(position);
        holder.turn.setText("Go "+b.getTurn());
        holder.duration.setText("Duration : "+b.getDuration() + " min");
        holder.distance.setText("Distance : "+b.getDistance() + " m");
        holder.area.setText("Area : "+b.getPlace_jname());
        holder.t2.setBackgroundResource(R.drawable.straight);
        holder.t2.setRotation(0);
        if(b.getTurn().equals("left"))
        {
            holder.t2.setBackgroundResource(R.drawable.left);
        }
        if(b.getTurn().equals("right"))
        {
            holder.t2.setBackgroundResource(R.drawable.right);
        }
        if(b.getTurn().equals("straight"))
        {
            holder.t2.setBackgroundResource(R.drawable.straight);
        }
        if(b.getTurn().equals("slight left"))
        {
            holder.t2.setBackgroundResource(R.drawable.slightright);
            holder.t2.setRotation(-90);

        }
        if(b.getTurn().equals("slight right"))
        {
            holder.t2.setBackgroundResource(R.drawable.slightright);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mEa.size();
    }

}
