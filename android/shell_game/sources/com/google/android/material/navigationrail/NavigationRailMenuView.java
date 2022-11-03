package com.google.android.material.navigationrail;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;
/* loaded from: classes.dex */
public class NavigationRailMenuView extends NavigationBarMenuView {
    private int itemMinimumHeight = -1;
    private final FrameLayout.LayoutParams layoutParams;

    public NavigationRailMenuView(Context context) {
        super(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        this.layoutParams = layoutParams;
        layoutParams.gravity = 49;
        setLayoutParams(layoutParams);
        setItemActiveIndicatorResizeable(true);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int size = View.MeasureSpec.getSize(i2);
        int size2 = getMenu().getVisibleItems().size();
        if (size2 <= 1 || !isShifting(getLabelVisibilityMode(), size2)) {
            i3 = measureSharedChildHeights(i, size, size2, null);
        } else {
            i3 = measureShiftingChildHeights(i, size, size2);
        }
        setMeasuredDimension(View.resolveSizeAndState(View.MeasureSpec.getSize(i), i, 0), View.resolveSizeAndState(i3, i2, 0));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                int measuredHeight = childAt.getMeasuredHeight() + i6;
                childAt.layout(0, i6, i5, measuredHeight);
                i6 = measuredHeight;
            }
        }
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuView
    protected NavigationBarItemView createNavigationBarItemView(Context context) {
        return new NavigationRailItemView(context);
    }

    private int makeSharedHeightSpec(int i, int i2, int i3) {
        int max = i2 / Math.max(1, i3);
        int i4 = this.itemMinimumHeight;
        if (i4 == -1) {
            i4 = View.MeasureSpec.getSize(i);
        }
        return View.MeasureSpec.makeMeasureSpec(Math.min(i4, max), 0);
    }

    private int measureShiftingChildHeights(int i, int i2, int i3) {
        int i4;
        View childAt = getChildAt(getSelectedItemPosition());
        if (childAt != null) {
            i4 = measureChildHeight(childAt, i, makeSharedHeightSpec(i, i2, i3));
            i2 -= i4;
            i3--;
        } else {
            i4 = 0;
        }
        return i4 + measureSharedChildHeights(i, i2, i3, childAt);
    }

    private int measureSharedChildHeights(int i, int i2, int i3, View view) {
        int i4;
        makeSharedHeightSpec(i, i2, i3);
        if (view == null) {
            i4 = makeSharedHeightSpec(i, i2, i3);
        } else {
            i4 = View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 0);
        }
        int childCount = getChildCount();
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt != view) {
                i5 += measureChildHeight(childAt, i, i4);
            }
        }
        return i5;
    }

    private int measureChildHeight(View view, int i, int i2) {
        if (view.getVisibility() == 8) {
            return 0;
        }
        view.measure(i, i2);
        return view.getMeasuredHeight();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMenuGravity(int i) {
        if (this.layoutParams.gravity != i) {
            this.layoutParams.gravity = i;
            setLayoutParams(this.layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMenuGravity() {
        return this.layoutParams.gravity;
    }

    public void setItemMinimumHeight(int i) {
        if (this.itemMinimumHeight != i) {
            this.itemMinimumHeight = i;
            requestLayout();
        }
    }

    public int getItemMinimumHeight() {
        return this.itemMinimumHeight;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isTopGravity() {
        return (this.layoutParams.gravity & 112) == 48;
    }
}
