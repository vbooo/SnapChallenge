package mypromotion.vboo.com.snapchallenge.network.interfaces.category

import mypromotion.vboo.com.snapchallenge.model.network.category.AddCategoryParam
import mypromotion.vboo.com.snapchallenge.model.network.category.AddCategoryResult
import mypromotion.vboo.com.snapchallenge.model.network.category.GetAllCategoryResult
import mypromotion.vboo.com.snapchallenge.network.interfaces.IServiceResultListener

interface ICategoryInterface {
    fun add(addCategoryParam: AddCategoryParam, token: String, bodyResultListener: IServiceResultListener<AddCategoryResult>)
    fun adds(addCategoriesParam: List<AddCategoryParam>, token: String, bodyResultListener: IServiceResultListener<AddCategoryResult>)
    fun getAllCategory(token: String, bodyResultListener: IServiceResultListener<GetAllCategoryResult>)
}