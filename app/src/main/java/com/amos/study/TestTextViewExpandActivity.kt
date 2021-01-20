package com.amos.study

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import com.amos.study.helper.CustomMovementMethod
import com.amos.study.helper.StockStringBuilderHelper
import kotlinx.android.synthetic.main.activity_test_textview_expand.*

/**
 * @author: amos
 * @date: 2021/1/20 9:53
 * @description:
 */
class TestTextViewExpandActivity : BaseActivity() {

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, TestTextViewExpandActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val MAX_LINE = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_textview_expand)
        tvContent.setOnClickListener {
            val isExpand = tvContent.tag as Boolean
            if (isExpand) {
                tvContent.tag = false
                tvContent.maxLines = MAX_LINE
            } else {
                val ellipsisCount = tvContent.layout.getEllipsisCount(tvContent.lineCount - 1)
                if (ellipsisCount > 0) {
                    tvContent.maxHeight = resources.displayMetrics.heightPixels;
                    tvContent.tag = true
                }
            }
        }

        val content: String =
            "6月2日讯，${'$'}新华医疗(SH600587)${'$'}发布关于媒体报道的澄清公告称，经核实，剔除威士达合并报表因素影响后，公司2019年度营业收入与201" +
                    "8年度相比实际增长2.21%。公司营业收入与经营活动现金流量表匹配，不存在营业收入虚增事项。" +
                    "公司营业成本与“购买商品、接受劳务支付的现金”金额匹配，不存在营业成本虚减事项。公司不存在偿债风险。"
        tvContent.text = StockStringBuilderHelper.setSpan(this, content)
        tvContent.movementMethod = StockStringBuilderHelper.CustomLinkMovementMethod.getInstance()
        tvContent.tag = false
        setExpand()
    }

    private fun setExpand() {
        val isExpand = tvContent.tag as Boolean
        if (isExpand) {
            tvContent.maxHeight = resources.displayMetrics.heightPixels;


        } else {
            tvContent.maxLines = MAX_LINE
        }
    }
}