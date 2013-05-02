package com.thoughtworks.carpods.UI;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.CarPodsDatabase;

public class PeopleListLoader extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    protected CarPodsDatabase carPodsDatabase;
    SimpleCursorAdapter cursorAdapter;

    static final String[] PROJECTION = {CarPodsDatabase.ROWID, CarPodsDatabase.FIRST_NAME, CarPodsDatabase.LAST_NAME};
    static final String SELECTION = "(("
            + CarPodsDatabase.FIRST_NAME + " NOT NULL) AND ("
            + CarPodsDatabase.LAST_NAME + " NOT NULL)"
            + ")";

    public PeopleListLoader() {
        super();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProgressBar progressBar = new ProgressBar(this);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        progressBar.setLayoutParams(layoutParams);
        getListView().setEmptyView(progressBar);

        ViewGroup rootView = (ViewGroup)findViewById(R.id.peopleList);
        rootView.addView(progressBar);

        String[] fromColumns = {CarPodsDatabase.FIRST_NAME, CarPodsDatabase.LAST_NAME};
        int[] toViews = {R.id.firstNameLabel, R.id.lastNameLabel};

        cursorAdapter = new SimpleCursorAdapter(this, R.layout.people_list_view, null, fromColumns, toViews, 0);
        setListAdapter(cursorAdapter);

        getLoaderManager().initLoader(0, null, this);
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // FIXME - still need the URI
        return new CursorLoader(this, null, PROJECTION, SELECTION, null, null);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        // TODO - figure out what we want this activity to do when a list item clicked
    }

}
