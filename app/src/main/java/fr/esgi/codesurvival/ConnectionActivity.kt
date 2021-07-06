package fr.esgi.codesurvival

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.esgi.codesurvival.users.ServerLoginTask
import fr.esgi.codesurvival.users.User
import fr.esgi.codesurvival.users.UserRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO

class ConnectionActivity : AppCompatActivity(){
    companion object {
        private const val TAG: String = "ConnectionActivity"
    }
    private var connectBtn : Button? = null
    private var emailET : EditText? = null
    private var passwordET : EditText? = null
    private var signUpRedirection : TextView? = null
    private val coroutineScope: CoroutineScope = CoroutineScope(CoroutineName(TAG))
    private var errorMessage : TextView? = null
    private var user : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connection_layout)
        connectBtn = findViewById(R.id.connection_btn)
        emailET = findViewById(R.id.emailET)
        passwordET = findViewById(R.id.passwordET)
        signUpRedirection = findViewById(R.id.sign_up_link_redirection)
        errorMessage = findViewById(R.id.error_message)

        connectBtn?.setOnClickListener { _: View? ->
            Log.d(TAG,"Connection button clicked")
            Log.d(TAG,"Email = "+emailET?.text)
            Log.d(TAG,"password = "+passwordET?.text)
            this.coroutineScope.launch(IO){

                val async1 = async {
                    val callback = ServerLoginTask(this@ConnectionActivity.user) {}
                    UserRepository.getUser(callback)
                    delay(1000)
                }
                async1.await()
                testValidity()
            }


        }

        signUpRedirection?.setOnClickListener { _: View? ->
            Log.d(TAG,"Sign up link clicked")
            signUpRedirection?.setTextColor(getColor(R.color.purple_500))
            signUpRedirection?.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
            startActivity(browserIntent)
        }
    }

    private suspend fun testValidity() {
        if (passwordET.toString() == "" && emailET.toString() == "")
            errorMessage?.text = getString(R.string.need_input)
        else if (passwordET.toString().length < 8){
            errorMessage?.text = getString(R.string.connection_error_to_short)
        }
        else if (user == null){
            errorMessage?.text = getString(R.string.user_do_no_exist)
        }
        else
            toTheNextActivity()
    }

    private suspend fun toTheNextActivity() : Unit {
        withContext(Default){
            val intent = Intent(applicationContext, ConnectedPage::class.java)
            startActivity(intent)
            Log.d(TAG, "toTheNextActivity: Called")
        }
    }

}