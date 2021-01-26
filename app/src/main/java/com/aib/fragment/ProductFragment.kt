package com.aib.fragment

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.aib.base.fragment.BaseFragment
import com.aib.bean.ProductBean
import com.aib.net.Status
import com.aib.widget.RoundProgress
import com.aib.p2p.R
import com.aib.viewmodel.MainViewModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.fragment_productlist.*

class ProductFragment : BaseFragment<ViewDataBinding>() {
    private val vm by viewModels<MainViewModel>()
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
                Status.LOAD -> dv.showLoad()
                Status.SUCCESS -> {
                    dv.showSuccess()
                    adapter.setNewInstance(it.data as MutableList<ProductBean>?)
                }
                Status.ERROR -> {
                    dv.showError {
                        loadData()
                    }
                }
                Status.EMPTY -> dv.showEmpty()
            }
        })
    }
}