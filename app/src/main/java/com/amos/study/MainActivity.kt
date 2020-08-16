package com.amos.study

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.common.ui.flow.CFlowLayout
import com.common.ui.flow.ICFlow
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_grid.layoutManager = GridLayoutManager(this, 3)
        rv_grid.addItemDecoration(CommonItemDecoration())
        rv_grid.adapter = GridAdapter(this)
    }


    class GridAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        var context: Context = context
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_grid, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return 11
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

}