package com.hdd.pakwan.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.hdd.pakwan.R
import com.hdd.pakwan.data.models.IntroSlide
import com.hdd.pakwan.domain.adapter.IntroSlideAdapter

class DashboardActivity : AppCompatActivity() {
    private lateinit var introSlideVP: ViewPager2
    private lateinit var indicatorContainer: LinearLayout
    private lateinit var btnNext: Button
    private lateinit var txtSkipIntro: TextView
    private val introSliderAdapter = IntroSlideAdapter(
        listOf(
            IntroSlide(
                "Pakwan1",
                "Pakwan is a deep fried, crisp bread made with all purpose flour. These fried breads have the flavor of carom seeds, cumin and crushed black pepper.",
                R.drawable.pakwan
            ),
            IntroSlide(
                "Pakwan1",
                "Pakwan is a deep fried, crisp bread made with all purpose flour. These fried breads have the flavor of carom seeds, cumin and crushed black pepper.",
                R.drawable.pakwan
            ),
            IntroSlide(
                "Pakwan1",
                "Pakwan is a deep fried, crisp bread made with all purpose flour. These fried breads have the flavor of carom seeds, cumin and crushed black pepper.",
                R.drawable.pakwan
            ),
            IntroSlide(
                "Pakwan1",
                "Pakwan is a deep fried, crisp bread made with all purpose flour. These fried breads have the flavor of carom seeds, cumin and crushed black pepper.",
                R.drawable.pakwan
            ),
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        introSlideVP = findViewById(R.id.introSliderVP)
        indicatorContainer = findViewById(R.id.indicatorContainer)
        btnNext = findViewById(R.id.buttonNext)
        txtSkipIntro = findViewById(R.id.txtSkipIntro)

        btnNext.setOnClickListener {
            if (introSlideVP.currentItem + 1 < introSliderAdapter.itemCount) {
                introSlideVP.currentItem += 1
            } else {
                Intent(applicationContext, LoginActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
        txtSkipIntro.setOnClickListener {
            Intent(applicationContext, LoginActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
        introSlideVP.adapter = introSliderAdapter
        setupIndicator()
        setCurrentIndicator(0)
        introSlideVP.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(

        ) {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
    }

    private fun setupIndicator() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.indicator_active)
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.indicator_inactive)
                )
            }
        }
    }

    //on back press trigger
    override fun onBackPressed() {
        showAlertDialog()
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Close App")
        builder.setIcon(R.drawable.exit)
        builder.setMessage("Are you sure to close app ?")
//        builder.setIcon(R.id.nav_sign_out)
        //performing Positive action
        builder.setPositiveButton("Yes") { _, _ ->
            super.onBackPressed()
        }
        //performing cancel action
        builder.setNeutralButton("Cancel") { _, _ ->
            Toast.makeText(this, "clicked cancel", Toast.LENGTH_SHORT).show()
        }
        //performing negative action
        builder.setNegativeButton("No") { _, _ ->
            Toast.makeText(this, "clicked No", Toast.LENGTH_SHORT).show()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()

    }

}