package com.amos.study.helper;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Layout;
import android.text.NoCopySpan;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.BaseMovementMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.textclassifier.TextLinks;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


import com.amos.study.R;

import java.util.prefs.PreferencesFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: mlsnatalie
 * @date: 2020/5/7 6:24 PM
 * @UpdateDate: 2020/5/7 6:24 PM
 * email：mlsnatalie@163.com
 * description：
 */
public class StockStringBuilderHelper {

    public static SpannableStringBuilder setSpan(Context mContext, String contentText) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(contentText);

//        String pattern1 = "(?:\\$)(.*?)\\(([A-Za-z]{2})(\\d{6})\\)(?:\\$)";
        String pattern = "(?:\\$)([^$]*?)\\(([A-Za-z]{2})(\\d{6})\\)(?:\\$)";
//        String pattern = "(?:\\$)([^$]*?)\\(([a-zA-Z]{2,5})\\|(([^$]*?){2,10})\\)(?:\\$)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(ssb);
        int i = 0;
        int start;
        int end;
        while(m.find()){
            i = i + 1;
            if (i > 1) {
                start = m.start() - 11 * (i - 1);
                end = m.end() - 11 * (i - 1);
            } else {
                start = m.start();
                end = m.end();
            }
      /*      ContractVo contractVo = new ContractVo();

            contractVo.setMarket(ssb.subSequence(end - 10, end - 8).toString().toLowerCase());
            contractVo.setCode(ssb.subSequence(end - 8, end - 2).toString());*/
            ssb.replace(start, end, ssb.subSequence(start, end - 11));
            CenterImageSpan imageSpan = new CenterImageSpan(mContext, R.mipmap.icon_stock_icon, ImageSpan.ALIGN_BASELINE);
            ssb.setSpan(imageSpan, start, start + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            ssb.setSpan(new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    Toast.makeText(widget.getContext(),"点击事件",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                    ds.setColor(Color.parseColor("#3683FF"));
                }
            }, start, end - 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //SPAN_EXCLUSIVE_EXCLUSIVE
        }
        return ssb;
    }

    /*public static SpannableStringBuilder setSpan(Context mContext, String contentText, OnClickListenerCallBack callBack) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(contentText);

//        String pattern1 = "(?:\\$)(.*?)\\(([A-Za-z]{2})(\\d{6})\\)(?:\\$)";
        String pattern = "(?:\\$)([^$]*?)\\(([A-Za-z]{2})(\\d{6})\\)(?:\\$)";
//        String pattern = "(?:\\$)([^$]*?)\\(([a-zA-Z]{2,5})\\|(([^$]*?){2,10})\\)(?:\\$)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(ssb);
        int i = 0;
        int start;
        int end;
        while(m.find()){
            i = i + 1;
            if (i > 1) {
                start = m.start() - 11 * (i - 1);
                end = m.end() - 11 * (i - 1);
            } else {
                start = m.start();
                end = m.end();
            }
           *//* ContractVo contractVo = new ContractVo();

            contractVo.setMarket(ssb.subSequence(end - 10, end - 8).toString().toLowerCase());
            contractVo.setCode(ssb.subSequence(end - 8, end - 2).toString());*//*
            ssb.replace(start, end, ssb.subSequence(start, end - 11));
            CenterImageSpan imageSpan = new CenterImageSpan(mContext, R.mipmap.icon_stock_icon, ImageSpan.ALIGN_BASELINE);
            ssb.setSpan(imageSpan, start, start + 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            ssb.setSpan(new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    *//*JumpUtil.jumpToStockDetail(mContext, contractVo);
                    callBack.onSuccess();*//*
                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                    ds.setColor(ContextCompat.getColor(mContext, R.color.color_3683FF));
                }
            }, start, end - 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ssb;
    }*/

    /**
     * 点击行情回调
     */
    public interface OnClickListenerCallBack {
        void onSuccess();
    }

    public static class CustomLinkMovementMethod extends BaseMovementMethod {
        private static final int CLICK = 1;
        private static final int UP = 2;
        private static final int DOWN = 3;

        private static final int HIDE_FLOATING_TOOLBAR_DELAY_MS = 200;

        @Override
        public boolean canSelectArbitrarily() {
            return false;
        }

        @Override
        protected boolean handleMovementKey(TextView widget, Spannable buffer, int keyCode,
                                            int movementMetaState, KeyEvent event) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_CENTER:
                case KeyEvent.KEYCODE_ENTER:
                    if (KeyEvent.metaStateHasNoModifiers(movementMetaState)) {
                        if (event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getRepeatCount() == 0 && action(CLICK, widget, buffer)) {
                            return true;
                        }
                    }
                    break;
            }
            return super.handleMovementKey(widget, buffer, keyCode, movementMetaState, event);
        }

        /*@Override
        protected boolean up(TextView widget, Spannable buffer) {
            if (action(UP, widget, buffer)) {
                return true;
            }

            return super.up(widget, buffer);
        }

        @Override
        protected boolean down(TextView widget, Spannable buffer) {
            if (action(DOWN, widget, buffer)) {
                return true;
            }

            return super.down(widget, buffer);
        }

        @Override
        protected boolean left(TextView widget, Spannable buffer) {
            if (action(UP, widget, buffer)) {
                return true;
            }

            return super.left(widget, buffer);
        }

        @Override
        protected boolean right(TextView widget, Spannable buffer) {
            if (action(DOWN, widget, buffer)) {
                return true;
            }

            return super.right(widget, buffer);
        }*/

        @Override
        public boolean onGenericMotionEvent(TextView widget, Spannable text, MotionEvent event) {
            return false;
        }

        private boolean action(int what, TextView widget, Spannable buffer) {
            Layout layout = widget.getLayout();

            int padding = widget.getTotalPaddingTop() +
                    widget.getTotalPaddingBottom();
            int areaTop = widget.getScrollY();
            int areaBot = areaTop + widget.getHeight() - padding;

            int lineTop = layout.getLineForVertical(areaTop);
            int lineBot = layout.getLineForVertical(areaBot);

            int first = layout.getLineStart(lineTop);
            int last = layout.getLineEnd(lineBot);

            ClickableSpan[] candidates = buffer.getSpans(first, last, ClickableSpan.class);

            int a = Selection.getSelectionStart(buffer);
            int b = Selection.getSelectionEnd(buffer);

            int selStart = Math.min(a, b);
            int selEnd = Math.max(a, b);

            if (selStart < 0) {
                if (buffer.getSpanStart(FROM_BELOW) >= 0) {
                    selStart = selEnd = buffer.length();
                }
            }

            if (selStart > last)
                selStart = selEnd = Integer.MAX_VALUE;
            if (selEnd < first)
                selStart = selEnd = -1;

            switch (what) {
                case CLICK:
                    if (selStart == selEnd) {
                        return false;
                    }

                    ClickableSpan[] links = buffer.getSpans(selStart, selEnd, ClickableSpan.class);

                    if (links.length != 1) {
                        return false;
                    }

                    ClickableSpan link = links[0];
                    /*if (link instanceof TextLinks.TextLinkSpan) {
                        ((TextLinks.TextLinkSpan) link).onClick(widget, TextLinks.TextLinkSpan.INVOCATION_METHOD_KEYBOARD);
                    } else {
                        link.onClick(widget);
                    }*/
                    link.onClick(widget);
                    break;

                case UP:
                    int bestStart, bestEnd;

                    bestStart = -1;
                    bestEnd = -1;

                    for (int i = 0; i < candidates.length; i++) {
                        int end = buffer.getSpanEnd(candidates[i]);

                        if (end < selEnd || selStart == selEnd) {
                            if (end > bestEnd) {
                                bestStart = buffer.getSpanStart(candidates[i]);
                                bestEnd = end;
                            }
                        }
                    }

                    if (bestStart >= 0) {
                        Selection.setSelection(buffer, bestEnd, bestStart);
                        return true;
                    }

                    break;

                case DOWN:
                    bestStart = Integer.MAX_VALUE;
                    bestEnd = Integer.MAX_VALUE;

                    for (int i = 0; i < candidates.length; i++) {
                        int start = buffer.getSpanStart(candidates[i]);

                        if (start > selStart || selStart == selEnd) {
                            if (start < bestStart) {
                                bestStart = start;
                                bestEnd = buffer.getSpanEnd(candidates[i]);
                            }
                        }
                    }

                    if (bestEnd < Integer.MAX_VALUE) {
                        Selection.setSelection(buffer, bestStart, bestEnd);
                        return true;
                    }

                    break;
            }

            return false;
        }

        @Override
        public boolean onTouchEvent(TextView widget, Spannable buffer,
                                    MotionEvent event) {
            int action = event.getAction();

            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                x -= widget.getTotalPaddingLeft();
                y -= widget.getTotalPaddingTop();

                x += widget.getScrollX();
                y += widget.getScrollY();

                Layout layout = widget.getLayout();
                int line = layout.getLineForVertical(y);
                int off = layout.getOffsetForHorizontal(line, x);

                ClickableSpan[] links = buffer.getSpans(off, off, ClickableSpan.class);

                if (links.length != 0) {
                    ClickableSpan link = links[0];
                    if (action == MotionEvent.ACTION_UP) {
                        /*if (link instanceof TextLinks.TextLinkSpan) {
                            ((TextLinks.TextLinkSpan) link).onClick(
                                    widget, TextLinks.TextLinkSpan.INVOCATION_METHOD_TOUCH);
                        } else {
                            link.onClick(widget);
                        }*/
                        link.onClick(widget);
                    } else if (action == MotionEvent.ACTION_DOWN) {
                        if (widget.getContext().getApplicationInfo().targetSdkVersion
                                >= Build.VERSION_CODES.P) {
                            // Selection change will reposition the toolbar. Hide it for a few ms for a
                            // smoother transition.
                             //widget.hideFloatingToolbar(HIDE_FLOATING_TOOLBAR_DELAY_MS);
                        }
                        Selection.setSelection(buffer,
                                buffer.getSpanStart(link),
                                buffer.getSpanEnd(link));
                    }
                    return true;
                } else {
                    Selection.removeSelection(buffer);
                }
            }

            return super.onTouchEvent(widget, buffer, event);
        }

        @Override
        public void initialize(TextView widget, Spannable text) {
            Selection.removeSelection(text);
            text.removeSpan(FROM_BELOW);
        }

        @Override
        public void onTakeFocus(TextView view, Spannable text, int dir) {
            Selection.removeSelection(text);

            if ((dir & View.FOCUS_BACKWARD) != 0) {
                text.setSpan(FROM_BELOW, 0, 0, Spannable.SPAN_POINT_POINT);
            } else {
                text.removeSpan(FROM_BELOW);
            }
        }

        public static MovementMethod getInstance() {
            if (sInstance == null)
                sInstance = new LinkMovementMethod();

            return sInstance;
        }


        private static LinkMovementMethod sInstance;
        private static Object FROM_BELOW = new NoCopySpan.Concrete();
    }
}
