package com.example.hao.smartlightkey;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hao on 2018/4/10.
 */

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.ViewHolder> {
    private List<String> device_name;


    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        void onClick( int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView edit;

        public ViewHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.device_name);
            edit = (TextView) view.findViewById(R.id.device_edit);
        }
    }
    public DeviceListAdapter(List<String> DeviceName){
        device_name = DeviceName;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String s = device_name.get(position);
        holder.name.setText(s);

        if( mOnItemClickListener!= null){
            holder.edit.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return device_name.size();
    }
}
