package com.example.planner.schedule;

import com.example.planner.MyDBHandler;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {

    private MutableLiveData<String> mTitle = new MutableLiveData<>();
    MyDBHandler myDBHandler;

    private LiveData<String> mText = Transformations.map(mTitle, new Function<String, String>() {
        @Override
        public String apply(String input) {
            switch (input) {
                case "Monday":
                    break;
                case "Tuesday":
                    break;
                case "Wednesday":
                    break;
                case "Thursday":
                    break;
                case "Friday":
                    break;
            }
            return "Your " + input + " classes:";
        }
    });

    public void setIndex(String index) {
        mTitle.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}
