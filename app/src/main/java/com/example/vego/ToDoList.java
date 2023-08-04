package com.example.vego;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.vego.Adapter.ToDoAdapter;
import com.example.vego.Model.To_Do_Model;
import com.example.vego.Utils.DatabaseHelperForToDoL;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToDoList extends AppCompatActivity implements OnDialogCloseListner{
    private RecyclerView mRecyclerview;
    private FloatingActionButton fab;
    private DatabaseHelperForToDoL myDB;

    private List<To_Do_Model> mList;
    private ToDoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        mRecyclerview = findViewById(R.id.recyclerview);
        fab = findViewById(R.id.fab2);
        myDB = new DatabaseHelperForToDoL(ToDoList.this);
        mList = new ArrayList<>();
        adapter = new ToDoAdapter(myDB , ToDoList.this);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapter);

        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager() , AddNewTask.TAG);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelper_forToDo(adapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);


    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.getAllTasks();
        Collections.reverse(mList);
        adapter.setTasks(mList);
        adapter.notifyDataSetChanged();

    }
}