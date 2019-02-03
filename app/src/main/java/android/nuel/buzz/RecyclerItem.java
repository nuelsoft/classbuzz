package android.nuel.buzz;

import android.view.View;
import android.widget.ImageView;

public class RecyclerItem {
    private View layout;
    private ImageView isSelected;

    public RecyclerItem(View layout, ImageView isSelected) {
        this.layout = layout;
        this.isSelected = isSelected;
    }

    public View getLayout() {
        return layout;
    }

    public ImageView getIsSelected() {
        return isSelected;
    }
}
