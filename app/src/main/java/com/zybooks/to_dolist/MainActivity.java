package com.zybooks.to_dolist;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import com.zybooks.to_dolist.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ToDoList mToDoList;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.clear_button).setOnClickListener(view -> clearButtonClick());

        mToDoList = new ToDoList(this);
        
        //sets the on click listener for the fab button; replace the snackbar code with the fab code
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            // Attempt to load a previously saved list
            mToDoList.readFromFile();
            displayList();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            // Save list for later
            mToDoList.saveToFile();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void clearButtonClick() {
        mToDoList.clear();
        displayList();
    }
}
