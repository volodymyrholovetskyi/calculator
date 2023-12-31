package ua.vholovetskyi.calculator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.Expression
import ua.vholovetskyi.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private var lastNumeric = false
    private var stateError = false
    private var lastDot = false
    private lateinit var expression: Expression
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

    }

    fun onAllClearClick(view: View) {
    }

    fun onEqualClick(view: View) {
    }

    fun onDigitClick(view: View) {
    }

    fun onOperatorClick(view: View) {
    }

    fun onBackClick(view: View) {
    }

    fun onClearClick(view: View) {
    }


}