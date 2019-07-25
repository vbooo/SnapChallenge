package mypromotion.vboo.com.snapchallenge.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientWS {

    companion object {
        val BASE_URL_DEV = "http://134.209.189.64/app_api/"
        val BASE_URL_PROD = "http://134.209.189.64/app_api/"

        fun getClient(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL_DEV)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }

}