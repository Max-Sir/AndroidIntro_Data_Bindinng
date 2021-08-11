package com.coherentsolutions.max.sir.androidintro.first

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.coherentsolutions.max.sir.androidintro.first.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val name=Name("Maxim Syromolotov","Max-Sir")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
//        findViewById<Button>(R.id.button).setOnClickListener() {
//            showHelloToast(it)
//        }
//        findViewById<EditText>(R.id.editName).setSelection(0)
//        findViewById<ImageButton>(R.id.imageButton).setOnClickListener() {
//            resetView()
//        }
        binding.myName=name
        binding.apply{
            button.setOnClickListener(::showHelloToast)
            invalidateAll()
            editName.setSelection(0)
            imageButton.setOnClickListener { resetView() }
        }
    }


    fun resetView() {
        binding.editName.apply(::makeVisible).apply { text.clear() }
        binding.button.apply(::makeVisible)

    }

    fun makeVisible(view: View, value: Int = View.VISIBLE) {
        view.visibility = value
    }

    fun makeGone(view: View) {
        view.visibility = View.GONE
    }

    fun showHelloToast(view: View) {


        val editText = binding.editName.apply(::makeGone)
        view.apply(::makeGone)

        Toast.makeText(this,"Hello ${editText.text}",Toast.LENGTH_LONG).show()

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun openWebPage(view: View) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.googleSeach)))
        startActivity(browserIntent)
    }
}


