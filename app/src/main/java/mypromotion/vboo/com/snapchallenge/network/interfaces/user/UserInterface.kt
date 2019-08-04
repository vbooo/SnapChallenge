package mypromotion.vboo.com.snapchallenge.network.interfaces.user

import mypromotion.vboo.com.snapchallenge.model.network.*
import mypromotion.vboo.com.snapchallenge.model.network.user.AddUserParam
import mypromotion.vboo.com.snapchallenge.model.network.user.AllUsersResult
import mypromotion.vboo.com.snapchallenge.model.network.user.FindUserByIdResult
import mypromotion.vboo.com.snapchallenge.model.network.user.UserLoginParam
import retrofit2.Call
import retrofit2.http.*

interface UserInterface {

    @POST("user/login")
    fun login(@Body userLoginParam: UserLoginParam): Call<LoginBodyResult>

    @POST("user/add")
    fun add(@Body userLoginParam: AddUserParam): Call<LoginBodyResult>

    @GET("user/{user-id}")
    fun findById(@Path("user-id") userId: String, @Header("x-access-token") token: String): Call<FindUserByIdResult>

    @GET("user/")
    fun getAllUsers(@Header("x-access-token") token: String): Call<AllUsersResult>
}