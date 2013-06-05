package com.thoughtworks.carpods.UI.people;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.Person;

import java.util.List;

public class PeopleAdapter extends ArrayAdapter<Person> {

    private final Context context;
    private final List<Person> people;

    public PeopleAdapter (Context context, List<Person> people) {
        super(context, R.layout.people_list);
        this.context = context;
        this.people = people;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.person_row, parent, false);

        TextView firstName = (TextView)rowView.findViewById(R.id.first_name_label);
        firstName.setText(people.get(position).getFirstName());

        TextView lastName = (TextView)rowView.findViewById(R.id.last_name_label);
        lastName.setText(people.get(position).getLastName());

        return rowView;
    }

    // apparently, you need to override getCount in order for a custom arrayadapter to work
    public int getCount() {
        return people.size();
    }
}
