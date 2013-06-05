package com.thoughtworks.carpods.UI.pod;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.Pod;

import java.util.List;

public class PodAdapter extends ArrayAdapter {

    private final Context context;
    private final List<Pod> podNames;

    public PodAdapter(Context context, List<Pod> podNames) {
        super(context, R.layout.pod_list);
        this.context = context;
        this.podNames = podNames;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.pod_row, parent, false);

        TextView firstName = (TextView)rowView.findViewById(R.id.pod_name_label);
        firstName.setText(podNames.get(position).getName());

        return rowView;
    }

    public int getCount() {
        return podNames.size();
    }

}
