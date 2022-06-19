package com.e.study1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
    public View getView(final int index, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.list_item, null);

        TextView id_tv = v.findViewById(R.id.id);
        TextView time_tv = v.findViewById(R.id.time);
        Button info_btn = v.findViewById(R.id.info_btn);

        id_tv.setText(""+list.get(index).getId());
        time_tv.setText(list.get(index).getServer_time());

        info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_Dialog(index);
            }
        });

        return v;
    }

    private void show_Dialog(final int id){
        List_Item item = list.get(id);
        String str = "서버 시간 : "+item.getServer_time()
                +"\n기기 시간 : "+item.getLocal_time()
                +"\n"+item.getInfo();
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        dialog = builder.setMessage(str)
                .setNegativeButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context,TimeUpdateActivity.class);
                        intent.putExtra("id",item.getId());
                        intent.putExtra("info",item.getInfo());
                        context.startActivity(intent);
                    }
                })
                .setPositiveButton("확인",null)
                .create();
        dialog.show();
    }


}
