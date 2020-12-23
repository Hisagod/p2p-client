package com.aib.sdk.arouter

import com.aib.lib.base.arouter.ArouterPath
import com.alibaba.android.arouter.launcher.ARouter

class ArouterManager private constructor() {
    companion object {
        val getInstance: ArouterManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ArouterManager()
        }
    }

    fun openMainPage() {
        ARouter.getInstance().build(ArouterPath.PATH_MAIN).navigation()
    }
}