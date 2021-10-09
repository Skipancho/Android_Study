package com.e.study1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyListAdapter extends BaseAdapter {
    private Context context;
    private List<List_Item> list;

    public MyListAdapter(Context context, List<List_Item> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int index) {
        return list.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.list_item, null);

        TextView title = v.findViewById(R.id.title);
        TextView body = v.findViewById(R.id.body);
        TextView num = v.findViewById(R.id.num);

        title.setText(list.get(index).getTitle());
        body.setText(list.get(index).getBody());
        num.setText(String.valueOf(list.get(index).getNum()));

        return v;
    }
}
