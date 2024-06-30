package xyz.mattjashworth.sample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import xyz.mattjashworth.yesno.YesNoButton.YesNoButton
import xyz.mattjashworth.yesno.YesNoResult

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rootView = findViewById<View>(R.id.main)


        val yesNoButtons = findViewById<YesNoButton>(R.id.yesNoDemo)
        yesNoButtons.setOnYesNoClickListener(object : YesNoButton.OnClickListener {
            override fun onClick(model: YesNoResult) {
                //Show Snackbar with result (Yes, No, None)
                Snackbar.make(rootView, model.name, Snackbar.LENGTH_LONG).show()
            }

        })

    }
}