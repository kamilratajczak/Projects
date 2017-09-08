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
import com.ami.fundapter.extractors.IntegerExtractor;
import com.ami.fundapter.interfaces.FunDapterFilter;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
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
    private ArrayList<String> products = new ArrayList<String>();
    private ArrayList<String> size = new ArrayList<String>();
    private MyAdapterClickInterface checkedListener;
    private int ide;
    private String ideStr;

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
                       BindDictionary<T> dictionary, MyAdapterClickInterface checkedListener) {
        this(context, dataItems, layoutResource, null, dictionary, checkedListener);
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
                       LongExtractor<T> idExtractor, BindDictionary<T> dictionary, MyAdapterClickInterface checkedListener) {
        this.mContext = context;
        this.mDataItems = dataItems;
        this.mOrigDataItems = dataItems;
        this.mLayoutResource = layoutResource;
        this.idExtractor = idExtractor;
        this.mBindDictionary = dictionary;
        this.checkedListener = checkedListener;

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

            holder.tvSearchFat = (TextView) v.findViewById(R.id.tvSearchFat);
            holder.tvSearchCalories = (TextView) v.findViewById(R.id.tvSearchCalories);
            holder.tvSearchCarbs = (TextView) v.findViewById(R.id.tvSearchCarbs);
            holder.tvSearchProtein = (TextView) v.findViewById(R.id.tvSearchProtein);
            holder.tvSearchSize = (TextView) v.findViewById(R.id.tvSearchSize);
            holder.tvProductsName = (TextView) v.findViewById(R.id.tvProductsName);
            holder.tvProductsWeight = (TextView) v.findViewById(R.id.tvProductsWeight);
            holder.tvProductsWeight2 = (TextView) v.findViewById(R.id.tvProductsWeight2);
            holder.layoutInvisible = (RelativeLayout) v.findViewById(R.id.layoutInvisible);
            holder.checkBox = (CheckBox) v.findViewById(R.id.checkBox);
            holder.numberPicker = (NumberPicker) v.findViewById(R.id.numberPicker);
            holder.numberPickerLayout = (RelativeLayout) v.findViewById(R.id.numberPickerLayout);
            holder.layoutSearchMeals = (RelativeLayout) v.findViewById(R.id.layoutSearchMeals);
            holder.quantityLayout = (RelativeLayout) v.findViewById(R.id.quantityLayout);

            // init the sub views and put them in a holder instance
            FunDapterUtils.initViews(v, holder, mBindDictionary);

            v.setTag(holder);
        } else {
            holder = (MyGenericViewHolder) v.getTag();
        }

        // Show the data
        final T item = getItem(position);
        showData(item, holder, position);

        holder.layoutInvisible.setVisibility(View.GONE);
        holder.numberPickerLayout.setVisibility(View.GONE);

        layoutSearchMeals(holder);
        quantityLayout(holder);
        checkBox(holder, position);
        numberPicker(holder);

        if(products.size() > 0) {
            for(int i = 0; i<products.size(); i++) {
                if (holder.tvProductsName.getText().toString().equals(products.get(i).toString())) {
                    holder.checkBox.setChecked(true);
                    break;
                }
                else
                    holder.checkBox.setChecked(false);

            }
        }

        return v;
    }

    private void numberPicker(final MyGenericViewHolder holder) {


        holder.numberPicker.setMaxValue(500);
        holder.numberPicker.setMinValue(1);
        holder.numberPicker.setValue(100);
        holder.numberPicker.setWrapSelectorWheel(true);

        holder.numberPicker.setTag(R.id.CALORIES_TAG, holder.tvSearchCalories.getText().toString());
        holder.numberPicker.setTag(R.id.FAT_TAG, holder.tvSearchFat.getText().toString());
        holder.numberPicker.setTag(R.id.CARBS_TAG, holder.tvSearchCarbs.getText().toString());
        holder.numberPicker.setTag(R.id.PROTEIN_TAG, holder.tvSearchProtein.getText().toString());

        holder.numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                holder.tvSearchSize.setText(newVal + "");

                String calories = String.valueOf(picker.getTag(R.id.CALORIES_TAG));
                float caloriesF = Float.parseFloat(calories);
                float newCalories = caloriesF*newVal/100;

                String fat = String.valueOf(picker.getTag(R.id.FAT_TAG));
                float fatF = Float.parseFloat(fat);
                float newFat = fatF*newVal/100;

                String carbs = String.valueOf(picker.getTag(R.id.CARBS_TAG));
                float carbsF = Float.parseFloat(carbs);
                float newCarbs = carbsF*newVal/100;

                String protein = String.valueOf(picker.getTag(R.id.PROTEIN_TAG));
                float proteinF = Float.parseFloat(protein);
                float newProtein = proteinF*newVal/100;

                holder.tvSearchCalories.setText(String.format("%.1f", newCalories));
                holder.tvSearchFat.setText(String.format("%.1f", newFat));
                holder.tvSearchCarbs.setText(String.format("%.1f", newCarbs));
                holder.tvSearchProtein.setText(String.format("%.1f", newProtein));
                holder.tvProductsWeight.setText(newVal + "");
                holder.tvProductsWeight2.setText(String.format("%.1f", newCalories));

                if(products.size() > 0) {
                    for (int i = 0; i < products.size(); i ++){
                        if(holder.tvProductsName.getText().toString().equals(products.get(i))){
                            holder.checkBox.setChecked(false);
                            products.remove(i);
                            size.remove(i);
                            checkedListener.changeMenu();
                        }
                    }
                }

            }
        });

    }

    private void checkBox(final MyGenericViewHolder holder, final int position) {

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameOfProduct = holder.tvProductsName.getText().toString();
                String sizeOfProduct = holder.tvSearchSize.getText().toString();

                if(holder.checkBox.isChecked()) {

                    if (products.size() < 7) {

                        products.add(nameOfProduct);

                        size.add(sizeOfProduct);

                        checkedListener.changeMenu();

                    }
                    else {
                        Toast.makeText(mContext, "Max 7 products in one time", Toast.LENGTH_LONG).show();
                        holder.checkBox.setChecked(false);
                    }
                }

                else{

                    products.remove(nameOfProduct);

                    size.remove(sizeOfProduct);

                    checkedListener.changeMenu();

                }}
        });

    }


    private void layoutSearchMeals(final MyGenericViewHolder holder) {

        holder.layoutSearchMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.layoutInvisible.getVisibility() == View.VISIBLE)
                    holder.layoutInvisible.setVisibility(View.GONE);
                else
                    holder.layoutInvisible.setVisibility(View.VISIBLE);

            }
        });

    }

    private void quantityLayout(final MyGenericViewHolder holder) {

        holder.quantityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.numberPickerLayout.getVisibility() == View.VISIBLE)
                    holder.numberPickerLayout.setVisibility(View.GONE);
                else
                    holder.numberPickerLayout.setVisibility(View.VISIBLE);

            }
        });

    }

    public ArrayList getClickedProducts(){

        return products;
    }

    public ArrayList getClickedSize(){

        return size;
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