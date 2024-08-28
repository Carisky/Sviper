package com.example.sviper

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val fragments = listOf(FirstFragment(), SecondFragment(), ThirdFragment())
    private var currentFragmentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Встановлюємо перший фрагмент при старті
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragments[currentFragmentIndex])
            .commit()

        // Налаштовуємо обробку свайпів
        val frameLayout = findViewById<FrameLayout>(R.id.fragmentContainer)
        frameLayout.setOnTouchListener(object : OnSwipeListener(this) {
            override fun onSwipeRight() {
                if (currentFragmentIndex > 0) {
                    currentFragmentIndex--
                    replaceFragment()
                }
            }

            override fun onSwipeLeft() {
                if (currentFragmentIndex < fragments.size - 1) {
                    currentFragmentIndex++
                    replaceFragment()
                }
            }
        })
    }

    private fun replaceFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragments[currentFragmentIndex])
            .commit()
    }
}