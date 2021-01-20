package com.amos.study.scroll;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amos.study.BaseActivity;
import com.amos.study.R;

/**
 * @author: amos
 * @date: 2020/12/22 14:23
 * @description:
 */
public class ScrollTestActivity extends BaseActivity {

    private RecyclerView rvList;
    private MAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_test);
        findViewById(R.id.viewTag1).setOnClickListener(v -> {

        });
        findViewById(R.id.viewTag2).setOnClickListener(v -> {

        });
        findViewById(R.id.viewTag3).setOnClickListener(v -> {

        });

        rvList = findViewById(R.id.rvList);
        initRecyclerView();
    }

    private void initRecyclerView() {
        rvList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        setAdapter();
    }

    private void setAdapter() {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
            return;
        }
        mAdapter = new MAdapter(this);
        rvList.setAdapter(mAdapter);
    }


    public static void launch(Context context) {
        Intent intent = new Intent(context, ScrollTestActivity.class);
        context.startActivity(intent);
    }

    static class MAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private Context mContext;
        private LayoutInflater inflater;

        public MAdapter(Context context) {
            mContext = context;
            inflater = LayoutInflater.from(mContext);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MViewHolder(inflater.inflate(R.layout.item_scroll_test, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }

    static class MViewHolder extends RecyclerView.ViewHolder {

        public MViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
