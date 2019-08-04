package mypromotion.vboo.com.snapchallenge.network.interfaces.category

import mypromotion.vboo.com.snapchallenge.model.network.category.AddCategoryParam
import mypromotion.vboo.com.snapchallenge.model.network.category.AddCategoryResult
import mypromotion.vboo.com.snapchallenge.model.network.category.GetAllCategoryResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface CategoryInterface {

    @POST("category/add")
    fun add(@Body addCategoryParam: AddCategoryParam, @Header("x-access-token") token: String): Call<AddCategoryResult>

    @POST("category/adds")
    fun adds(@Body addCategoriesParam: List<AddCategoryParam>, @Header("x-access-token") token: String): Call<AddCategoryResult>

    @GET("category")
    fun getAllCategory(@Header("x-access-token") token: String): Call<GetAllCategoryResult>
}