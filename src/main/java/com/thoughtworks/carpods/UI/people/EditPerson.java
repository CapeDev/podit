package com.thoughtworks.carpods.UI.people;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import com.thoughtworks.carpods.plumb.PodActivity;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.DataAccessFactory;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;
import com.thoughtworks.carpods.fun.ViewCast;

import javax.inject.Inject;
import java.io.FileOutputStream;
import java.util.Calendar;

public class EditPerson extends PodActivity {
    public static final String PICTURE_PREFIX = "person_";
    protected static final int SELECT_PICTURE = 1;

    @Inject DataAccessFactory dataAccessFor;

    private PeopleDataAccess peopleDataAccess;
    private ViewCast viewCast;
    private Bitmap bitmap;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewCast = new ViewCast(this);
        peopleDataAccess = dataAccessFor.people(this);

        setContentView(R.layout.edit_person);
        setUpImagePicker();
    }

    private void setUpImagePicker() {
        ImageButton imageButton = viewCast.imageButton(R.id.profile_picture);
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
        String pictureName = String.format("%s_%d.jpg", PICTURE_PREFIX, Calendar.getInstance().getTimeInMillis()).toLowerCase();

        try {
            FileOutputStream outputStream = openFileOutput(pictureName, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
            return pictureName;
        } catch (Exception e) {
            Log.d(getClass().getName(), e.getMessage());
        }

        return "";
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
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                bitmap = BitmapFactory.decodeFile(getPath(selectedImageUri));
                viewCast.imageButton(R.id.profile_picture).setImageBitmap(bitmap);
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