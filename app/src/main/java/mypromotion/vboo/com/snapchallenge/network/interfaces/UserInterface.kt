package mypromotion.vboo.com.snapchallenge.network.interfaces

import mypromotion.vboo.com.snapchallenge.model.network.*
import retrofit2.Call
import retrofit2.http.*

interface UserInterface {

    @POST("user/login")
    fun login(@Body userLoginData: UserLoginData): Call<LoginBodyResult>

    @POST("user/add")
    fun add(@Body userLoginData: AddUserData): Call<LoginBodyResult>

    @GET("user/{user-id}")
    fun findById(@Path("user-id") userId: String, @Header("x-access-token") token: String): Call<FindUserByIdResult>

    @GET("user/")
    fun getAllUsers(@Header("x-access-token") token: String): Call<AllUsersResult>
}