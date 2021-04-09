package com.study.stockcloud

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory

/**
 * @author: amos
 * @date: 2021/4/8 17:19
 * @description:
 */
class StockCloudActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_cloud)
        supportFragmentManager.beginTransaction()
            .replace(R.id.flContainer,supportFragmentManager.fragmentFactory.instantiate(classLoader,StockCloudPagerFragment::class.java.name))
            .commitNowAllowingStateLoss()
    }
}