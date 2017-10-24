package com.example.android.recyclerview.model;

import com.example.android.recyclerview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ETORO on 02/05/2017.
 */
public class DerpData {

    private static final String[] titles = {"The Just Shall Live By Faith and not by sight. For without faith, no man can please God",
            "It is more Blessed To Give Than Receive", "Thy Will Be Done on earth as it is in heaven. Give us this day our daily bread"
    };

    private static final int[] image = {android.R.drawable.ic_dialog_info, android.R.drawable.ic_media_play,
            android.R.drawable.ic_lock_idle_alarm};

    //private static final int icon = R.drawable.ic_filter_vintage_black_36dp;

    private static String[] subTitles = {"Bible Quote", "New Testament", "The Lord's Prayer"};


    public static List<ListItem> getListData(){
        List<ListItem> data = new ArrayList<>();


        //this is just to repeat the process 4 times so that we can have 12 items in order to show scrollable attribute


        //RecyclerView
        for (int x=0; x<5; x++){

            for(int i= 0; i< titles.length && i<image.length; i++){
                ListItem listItem = new ListItem();
                listItem.setImageResId(image[i]);
                listItem.setTitle(titles[i]);
                listItem.setSubTitle(subTitles[i]);
                data.add(listItem);

            }
        }
        return data;
    }

    public static ListItem getRandomListItem(){
        int rand = new Random().nextInt(3);

        ListItem item = new ListItem();

        item.setTitle(titles[rand]);
        item.setSubTitle(subTitles[rand]);
        item.setImageResId(image[rand]);

        return item;
    }
}
