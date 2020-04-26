package com.cobosideas.lista.activities.lists;

import android.content.Intent;
import android.os.Bundle;

import com.cobosideas.lista.MainActivityLista;
import com.cobosideas.lista.global.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.cobosideas.lista.R;

public class ActivityLists extends AppCompatActivity {
    //CODE_STRING_ACTIVITY_LISTS
    final String CODE_STRING_ACTIVITY_LISTS = Constants.CODES_ACTIVITY_LISTS.CODE_STRING_ACTIVITY_LISTS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String sessionId = getIntent().getStringExtra(CODE_STRING_ACTIVITY_LISTS);
        setContentView(R.layout.activity_lists);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(sessionId);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Going back to MainActivityLista */
                Intent intent = new Intent(getApplicationContext(), MainActivityLista.class);
                int requestCode = 1; // Or some number you choose
                startActivityForResult(intent, requestCode);
            }
        });


    }
}
