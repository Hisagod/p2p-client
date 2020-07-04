package com.aib.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.aib.lib.base.expand.getViewModel
import com.aib.lib.base.fragment.BaseFragment
import com.aib.lib.base.image.GlideManager
import com.aib.p2p.R
import com.aib.viewmodel.MainViewModel
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.youth.banner.adapter.BannerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    private val vm by lazy { getViewModel(MainViewModel::class.java) }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initData() {
        loadBanner()
    }

    private fun loadBanner() {
        vm.getBanner().observe(this, Observer {
            val imgs = mutableListOf<String>()
            it.forEach {
                imgs.add(it.img)
            }
            banner.adapter = object : BannerAdapter<String, BaseViewHolder>(imgs) {
                override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
                    val view = LayoutInflater.from(context).inflate(R.layout.item_banner, parent, false)
                    return BaseViewHolder(view)
                }

                override fun onBindView(holder: BaseViewHolder?, data: String?, position: Int, size: Int) {
                    holder?.getView<ImageView>(R.id.iv_banner)?.apply {
                        GlideManager.getInstance.loadCommonNetPath(this, data ?: "")
                    }
                }
            }
        })
    }
}