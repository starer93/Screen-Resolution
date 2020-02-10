package com.android.screenres

import android.app.Presentation
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide

class ScreenPresentation(outerContext: Context?, display: Display?) : Presentation(outerContext, display) {

    private var imageView: ImageView? = null
    private var btnLoad: Button? = null
    // private var editInput: EditText? = null
    private var txtScreenSize: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view= LayoutInflater.from(context).inflate(R.layout.activity_main,null)
        view.layoutParams = RelativeLayout.LayoutParams(1080,1920)
        setContentView(view)
        initView(view)
        load2()
    }


    private fun initView(view:View)
    {

        txtScreenSize = view.findViewById(R.id.txt_aaa)
        imageView = view.findViewById(R.id.img_full)
        btnLoad = view.findViewById(R.id.btn_load)
        btnLoad?.visibility = View.INVISIBLE
    }


    fun loadImage(uri:Uri?) {
        Glide.with(context).load(uri).into(imageView!!)
    }


    private fun load2() {
        val metrics= DisplayMetrics()
        window.windowManager.defaultDisplay.getRealMetrics(metrics)
//        val size = Point()
//        display.getSize(size)

        val width = metrics.widthPixels
        val height = metrics.heightPixels
        txtScreenSize!!.text = "width: " + width + " height: " + height + " density: "+ metrics.density + " dpi " + metrics.densityDpi
    }
}