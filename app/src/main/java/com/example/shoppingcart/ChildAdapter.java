package com.example.shoppingcart;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by J on 2017/10/25.
 */

public class ChildAdapter extends  RecyclerView.Adapter<ChildAdapter.MyChildeViewHolder>{

    private Context context;
    private List<ChildBean> childList;

    public ChildAdapter(Context context, List<ChildBean> childList) {
        this.context = context;
        this.childList = childList;
    }

    @Override
    public MyChildeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.child_layout, null);
        MyChildeViewHolder myChildeViewHolder = new MyChildeViewHolder(inflate);
        return myChildeViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyChildeViewHolder holder, final int position) {
        holder.cb_child.setChecked(childList.get(position).isChecked);
        holder.tv_name.setText(childList.get(position).name);
        holder.tv_price.setText(childList.get(position).pricce+"");
        holder.tv_num.setText(childList.get(position).num+"");

        holder.cb_child.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (onChildItemClickListener!=null)
                {
                    onChildItemClickListener.onChildItemClickListener(isChecked,position);
                }
            }
        });
    }
    private OnChildItemClickListener onChildItemClickListener;

    public void setOnChildItemClickListener(OnChildItemClickListener onChildItemClickListener) {
        this.onChildItemClickListener = onChildItemClickListener;
    }

    public interface OnChildItemClickListener{
        void onChildItemClickListener(boolean isChecked, int position);
    }

    @Override
    public int getItemCount() {
        return childList.size();
    }

    public  class MyChildeViewHolder extends RecyclerView.ViewHolder{
            private TextView tv_price;
            private TextView tv_num;
        private CheckBox cb_child;
        private TextView tv_name;
        public MyChildeViewHolder(View itemView) {
            super(itemView);
            tv_price=  itemView.findViewById(R.id.price_item);
            tv_num=  itemView.findViewById(R.id.num_item);
            tv_name=  itemView.findViewById(R.id.name_item);
            cb_child=  itemView.findViewById(R.id.check_item);
        }
    }
}
