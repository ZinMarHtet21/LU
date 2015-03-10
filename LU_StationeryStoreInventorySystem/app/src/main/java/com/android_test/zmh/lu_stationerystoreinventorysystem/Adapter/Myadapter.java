package com.android_test.zmh.lu_stationerystoreinventorysystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android_test.zmh.lu_stationerystoreinventorysystem.Models.Requisition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by student on 6/3/15.
 */

public class Myadapter extends BaseAdapter {
List<Requisition> list = new ArrayList<Requisition>();
    Context ctx;
    LayoutInflater inflater;

    public Myadapter(Context contxt, List<Requisition> listData) {
    ctx = contxt;
    list =listData;
    inflater = LayoutInflater.from(this.ctx);
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

       /* if(convertView == null) {
            convertView = inflater.inflate(R.layout.layout_list_item, parent);
            mViewHolder = new MyViewHolder();
            convertView.setTag(mViewHolder);


        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }*/
      //  convertView = inflater.inflate(R.layout.layout_list_item, parent);
//LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  //      LayoutInflater inflater = LayoutInflater.from(ctx);

//        View v = inflater.inflate(R.layout.weather_row, null, true);


        return null;
    }

}
