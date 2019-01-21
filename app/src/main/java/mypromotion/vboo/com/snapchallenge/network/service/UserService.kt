package mypromotion.vboo.com.snapchallenge.network.service

import mypromotion.vboo.com.snapchallenge.model.User
import mypromotion.vboo.com.snapchallenge.network.ClientWS
import mypromotion.vboo.com.snapchallenge.network.ServiceResult
import mypromotion.vboo.com.snapchallenge.network.interfaces.IServiceResultListener
import mypromotion.vboo.com.snapchallenge.network.interfaces.IUserInterface
import mypromotion.vboo.com.snapchallenge.network.interfaces.UserInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserService: IUserInterface {


    override fun addUser(resultListener: IServiceResultListener<User>) {
        val call = getmMediaInterface()?.addUser()
        call?.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val result = ServiceResult<User>()

                if (response.code() == 200) {
                    result.setmData(response.body())
                } else {
                    //result.setmError(ServiceException(response.code()))
                }

                if (resultListener != null)
                    resultListener.onResult(result)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                /*val result = ServiceResult<User>()
                result.setmError(ServiceException(t, ServiceExceptionType.UNKNOWN))
                if (resultListener != null)
                    resultListener.onResult(result)*/
            }
        })
    }

    private var mUserInterface: UserInterface? = null

    private fun getmMediaInterface(): UserInterface? {
        if (mUserInterface == null)
            mUserInterface = ClientWS.getClient().create(UserInterface::class.java)
        return mUserInterface
    }
}