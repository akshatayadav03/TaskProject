package com.nimapinfotech.machinetask.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimapinfotech.machinetask.BR;
import com.nimapinfotech.machinetask.R;
import com.nimapinfotech.machinetask.database.Database;
import com.nimapinfotech.machinetask.databinding.RecordListBinding;
import com.nimapinfotech.machinetask.model.Record;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder> {
    ArrayList<Record> recordArrayList;
    Context context;
    public ViewAdapter(Context context,ArrayList<Record> recordArrayList){
        this.context = context;
        this.recordArrayList = recordArrayList;
    }
    @NonNull
    @Override
    public ViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecordListBinding recordListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.record_list,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(recordListBinding);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter.MyViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            final Record record = recordArrayList.get(position);
            myViewHolder.bind(record);
            Database database = Database.getInstance(context);
            database.insertImage(record.getMainImageURL());
            Log.d("recordImage",record.getMainImageURL());
            database.closeDB();

            myViewHolder.recordListBinding.favImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myViewHolder.recordListBinding.favImageView.setBackgroundResource(R.drawable.ic_heart_fill);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return recordArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        RecordListBinding recordListBinding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public MyViewHolder(RecordListBinding recordListBindings){
            super(recordListBindings.getRoot());
            this.recordListBinding = recordListBindings;
        }

        public void bind(Object object){
            recordListBinding.setVariable(BR.Record,object);
            recordListBinding.executePendingBindings();
        }
    }
}
