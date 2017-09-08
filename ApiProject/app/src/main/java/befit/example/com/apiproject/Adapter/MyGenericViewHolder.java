package befit.example.com.apiproject.Adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    public TextView tvImie, tvId;
    public ImageView ivUlubione;
    public RelativeLayout rlListItem;

    public MyGenericViewHolder() {
    }
}
