package befit.example.com.favouriteplaces.Adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ami.fundapter.GenericViewHolder;

public class MyGenericViewHolder extends GenericViewHolder {
    public View root;
    public TextView[] stringFields;
    public ImageView[] dynamicImageFields;
    public ImageView[] staticImageFields;
    public View[] conditionalVisibilityFields;
    public ProgressBar[] progressBarFields;
    public View[] baseFields;
    public CompoundButton[] checkableFields;
    public RelativeLayout rlAdres, rlOpis;
    public TextView tvOpis, tvAddress;
    public ImageView ivItem;

    public MyGenericViewHolder() {
    }
}
