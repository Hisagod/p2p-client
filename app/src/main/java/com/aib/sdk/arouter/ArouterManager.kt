package com.aib.sdk.arouter

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import javax.inject.Inject

class ArouterManager @Inject constructor() {

    fun openHomePage(): Fragment {
        return ARouter.getInstance().build(ArouterPath.PATH_HOME_PAGE).navigation() as Fragment
    }

    fun openProductPage(): Fragment {
        return ARouter.getInstance().build(ArouterPath.PATH_PRODUCT_PAGE).navigation() as Fragment
    }

    fun openMinePage(): Fragment {
        return ARouter.getInstance().build(ArouterPath.PATH_MINE_PAGE).navigation() as Fragment
    }

    fun openMainPage() {
        ARouter.getInstance().build(ArouterPath.PATH_MAIN).navigation()
    }

    fun openAboutUs() {
        ARouter.getInstance().build(ArouterPath.PATH_ABOUT_US).navigation()
    }

    fun openRegister() {
        ARouter.getInstance().build(ArouterPath.PATH_REGISTER).navigation()
    }
}