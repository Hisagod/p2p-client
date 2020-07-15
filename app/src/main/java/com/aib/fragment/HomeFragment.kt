package com.aib.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.aib.lib.base.bean.HomeBean
import com.aib.lib.base.expand.getViewModel
import com.aib.lib.base.fragment.BaseFragment
import com.aib.lib.base.image.GlideManager
import com.aib.lib.base.net.NetStatus
import com.aib.lib.base.widget.RoundProgress
import com.aib.p2p.R
import com.aib.viewmodel.MainViewModel
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    private val vm by lazy { getViewModel(MainViewModel::class.java) }

    private var isFirstLoad = true

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initData() {
        rv.adapter = adapter
        loadData()
        refresh()
    }

    private fun refresh() {
        srl.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        vm.getBanner().observe(this, Observer {
            srl.finishRefresh()
            when (it.status) {
                NetStatus.LOAD -> {
                    if (isFirstLoad) {
                        dv.showLoad()
                        isFirstLoad = false
                    }
                }
                NetStatus.SUCCESS -> {
                    dv.showSuccess()

                    val listData = mutableListOf<HomeBean>()
                    listData.add(HomeBean(it.data?.product, it.data?.banner, HomeBean.BANNER))
                    listData.add(HomeBean(it.data?.product, it.data?.banner, HomeBean.PRODUCT))
                    adapter.setNewInstance(listData)
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

    object adapter : BaseMultiItemQuickAdapter<HomeBean, BaseViewHolder>() {
        init {
            addItemType(HomeBean.BANNER, R.layout.item_banner)
            addItemType(HomeBean.PRODUCT, R.layout.item_home_product)
        }

        override fun convert(holder: BaseViewHolder, item: HomeBean) {
            when (holder.itemViewType) {
                HomeBean.BANNER -> {
                    val imgs = mutableListOf<String>()

                    item.banner.forEach {
                        imgs.add(it.img)
                    }

                    holder.getView<Banner<*, *>>(R.id.banner).adapter = object : BannerAdapter<String, BaseViewHolder>(imgs) {
                        override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
                            val view = LayoutInflater.from(context).inflate(R.layout.item_image_view, parent, false)
                            return BaseViewHolder(view)
                        }

                        override fun onBindView(holder: BaseViewHolder?, data: String?, position: Int, size: Int) {
                            holder?.getView<ImageView>(R.id.iv_banner)?.apply {
                                GlideManager.getInstance.loadCommonNetPath(this, data ?: "")
                            }
                        }
                    }
                }
                HomeBean.PRODUCT -> {
                    val product = item.product
                    holder.setText(R.id.tv_home_product, product.name)
                    holder.setText(R.id.tv_home_yearrate, "${product.yearRate}%")
                    holder.getView<RoundProgress>(R.id.roundPro_home).progress = product.progress
                }
            }
        }
    }
}