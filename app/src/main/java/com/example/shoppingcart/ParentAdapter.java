package com.example.shoppingcart;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * @author -              时志邦
 * @date -                2017/10/25
 * @description -        TODO
 */
public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.MyPaintViewHolder> {
    private List<ParntBean> paintList ;
    private Context context;

    public ParentAdapter(List<ParntBean> paintList, Context context) {
        this.paintList = paintList;
        this.context = context;
    }

    @Override
    public MyPaintViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.paint_layout, null);
        MyPaintViewHolder viewHolder = new MyPaintViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyPaintViewHolder holder, final int position) {
        boolean flag=true;
        for (ChildBean childBean : paintList.get(position).childList) {
            if (childBean.isChecked==false)
                flag=false;
        }
            holder.mCbPaint.setChecked(flag);
        holder.rv_child.setLayoutManager(new LinearLayoutManager(context));
        final ChildAdapter childAdapter = new ChildAdapter(context, paintList.get(position).childList);
        holder.rv_child.setAdapter(childAdapter);
        holder.tv_name.setText(paintList.get(position).shopname);
        holder.mCbPaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null)
                {
                    onItemClickListener.onParentItemClickListener(holder.mCbPaint.isChecked(),position,childAdapter);
                    holder.mCbPaint.setChecked(holder.mCbPaint.isChecked());
                }
            }
        });
        childAdapter.setOnChildItemClickListener(new ChildAdapter.OnChildItemClickListener() {
            @Override
            public void onChildItemClickListener(boolean isChecked, int p) {
                if (onItemClickListener!=null)
                {
                    paintList.get(position).childList.get(p).isChecked=isChecked;
                    boolean flag=true;
                    for (ChildBean childBean : paintList.get(position).childList) {
                        if (childBean.isChecked==false)
                            flag=false;
                    }
                    holder.mCbPaint.setChecked(flag);
                    onItemClickListener.onChildItemClickListener(flag,isChecked,position,p);
                }
            }
        });
    }


    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onChildItemClickListener(boolean parentIsCheck, boolean isChecked, int parentPosition, int childPosition);
        void onParentItemClickListener(boolean isChecked, int position,ChildAdapter childAdapter);
    }

    @Override
    public int getItemCount() {
        return paintList.size();
    }



    public class MyPaintViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_name;
        private CheckBox mCbPaint;
        private RecyclerView rv_child;
        public MyPaintViewHolder(View itemView) {
            super(itemView);
            mCbPaint = itemView.findViewById(R.id.cb_paint);
            tv_name = itemView.findViewById(R.id.tv_name_paint);
            rv_child = itemView.findViewById(R.id.rv_child);
        }
    }
}
