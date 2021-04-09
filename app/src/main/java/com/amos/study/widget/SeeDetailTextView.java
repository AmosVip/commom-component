//package com.amos.study.widget;
//
//import android.content.Context;
//import android.content.res.Resources;
//import android.os.Build;
//import android.text.Layout;
//import android.text.SpannableString;
//import android.text.Spanned;
//import android.text.StaticLayout;
//import android.text.TextPaint;
//import android.text.method.LinkMovementMethod;
//import android.text.style.ClickableSpan;
//import android.util.AttributeSet;
//import android.view.View;
//
//import androidx.appcompat.widget.AppCompatTextView;
//
//import com.amos.study.R;
//
///**
// * @author: amos
// * @date: 2021/3/4 14:15
// * @description: 展开和收起view
// */
//public class SeeDetailTextView extends AppCompatTextView {
//    private String originText;// 原始内容文本
//    private int initWidth = 0;// TextView可展示宽度
//    private int mMaxLines = 2;// TextView最大行数
//    private SpannableString SPAN_END = null;
//    private String mDefaultEndWord = "查看详情";
//
//    public SeeDetailTextView(Context context) {
//        super(context);
//    }
//
//    public SeeDetailTextView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public SeeDetailTextView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        initWidth = getMeasuredWidth();
//    }
//
//    /**
//     * 设置要追加的文案
//     * 例如: 全文 | 查看更多
//     */
//    public SeeDetailTextView setEndWord(String endWord) {
//        mDefaultEndWord = endWord;
//        return this;
//    }
//
//    /**
//     * 设置文案
//     * 注意: 不要使用 textView 的 setText
//     */
//    public void setTextWord(CharSequence content) {
//        originText = content == null ? "" : content.toString();
//        post(new Runnable() {
//            @Override
//            public void run() {
//                setSeeDefaultText();
//            }
//        });
//    }
//
//    /**
//     * 设置TextView可显示的最大行数
//     *
//     * @param maxLines 最大行数
//     */
//    @Override
//    public void setMaxLines(int maxLines) {
//        this.mMaxLines = maxLines;
//        super.setMaxLines(maxLines);
//    }
//
//
//    /**
//     * 查看详情扩展文案
//     */
//    private void initEndWordSpan() {
//        String content = mDefaultEndWord;
//        SPAN_END = new SpannableString(content);
//        ButtonSpan span = new ButtonSpan(getContext(), getTextSize(), new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //点击回调
//
//            }
//        });
//        SPAN_END.setSpan(span, 0, content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//    }
//
//
//    /**
//     * 设置查看详情文案
//     */
//    private void setSeeDefaultText() {
//        if (SPAN_END == null) {
//            initEndWordSpan();
//        }
//        int maxLines = getMaxLines();
//        String workingText = originText;
//        if (maxLines != -1) {
//            Layout layout = createWorkingLayout(workingText);
//            if (layout.getLineCount() > maxLines) {
//                //获取一行显示字符个数，然后截取字符串数
//                workingText = originText.substring(0, layout.getLineEnd(maxLines - 1)).trim();// 收起状态原始文本截取展示的部分
//                String showText = originText.substring(0, layout.getLineEnd(maxLines - 1)).trim() + "..." + SPAN_END;
//                Layout layout2 = createWorkingLayout(showText);
//                // 对workingText进行-1截取，直到展示行数==最大行数，并且添加 SPAN_CLOSE 后刚好占满最后一行
//                while (layout2.getLineCount() > maxLines) {
//                    int lastSpace = workingText.length() - 1;
//                    if (lastSpace == -1) {
//                        break;
//                    }
//                    workingText = workingText.substring(0, lastSpace);
//                    layout2 = createWorkingLayout(workingText + "..." + SPAN_END);
//                }
//                workingText = workingText + "...";
//
//            } else { //如果不大于 就用空格填充
//                String showText = originText + "..." + SPAN_END;
//                Layout layout2 = createWorkingLayout(showText);
//                int currentLintCount = layout2.getLineCount();
//                //如果追加了
//                if (currentLintCount > maxLines) {
//                    while (layout2.getLineCount() > maxLines) {
//                        int lastSpace = workingText.length() - 1;
//                        if (lastSpace == -1) {
//                            break;
//                        }
//                        workingText = workingText.substring(0, lastSpace);
//                        layout2 = createWorkingLayout(workingText + "..." + SPAN_END);
//                    }
//                }
//                workingText = workingText + "...";
//            }
//        }
//        setText(workingText);
//        //必须使用append，不能在上面使用+连接，否则spannable会无效
//        append(SPAN_END);
//        setMovementMethod(LinkMovementMethod.getInstance());
//    }
//
//
//    //返回textview的显示区域的layout，该textview的layout并不会显示出来，只是用其宽度来比较要显示的文字是否过长
//    private Layout createWorkingLayout(String workingText) {
//        return new StaticLayout(workingText, getPaint(), initWidth - getPaddingLeft() - getPaddingRight(),
//                Layout.Alignment.ALIGN_NORMAL, getLineSpacingMultiplier(), getLineSpacingExtra(), false);
//    }
//
//    private static class ButtonSpan extends ClickableSpan {
//
//        OnClickListener onClickListener;
//        private Context context;
//        private int colorId;
//        private float textSizePx;
//
//        public ButtonSpan(Context context, float textSizePx, OnClickListener onClickListener) {
//            this(context, textSizePx, onClickListener, R.color.color_E03C34);
//        }
//
//        public ButtonSpan(Context context, float textSizePx, OnClickListener onClickListener, int colorId) {
//            this.onClickListener = onClickListener;
//            this.context = context;
//            this.colorId = colorId;
//            this.textSizePx = textSizePx;
//        }
//
//        @Override
//        public void updateDrawState(TextPaint ds) {
//            ds.setColor(context.getResources().getColor(colorId));
//            //ds.setTextSize(dip2px(16));
//            ds.setTextSize(textSizePx);
//            ds.setUnderlineText(false);
//        }
//
//        @Override
//        public void onClick(View widget) {
//            if (onClickListener != null) {
//                onClickListener.onClick(widget);
//            }
//        }
//
//        public static int dip2px(float dipValue) {
//            final float scale = Resources.getSystem().getDisplayMetrics().density;
//            return (int) (dipValue * scale + 0.5f);
//        }
//    }
//}
