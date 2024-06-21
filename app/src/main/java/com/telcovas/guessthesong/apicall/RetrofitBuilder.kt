package com.telcovas.guessthesong.apicall

import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

   // private const val BASE_URL = "https://5e510330f2c0d300147c034c.mockapi.io/"
    private const val BASE_URL = "https://globicall.globicallservices.com/SongsQuizApp/"
    private fun getRetrofit1(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

            .build()
    }

    val apiService: ApiService = getRetrofit()


    fun getRetrofit(baseUrl: String = BASE_URL,
                   connectionTimeOutInSec: Long = 10,
                   readTimeOutInSec: Long = 30,
                   writeTimeOutInSec: Long = 60): ApiService {
        return getRetrofitClient(getokhttpClientBuilder(connectionTimeOutInSec, readTimeOutInSec, writeTimeOutInSec), baseUrl).create(ApiService::class.java)
    }

    /* fun apiChatDemo(baseUrl: String = NetworkConstants.CHAT_DEMO_BASE_URL,
                    connectionTimeOutInSec: Long = 10,
                    readTimeOutInSec: Long = 30,
                    writeTimeOutInSec: Long = 60): ApiInterface {
         return getRetrofitClient(getokhttpClientBuilder(connectionTimeOutInSec, readTimeOutInSec, writeTimeOutInSec), baseUrl).create(ApiInterface::class.java)
     }
 */

    fun getRetrofitClient(okHttpClientBuilder: OkHttpClient.Builder, baseUrl: String) = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addConverterFactory(ScalarsConverterFactory.create())
        .client(okHttpClientBuilder.build())
        .baseUrl(baseUrl)
        .build()

    //var httpCacheDirectory: File = File(MyApplication.appContext.cacheDir, "cache_file")

    //var cache = Cache(httpCacheDirectory, 20 * 1024 * 1024)



    fun getokhttpClientBuilder(connectTimeoutInSec: Long, readTimeoutInSec: Long, writeTimeoutInSec: Long): OkHttpClient.Builder {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
       // okHttpClientBuilder.cache(cache)

        okHttpClientBuilder.connectTimeout(connectTimeoutInSec, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(readTimeoutInSec, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(writeTimeoutInSec, TimeUnit.SECONDS)
        return okHttpClientBuilder
    }





}