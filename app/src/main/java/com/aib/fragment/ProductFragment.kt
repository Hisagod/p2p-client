package com.aib.fragment

import androidx.lifecycle.Observer
import com.aib.lib.base.bean.ProductBean
import com.aib.lib.base.expand.getViewModel
import com.aib.lib.base.fragment.BaseFragment
import com.aib.lib.base.net.NetStatus
import com.aib.lib.base.widget.RoundProgress
import com.aib.p2p.R
import com.aib.viewmodel.MainViewModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_productlist.*

class ProductFragment : BaseFragment() {
    private val vm by lazy { getViewModel(MainViewModel::class.java) }
    private lateinit var adapter: BaseQuickAdapter<ProductBean, BaseViewHolder>
    override fun getLayoutId(): Int {
        return R.layout.fragment_productlist
    }

    override fun initData() {
        adapter = object : BaseQuickAdapter<ProductBean, BaseViewHolder>(R.layout.item_product) {
            override fun convert(holder: BaseViewHolder, item: ProductBean) {
                holder.setText(R.id.p_name, item.name)
                holder.setText(R.id.p_money, item.money.toString())
                holder.setText(R.id.p_yearlv, item.yearRate)
                holder.setText(R.id.p_suodingdays, item.suodingDays.toString())
                holder.setText(R.id.p_minzouzi, item.minTouMoney.toString())
                holder.setText(R.id.p_minnum, item.menberNum.toString())

                holder.getView<RoundProgress>(R.id.p_progresss).progress = item.progress
            }
        }
        rv.adapter = adapter

        loadData()
    }

    private fun loadData() {
        vm.getProductList().observe(this, Observer {
            when (it.status) {
                NetStatus.LOAD -> dv.showLoad()
                NetStatus.SUCCESS -> {
                    dv.showSuccess()
                    adapter.setNewInstance(it.data as MutableList<ProductBean>?)
                }
                NetStatus.ERROR -> {
                    dv.showError {
                        loadData()
                    }
                }
                NetStatus.EMPTY -> dv.showEmpty()
            }
        })
    }
}