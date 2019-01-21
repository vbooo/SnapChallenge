package mypromotion.vboo.com.snapchallenge.network

class ServiceResult<T> {
    internal var mData: T? = null

    internal var mError: ServiceException? = null

    fun getmData(): T? {
        return mData
    }

    fun setmData(mData: T?) {
        this.mData = mData
    }

    fun getmError(): ServiceException? {
        return mError
    }

    fun setmError(mError: ServiceException) {
        this.mError = mError
    }
}