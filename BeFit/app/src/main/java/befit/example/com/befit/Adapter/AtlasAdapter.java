package befit.example.com.befit.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ami.fundapter.BindDictionary;
import com.ami.fundapter.FunDapterUtils;
import com.ami.fundapter.interfaces.FunDapterFilter;

import java.util.ArrayList;
import java.util.List;

import befit.example.com.befit.R;
import interfaces.LongExtractor;
import interfaces.MyAdapterClickInterface;

/**
 * A generic adapter that takes a BindDictionary and data and shows them. Does
 * basic validation for you for all fields and also handles the ViewHolder
 * pattern.
 *
 * @param <T>
 * @author Ami G
 */
public class AtlasAdapter<T> extends BaseAdapter implements Filterable {

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
    public AtlasAdapter(Context context, List<T> dataItems, int layoutResource,
                        BindDictionary<T> dictionary) {
        this(context, dataItems, layoutResource, null, dictionary);
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


    public AtlasAdapter(Context context, List<T> dataItems, int layoutResource,
                        LongExtractor<T> idExtractor, BindDictionary<T> dictionary) {
        this.mContext = context;
        this.mDataItems = dataItems;
        this.mOrigDataItems = dataItems;
        this.mLayoutResource = layoutResource;
        this.idExtractor = idExtractor;
        this.mBindDictionary = dictionary;

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

            holder.tvAtlasDescription = (TextView) v.findViewById(R.id.tvAtlasDescription);
            holder.tvAtlasName = (TextView) v.findViewById(R.id.tvAtlasName);
            holder.atlasLayout = (RelativeLayout) v.findViewById(R.id.atlasLayout);
            holder.atlasDescriptionLayout = (RelativeLayout) v.findViewById(R.id.atlasDescriptionLayout);

            // init the sub views and put them in a holder instance
            FunDapterUtils.initViews(v, holder, mBindDictionary);

            v.setTag(holder);
        } else {
            holder = (MyGenericViewHolder) v.getTag();
        }

        // Show the data
        final T item = getItem(position);
        showData(item, holder, position);

        holder.atlasDescriptionLayout.setVisibility(View.GONE);

        holder.atlasLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.atlasDescriptionLayout.getVisibility() == View.VISIBLE)
                    holder.atlasDescriptionLayout.setVisibility(View.GONE);
                else
                    holder.atlasDescriptionLayout.setVisibility(View.VISIBLE);
            }
        });

        return v;

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

    public AtlasAdapter<T> setAlternatingBackground(int oddColorRes, int evenColorRes) {

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