package com.thoughtworks.carpods;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.thoughtworks.carpods.data.CarPodsDatabase;

//example class based on Kris Gonzalez's myTW screencast
public class HelloAndroidActivity extends Activity {

    private static String TAG = "carpods-android";
    LongRunningOperationExecutor operationExecutor = new LongRunningOperationExecutor();

    private Context context;
    private CarPodsDatabase carPodsDatabase;

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
        setContentView(R.layout.main);

        this.context = this;

        if(carPodsDatabase == null) {
            carPodsDatabase = new CarPodsDatabase(context);
        }

        TextView view_database_field = (TextView)findViewById(R.id.view_database_field);
        String firstPersonFromDatabase = getexampleStringFromDatabase();
        view_database_field.setText(firstPersonFromDatabase);
    }

    private String getexampleStringFromDatabase() {
        return carPodsDatabase.getExampleString();
    }

    @Override
    public void onResume() {
        super.onResume();

        operationExecutor.execute(this);
    }

    public void callbackAfterExecution() {
    }

    public void updateMessage(View view) {
        // onClick methods specified in layout files must have the above method signature style
        // the view that gets passed to the method, is the view that is calling it (specified in its android:onClick annotation)
        // the View parameter can be null for testing, but will never be null in production
        // e.g. public void methodName(View view) {}

        EditText editTextView = (EditText)findViewById(R.id.edit_message_field);
        String newText = editTextView.getText().toString();

        TextView messageTextView = (TextView)findViewById(R.id.message_field);
        messageTextView.setText(newText);

        editTextView.setText("");

        carPodsDatabase.saveStringExample(newText);
    }
}