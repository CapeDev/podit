package com.thoughtworks.carpods.UI.people;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.thoughtworks.carpods.PodActivity;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.DataAccessFactory;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;
import com.thoughtworks.carpods.fun.ViewCast;

import javax.inject.Inject;

public class EditPerson extends PodActivity {
    @Inject DataAccessFactory dataAccessFor;

    private PeopleDataAccess peopleDataAccess;
    private ViewCast viewCast;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewCast = new ViewCast(this);

        setContentView(R.layout.edit_person);
        setUpImagePicker();

        peopleDataAccess = dataAccessFor.people(this);
    }

    private void setUpImagePicker() {
        ImageButton imageButton = viewCast.imageButton(R.id.profile_picture);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });
    }

    public void save(MenuItem item) {
        Person person = getDataFromView();
        peopleDataAccess.savePerson(person);
        finish();
    }

    protected Person getDataFromView() {
        return new Person.Builder().firstName(getFirstNameFromView())
                                   .lastName(getLastNameFromView())
                                   .homeLocation(getHomeLocationFromView())
                                   .aboutMe(getAboutMeFromView())
                                   .build();
    }

    protected String getLastNameFromView() {
        return viewCast.editText(R.id.last_name_input).getText().toString();
    }

    protected String getFirstNameFromView() {
        return viewCast.editText(R.id.first_name_input).getText().toString();
    }

    protected String getHomeLocationFromView() {
        return viewCast.editText(R.id.home_location_input).getText().toString();
    }

    protected String getAboutMeFromView() {
        return viewCast.editText(R.id.about_me_input).getText().toString();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri selectedImageUri = data.getData();
                Toast.makeText(this, getPath(selectedImageUri), Toast.LENGTH_SHORT).show();
                viewCast.imageButton(R.id.profile_picture).setImageBitmap(BitmapFactory.decodeFile(getPath(selectedImageUri)));
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_person, menu);
        return true;
    }
}