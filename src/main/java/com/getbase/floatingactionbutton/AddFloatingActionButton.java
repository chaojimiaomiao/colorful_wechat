package com.getbase.floatingactionbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;

import com.rarnu.tophighlight.R;

public class AddFloatingActionButton extends FloatingActionButton {
  Context mContext;
  int mPlusColor;

  public AddFloatingActionButton(Context context) {
    this(context, null);
  }

  public AddFloatingActionButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    mContext = context;
  }

  public AddFloatingActionButton(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    mContext = context;
  }

  @Override
  void init(Context context, AttributeSet attributeSet) {
    TypedArray attr = context.obtainStyledAttributes(attributeSet, R.styleable.AddFloatingActionButton, 0, 0);
    mPlusColor = attr.getColor(R.styleable.AddFloatingActionButton_fab_plusIconColor, getColor(android.R.color.white));
    attr.recycle();

    mContext = context;
    super.init(context, attributeSet);
  }

  /**
   * @return the current Color of plus icon.
   */
  public int getPlusColor() {
    return mPlusColor;
  }

  public void setPlusColorResId(@ColorRes int plusColor) {
    setPlusColor(getColor(plusColor));
  }

  public void setPlusColor(int color) {
    if (mPlusColor != color) {
      mPlusColor = color;
      updateBackground();
    }
  }

  @Override
  public void setIcon(@DrawableRes int icon) {
    throw new UnsupportedOperationException("Use FloatingActionButton if you want to use custom icon");
  }

  @Override
  Drawable getIconDrawable() {
    /*final float iconSize = getDimension(R.dimen.fab_icon_size);
    final float iconHalfSize = iconSize / 2f;

    final float plusSize = getDimension(R.dimen.fab_plus_icon_size);
    final float plusHalfStroke = getDimension(R.dimen.fab_plus_icon_stroke) / 2f;
    final float plusOffset = (iconSize - plusSize) / 2f;

    final Shape shape = new Shape() {
      @Override
      public void draw(Canvas canvas, Paint paint) {
        canvas.drawRect(0, iconHalfSize - plusHalfStroke, iconSize - plusOffset, iconHalfSize + plusHalfStroke, paint);
        canvas.drawRect(iconHalfSize - plusHalfStroke, plusOffset, iconHalfSize + plusHalfStroke, iconSize - plusOffset, paint);
      }
    };

    ShapeDrawable drawable = new ShapeDrawable(shape);

    final Paint paint = drawable.getPaint();
    paint.setColor(mPlusColor);
    paint.setStyle(Style.FILL);
    paint.setAntiAlias(true);*/


    return mContext.getResources().getDrawable(R.drawable.baguamiao);
    //return drawable;
  }
}
