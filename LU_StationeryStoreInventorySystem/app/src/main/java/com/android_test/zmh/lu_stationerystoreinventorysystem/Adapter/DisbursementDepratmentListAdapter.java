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
public class DisbursementDepratmentListAdapter extends BaseAdapter {

    ArrayList<DepartmentDisbursementList> list = new ArrayList<DepartmentDisbursementList>();
    Context ctx;
    LayoutInflater layoutInflater;

    public DisbursementDepratmentListAdapter(Context context, ArrayList<DepartmentDisbursementList> listData) {
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
            convertView = layoutInflater.inflate(R.layout.row_disbursement_department, null);
        }

        TextView text_department = (TextView)convertView.findViewById(R.id.department);
        TextView text_representative= (TextView)convertView.findViewById(R.id.representative);
        TextView text_collectionpoint = (TextView)convertView.findViewById(R.id.collectionpoint);

        text_department.setText(list.get(position).getDepartment().getName());
        text_representative.setText(list.get(position).getDepartment().getRepresentative());
        text_collectionpoint.setText(list.get(position).getDepartment().getCollection_point());

        return convertView;
    }
}
