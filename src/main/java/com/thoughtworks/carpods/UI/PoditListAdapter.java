package com.thoughtworks.carpods.UI;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.Listable;

import java.io.File;
import java.util.List;

public class PoditListAdapter extends ArrayAdapter<Listable> {

    private final Context context;
    private final List<? extends Listable> items;

    public PoditListAdapter(Context context, List<? extends Listable> items) {
        super(context, R.layout.list_with_icons);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_row, parent, false);

        Listable item = items.get(position);

        TextView firstName = (TextView)rowView.findViewById(R.id.name);
        firstName.setText(item.label());

        ImageView picture = (ImageView) rowView.findViewById(R.id.profile_picture);
        File imageFile = new File(context.getFilesDir(), item.iconPath());
        if (imageFile.exists() && imageFile.isFile()) {
            picture.setImageURI(Uri.fromFile(imageFile));
        }

        return rowView;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Listable getItem(int position) {
        return items.get(position);
    }
}
