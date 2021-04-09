/*
 * Copyright 2013 Robert Theis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.study.stockcloud.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.study.stockcloud.treemap.AbstractMapLayout;
import com.study.stockcloud.treemap.Mappable;
import com.study.stockcloud.treemap.Rect;
import com.study.stockcloud.treemap.SquarifiedLayout;
import com.study.stockcloud.treemap.TreeModel;


public class MapLayoutView extends View {

    private AbstractMapLayout mapLayout;
    private Mappable[] mappableItems;
    private Paint mRectBackgroundPaint;
    private Paint mRectBorderPaint;
    private Paint mTextPaint;
    private Paint mPricePaint;
    private OnTreeMapItemClickListener onTreeMapItemClickListener;
    int curX = 0, curY = 0;
    private double maxValue;
    private double minValue;

    public MapLayoutView(Context context, AttributeSet attributeSet) {
        super(context);
    }

    public MapLayoutView(Context context, TreeModel model) {
        super(context);

        mapLayout = new SquarifiedLayout();

        mappableItems = model.getTreeItems();//getItems();
        maxValue = model.getBoundary()[0];
        minValue = model.getBoundary()[1];

        // Set up the Paint for the rectangle background
        mRectBackgroundPaint = new Paint();
        mRectBackgroundPaint.setColor(Color.CYAN);
        mRectBackgroundPaint.setStyle(Paint.Style.FILL);

        // Set up the Paint for the rectangle border
        mRectBorderPaint = new Paint();
        mRectBorderPaint.setColor(Color.WHITE);
        mRectBorderPaint.setStyle(Paint.Style.STROKE); // outline the rectangle
        mRectBorderPaint.setStrokeWidth(1); // single-pixel outline

        // Set up the Paint for the text label
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(ViewUtils.sp2px(context.getResources(), 16));

        mPricePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPricePaint.setColor(Color.WHITE);
        mPricePaint.setTextSize(ViewUtils.sp2px(context.getResources(), 12));

    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // Lay out the placement of the rectangles within the area available to this view
        mapLayout.layout(mappableItems, new Rect(0, 0, w, h));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw all the rectangles and their labels
        for (int i = 0; i < mappableItems.length; i++) {
            TreeMapItem item = (TreeMapItem) mappableItems[i];
            mRectBackgroundPaint.setColor(Color.parseColor(item.getColor()));
            drawRectangle(canvas, item.getBoundsRectF());
            drawText(canvas, item.getLabel(), item.getReference(), item.getBoundsRectF());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                canClick = true;
                curX = (int) event.getX();
                curY = (int) event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                if (Math.abs(Math.abs(event.getX()) - curX) > 70 || Math.abs(Math.abs(event.getY()) - curY) > 70) {
                    canClick = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                findRect(curX, curY);
                break;
        }
        return true;
    }


    boolean canClick = false;

    private void findRect(int x, int y) {
        if (mappableItems != null && mappableItems.length > 0) {
            for (int i = 0; i < mappableItems.length; i++) {
                TreeMapItem item = (TreeMapItem) mappableItems[i];
                RectF bounds = item.getBoundsRectF();
                if (x > bounds.left && x < bounds.right && y > bounds.top && y < bounds.bottom) {
                    if (onTreeMapItemClickListener != null)
                        if (canClick) {
                            onTreeMapItemClickListener.onClick(item.getData());
                        }
                }
            }
        }
    }

    private void drawRectangle(Canvas canvas, RectF rectF) {
        // Draw the rectangle's background
        canvas.drawRect(rectF, mRectBackgroundPaint);

        // Draw the rectangle's border
        canvas.drawRect(rectF, mRectBorderPaint);
    }

    private void drawText(Canvas canvas, String text, String precent, RectF rectF) {
        // Don't draw text for small rectangles
//        if (rectF.width() > 50) {
            float textSize = Math.min(rectF.width() / 6, 50);
            mTextPaint.setTextSize(textSize);

            float centerY = rectF.top + rectF.height() / 2;

            float strWidth = mTextPaint.measureText(text);
            canvas.drawText(text, rectF.left + rectF.width() / 2 - strWidth / 2, centerY, mTextPaint);

            float textSize1 = Math.min(rectF.width() / 10, 35);
            mPricePaint.setTextSize(textSize1);
            float strWidth1 = mPricePaint.measureText(precent);
            canvas.drawText(precent, rectF.left + rectF.width() / 2 - strWidth1 / 2, centerY + textSize1 + ViewUtils.dp2px(getResources(), 3), mPricePaint);
//        }
    }

    public void setOnItemClickListener(OnTreeMapItemClickListener onClickListener) {
        this.onTreeMapItemClickListener = onClickListener;
    }

    public interface OnTreeMapItemClickListener<T> {
        void onClick(T t);
    }
}

