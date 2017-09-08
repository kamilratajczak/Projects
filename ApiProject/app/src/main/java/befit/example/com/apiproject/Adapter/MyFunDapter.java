package befit.example.com.apiproject.Adapter;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ami.fundapter.BindDictionary;
import com.ami.fundapter.FunDapterUtils;
import com.ami.fundapter.interfaces.FunDapterFilter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import befit.example.com.apiproject.interfaces.LongExtractor;
import befit.example.com.apiproject.R;
import befit.example.com.apiproject.interfaces.DetailBackground;

/**
 * A generic adapter that takes a BindDictionary and data and shows them. Does
 * basic validation for you for all fields and also handles the ViewHolder
 * pattern.
 *
 * @param <T>
 * @author Ami G
 */
public class MyFunDapter<T> extends BaseAdapter implements Filterable {

    protected List<T> mDataItems;
    protected List<T> mOrigDataItems;
    protected LongExtractor<T> idExtractor;
    protected final Context mContext;
    private final int mLayoutResource;
    private final BindDictionary<T> mBindDictionary;
    private int oddColorRes;
    private int evenColorRes;
    private FunDapterFilter<T> funDapterFilter;
    private Filter mFilter;
    private SharedPreferences sp;
    private ArrayList<String> alImiona = new ArrayList<String>();
    private boolean mTwoPane;
    private DetailBackground detailBackground;

    /**
     * A generic adapter that takes a BindDictionary and data and shows them.
     * Does basic validation for you for all fields and also handles the
     * ViewHolder pattern.
     *
     * @param context
     * @param dataItems      - An arraylist of model items
     * @param layoutResource - resource ID of a layout to inflate for each item. (Example:
     *                       R.layout.list_item)
     * @param dictionary     - The dictionary that will match between fields and data.
     */
    public MyFunDapter(Context context, List<T> dataItems, int layoutResource,
                       BindDictionary<T> dictionary, boolean mTwoPane, DetailBackground detailBackground) {
        this(context, dataItems, layoutResource, null, dictionary, mTwoPane, detailBackground);
    }

    /**
     * A generic adapter that takes a BindDictionary and data and shows them.
     * Does basic validation for you for all fields and also handles the
     * ViewHolder pattern.
     *
     * @param context
     * @param dataItems      - An arraylist of model items
     * @param layoutResource - resource ID of a layout to inflate for each item. (Example:
     *                       R.layout.list_item)
     * @param idExtractor    - The extractor that will get stable ids from the data item
     * @param dictionary     - The dictionary that will match between fields and data.
     */


    public MyFunDapter(Context context, List<T> dataItems, int layoutResource,
                       LongExtractor<T> idExtractor, BindDictionary<T> dictionary, boolean mTwoPane, DetailBackground detailBackground) {
        this.mContext = context;
        this.mDataItems = dataItems;
        this.mOrigDataItems = dataItems;
        this.mLayoutResource = layoutResource;
        this.idExtractor = idExtractor;
        this.mBindDictionary = dictionary;
        this.mTwoPane = mTwoPane;
        this.detailBackground = detailBackground;

    }

    /**
     * Replace the current dataset with a new one and refresh the views. This
     * will call notifyDataSetChanged() for you.
     *
     * @param dataItems
     */
    public void updateData(List<T> dataItems) {
        this.mDataItems = dataItems;
        this.mOrigDataItems = dataItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mDataItems == null || mBindDictionary == null) return 0;

        return mDataItems.size();
    }

    @Override
    public T getItem(int position) {
        return mDataItems.get(position);
    }

    @Override
    public boolean hasStableIds() {
        if (idExtractor == null) return super.hasStableIds();
        else return true;
    }

    @Override
    public long getItemId(int position) {
        if (idExtractor == null) return position;
        else return idExtractor.getLongValue(getItem(position), position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Inflate a new view or use a recycled view.
        View v = convertView;
        final MyGenericViewHolder holder;
        if (null == v) {
            LayoutInflater vi =
                    (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(mLayoutResource, null);
            holder = new MyGenericViewHolder();
            holder.root = v;
            holder.tvImie = (TextView) v.findViewById(R.id.tvImie);
            holder.tvId = (TextView) v.findViewById(R.id.tvId);
            holder.ivUlubione = (ImageView) v.findViewById(R.id.ivUlubione);
            holder.rlListItem = (RelativeLayout) v.findViewById(R.id.rlListItem);

            // init the sub views and put them in a holder instance
            FunDapterUtils.initViews(v, holder, mBindDictionary);

            v.setTag(holder);
        } else {
            holder = (MyGenericViewHolder) v.getTag();
        }

        // Show the data
        final T item = getItem(position);
        showData(item, holder, position);

        sp = mContext.getSharedPreferences("App_names", Context.MODE_PRIVATE);

        pobierzImiona();

        setImage(holder);

        ivUlubione(holder);

        if(mTwoPane)
        setBackground(holder);

        v.setTag(R.id.ID_TAG, holder.tvId.getText().toString());

        return v;
    }

    private void setBackground(MyGenericViewHolder holder) {

        String selectedId = sp.getString("selected", null);
        if(selectedId != null){
            if (holder.tvId.getText().toString().equals(selectedId)) {
                holder.rlListItem.setBackgroundColor(Color.parseColor("#bbbbbb"));
            }
            else
                holder.rlListItem.setBackgroundColor(Color.parseColor("#cccccc"));
        }


    }

    private void setImage(MyGenericViewHolder holder) {

        if(alImiona.size()>0) {
            for (int i = 0; i < alImiona.size(); i++) {
                if (holder.tvId.getText().toString().equals(alImiona.get(i))) {
                    holder.ivUlubione.setImageResource(R.drawable.ic_favorite_black_24dp);
                    holder.ivUlubione.setTag(R.id.RESOURCE_TAG, "black");
                    break;
                } else {
                    holder.ivUlubione.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    holder.ivUlubione.setTag(R.id.RESOURCE_TAG, "border");
                }
            }
        }
        else{
            holder.ivUlubione.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            holder.ivUlubione.setTag(R.id.RESOURCE_TAG, "border");
        }

    }

    private void ivUlubione(final MyGenericViewHolder holder) {

        holder.ivUlubione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.ivUlubione.getTag(R.id.RESOURCE_TAG).equals("border")) {
                    holder.ivUlubione.setImageResource(R.drawable.ic_favorite_black_24dp);
                    holder.ivUlubione.setTag(R.id.RESOURCE_TAG, "black");

                    alImiona.add(holder.tvId.getText().toString());

                    zapiszImie();

                    Toast.makeText(mContext, "Dodano do ulubionych", Toast.LENGTH_LONG).show();
                }

                else{
                    holder.ivUlubione.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    holder.ivUlubione.setTag(R.id.RESOURCE_TAG, "border");

                    alImiona.remove(holder.tvId.getText().toString());

                    sp.edit().remove("DATE_NAME").commit();

                    zapiszImie();

                    Toast.makeText(mContext, "Usunięto z ulubionych", Toast.LENGTH_LONG).show();
                }

                if(mTwoPane)
                detailBackground.change();
            }
        });

    }

    private void zapiszImie() {

        SharedPreferences.Editor editor = sp.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(alImiona);
        editor.putStringSet("DATE_NAME", set);
        editor.apply();

    }

    private void pobierzImiona() {

        Set<String> set = sp.getStringSet("DATE_NAME", null);
        if(set != null) {
            alImiona.clear();
            alImiona.addAll(set);
        }
    }

    private void showData(T item, MyGenericViewHolder holder, int position) {

        // handles alternating background colors if selected
        if (oddColorRes > 0 && evenColorRes > 0) {
            if (position % 2 == 0) {
                holder.root.setBackgroundColor(mContext.getResources().getColor(evenColorRes));
            } else {
                holder.root.setBackgroundColor(mContext.getResources().getColor(oddColorRes));
            }
        }

        FunDapterUtils.showData(item, holder, position, mBindDictionary);
    }

    public MyFunDapter<T> setAlternatingBackground(int oddColorRes, int evenColorRes) {

        if (oddColorRes <= 0 || evenColorRes <= 0) {
            throw new IllegalArgumentException("Color parameters are illegal");
        }

        this.oddColorRes = oddColorRes;
        this.evenColorRes = evenColorRes;

        return this;
    }

    /**
     * @param idExtractor - used to extract a stable id from the underlying data item (like a database id or something)
     */
    public void setIdExtractor(LongExtractor<T> idExtractor) {
        this.idExtractor = idExtractor;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    /**
     * Use this method to enable filtering in the adapter.
     *
     * @param filter - a filter implementation for your adapter.
     */
    public void initFilter(FunDapterFilter<T> filter) {

        if (filter == null)
            throw new IllegalArgumentException("Cannot pass a null filter to FunDapter");

        this.funDapterFilter = filter;

        mFilter = new Filter() {

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                @SuppressWarnings("unchecked") List<T> list = (List<T>) results.values;

                if (results.count == 0) {
                    resetData();
                } else {
                    mDataItems = list;
                }

                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                if (constraint == null || constraint.length() == 0) {

                    // No constraint - no point in filtering.
                    results.values = mOrigDataItems;
                    results.count = mOrigDataItems.size();
                } else {
                    // Perform the filtering operation

                    List<T> filter =
                            funDapterFilter.filter(constraint.toString(), mOrigDataItems);

                    results.count = filter.size();
                    results.values = filter;

                }

                return results;
            }
        };
    }



    public void resetData() {
        mDataItems = mOrigDataItems;
    }
}