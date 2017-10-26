package com.example.shoppingcart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ParentAdapter.OnItemClickListener {

    private RecyclerView rv_father;
    private CheckBox cb_check_all;
    private TextView sunprice;
    private LinearLayout linearLayout;
    private List<ParntBean> paintList = new ArrayList<>();
    private DecimalFormat decimalFormat;
    private ParentAdapter parentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        rv_father.setLayoutManager(new LinearLayoutManager(this));
        parentAdapter = new ParentAdapter(paintList, this);
        rv_father.setAdapter(parentAdapter);
        parentAdapter.setOnItemClickListener(this);

    }

    private void initData() {
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            List<ChildBean> childList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                childList.add(new ChildBean("商品" + i, 100, 2, false));
            }
            paintList.add(new ParntBean(childList, false, "商家" + i));
        }
        ;
    }

    private void initView() {
        rv_father = (RecyclerView) findViewById(R.id.rv_father);
        cb_check_all = (CheckBox) findViewById(R.id.cb_check_all);
        sunprice = (TextView) findViewById(R.id.sunprice);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        cb_check_all.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        sum=0;
        sumprice=0;
        for (ParntBean parntBean : paintList) {
            for (ChildBean childBean : parntBean.childList) {
                if (cb_check_all.isChecked())
                {
                    sum++;
                    sumprice+=childBean.num*
                            childBean.pricce;
                    if (childBean.isChecked==false)
                    {
                        childBean.isChecked=true;
                    }
                }else {
                    if (childBean.isChecked)
                    {
                        childBean.isChecked=false;
                    }
                }
            }
        }
        parentAdapter.notifyDataSetChanged();
        sunprice.setText("个数:"+sum+" 价格:"+sumprice);
    }
    public void check()
    {
        boolean flag=true;
        for (ParntBean parntBean : paintList) {
            for (ChildBean childBean : parntBean.childList) {
                if (childBean.isChecked==false)
                    flag=false;

            }
        }
        cb_check_all.setChecked(flag);
        sunprice.setText("个数:"+sum+" 价格:"+sumprice);
    }
    private int sum=0;
    private int sumprice=0;

    @Override
    public void onChildItemClickListener(boolean flag, boolean isChecked, int parentPosition, int childPosition) {
        paintList.get(parentPosition).isParntChecked=flag;
        if (isChecked)
        {
            sum++;
            sumprice+=paintList.get(parentPosition).childList.get(childPosition).num*
                    paintList.get(parentPosition).childList.get(childPosition).pricce;
        }
        else{
            sum--;
            sumprice-=paintList.get(parentPosition).childList.get(childPosition).num*
                    paintList.get(parentPosition).childList.get(childPosition).pricce;
        }
        check();
    }

    @Override
    public void onParentItemClickListener(boolean isChecked, int position, ChildAdapter childAdapter) {
        for (ChildBean childBean : paintList.get(position).childList) {
            if (!childBean.isChecked==isChecked)
            childBean.isChecked=isChecked;
        }
        childAdapter.notifyDataSetChanged();
        check();
    }
}
