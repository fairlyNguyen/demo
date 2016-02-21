package com.example.fairlynguyen.myapplication.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fairlynguyen.myapplication.R;
import com.example.fairlynguyen.myapplication.implments.SwipeItemMangerImpl;
import com.example.fairlynguyen.myapplication.interfaces.SwipeAdapterInterface;
import com.example.fairlynguyen.myapplication.interfaces.SwipeItemMangerInterface;
import com.example.fairlynguyen.myapplication.util.Attributes;
import com.example.fairlynguyen.myapplication.widget.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fairlyNGUYEN on 2/21/2016.
 */
public class CustomListViewAdapter extends ArrayAdapter implements SwipeItemMangerInterface, SwipeAdapterInterface {
    private SwipeItemMangerImpl mItemManger;
    private Context mContext;
    private ArrayList<String> mListData;
    private int mResource;

    public CustomListViewAdapter(Context context, int resource, ArrayList<String> listData) {
        super(context, resource, listData);
        this.mContext = context;
        this.mListData = listData;
        this.mResource = resource;
        mItemManger = new SwipeItemMangerImpl(this);
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public void notifyDatasetChanged() {
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(mResource, parent, false);

            holder.mSwipeLayout = (SwipeLayout) convertView.findViewById(getSwipeLayoutResourceId(position));
            holder.mTitle = (TextView) convertView.findViewById(R.id.titleItem);
            holder.mIconRemove = (ImageView) convertView.findViewById(R.id.iconTrash);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mSwipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        holder.mTitle.setText(mListData.get(position));
        holder.mIconRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListData.remove(position);
                notifyDataSetChanged();
                mItemManger.closeItem(position);
                Toast.makeText(mContext, "Item deleted!", Toast.LENGTH_SHORT).show();
            }
        });
        holder.mSwipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getOpenItems().get(0) != -1) {
                    mItemManger.closeItem(getOpenItems().get(0));
                } else {
                    Toast.makeText(mContext, mListData.get(position), Toast.LENGTH_SHORT).show();
                }
            }
        });
        mItemManger.bind(convertView, position);

        return convertView;
    }

    @Override
    public void openItem(int position) {
        mItemManger.openItem(position);
    }

    @Override
    public void closeItem(int position) {
        mItemManger.closeItem(position);
    }

    @Override
    public void closeAllExcept(SwipeLayout layout) {
        mItemManger.closeAllExcept(layout);
    }

    @Override
    public void closeAllItems() {
        mItemManger.closeAllItems();
    }

    @Override
    public List<Integer> getOpenItems() {
        return mItemManger.getOpenItems();
    }

    @Override
    public List<SwipeLayout> getOpenLayouts() {
        return mItemManger.getOpenLayouts();
    }

    @Override
    public void removeShownLayouts(SwipeLayout layout) {
        mItemManger.removeShownLayouts(layout);
    }

    @Override
    public boolean isOpen(int position) {
        return mItemManger.isOpen(position);
    }

    @Override
    public Attributes.Mode getMode() {
        return mItemManger.getMode();
    }

    @Override
    public void setMode(Attributes.Mode mode) {
        mItemManger.setMode(mode);
    }

    class ViewHolder {
        private SwipeLayout mSwipeLayout;
        private TextView mTitle;
        private ImageView mIconRemove;
    }
}
