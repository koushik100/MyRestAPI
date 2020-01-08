package com.koushik.myrestapi

import android.os.AsyncTask
import okhttp3.Headers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONException
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

const val MULTIPART = "multipart/form-data"
const val URLENCODER = "application/x-www-form-urlencoded"
const val CONTENT_TYPE = "content-type"

class FormPost(
    val url: String,
    val METHOD: String,
    val params: HashMap<String, Any>?,
    val header: HashMap<String, Any>?,
    val listner: Responselistener
) : AsyncTask<Void, String, ResponseData?>() {
    override fun onPostExecute(result: ResponseData?) {
        super.onPostExecute(result)
        if (result == null) {
            listner.OnError("Something Went Wrong please try again")
        } else {

            try {
                Klog.d("## FORM RESPONSE_", result.response)
                val respCode = result.code
                when (respCode) {
                    200 -> listner.OnSucess(result.response)
                    401 -> listner.OnError("Authentication failed.")
                    500 -> listner.OnError("The request was not completed. The server met an unexpected condition.")
                    503 -> listner.OnError("The request was not completed. The server is temporarily overloading or down.")
                    403 -> listner.OnError("Access is forbidden to the requested page.")
                    404 -> listner.OnError("The server can not find the requested page.")
                    else -> listner.OnError("Unknown error please try again later.")
                }


            } catch (e: JSONException) {
                e.printStackTrace()
                listner.OnError("Something Went Wrong please try again later.")
            }

        }
    }

    override fun onPreExecute() {
        super.onPreExecute()
        listner.Start()
    }

    override fun doInBackground(vararg p0: Void?): ResponseData? {
        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()


        val MEDIA_TYPE_PNG = "image/jpeg".toMediaTypeOrNull()
//        final MediaType MEDIA_TYPE_PNG = MediaType.parse("*/*");

        val bodybuilder = MultipartBody.Builder()
        bodybuilder.setType(MultipartBody.FORM)

        if (params != null)
            for (entry in params.entries) {
                if (entry.value is File) {
                    val Requploadfile = entry.value as File
                    Klog.d("## PATH FI-", Requploadfile.path)
                    bodybuilder.addFormDataPart(
                        entry.key,
                        Requploadfile.name,
                        Requploadfile.asRequestBody(MEDIA_TYPE_PNG)
                    )
                } else
                    bodybuilder.addFormDataPart(entry.key, entry.value.toString())
            }
        val requestBody = bodybuilder
            .build()

        val builder = Headers.Builder()
        if (header != null)
            for (entry in header.entries) {
                println(entry.key + "/" + entry.value)
                builder.add(entry.key, entry.value.toString())
            }
        val h = builder.build()

        val request = Request.Builder()
            .url(url)
            .method(METHOD, requestBody)
//            .post(requestBody)
//            .addHeader("content-type", "multipart/form-data")
            .headers(h)
            .build()


        try {

            val response = client.newCall(request).execute()
            return ResponseData(response.code, response.body!!.string())
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

    }
}

