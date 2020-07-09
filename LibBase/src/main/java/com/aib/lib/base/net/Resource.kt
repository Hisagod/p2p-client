package com.aib.lib.base.net

data class Resource<D> constructor(var msg: String?, var status: NetStatus, var data: D?) {
    companion object {
        fun <D> load(): Resource<D> {
            return Resource(null, NetStatus.LOAD, null)
        }

        fun <D> success(data: D?): Resource<D> {
            return Resource(null, NetStatus.SUCCESS, data)
        }

        fun <D> error(msg: String = "加载失败"): Resource<D> {
            return Resource(msg, NetStatus.ERROR, null)
        }

        fun <D> empty(): Resource<D> {
            return Resource(null, NetStatus.EMPTY, null)
        }
    }
}
