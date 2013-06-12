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

        Person person = people.get(position);

        TextView firstName = (TextView)rowView.findViewById(R.id.first_name_label);
        firstName.setText(String.format("%s %s", person.getFirstName(), person.getLastName()));

        return rowView;
    }

    @Override
    public int getCount() {
        return people.size();
    }

    @Override
    public Person getItem(int position) {
        return people.get(position);
    }
}
