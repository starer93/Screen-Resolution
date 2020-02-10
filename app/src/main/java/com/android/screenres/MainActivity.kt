package com.android.screenres

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Point
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


open class MainActivity : AppCompatActivity() {

    private var presentation:ScreenPresentation? = null
    private var imageView: ImageView? = null
    private var btnLoad:Button? = null
   // private var editInput: EditText? = null
    private var txtScreenSize: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//        actionBar?.hide()
//        loadScreenSize()
        load2()
        showBlankScreen()
    }

    private fun initView()
    {

        txtScreenSize = findViewById(R.id.txt_aaa)
        imageView = findViewById(R.id.img_full)
        btnLoad = findViewById(R.id.btn_load)
        btnLoad?.setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 1 && resultCode == Activity.RESULT_OK)
        {
            presentation?.loadImage(data?.data)
           Glide.with(this).load(data?.data).into(imageView!!)
        }
    }

    private fun load2()
    {
        val metrics= DisplayMetrics()
        windowManager.defaultDisplay.getRealMetrics(metrics)

        val width: Int =  metrics.widthPixels
        val height: Int = metrics.heightPixels

        txtScreenSize!!.text = "width: " + width + " height: " + height + " density: "+ metrics.density + " dpi " + metrics.densityDpi
    }

    private fun showBlankScreen() {
        if (!isFinishing) {
            var displayManager = getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
            var presentationDisplays = displayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION)
            if (presentationDisplays.isNotEmpty()) {
                // If there is more than one suitable presentation display, then we could consider
                // giving the user a choice.  For this one, we simply choose the first display
                val display = presentationDisplays[0]
                presentation = ScreenPresentation(this, display)
                presentation!!.show()
            }
        }
    }
}
