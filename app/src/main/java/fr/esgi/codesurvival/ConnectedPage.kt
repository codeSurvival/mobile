package fr.esgi.codesurvival



import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConnectedPage : AppCompatActivity() {
    companion object{
        const val TAG = "NeedANameActivity"
    }
    private var username : TextView? = null
    private var email : TextView? = null
    private var role : TextView? = null
    private var token : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.connected_page)
        Log.d(TAG, "onCreate: ")
        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        role = findViewById(R.id.role)
        token = findViewById(R.id.token)

        username?.let { _ ->  "let test" }

        token?.text = intent.getStringExtra("userToken")
        //username?.text = intent.getStringExtra("username")
        email?.text = intent.getStringExtra("userEmail")
        role?.text = intent.getStringExtra("userRole")
    }
}