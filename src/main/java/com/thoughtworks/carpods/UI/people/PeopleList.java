package com.thoughtworks.carpods.UI.people;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.UI.pod.EditPod;
import com.thoughtworks.carpods.data.DataAccessFactory;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;
import com.thoughtworks.carpods.plumb.ListPodActivity;

import javax.inject.Inject;
import java.util.List;

public class PeopleList extends ListPodActivity {
    static final private String CLASS_TAG = "PeopleList";

    @Inject DataAccessFactory dataAccessFor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.people_list);

        PeopleDataAccess dataAccess = dataAccessFor.people(this);
        List<Person> peopleNames = dataAccess.getAllPeopleNames();

        Toast.makeText(this, "I've got " + peopleNames.size() + " from the database.", Toast.LENGTH_LONG).show();

        setListAdapter(new PeopleAdapter(this, peopleNames));
        setUpActionBar();
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        Person item = (Person) listView.getAdapter().getItem(position);

        if (sourceActivityIs(EditPod.class)) {
            Intent intent = new Intent();
            intent.putExtra("personId", item.getId());
            setResult(Activity.RESULT_OK, intent);
            finish();
        } else {
            Intent intent = new Intent(this, DisplayPerson.class);
            intent.putExtra("personId", item.getId());
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private boolean sourceActivityIs(Class<? extends Activity> anActivity) {
        return getIntent().hasExtra("sourceActivity") && getIntent().getStringExtra("sourceActivity").equals(anActivity.getSimpleName());
    }
}