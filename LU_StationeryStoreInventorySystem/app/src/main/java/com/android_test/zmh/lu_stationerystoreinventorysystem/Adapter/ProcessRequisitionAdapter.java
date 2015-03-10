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

/**
 * Created by student on 8/3/15.
 */
public class ProcessRequisitionAdapter  extends BaseAdapter{
    ArrayList<Item> list = new ArrayList<Item>();
    Context ctx;
    LayoutInflater layoutInflater;

    public ProcessRequisitionAdapter(Context context, ArrayList<Item> listData) {
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
            convertView = layoutInflater.inflate(R.layout.row_confirm_collected_item, null);
        }

        TextView text_description = (TextView)convertView.findViewById(R.id.description);
        TextView text_qty= (TextView)convertView.findViewById(R.id.qty);

        text_description.setText(list.get(position).getDescription());
        text_qty.setText(String.valueOf(list.get(position).getBalance()));

        return convertView;
    }

}
