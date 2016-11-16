package com.example.chy.challenge.Adepter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.chy.challenge.Models.BlackListInfo;
import com.example.chy.challenge.MyBlackList;
import com.example.chy.challenge.R;
import com.example.chy.challenge.Utils.LogUtils;

import java.util.List;

/**
 * Created by 77588 on 2016/11/16.
 */

public class MyBlackListAdepter extends ArrayAdapter<BlackListInfo.DataBean>{
    private int resourceId;
    private MyBlackList myBlackList;

    public MyBlackListAdepter(Context context, int textViewResourceId, List<BlackListInfo.DataBean> objects, MyBlackList myBlackList) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.myBlackList = myBlackList;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final BlackListInfo.DataBean dataBean = getItem(position);
        View view;
        final ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.realname = (TextView) view.findViewById(R.id.realname);
            viewHolder.position_type = (TextView) view.findViewById(R.id.position_type);
            viewHolder.city = (TextView) view.findViewById(R.id.city);
            viewHolder.work_life = (TextView) view.findViewById(R.id.work_life);
            viewHolder.degree = (TextView) view.findViewById(R.id.degree);
            viewHolder.work_property = (TextView) view.findViewById(R.id.work_property);
            viewHolder.del = (Button) view.findViewById(R.id.delBlackList);
            viewHolder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myBlackList.delete(dataBean.getUserid(),"2",dataBean.getBlackid());
                    LogUtils.e("Tip","flag");
                }
            });
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.realname.setText(dataBean.getBlacks().getRealname());
        viewHolder.position_type.setText(dataBean.getBlacks().getPosition_type());
        viewHolder.city.setText(dataBean.getBlacks().getCity());
        viewHolder.work_life.setText(dataBean.getBlacks().getWork_life());
        viewHolder.degree.setText(dataBean.getBlacks().getEducation().get(0).getDegree());
        viewHolder.work_property.setText(dataBean.getBlacks().getWork_property());
        return view;
    }


    class ViewHolder {
        private TextView realname;
        private TextView position_type;
        private TextView city;
        private TextView work_life;
        private TextView degree;
        private TextView work_property;
        private Button del;
    }
}
