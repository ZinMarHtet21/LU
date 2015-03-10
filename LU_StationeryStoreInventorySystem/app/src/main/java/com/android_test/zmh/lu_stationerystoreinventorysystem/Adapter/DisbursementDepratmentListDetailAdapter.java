package com.android_test.zmh.lu_stationerystoreinventorysystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.DepartmentDisbursementList;
import com.android_test.zmh.lu_stationerystoreinventorysystem.R;

import java.util.ArrayList;

/**
 * Created by student on 8/3/15.
 */
public class DisbursementDepratmentListDetailAdapter extends BaseAdapter{

    DepartmentDisbursementList ddl;
    Context ctx;
    LayoutInflater layoutInflater;

    public DisbursementDepratmentListDetailAdapter(Context context, DepartmentDisbursementList listData) {
        ctx = context;
        ddl =listData;
        layoutInflater = LayoutInflater.from(this.ctx);
    }

    @Override
    public int getCount() {
        return ddl.getDisbursementList().size();
    }

    @Override
    public Object getItem(int position) {
        return ddl.getDisbursementList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.row_disbursement_department_detail, null);
        }

        TextView text_item = (TextView)convertView.findViewById(R.id.item);
        TextView text_qty= (TextView)convertView.findViewById(R.id.qty);
        TextView text_actual_qty = (TextView)convertView.findViewById(R.id.actual_qty);

        text_item.setText(ddl.getDisbursementList().get(position).getItemName());
        text_qty.setText(String.valueOf(ddl.getDisbursementList().get(position).getQty()));
        text_actual_qty.setText(String.valueOf(ddl.getDisbursementList().get(position).getActualQty()));

        return convertView;
    }
}
