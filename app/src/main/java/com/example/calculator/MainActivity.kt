package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var tvResult: TextView
    private lateinit var previousTvResult: TextView
    private var wasNumber = true
    private var first = true
    private var previousId: Int = 0
    private var previousResult: Float = 0F
    private var result: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myClickListener = View.OnClickListener {
            when (it.id) {
                binding.btnEqual.id -> equal()
                binding.btnDel.id -> deleteLast(getText())
                binding.btnClear.id -> clearString()
                binding.btnPlus.id -> startToCalc(1)
                binding.btnMin.id -> startToCalc(2)
                binding.btnDiv.id -> startToCalc(3)
                binding.btnX.id -> startToCalc(4)
                binding.btn1.id -> addNumber("1")
                binding.btn2.id -> addNumber("2")
                binding.btn3.id -> addNumber("3")
                binding.btn4.id -> addNumber("4")
                binding.btn5.id -> addNumber("5")
                binding.btn6.id -> addNumber("6")
                binding.btn7.id -> addNumber("7")
                binding.btn8.id -> addNumber("8")
                binding.btn9.id -> addNumber("9")
                binding.btn0.id -> addNumber("0")
            }
        }

        initButtons(myClickListener)
    }

    private fun equal() {
        startToCalc(previousId)
    }

    @SuppressLint("SetTextI18n")
    private fun calculate (id: Int){
        val number = getText().toFloat()
        when (id) {
            0 -> {
                result = number
            }
            1 -> { // 1 при +
                result = result.plus(number)
                previousTvResult.text = "$previousResult + $number = "
            }
            2 -> { // 2 при -
                result = result.minus(number)
                previousTvResult.text = "$previousResult - $number = "
            }
            3 -> { // 3 при /
                result = result.div(number)
                previousTvResult.text = "$previousResult / $number = "
            }
            4 -> { // 4 при *
                result = result.times(number)
                previousTvResult.text = "$previousResult * $number = "
            }
        }
        wasNumber = false
    }

    private fun startToCalc(id:Int) {
        if(getText().isBlank()) {
            return
        }
        if (wasNumber) {
            calculate(previousId)
            tvResult.text = result.toString()
            previousResult = result
        }
        previousId = id
    }

    private fun addNumber(number: String) {
        if (wasNumber) {
            tvResult.text = getText().plus(number)
        } else {
            tvResult.text = number
        }
        wasNumber = true
    }

    private fun getText():String {
        return tvResult.text.toString()
    }

    private fun deleteLast(s: String) {
        if (s.length <= 1) {
            tvResult.text = ""
        } else {
            tvResult.text = s.dropLast(1)
            result = getText().toFloat()
            previousResult = result
        }
    }

    private fun clearString() {
        if (result == 0F) {
            previousTvResult.text = ""
        }
        result = 0F
        previousResult = 0F
        previousId = 0
        tvResult.text = ""
        wasNumber = true
        first = true
    }

    private fun initButtons(myClickListener: View.OnClickListener) {
        tvResult = findViewById(R.id.tvResult)
        previousTvResult = findViewById(R.id.previousTvResult)

        binding.btn1.setOnClickListener(myClickListener)
        binding.btn2.setOnClickListener(myClickListener)
        binding.btn3.setOnClickListener(myClickListener)
        binding.btn4.setOnClickListener(myClickListener)
        binding.btn5.setOnClickListener(myClickListener)
        binding.btn6.setOnClickListener(myClickListener)
        binding.btn7.setOnClickListener(myClickListener)
        binding.btn8.setOnClickListener(myClickListener)
        binding.btn9.setOnClickListener(myClickListener)
        binding.btn0.setOnClickListener(myClickListener)
        binding.btnClear.setOnClickListener(myClickListener)
        binding.btnEqual.setOnClickListener(myClickListener)
        binding.btnPlus.setOnClickListener(myClickListener)
        binding.btnMin.setOnClickListener(myClickListener)
        binding.btnDot.setOnClickListener(myClickListener)
        binding.btnDiv.setOnClickListener(myClickListener)
        binding.btnX.setOnClickListener(myClickListener)
        binding.btnDel.setOnClickListener(myClickListener)
    }
}