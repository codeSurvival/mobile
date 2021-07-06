package fr.esgi.codesurvival

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import org.json.JSONObject
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory

class HTTP {
    private val TAG = "Http"

    fun queryServer(path: String?): String? {
        try {
            val url = URL(path)

            // Seems to be a problem when accessing a simple http connection
            // Needs to cast on https because of call to setSSLSocketFactory
            // HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            val conn = url.openConnection() as HttpsURLConnection
            conn.readTimeout = 15000
            conn.connectTimeout = 15000
            conn.requestMethod = "GET"
            conn.doOutput = true
            conn.doInput = true
            conn.setRequestProperty("Accept", "application/json")

            //Old Android versions have problems when accessing https and generate a "handshake error"
            //So we created class TLS12SocketFactory and we added a call to ProviderInstall into MainActivity
            // ProviderInstaller.installIfNeeded(getApplicationContext());
            // This call could maybe be removed if we stop supporting Android 4.4 since it may be costly
            //https://stackoverflow.com/questions/28943660/how-to-enable-tls-1-2-support-in-an-android-application-running-on-android-4-1
            //https://stackoverflow.com/questions/34493334/android-4-4-2-ssl-handshake-aborted
            val responseCode = conn.responseCode
            Log.d("http.query", "AFTER getResponseCode : $responseCode")
            return if (responseCode == HttpsURLConnection.HTTP_OK) {
                val `in` = BufferedReader(
                    InputStreamReader(
                        conn.inputStream
                    )
                )
                val sb = StringBuffer("")
                var line: String? = ""
                //get sb to string
                while (`in`.readLine().also { line = it } != null) {
                    sb.append(line)
                    break
                }
                `in`.close()
                Log.d("response", sb.toString())
                sb.toString()
            } else {
                return "false : $responseCode"
            }
        } catch (e: Exception) {
            Log.d(TAG, String.format("queryServer: Exception %s caught", e.javaClass.name))
            e.printStackTrace()
        }
        return null
    }

    // Old Android versions have problems when accessing https and generate a "handshake error"
    // See comments on method queryServer
    // POST method does not seem to need adaptation
    fun queryPostServer(path: String?, params: JSONObject): String? {
        try {
            val url = URL(path)
            val conn = url.openConnection() as HttpURLConnection
            conn.readTimeout = 15000
            conn.connectTimeout = 15000
            conn.requestMethod = "POST"
            conn.doOutput = true
            conn.doInput = true
            val os = conn.outputStream
            val writer = BufferedWriter(
                OutputStreamWriter(os, "UTF-8")
            )
            writer.write(getPostDataString(params))
            writer.flush()
            writer.close()
            os.close()
            val responseCode = conn.responseCode
            return if (responseCode == HttpsURLConnection.HTTP_OK) {
                val `in` = BufferedReader(
                    InputStreamReader(
                        conn.inputStream
                    )
                )
                val sb = StringBuffer("")
                var line: String? = ""
                while (`in`.readLine().also { line = it } != null) {
                    sb.append(line)
                    break
                }
                `in`.close()
                Log.d("response", sb.toString())
                sb.toString()
            } else {
                return "false : $responseCode"
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    @Throws(Exception::class)
    fun getPostDataString(params: JSONObject): String? {
        val result = StringBuilder()
        var first = true
        val itr = params.keys()
        while (itr.hasNext()) {
            val key = itr.next()
            val value = params[key]
            if (first) first = false else result.append("&")
            result.append(URLEncoder.encode(key, "UTF-8"))
            result.append("=")
            result.append(URLEncoder.encode(value.toString(), "UTF-8"))
        }
        return result.toString()
    }

    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

    fun isWifiOn(context: Context): Boolean {
        val connManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return mWifi!!.isConnected
    }
}