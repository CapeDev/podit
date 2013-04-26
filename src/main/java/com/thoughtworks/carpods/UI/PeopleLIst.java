package com.thoughtworks.carpods.UI;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.CarPodsDatabase;

public class PeopleList extends ListActivity {
    protected CarPodsDatabase carPodsDatabase;
    SimpleCursorAdapter mAdapter;

    public PeopleList() {
        super();
    }

    String[] testArray = {"hello"};
    int[] toViews = {R.id.peopleList};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mAdapter = new SimpleCursorAdapter(this, R.layout.people_list_view, null, testArray, toViews, 0);
           setListAdapter(mAdapter);
    }


}
