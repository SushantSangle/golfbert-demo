package xyz.sushant.golfbertdemo.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val TIMEOUT_CONNECTION = 1
const val TIMEOUT_READ = 5
const val BASE_URL = "https://api.golfbert.com/v1"

class ApiClient(var context: Context) {
    var routes : ApiInterface

     init {
        routes = retrofit().create(ApiInterface::class.java)
    }

    fun retrofit(): Retrofit {
        val builder = getBuilder()

        val httpClient = OkHttpClient.Builder()
            .readTimeout(TIMEOUT_READ.toLong(), TimeUnit.MINUTES)
            .connectTimeout(TIMEOUT_CONNECTION.toLong(), TimeUnit.MINUTES)

        // Header interceptor
        httpClient.addInterceptor(Interceptor {
            it.proceed(it.request())
        })

        // Logger interceptor
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(interceptor)

        val client = httpClient.build()
        return builder.client(client).build()
    }

    private fun getBuilder(): Retrofit.Builder {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
    }
}