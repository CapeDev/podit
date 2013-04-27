package com.thoughtworks.carpods.UI;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.CarPodsDatabase;

public class PeopleList extends ListActivity {
    protected CarPodsDatabase carPodsDatabase;
    SimpleCursorAdapter mAdapter;
    private static final String PERSON_TABLE = "personTable";

    public PeopleList() {
        super();
    }

    Cursor cursor = carPodsDatabase.getFirstPersonFromDatabaseAsCursor();

    String[] testArray = {"first_name_label"};
    int[] toViews = {R.id.peopleList};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mAdapter = new SimpleCursorAdapter(this, R.layout.people_list_view, cursor, testArray, toViews, 0);
           setListAdapter(mAdapter);
    }


}
