package mypromotion.vboo.com.snapchallenge.network.interfaces

import mypromotion.vboo.com.snapchallenge.network.ServiceResult

interface IServiceResultListener<Any> {
    abstract fun onResult(result: ServiceResult<Any>)
}