package com.example.planner;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends androidx.fragment.app.ListFragment {
    ItemSelected activity;
    MyDBHandler myDBHandler;
    ArrayList<String> names;

    public interface ItemSelected{
        void onItemSelected(int index);

    }
    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (ItemSelected) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myDBHandler = new MyDBHandler(getActivity(),null,null,1);
        names = myDBHandler.getArrayOfTaskNames();
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, names));
        if(this.getActivity().findViewById(R.id.layout_portrait) == null)
            activity.onItemSelected(0);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        activity.onItemSelected(position);
    }

    @Override
    public void onResume() {
        super.onResume();
        names = myDBHandler.getArrayOfTaskNames();
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, names));
    }


}
