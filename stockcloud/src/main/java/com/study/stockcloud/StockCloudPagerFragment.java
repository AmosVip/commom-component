package com.study.stockcloud;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.study.stockcloud.bean.BaseStockCloudPlateRankModel;
import com.study.stockcloud.bean.StockCloudPlateRankModel;
import com.study.stockcloud.treemap.TreeModel;
import com.study.stockcloud.view.MapLayoutView;
import com.study.stockcloud.view.StockCloudBottomView;
import com.study.stockcloud.view.TreeMapItem;
import com.study.stockcloud.view.ViewUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class StockCloudPagerFragment extends Fragment implements View.OnClickListener {

    protected int showType; //展示类型：0:涨跌幅，1：资金净流入
    protected int countType = 30; //显示数据量：前10，前30，前50
    protected double[] boundary = new double[2]; //边界
    private int currentQryTm = 1;
    private int currentPLatType = 0; //平台
    private int currentLmt = 30;

    public static String CLOUD_PLAT_TYPE = "plat_type";
    protected FrameLayout treeMapContainer;
    private String currentStringSelect;

    private StockCloudBottomView stockCloudBottomView;
    private Boolean popIsShow = false;
    private PopupWindow popupWindow;
    private RecyclerView recyclerView;

    public static StockCloudPagerFragment buildFragment(int PlateType) {
        StockCloudPagerFragment fragment = new StockCloudPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CLOUD_PLAT_TYPE, PlateType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock_cloud_layout, container, false);
        treeMapContainer = view.findViewById(R.id.treemap_container);
        stockCloudBottomView = view.findViewById(R.id.cloud_bottom_view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initPopWindows();
        /*progressContent.justShowProgress();
        currentPLatType = getArguments().getInt(CLOUD_PLAT_TYPE);
        presenter.getCloudData(currentPLatType, currentQryTm);*/

        addMapView();
    }




    protected void addMapView() {
        List<StockCloudPlateRankModel> tempList = new ArrayList<>();
        tempList.add(new StockCloudPlateRankModel(6,"京东方A"));
        tempList.add(new StockCloudPlateRankModel(6,"大北农"));
        tempList.add(new StockCloudPlateRankModel(4,"紫金矿业"));
        tempList.add(new StockCloudPlateRankModel(3,"通威股份"));
        tempList.add(new StockCloudPlateRankModel(2,"恒立液压"));
        tempList.add(new StockCloudPlateRankModel(2,"长安汽车"));
        tempList.add(new StockCloudPlateRankModel(1,"长城汽车"));
        addMapView(tempList);
    }

    protected void addMapView(List<? extends BaseStockCloudPlateRankModel> modelList) {
        treeMapContainer.removeAllViews();
        treeMapContainer.addView(generatorTreeMapView(modelList.size() > countType ? modelList.subList(0, countType) : modelList));
    }

    private MapLayoutView generatorTreeMapView(List<? extends BaseStockCloudPlateRankModel> modelList) {
        MapLayoutView mapLayoutView = new MapLayoutView(getActivity(), getTreeModel(showType, modelList));
        mapLayoutView.setOnItemClickListener(getOnTreeMapItemClickListener());
        stockCloudBottomView.setData(boundary[0], boundary[1], showType);
        return mapLayoutView;
    }

    protected MapLayoutView.OnTreeMapItemClickListener getOnTreeMapItemClickListener() {
        return (MapLayoutView.OnTreeMapItemClickListener<BaseStockCloudPlateRankModel>) model -> {


        };
    }

    protected TreeModel getTreeModel(int type, List<? extends BaseStockCloudPlateRankModel> modelList) {
        boundary = new double[2]; //每次都创建新的
        TreeModel treeModel = new TreeModel();
        if (modelList != null && modelList.size() > 0) {
            for (BaseStockCloudPlateRankModel rankModel : modelList) {
                //计算边界,涨跌幅显示的是百分比，因此需要*100
                double value = type == 0 ? rankModel.getRate() * 100 : rankModel.getTuov();
                if (value >= 0) {
                    if (value > boundary[0]) {
                        boundary[0] = value;
                    }
                } else {
                    if (value < boundary[1]) {
                        boundary[1] = value;
                    }
                }
            }

            if (type == 0) {
                //涨跌幅 取绝对值最大的为上下界
                if (Math.abs(boundary[0]) > Math.abs(boundary[1])) {
                    boundary[1] = -boundary[0];
                } else {
                    boundary[0] = Math.abs(boundary[1]);
                }
            }
            boolean showTxt = true; //第一个位置显示涨跌幅与净流入
            for (BaseStockCloudPlateRankModel rankModel : modelList) {
                double value;
                String color;
                String valueStr;
                if (type == 0) {
                    value = rankModel.getRate() * 100;
                    color = TreeMapColorUtil.getTargetColor(value, boundary[0], boundary[1]);
                    valueStr = ViewUtils.formatDouble(value) + "%";
                    if (showTxt)
                        valueStr = "涨跌幅：" + valueStr;
                } else {
                    value = rankModel.getTuov();
                    valueStr = ViewUtils.formatData(value);
                    color = TreeMapColorUtil.getTargetColor(value, boundary[0], boundary[1]);
                    if (showTxt)
                        valueStr = "净流入：" + valueStr;
                }
                showTxt = false;
                treeModel.addChild(new TreeModel(new TreeMapItem(rankModel.getProportion(), valueStr, color, rankModel.getLabel(), rankModel)));
            }
        }

        treeModel.setBoundary(boundary);
        return treeModel;
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        /*if (i == R.id.stock_cloud_day_ll) {
            timeTitle.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_red));
            timeTitle.setCompoundDrawables(null, null, triRed, null);
            currentStringSelect = timeTitle.getText().toString();
            showPopWindows(dayString);

        } else if (i == R.id.stock_cloud_per_ll) {
            percentTitle.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_red));
            percentTitle.setCompoundDrawables(null, null, triRed, null);
            currentStringSelect = percentTitle.getText().toString();
            showPopWindows(perString);

        } else if (i == R.id.stock_cloud_number_ll) {
            countTitle.setTextColor(ContextCompat.getColor(getActivity(), R.color.color_red));
            countTitle.setCompoundDrawables(null, null, triRed, null);
            currentStringSelect = countTitle.getText().toString();
            showPopWindows(numString);

        }*/
    }


    private void showPopWindows(List<String> strings) {
        if (popIsShow) {
            popupWindow.dismiss();
            popIsShow = false;
        } else {
            //commonAdapter.setDatas(strings);
            //popupWindow.showAsDropDown(popLinear);
            popIsShow = true;
        }
    }


    private void initPopWindows() {
        popupWindow = new PopupWindow();
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_stock_cloud_pop, null);
        recyclerView = view.findViewById(R.id.stock_cloud_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //recyclerView.setAdapter(commonAdapter);
        popupWindow.setOnDismissListener(() -> {


            popIsShow = false;
        });
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setContentView(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.white_normal));
    }


}
