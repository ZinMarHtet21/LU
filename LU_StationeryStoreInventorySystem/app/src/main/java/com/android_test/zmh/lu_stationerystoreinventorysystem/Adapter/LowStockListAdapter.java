package com.android_test.zmh.lu_stationerystoreinventorysystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Item;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 7/3/15.
 */
public class LowStockListAdapter extends BaseAdapter {
    ArrayList<Item> list = new ArrayList<Item>();
    Context ctx;
    LayoutInflater layoutInflater;

    public LowStockListAdapter(Context context, ArrayList<Item> listData) {
        ctx = context;
        list =listData;
        layoutInflater = LayoutInflater.from(this.ctx);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.row_stockitem, null);
        }

        TextView text_description = (TextView)convertView.findViewById(R.id.description);
        TextView text_reorderlevel= (TextView)convertView.findViewById(R.id.reorderlevel);
        TextView text_balance = (TextView)convertView.findViewById(R.id.balance);

        text_description.setText(list.get(position).getDescription());
        text_reorderlevel.setText(String.valueOf(list.get(position).getReorderLevel()));
        text_balance.setText(String.valueOf(list.get(position).getBalance()));

        return convertView;
    }

}
