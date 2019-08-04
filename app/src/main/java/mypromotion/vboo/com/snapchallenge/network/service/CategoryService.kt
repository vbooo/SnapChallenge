package mypromotion.vboo.com.snapchallenge.network.service

import mypromotion.vboo.com.snapchallenge.model.network.category.AddCategoryParam
import mypromotion.vboo.com.snapchallenge.model.network.category.AddCategoryResult
import mypromotion.vboo.com.snapchallenge.model.network.category.GetAllCategoryResult
import mypromotion.vboo.com.snapchallenge.network.ClientWS
import mypromotion.vboo.com.snapchallenge.network.ServiceException
import mypromotion.vboo.com.snapchallenge.network.ServiceResult
import mypromotion.vboo.com.snapchallenge.network.interfaces.IServiceResultListener
import mypromotion.vboo.com.snapchallenge.network.interfaces.category.CategoryInterface
import mypromotion.vboo.com.snapchallenge.network.interfaces.category.ICategoryInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryService: ICategoryInterface {

    private var mCategoryInterface: CategoryInterface? = null


    private fun getCategoryInterface(): CategoryInterface? {
        if (mCategoryInterface == null)
            mCategoryInterface = ClientWS.getClient().create(CategoryInterface::class.java)
        return mCategoryInterface
    }

    override fun add(addCategoryParam: AddCategoryParam, token: String, bodyResultListener: IServiceResultListener<AddCategoryResult>) {
        val call = getCategoryInterface()?.add(addCategoryParam, token)
        call?.enqueue(object : Callback<AddCategoryResult> {
            override fun onResponse(call: Call<AddCategoryResult>, response: Response<AddCategoryResult>) {
                val result = ServiceResult<AddCategoryResult>()

                if (response.code() == 200) {
                    result.setmData(response.body())
                } else {
                    result.setmError(ServiceException())
                }

                bodyResultListener.onResult(result)
            }

            override fun onFailure(call: Call<AddCategoryResult>, t: Throwable) {
                /*val result = ServiceResult<User>()
                result.setmError(ServiceException(t, ServiceExceptionType.UNKNOWN))
                if (bodyResultListener != null)
                    bodyResultListener.onResult(result)*/
                val toto = t.localizedMessage
            }
        })
    }

    override fun adds(addCategoriesParam: List<AddCategoryParam>, token: String, bodyResultListener: IServiceResultListener<AddCategoryResult>) {
        val call = getCategoryInterface()?.adds(addCategoriesParam, token)
        call?.enqueue(object : Callback<AddCategoryResult> {
            override fun onResponse(call: Call<AddCategoryResult>, response: Response<AddCategoryResult>) {
                val result = ServiceResult<AddCategoryResult>()

                if (response.code() == 200) {
                    result.setmData(response.body())
                } else {
                    result.setmError(ServiceException())
                }

                bodyResultListener.onResult(result)
            }

            override fun onFailure(call: Call<AddCategoryResult>, t: Throwable) {
                /*val result = ServiceResult<User>()
                result.setmError(ServiceException(t, ServiceExceptionType.UNKNOWN))
                if (bodyResultListener != null)
                    bodyResultListener.onResult(result)*/
                val toto = t.localizedMessage
            }
        })
    }

    override fun getAllCategory(token: String, bodyResultListener: IServiceResultListener<GetAllCategoryResult>) {
        val call = getCategoryInterface()?.getAllCategory(token)
        call?.enqueue(object : Callback<GetAllCategoryResult> {
            override fun onResponse(call: Call<GetAllCategoryResult>, response: Response<GetAllCategoryResult>) {
                val result = ServiceResult<GetAllCategoryResult>()

                if (response.code() == 200) {
                    result.setmData(response.body())
                } else {
                    result.setmError(ServiceException())
                }

                bodyResultListener.onResult(result)
            }

            override fun onFailure(call: Call<GetAllCategoryResult>, t: Throwable) {
                /*val result = ServiceResult<User>()
                result.setmError(ServiceException(t, ServiceExceptionType.UNKNOWN))
                if (bodyResultListener != null)
                    bodyResultListener.onResult(result)*/
                val toto = t.localizedMessage
            }
        })
    }

}