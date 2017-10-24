package com.example.android.recyclerview.ui;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.transition.Fade;
import android.view.View;
import android.widget.Button;

import com.example.android.recyclerview.R;
import com.example.android.recyclerview.adapter.DerpAdapter;
import com.example.android.recyclerview.model.DerpData;
import com.example.android.recyclerview.model.ListItem;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity implements DerpAdapter.ItemClickCallBack{  //implement is for the interface we
    //created in our DerpAdapter class

    private static final String BUNDLE_EXTRA = "BUNDLE_EXTRA";
    private static final String EXTRA_QUOTE = "EXTRA_QUOTE";        // I used the string directly instead
    private static final String EXTRA_ATTR = "EXTRA_ATTR";
    private static final String EXTRA_IMAGE_RED_ID = "EXTRA_IMAGE_RED_ID";

    private RecyclerView recyclerView;
    private DerpAdapter adapter;
    private ArrayList listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listData = (ArrayList) DerpData.getListData();

        recyclerView = (RecyclerView) findViewById(R.id.rec_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));  //we could have also used
        //GridLayoutManager or StaggeredGridLayoutManager depending in what we needed to achieve

       // adapter = new DerpAdapter(DerpData.getListData(), this); //adapter = new DerpAdapter(listData, this);       //this gets the 12 items and passes it into the constructor
        //of the adapter ie DerpAdapter

        adapter = new DerpAdapter(listData, this);

        recyclerView.setAdapter(adapter);
        adapter.setItemClickCallBack(this);

        //THIS SET OF CODES IS FOR THE SWIPE AND MOVING ACTION OF THE ITEMS IN THE RECYCLER VIEW
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);  //attaches the touch helper to our recycler view


        //SETS ONCLICKLISTENER TO THE BUTTON CREATED
        Button addButton = (Button) findViewById(R.id.btn_add_item);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemToList();
            }
        });

    }

    //THIS SET OF CODES IS FOR THE SWIPE AND MOVING ACTION OF THE ITEMS IN THE RECYCLER VIEW
    private ItemTouchHelper.Callback createHelperCallback(){
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,
                                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT){
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target){
                moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir){
                deleteItem(viewHolder.getAdapterPosition());

            }




        };
        return simpleCallback;
    }

    private void addItemToList(){
        ListItem item = DerpData.getRandomListItem();
        listData.add(item);
        adapter.notifyItemInserted(listData.indexOf(item));
    }
    private void moveItem(int oldPos, int newPos){
        ListItem item = (ListItem) listData.get(oldPos);
        listData.remove(oldPos);
        listData.add(newPos, item);
        adapter.notifyItemMoved(oldPos, newPos);

    }
    private void deleteItem(final int position){
        listData.remove(position);
        adapter.notifyItemRemoved(position);
    }



    @Override
    public void onItemClick(View v, int p) {
        ListItem item = (ListItem) listData.get(p);

        Intent intent = new Intent(this, DetailActivity.class);

        Bundle extras = new Bundle();

        extras.putString("EXTRA_QUOTE", item.getTitle());
        extras.putString(EXTRA_ATTR, item.getSubTitle());
        extras.putInt(EXTRA_IMAGE_RED_ID, item.getImageResId());
        intent.putExtra(BUNDLE_EXTRA, extras);
        //This next set of codes is for api version greater than 21 so that the animation can come into play
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) { //lollipop is for version21
            getWindow().setEnterTransition(new Fade(Fade.IN));
            getWindow().setExitTransition(new Fade(Fade.OUT));

            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    //Each pair picks a view and its corresponding String as we declared in string.xml
                    new Pair<View, String>(v.findViewById(R.id.im_item_icon), getString(R.string.transition_image)),
                    new Pair<View, String>(v.findViewById(R.id.item_text), getString(R.string.transition_quote)),
                    new Pair<View, String>(v.findViewById(R.id.sub_title), getString(R.string.transition_attribution))
            );
            ActivityCompat.startActivity(this, intent, options.toBundle());

        }
        else{
            startActivity(intent);
        }



    }

    @Override
    public void onFavouriteClicked(int p) {
        ListItem item = (ListItem) listData.get(p);

        //this alternates the state of the image when clicked
        if (item.isFavourite()){
            item.setFavourite(false);
        }

        else{
            item.setFavourite(true);
        }

        //pass new data to adapter and update

        //adapter.setListData(listData);
        adapter.notifyDataSetChanged();  //not used in all cases esp when data is from web or db



    }
}
