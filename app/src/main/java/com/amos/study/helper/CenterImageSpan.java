package com.amos.study.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.style.ImageSpan;

import androidx.annotation.NonNull;

/**
 * @author: mlsnatalie
 * @date: 2020/5/7 6:08 PM
 * @UpdateDate: 2020/5/7 6:08 PM
 * email：mlsnatalie@163.com
 * description：
 */
public class CenterImageSpan extends ImageSpan {
    public CenterImageSpan(@NonNull Bitmap b) {
        super(b);
    }

    public CenterImageSpan(@NonNull Bitmap b, int verticalAlignment) {
        super(b, verticalAlignment);
    }

    public CenterImageSpan(@NonNull Context context, @NonNull Bitmap bitmap) {
        super(context, bitmap);
    }

    public CenterImageSpan(@NonNull Context context, @NonNull Bitmap bitmap, int verticalAlignment) {
        super(context, bitmap, verticalAlignment);
    }

    public CenterImageSpan(@NonNull Drawable drawable) {
        super(drawable);
    }

    public CenterImageSpan(@NonNull Drawable drawable, int verticalAlignment) {
        super(drawable, verticalAlignment);
    }

    public CenterImageSpan(@NonNull Drawable drawable, @NonNull String source) {
        super(drawable, source);
    }

    public CenterImageSpan(@NonNull Drawable drawable, @NonNull String source, int verticalAlignment) {
        super(drawable, source, verticalAlignment);
    }

    public CenterImageSpan(@NonNull Context context, @NonNull Uri uri) {
        super(context, uri);
    }

    public CenterImageSpan(@NonNull Context context, @NonNull Uri uri, int verticalAlignment) {
        super(context, uri, verticalAlignment);
    }

    public CenterImageSpan(@NonNull Context context, int resourceId) {
        super(context, resourceId);
    }

    public CenterImageSpan(@NonNull Context context, int resourceId, int verticalAlignment) {
        super(context, resourceId, verticalAlignment);
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
//        super.draw(canvas, text, start, end, x, top, y, bottom, paint);
        Drawable b = getDrawable();
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        int transY = (y + fm.descent + y + fm.ascent) / 2 - b.getBounds().bottom / 2;//计算y方向的位移
//        val fm = paint.fontMetricsInt
//        val transY = (y + fm.descent + y + fm.ascent) / 2 - b.bounds.bottom / 2//计算y方向的位移
        canvas.save();
        canvas.translate(x, (float) transY);//绘制图片位移一段距离
        b.draw(canvas);
        canvas.restore();
    }
}
