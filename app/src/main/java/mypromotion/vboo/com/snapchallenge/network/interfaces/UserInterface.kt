package mypromotion.vboo.com.snapchallenge.network.interfaces

import mypromotion.vboo.com.snapchallenge.model.User
import retrofit2.Call
import retrofit2.http.POST

interface UserInterface {

    @POST("users/add")
    abstract fun addUser(): Call<User>
}