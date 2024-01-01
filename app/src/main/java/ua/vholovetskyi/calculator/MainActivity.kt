package ua.vholovetskyi.calculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import ua.vholovetskyi.calculator.databinding.ActivityMainBinding
import java.lang.Exception

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
        mainBinding.dataTextView.text = ""
        mainBinding.resultTextView.text = ""
        stateError = false
        lastDot = false
        lastNumeric = false
        mainBinding.resultTextView.visibility = View.GONE

    }

    fun onEqualClick(view: View) {
        onEqual()
        mainBinding.dataTextView.text = mainBinding.resultTextView.text.toString().drop(1)
    }

    fun onDigitClick(view: View) {
        if (stateError) {
            mainBinding.dataTextView.text = (view as Button).text
            stateError = false
        } else {
            mainBinding.dataTextView.append((view as Button).text)
        }
        lastNumeric = true
        onEqual()
    }

    fun onOperatorClick(view: View) {
        if (!stateError && lastNumeric) {
            mainBinding.dataTextView.append((view as Button).text)
            lastDot = false
            lastNumeric = false
            onEqual()
        }
    }

    fun onBackClick(view: View) {
        mainBinding.dataTextView.text = mainBinding.dataTextView.text.toString().dropLast(1)
        try {
            val lastChar = mainBinding.dataTextView.text.toString().last()
            if (lastChar.isDigit()) {
                onEqual()
            }
        } catch (e: Exception) {
            mainBinding.resultTextView.text = ""
            mainBinding.resultTextView.visibility = View.VISIBLE
            Log.e("Last char error", e.toString())
        }
    }

    fun onClearClick(view: View) {
        mainBinding.dataTextView.text = ""
        lastNumeric = false
    }

    fun onEqual() {
        if (lastNumeric && !stateError) {
            val text = mainBinding.dataTextView.text.toString()
            expression = ExpressionBuilder(text).build()
            try {
                val result = expression.evaluate()
                mainBinding.resultTextView.visibility = View.VISIBLE
                mainBinding.resultTextView.text = "=" + result.toString()

            } catch (ex: ArithmeticException) {
                Log.e("Evaluate Error", ex.toString())
                mainBinding.resultTextView.text = "Error"
                stateError = true
                lastNumeric = false
            }
        }
    }

}