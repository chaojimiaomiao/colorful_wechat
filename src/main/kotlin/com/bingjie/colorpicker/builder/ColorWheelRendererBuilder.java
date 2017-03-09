package com.bingjie.colorpicker.builder;

import com.bingjie.colorpicker.ColorPickerView;
import com.bingjie.colorpicker.renderer.ColorWheelRenderer;
import com.bingjie.colorpicker.renderer.FlowerColorWheelRenderer;
import com.bingjie.colorpicker.renderer.SimpleColorWheelRenderer;

public class ColorWheelRendererBuilder {
	public static ColorWheelRenderer getRenderer(ColorPickerView.WHEEL_TYPE wheelType) {
		switch (wheelType) {
			case CIRCLE:
				return new SimpleColorWheelRenderer();
			case FLOWER:
				return new FlowerColorWheelRenderer();
		}
		throw new IllegalArgumentException("wrong WHEEL_TYPE");
	}
}