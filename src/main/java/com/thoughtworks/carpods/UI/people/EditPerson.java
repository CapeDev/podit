package com.thoughtworks.carpods.UI.people;


import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.DataAccessFactory;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;
import com.thoughtworks.carpods.plumb.PodActivity;

import javax.inject.Inject;

import static com.thoughtworks.carpods.fun.FileMoveUtils.tryToSaveBitmapInInternalStorage;
import static com.thoughtworks.carpods.fun.PathUtils.getPath;
import static com.thoughtworks.carpods.fun.ViewCast.editText;
import static com.thoughtworks.carpods.fun.ViewCast.imageButton;

public class EditPerson extends PodActivity {
    private static final String TAG = "EditPerson";

    protected static final String PICTURE_PREFIX = "person_";
    protected static final int SELECT_PICTURE = 1;

    @Inject DataAccessFactory dataAccessFor;

    private PeopleDataAccess peopleDataAccess;
    private Bitmap profilePicture;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        peopleDataAccess = dataAccessFor.people(this);

        setContentView(R.layout.edit_person);
        setUpImagePicker();
        setUpActionBar();
    }

    private void setUpImagePicker() {
        ImageButton imageButton = imageButton(this, R.id.profile_picture);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }
        });
    }

    public void save(MenuItem item) {
        Person person = getDataFromView();
        peopleDataAccess.savePerson(person);
        finish();
    }

    private String savePictureToDisk() {
        return profilePicture == null ? "" : tryToSaveBitmapInInternalStorage(getBaseContext(), profilePicture, PICTURE_PREFIX);
    }

    protected Person getDataFromView() {
        return new Person.Builder().firstName(getFirstNameFromView())
                                   .lastName(getLastNameFromView())
                                   .homeLocation(getHomeLocationFromView())
                                   .aboutMe(getAboutMeFromView())
                                   .picture(savePictureToDisk())
                                   .build();
    }

    protected String getLastNameFromView() {
        return editText(this, R.id.last_name_input).getText().toString();
    }

    protected String getFirstNameFromView() {
        return editText(this, R.id.first_name_input).getText().toString();
    }

    protected String getHomeLocationFromView() {
        return editText(this, R.id.home_location_input).getText().toString();
    }

    protected String getAboutMeFromView() {
        return editText(this, R.id.about_me_input).getText().toString();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE) {
            profilePicture = BitmapFactory.decodeFile(getPath(data.getData(), getContentResolver()));
            imageButton(this, R.id.profile_picture).setImageBitmap(profilePicture);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_action_bar, menu);
        return true;
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
}