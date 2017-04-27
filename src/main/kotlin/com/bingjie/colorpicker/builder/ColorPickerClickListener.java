package com.bingjie.colorpicker.builder;

/**
 * Created by Charles Anderson on 4/17/15.
 */
public interface ColorPickerClickListener {
    //void onClick(DialogInterface d, int lastSelectedColor, Integer[] allColors);


    void onClick(int lastSelectedColor, Integer[] allColors);
}
