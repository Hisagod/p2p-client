package com.aib.fragment

import androidx.fragment.app.viewModels
import com.aib.adapter.RecommonAdapter
import com.aib.base.fragment.BaseFragment
import com.aib.bean.HomeBean
import com.aib.net.Status
import com.aib.p2p.R
import com.aib.p2p.databinding.FragmentHomeBinding
import com.aib.viewmodel.MainViewModel
import com.chad.library.adapter.base.BaseBinderAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val vm by viewModels<MainViewModel>()
    private val homeAdapter = BaseBinderAdapter()

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initData() {
        rv.adapter = homeAdapter
        homeAdapter.addItemBinder(HomeBean::class.java, com.aib.adapter.BannerAdapter())
        homeAdapter.addItemBinder(HomeBean.ProductBean::class.java, RecommonAdapter())
        loadData()
        refresh()
    }

    private fun refresh() {
        srl.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        vm.mainData.observe(this) {
            binding.resource = it
            if (it.status == Status.SUCCESS) {
                val dataItem = mutableListOf<Any>()
                it.data?.let { bean ->
                    dataItem.add(bean)
                    dataItem.add(bean.product)
                }
                homeAdapter.setList(dataItem)
            }
        }
        vm.getHome()
    }
}