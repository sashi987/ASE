package com.example.shashi.giveaway;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemListBase4Adapter extends BaseAdapter {
    private static ArrayList<GiveAwayItemDetails> itemDetailsrrayList;

    private Integer[] imgid = {
            R.drawable.electronics,
            R.drawable.cric,
            R.drawable.household,
            R.drawable.furniture,
            R.drawable.furniture,
            R.drawable.electronics
    };

    private LayoutInflater l_Inflater;

    public ItemListBase4Adapter(Context context, ArrayList<GiveAwayItemDetails> results) {
        itemDetailsrrayList = results;
        l_Inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return itemDetailsrrayList.size();
    }

    public Object getItem(int position) {
        return itemDetailsrrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = l_Inflater.inflate(R.layout.item_details_view, null);
            holder = new ViewHolder();
            holder.txt_itemName = (TextView) convertView.findViewById(R.id.name);
            holder.txt_itemDescription = (TextView) convertView.findViewById(R.id.itemDescription);
            holder.txt_itemPrice = (TextView) convertView.findViewById(R.id.price);
            holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_itemName.setText(itemDetailsrrayList.get(position).getName());
        holder.txt_itemDescription.setText("Subscribed By");
        holder.txt_itemPrice.setText(itemDetailsrrayList.get(position).getSubscribedby());
        if((itemDetailsrrayList.get(position).getitemcategory()).equalsIgnoreCase("furniture"))
            holder.itemImage.setImageResource(imgid[3]);
        else if( (itemDetailsrrayList.get(position).getitemcategory()).equalsIgnoreCase("household"))
            holder.itemImage.setImageResource(imgid[2]);
        else if( (itemDetailsrrayList.get(position).getitemcategory()).equalsIgnoreCase("electronics"))
            holder.itemImage.setImageResource(imgid[0]);
        else if((itemDetailsrrayList.get(position).getitemcategory()).equalsIgnoreCase("sports"))
            holder.itemImage.setImageResource(imgid[1]);
//		imageLoader.DisplayImage("http://192.168.1.28:8082/ANDROID/images/BEVE.jpeg", holder.itemImage);

        return convertView;
    }

    static class ViewHolder {
        TextView txt_itemName;
        TextView txt_itemDescription;
        TextView txt_itemPrice;
        ImageView itemImage;
    }
}
