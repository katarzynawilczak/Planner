package com.example.planner.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.planner.R;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class WedFragment extends Fragment {

    private static final String TAG = "Wednesday";
    private MyDBHandlerSchedule myDBHandler;
    private ArrayList<String> classNames;
    private ArrayList<String> classDays;
    private ArrayList<String> timesFrom;
    private ArrayList<String> timesTil;
    private PageViewModel pageViewModel;

    public WedFragment() {
        // Required empty public constructor
    }

    public static WedFragment newInstance() {
        return new WedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        pageViewModel.setIndex(TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myDBHandler = new MyDBHandlerSchedule(getActivity(),null,null,1);
        List<ClassItem> classes = new ArrayList<>();
        classNames = myDBHandler.getArrayOfClassNames();
        classDays = myDBHandler.getArrayOfClassDays();
        timesFrom = myDBHandler.getArrayOfTimeFrom();
        timesTil = myDBHandler.getArrayOfTimeTil();

        int i;
        for (i=0; i<classNames.size(); i++) {
            System.out.println("");
            if (classDays.get(i).equals("Wednesday")) {
                classes.add(new ClassItem(classNames.get(i), timesFrom.get(i), timesTil.get(i)));
            }
        }

        ClassItem[] cl = new ClassItem[classes.size()];
        final ClassItem[] classItems = classes.toArray(cl);
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.mon_fragment, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        /* list view */
        final ListView listMon = (ListView) root.findViewById(R.id.listMon);

        ArrayAdapter<ClassItem> listViewAdapter = new ArrayAdapter<ClassItem>(
                getActivity(),
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                classItems
        ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(classItems[position].getClassName());
                text2.setText(classItems[position].getFrom() + " - " + classItems[position].getTil());
                return view;
            }
        };

        listMon.setAdapter(listViewAdapter);

        return root;
    }
}