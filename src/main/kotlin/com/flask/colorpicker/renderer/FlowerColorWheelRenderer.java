package com.flask.colorpicker.renderer;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.flask.colorpicker.ColorCircle;
import com.flask.colorpicker.builder.PaintBuilder;

/**
 * HSV是 色相hue,饱和度saturation,明度value
 * 这是一个圆柱形的色彩模型,平面只是其中的一层, 纵向是明度不能表示,所以用下面的滑块来改变
 * hsv[2] = colorWheelRenderOption.lightness;可以看出明度固定
 * 若要减少深色,仅留浅色。外圈饱和度高的可适当去掉
 * float radius = maxRadius * p; hsv[1] = radius / maxRadius; 由此可看出hsv[1] = p囧
 */
public class FlowerColorWheelRenderer extends AbsColorWheelRenderer {
	private Paint selectorFill = PaintBuilder.newPaint().build();
	private float[] hsv = new float[3];
	private float sizeJitter = 1.2f;

	@Override
	public void draw() {
		final int setSize = colorCircleList.size();
		int currentCount = 0;
		float half = colorWheelRenderOption.targetCanvas.getWidth() / 2f;
		int density = colorWheelRenderOption.density;
		float strokeWidth = colorWheelRenderOption.strokeWidth;
		float maxRadius = colorWheelRenderOption.maxRadius;
		float cSize = colorWheelRenderOption.cSize;

		//i的取值范围[0, density-1]。
		for (int i = 0; i < density - 6; i++) {//根据密度决定画几周
			float p = (float) i / (density - 1); // 0~1
			float jitter = (i - density / 2f) / density; // -0.5 ~ 0.5
			float radius = maxRadius * p;
			float size = Math.max(1.5f + strokeWidth, cSize + (i == 0 ? 0 : cSize * sizeJitter * jitter));
			int total = Math.min(calcTotalCount(radius, size), density * 2);

			for (int j = 0; j < total; j++) {//色轮一周,每周的饱和度固定
				double angle = Math.PI * 2 * j / total + (Math.PI / total) * ((i + 1) % 2);
				float x = half + (float) (radius * Math.cos(angle));
				float y = half + (float) (radius * Math.sin(angle));
				hsv[0] = (float) (angle * 180 / Math.PI);
				hsv[1] = radius / maxRadius;
				Log.e("有没有重复的饱和度值","hsv[1]: " + hsv[1]);
				hsv[2] = colorWheelRenderOption.lightness;
				selectorFill.setColor(Color.HSVToColor(hsv));
				selectorFill.setAlpha(getAlphaValueAsInt());

				colorWheelRenderOption.targetCanvas.drawCircle(x, y, size - strokeWidth, selectorFill);

				if (currentCount >= setSize)
					colorCircleList.add(new ColorCircle(x, y, hsv));
				else colorCircleList.get(currentCount).set(x, y, hsv);
				currentCount++;
			}
		}
	}
}