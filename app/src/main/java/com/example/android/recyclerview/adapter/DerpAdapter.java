package com.example.android.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.recyclerview.R;
import com.example.android.recyclerview.model.ListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ETORO on 02/05/2017.
 */

//this is the adapter class
public class DerpAdapter extends RecyclerView.Adapter<DerpAdapter.DerpHolder> {

    private List<ListItem> listData;
    private LayoutInflater inflater;
    Context context;



    private ItemClickCallBack itemClickCallBack;       // this is an interface which allows us to communicate with our actvity
    //without the adapter explicitly calling it to store somewhere first

    public interface ItemClickCallBack{
        void onItemClick( View v, int p);  //here when an item is clickec both the View(v) and the position (p) of the item is
                                            //being carried. The view is carried cos of the transition part
        void onFavouriteClicked(int p);

    }
    public void setItemClickCallBack( final ItemClickCallBack itemClickCallBack) {
        this.itemClickCallBack = itemClickCallBack;
    }


    public DerpAdapter(List<ListItem> listData, Context context){
        this.inflater = LayoutInflater.from(context);
        this.listData = listData;
        this.context = context;     //this ws for testing the toast
    }


    @Override
    public DerpHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new DerpHolder(view);
    }

    @Override
    public void onBindViewHolder(DerpHolder holder, int position) {
        ListItem listItem = listData.get(position);
        holder.title.setText(listItem.getTitle());
        holder.subtitle.setText(listItem.getSubTitle());

        if (listItem.isFavourite()){
            holder.favourited.setImageResource(R.drawable.ic_star_black_24dp);
        }
        else{
            holder.favourited.setImageResource(R.drawable.ic_star_border_black_24dp);
        }
        holder.image.setImageResource(listItem.getImageResId());

    }


    //to update our recycler view's adapter data
    public void setListData (ArrayList<ListItem> exerciseList){
        this.listData.clear();
        this.listData.addAll(exerciseList);
    }


    @Override
    public int getItemCount() {

//        Toast.makeText(context, "" + listData.size(), Toast.LENGTH_SHORT).show();
        return listData.size();

    }

    //A RecyclerView adapter needs a ViewHolder object, so we create a class that extends ViewHolder
    class DerpHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private TextView subtitle;
        private ImageView favourited;
        private TextView title;
        private ImageView image;
        private View container;  //this is the LinearLayout in our list_item.xml

        public DerpHolder(View itemView) {

            super(itemView);

            title = (TextView) itemView.findViewById(R.id.item_text);
            subtitle =(TextView) itemView.findViewById(R.id.sub_title);
            favourited = (ImageView) itemView.findViewById(R.id.item_icon_sec_);
            image = (ImageView) itemView.findViewById(R.id.im_item_icon);
            favourited.setOnClickListener(this);
            container = itemView.findViewById(R.id.cont_item_root);
            container.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.cont_item_root){
                itemClickCallBack.onItemClick(v, getAdapterPosition());

            }
            else{
                itemClickCallBack.onFavouriteClicked(getAdapterPosition());

            }

        }
    }
}
