package mediseen.helpers;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by elysi on 2/21/2016.
 */
public class ChangeVisibilityHelper {
    public static void changeVisibility(ViewGroup visible, ViewGroup gone) {
        visible.setVisibility(View.VISIBLE);
        for (int i = 0; i < visible.getChildCount(); i++) {
            View child = visible.getChildAt(i);
            child.setVisibility(View.VISIBLE);
            child.postInvalidate();
        }
        gone.setVisibility(View.GONE);

        for (int i = 0; i < gone.getChildCount(); i++) {
            View child = gone.getChildAt(i);
            child.setVisibility(View.GONE);
            child.postInvalidate();
        }
        visible.clearAnimation();
        visible.startLayoutAnimation();

    }
}
