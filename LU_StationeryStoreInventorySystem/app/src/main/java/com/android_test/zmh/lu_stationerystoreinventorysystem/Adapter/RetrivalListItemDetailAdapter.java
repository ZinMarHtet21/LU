package com.android_test.zmh.lu_stationerystoreinventorysystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RetrivalItem;
import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.RetrivalItemDetail;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import java.util.ArrayList;

/**
 * Created by student on 15/3/15.
 */
public class RetrivalListItemDetailAdapter extends BaseAdapter {
    ArrayList<RetrivalItemDetail> list = new ArrayList<RetrivalItemDetail>();
    Context ctx;
    LayoutInflater layoutInflater;


    public RetrivalListItemDetailAdapter(Context context, ArrayList<RetrivalItemDetail> listData) {
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
            convertView = layoutInflater.inflate(R.layout.row_retrivalitem_detail, null);
        }

        TextView text_department = (TextView)convertView.findViewById(R.id.department);
        TextView text_qty= (TextView)convertView.findViewById(R.id.qty);
        EditText edit_actul = (EditText)convertView.findViewById(R.id.actual_qty);




//        text_description.setText(list.get(position).getDescription());
//        text_qty.setText(String.valueOf(list.get(position).getBalance()));

        return convertView;




    }

}
