package mypromotion.vboo.com.snapchallenge.network

class ServiceException: Exception() {
    private var mCode: Int = 0
    private var mType: ServiceExceptionType? = null


    fun getCode(): Int {
        return mCode
    }

    fun setCode(code: Int) {
        this.mCode = code
        setType(getTypeForCode(code))
    }

    fun getType(): ServiceExceptionType? {
        return mType
    }

    fun setType(type: ServiceExceptionType) {
        this.mType = type
    }


    fun getTypeForCode(code: Int): ServiceExceptionType {
        when (code) {
            400 -> return ServiceExceptionType.BAD_REQUEST
            401 -> return ServiceExceptionType.UNAUTHORIZED
            403 -> return ServiceExceptionType.FORBIDDEN
            404 -> return ServiceExceptionType.NOT_FOUND
            405 -> return ServiceExceptionType.METHOD_NOT_ALLOWED
            409 -> return ServiceExceptionType.CONFLICT
            410 -> return ServiceExceptionType.GONE
            500 -> return ServiceExceptionType.SERVER_ERROR
            501 -> return ServiceExceptionType.NOT_IMPLEMENTED
            else -> return ServiceExceptionType.UNKNOWN
        }
    }
}