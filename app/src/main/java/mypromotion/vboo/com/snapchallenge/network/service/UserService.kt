package mypromotion.vboo.com.snapchallenge.network.service

import mypromotion.vboo.com.snapchallenge.model.network.*
import mypromotion.vboo.com.snapchallenge.model.network.user.AddUserParam
import mypromotion.vboo.com.snapchallenge.model.network.user.AllUsersResult
import mypromotion.vboo.com.snapchallenge.model.network.user.FindUserByIdResult
import mypromotion.vboo.com.snapchallenge.model.network.user.UserLoginParam
import mypromotion.vboo.com.snapchallenge.network.ClientWS
import mypromotion.vboo.com.snapchallenge.network.ServiceException
import mypromotion.vboo.com.snapchallenge.network.ServiceResult
import mypromotion.vboo.com.snapchallenge.network.interfaces.IServiceResultListener
import mypromotion.vboo.com.snapchallenge.network.interfaces.user.IUserInterface
import mypromotion.vboo.com.snapchallenge.network.interfaces.user.UserInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserService: IUserInterface {


    override fun add(addUserParam: AddUserParam, bodyResultListener: IServiceResultListener<LoginBodyResult>) {
        val call = getUserInterface()?.add(addUserParam)
        call?.enqueue(object : Callback<LoginBodyResult> {
            override fun onResponse(call: Call<LoginBodyResult>, response: Response<LoginBodyResult>) {
                val result = ServiceResult<LoginBodyResult>()

                if (response.code() == 200) {
                    result.setmData(response.body())
                } else {
                    result.setmError(ServiceException())
                }

                bodyResultListener.onResult(result)
            }

            override fun onFailure(call: Call<LoginBodyResult>, t: Throwable) {
                /*val result = ServiceResult<User>()
                result.setmError(ServiceException(t, ServiceExceptionType.UNKNOWN))
                if (bodyResultListener != null)
                    bodyResultListener.onResult(result)*/
                val toto = t.localizedMessage
            }
        })
    }


    override fun findById(userId: String, token: String, bodyResultListener: IServiceResultListener<FindUserByIdResult>) {
        val call = getUserInterface()?.findById(userId, token)
        call?.enqueue(object : Callback<FindUserByIdResult> {
            override fun onResponse(call: Call<FindUserByIdResult>, response: Response<FindUserByIdResult>) {
                val result = ServiceResult<FindUserByIdResult>()

                if (response.code() == 200) {
                    result.setmData(response.body())
                } else {
                    result.setmError(ServiceException())
                }

                bodyResultListener.onResult(result)
            }

            override fun onFailure(call: Call<FindUserByIdResult>, t: Throwable) {
                /*val result = ServiceResult<User>()
                result.setmError(ServiceException(t, ServiceExceptionType.UNKNOWN))
                if (bodyResultListener != null)
                    bodyResultListener.onResult(result)*/
                val toto = t.localizedMessage
            }
        })
    }


    override fun getAllUsers(token: String, bodyResultListener: IServiceResultListener<AllUsersResult>) {
        val call = getUserInterface()?.getAllUsers(token)
        call?.enqueue(object : Callback<AllUsersResult> {
            override fun onResponse(call: Call<AllUsersResult>, response: Response<AllUsersResult>) {
                val result = ServiceResult<AllUsersResult>()

                if (response.code() == 200) {
                    result.setmData(response.body())
                } else {
                    result.setmError(ServiceException())
                }

                bodyResultListener.onResult(result)
            }

            override fun onFailure(call: Call<AllUsersResult>, t: Throwable) {
                /*val result = ServiceResult<User>()
                result.setmError(ServiceException(t, ServiceExceptionType.UNKNOWN))
                if (bodyResultListener != null)
                    bodyResultListener.onResult(result)*/
                val toto = t.localizedMessage
            }
        })
    }

    private var mUserInterface: UserInterface? = null

    override fun login(userLoginParam: UserLoginParam, bodyResultListener: IServiceResultListener<LoginBodyResult>) {
        val call = getUserInterface()?.login(userLoginParam)
        call?.enqueue(object : Callback<LoginBodyResult> {
            override fun onResponse(call: Call<LoginBodyResult>, response: Response<LoginBodyResult>) {
                val result = ServiceResult<LoginBodyResult>()

                if (response.code() == 200) {
                    result.setmData(response.body())
                } else {
                    result.setmError(ServiceException())
                }

                bodyResultListener.onResult(result)
            }

            override fun onFailure(call: Call<LoginBodyResult>, t: Throwable) {
                /*val result = ServiceResult<User>()
                result.setmError(ServiceException(t, ServiceExceptionType.UNKNOWN))
                if (bodyResultListener != null)
                    bodyResultListener.onResult(result)*/
                val toto = t.localizedMessage
            }
        })
    }

    private fun getUserInterface(): UserInterface? {
        if (mUserInterface == null)
            mUserInterface = ClientWS.getClient().create(UserInterface::class.java)
        return mUserInterface
    }
}