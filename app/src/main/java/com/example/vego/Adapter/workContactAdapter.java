package com.example.vego.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.vego.Contact;
import com.example.vego.R;

import java.util.ArrayList;
import java.util.List;

public class workContactAdapter extends ArrayAdapter {
    List<Contact> contactList;
    Context context;

    public workContactAdapter(@NonNull Context context, List<Contact> contactList) {
        super(context, 0, contactList);
        this.contactList = contactList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(context).inflate(R.layout.item_view,parent, false);

            Contact contact = contactList.get(position);

            TextView name = listItem.findViewById(R.id.textViewName);
            name.setText(contact.getName());
            TextView number = listItem.findViewById(R.id.textViewNumber);
            number.setText(contact.getNumber());
        }
        return listItem;
    }
}
