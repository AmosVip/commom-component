//package com.study.stockcloud.detail
//
//import android.graphics.drawable.Drawable
//import android.os.Bundle
//import androidx.core.content.ContextCompat
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.PopupWindow
//import androidx.fragment.app.Fragment
//
//import com.study.stockcloud.R
//import com.study.stockcloud.TreeMapColorUtil
//import com.study.stockcloud.treemap.TreeModel
//import com.study.stockcloud.view.MapLayoutView
//import com.study.stockcloud.view.TreeMapItem
//import com.study.stockcloud.view.ViewUtils
//import java.util.*
//
//
///**
// * 介绍：大盘云图详情页
// * 作者：sunwentao
// * 邮箱：wentao.sun@yintech.cn
// * 时间: 17/5/18
// */
//private const val KEY_PLAT_EI = "key_plat_ei"
//private const val KEY_QRY_TM = "key_qry_tm"
//private const val KEY_LMT = "key_lmt"
//private const val KEY_TITLE = "key_title"
//
//fun buildFragment(plateE: Int, qry: Int, lmt: Int, title: String): StockCloudDetailFragment {
//    var fragment = StockCloudDetailFragment()
//    var bundle = Bundle()
//    bundle.putInt(KEY_PLAT_EI, plateE)
//    bundle.putInt(KEY_QRY_TM, qry)
//    bundle.putInt(KEY_LMT, lmt)
//    bundle.putString(KEY_TITLE, title)
//    fragment.arguments = bundle
//    return fragment
//}
//
//class StockCloudDetailFragment : Fragment(), View.OnClickListener,
//    StockCloudDetailView {
//
//    var showType: Int = 0 //展示类型：0:涨跌幅，1：资金净流入
//    var countType = 30 //显示数据量：前10，前30，前50
//    private var boundary = DoubleArray(2) //边界
//    private var currentPLatEi = 0
//    private var currentQryTm = 1
//    private var titleName: String = ""
//    private var sourceData: List<StockCloudPlateDetailRankModel> = arrayListOf()
//    private var numString = ArrayList<String>()
//    private var dayString = ArrayList<String>()
//    private var perString = ArrayList<String>()
//    private var triGray: Drawable? = null
//    private var triRed: Drawable? = null
//    private var popupWindow: PopupWindow? = null
//
//    //private var commonAdapter: CommonAdapter<String>? = null
//    private var recyclerView: RecyclerView? = null
//    private var popIsShow: Boolean = false
//    private var currentStringSelect: String = ""
//
//    /**
//     * 设置数据
//     */
//    fun showCloudData(cloudPlateRankModels: List<StockCloudPlateDetailRankModel>) {
//        this.sourceData = cloudPlateRankModels
//        addMapView()
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater?.inflate(R.layout.fragment_stock_cloud_detail, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        //cloud_stock_detail_pc.justShowProgress()
//        initPopWindowsData()
//        initClick()
//        initCompoundDrawables()
//        initPopWindows()
//        getCloudParams()
//        setTitleInfo()
//        //presenter.getCloudDetailData(currentPLatEi, currentQryTm)
//    }
//
//    private fun initClick() {
//        /*stock_cloud_detail_day_ll.setOnClickListener(this)
//        stock_cloud_detail_number_ll.setOnClickListener(this)
//        stock_cloud_detail_per_ll.setOnClickListener(this)*/
//    }
//
//    private fun initCompoundDrawables() {
//        /*triGray = ContextCompat.getDrawable(activity!!, R.mipmap.icon_cloud_triangle_gray)
//        triRed = ContextCompat.getDrawable(activity!!, R.mipmap.icon_cloud_triangle_red)
//        triGray?.setBounds(0, 0, (triGray?.minimumWidth) ?: 0, (triGray?.minimumHeight) ?: 0)
//        triRed?.setBounds(0, 0, (triRed?.minimumWidth) ?: 0, (triRed?.minimumHeight) ?: 0)*/
//    }
//
//
//    private fun resetCompoundDraw() {
//        /*stock_cloud_detail_day.setCompoundDrawables(null, null, triGray, null)
//        stock_cloud_detail_per.setCompoundDrawables(null, null, triGray, null)
//        stock_cloud_detail_number.setCompoundDrawables(null, null, triGray, null)*/
//    }
//
//    private fun initPopWindowsData() {
//        dayString.add("今日")
//        dayString.add("近5日")
//        dayString.add("近10日")
//        perString.add("涨跌幅")
//        perString.add("资金净流入")
//        numString.add("前10")
//        numString.add("前30")
//        numString.add("前50")
//    }
//
//    private fun setTitleInfo() {
//        //title_bar.setTitle(titleName)
//    }
//
//    private fun getCloudParams() {
//        currentPLatEi = arguments!!.getInt(KEY_PLAT_EI)
////        currentQryTm = arguments.getInt(KEY_QRY_TM)
////        currentLmt = arguments.getInt(KEY_LMT)
//        titleName = arguments!!.getString(KEY_TITLE) ?: ""
//    }
//
//    fun addMapView() {
//        addMapView(sourceData)
//    }
//
//    private fun addMapView(modelList: List<BaseStockCloudPlateRankModel>) {
//        /*treemap_container.removeAllViews()
//        treemap_container.addView(
//            generatorTreeMapView(
//                if (modelList.size > countType) modelList.subList(
//                    0,
//                    countType
//                ) else modelList
//            )
//        )*/
//    }
//
//    private fun generatorTreeMapView(modelList: List<BaseStockCloudPlateRankModel>): MapLayoutView {
//        val mapLayoutView =
//            MapLayoutView(context, getTreeModel(showType, modelList))
//        mapLayoutView.setOnItemClickListener(getOnTreeMapItemClickListener())
//        //cloud_bottom_view.setData(boundary[0], boundary[1], showType)
//        return mapLayoutView
//    }
//
//    private fun getOnTreeMapItemClickListener(): MapLayoutView.OnTreeMapItemClickListener<*> {
//        return MapLayoutView.OnTreeMapItemClickListener<StockCloudPlateDetailRankModel> { model ->
//            /*val stock = Stock()
//            stock.symbol = model.SecurityCode.substring(2, model.SecurityCode.length)
//            stock.name = model.SecurityName
//            stock.market = model.SecurityCode.substring(0, 2)
//            stock.exchange = model.SecurityCode.substring(0, 2)
//            val extras: HashMap<String, String>? = null
//            activity!!.startActivity(QotationDetailActivity.buildIntent(activity, stock, extras))*/
//        }
//    }
//
//    private fun getTreeModel(type: Int, modelList: List<BaseStockCloudPlateRankModel>?): TreeModel {
//        boundary = DoubleArray(2) //每次都创建新的
//        val treeModel = TreeModel()
//        if (modelList != null && modelList.isNotEmpty()) {
//            modelList
//                .map {
//                    //计算边界,涨跌幅显示的是百分比，因此需要*100
//                    if (type == 0) it.rate * 100 else it.tuov
//                }
//                .forEach {
//                    if (it >= 0) {
//                        if (it > boundary[0]) {
//                            boundary[0] = it
//                        }
//                    } else {
//                        if (it < boundary[1]) {
//                            boundary[1] = it
//                        }
//                    }
//                }
//
//            if (type == 0) {
//                //涨跌幅 取绝对值最大的为上下界
//                if (Math.abs(boundary[0]) > Math.abs(boundary[1])) {
//                    boundary[1] = -boundary[0]
//                } else {
//                    boundary[0] = Math.abs(boundary[1])
//                }
//            }
//            var showTxt = true //第一个位置显示涨跌幅与净流入
//            for (rankModel in modelList) {
//                val value: Double
//                val color: String
//                var valueStr: String
//                if (type == 0) {
//                    value = rankModel.rate * 100
//                    color = TreeMapColorUtil.getTargetColor(value, boundary[0], boundary[1])
//                    valueStr = ViewUtils.formatDouble(value) + "%"
//                    if (showTxt)
//                        valueStr = "涨跌幅：" + valueStr
//                } else {
//                    value = rankModel.tuov
//                    valueStr = ViewUtils.formatData(value)
//                    color = TreeMapColorUtil.getTargetColor(value, boundary[0], boundary[1])
//
//                    if (showTxt)
//                        valueStr = "净流入：" + valueStr
//                }
//                showTxt = false
//                treeModel.addChild(
//                    TreeModel(
//                        TreeMapItem(
//                            rankModel.proportion,
//                            valueStr,
//                            color,
//                            rankModel.label,
//                            rankModel
//                        )
//                    )
//                )
//            }
//        }
//
//        treeModel.boundary = boundary
//        return treeModel
//    }
//
//    private fun showPopWindows(strings: List<String>) {
//        popIsShow = if (popIsShow) {
//            popupWindow?.dismiss()
//            false
//        } else {
//            //commonAdapter?.setDatas(strings)
//            //popupWindow?.showAsDropDown(pop_ll)
//            true
//        }
//    }
//
//    private fun initRecyclerView() {
//        /*commonAdapter = object :
//            CommonAdapter<String>(activity!!, R.layout.view_stock_cloud_pop_rv_item, ArrayList()) {
//
//            override fun convert(holder: ViewHolder, s: String, position: Int) {
//                val textView = holder.getView<TextView>(R.id.stock_cloud_rv_tv)
//                textView.text = s
//                if (dayString.contains(s)) {
//                    textView.gravity = Gravity.CENTER_VERTICAL or Gravity.LEFT
//                }
//                if (perString.contains(s)) {
//                    textView.gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER
//                }
//                if (numString.contains(s)) {
//                    textView.gravity = Gravity.CENTER_VERTICAL or Gravity.RIGHT
//                }
//                if (s == currentStringSelect) {
//                    textView.setTextColor(ContextCompat.getColor(activity!!, R.color.color_red))
//                } else {
//                    textView.setTextColor(
//                        ContextCompat.getColor(
//                            activity!!,
//                            R.color.ggt_text_common_black
//                        )
//                    )
//                }
//            }
//        }*/
//
//    }
//
//    private fun initRecyclerViewClick() {
//        /*commonAdapter?.setOnItemClickListener(object : CommonAdapter.OnItemClickListener<String> {
//            override fun onItemClick(parent: ViewGroup?, view: View, s: String, position: Int) {
//                when (s) {
//                    "涨跌幅" -> if (showType != 0) {
//                        stock_cloud_detail_per.text = "涨跌幅"
//                        showType = 0
//                        addMapView()
//                    }
//
//                    "资金净流入" -> if (showType != 1) {
//                        stock_cloud_detail_per.text = "资金净流入"
//                        showType = 1
//                        addMapView()
//                    }
//                    "今日" -> if (currentQryTm != 1) {
//                        stock_cloud_detail_day.text = "今日"
//                        currentQryTm = 1
//                        presenter.getCloudDetailData(currentPLatEi, currentQryTm)
//                    }
//                    "近5日" -> if (currentQryTm != 5) {
//                        stock_cloud_detail_day.text = "近5日"
//                        currentQryTm = 5
//                        presenter.getCloudDetailData(currentPLatEi, currentQryTm)
//                    }
//                    "近10日" -> if (currentQryTm != 10) {
//                        stock_cloud_detail_day.text = "近10日"
//                        currentQryTm = 10
//                        presenter.getCloudDetailData(currentPLatEi, currentQryTm)
//                    }
//                    "前10" -> if (countType != 10) {
//                        stock_cloud_detail_number.text = "前10"
//                        countType = 10
//                        addMapView()
//                    }
//                    "前30" -> if (countType != 30) {
//                        stock_cloud_detail_number.text = "前30"
//                        countType = 30
//                        addMapView()
//                    }
//                    "前50" -> if (countType != 50) {
//                        stock_cloud_detail_number.text = "前50"
//                        countType = 50
//                        addMapView()
//                    }
//                }
//                popupWindow?.dismiss()
//
//            }
//
//            override fun onItemLongClick(
//                parent: ViewGroup?,
//                view: View,
//                s: String,
//                position: Int
//            ): Boolean {
//                return false
//            }
//        })*/
//
//    }
//
//    private fun initPopWindows() {
//        popupWindow = PopupWindow()
//        val view = LayoutInflater.from(activity).inflate(R.layout.view_stock_cloud_pop, null)
//        recyclerView = view.findViewById(R.id.stock_cloud_rv)
//        recyclerView?.layoutManager = LinearLayoutManager(activity)
//        initRecyclerView()
//        initRecyclerViewClick()
//        //recyclerView?.adapter = commonAdapter
//        popupWindow?.setOnDismissListener({
//            resetTextColor()
//            resetCompoundDraw()
//            popIsShow = false
//        })
//        popupWindow?.isOutsideTouchable = true
//        popupWindow?.isFocusable = true
//        popupWindow?.contentView = view
//        popupWindow?.width = ViewGroup.LayoutParams.MATCH_PARENT
//        popupWindow?.height = ViewGroup.LayoutParams.WRAP_CONTENT
//        popupWindow?.setBackgroundDrawable(
//            ContextCompat.getDrawable(
//                activity!!,
//                R.drawable.white_normal
//            )
//        )
//    }
//
//    private fun resetTextColor() {
//        /*stock_cloud_detail_day.setTextColor(
//            ContextCompat.getColor(
//                activity!!,
//                R.color.ggt_text_common_black
//            )
//        )
//        stock_cloud_detail_per.setTextColor(
//            ContextCompat.getColor(
//                activity!!,
//                R.color.ggt_text_common_black
//            )
//        )
//        stock_cloud_detail_number.setTextColor(
//            ContextCompat.getColor(
//                activity!!,
//                R.color.ggt_text_common_black
//            )
//        )*/
//    }
//
//    override fun onClick(v: View?) {
//        /*when (v?.id) {
//            R.id.stock_cloud_detail_day_ll -> {
//                stock_cloud_detail_day.setTextColor(
//                    ContextCompat.getColor(
//                        activity!!,
//                        (R.color.color_red)
//                    )
//                )
//                stock_cloud_detail_day.setCompoundDrawables(null, null, triRed, null)
//                currentStringSelect = stock_cloud_detail_day.text.toString()
//                showPopWindows(dayString)
//            }
//            R.id.stock_cloud_detail_per_ll -> {
//                stock_cloud_detail_per.setTextColor(
//                    ContextCompat.getColor(
//                        activity!!,
//                        (R.color.color_red)
//                    )
//                )
//                stock_cloud_detail_per.setCompoundDrawables(null, null, triRed, null)
//                currentStringSelect = stock_cloud_detail_per.text.toString()
//                showPopWindows(perString)
//            }
//            R.id.stock_cloud_detail_number_ll -> {
//                stock_cloud_detail_number.setTextColor(
//                    ContextCompat.getColor(
//                        activity!!,
//                        (R.color.color_red)
//                    )
//                )
//                stock_cloud_detail_number.setCompoundDrawables(null, null, triRed, null)
//                currentStringSelect = stock_cloud_detail_number.text.toString()
//                showPopWindows(numString)
//            }
//        }*/
//    }
//}