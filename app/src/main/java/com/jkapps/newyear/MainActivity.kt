package com.jkapps.newyear

import android.animation.*
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.doOnPreDraw
import com.jkapps.newyear.Decoration.*
import com.jkapps.newyear.entity.AnimationProperties
import com.jkapps.newyear.entity.Dimension
import com.jkapps.newyear.entity.Point

class MainActivity : AppCompatActivity() {
    private lateinit var tree : Tree

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout = findViewById<View>(R.id.layout)

        val btnCandyCane: ImageButton = findViewById(R.id.btn_candy_cane)
        val btnGingerbread: ImageButton = findViewById(R.id.btn_gingerbread_man)
        val btnStocking: ImageButton = findViewById(R.id.btn_stocking)
        val btnWreath: ImageButton = findViewById(R.id.btn_wreath)
        val btnStar: ImageButton = findViewById(R.id.btn_star)

        val buttons = mapOf(
                CANDY_CANE to btnCandyCane,
                GINGERBREAD to btnGingerbread,
                STOCKING to btnStocking,
                WREATH to btnWreath,
                STAR to btnStar)


        layout.doOnPreDraw {
            val ivTree = findViewById<View>(R.id.iv_background)
            val point = Point(ivTree.x, ivTree.y)
            val dimension =  Dimension(ivTree.width, ivTree.height)
            val propertyProvider = AnimationPropertyProvider(btnStar.width.toFloat(), point, dimension)

            tree = Tree(propertyProvider)
        }

        buttons.forEach { entity ->
            entity.value.setOnClickListener { view ->
                tree.tryToDecorate(entity.key) {
                    view.background = ContextCompat.getDrawable(this@MainActivity, android.R.color.transparent)
                    animate(view, it)
                }
            }
        }
    }

    private fun animate(view: View, animationProperty: AnimationProperties) {
        val bounceAnim = AnimatorSet().apply {
            playTogether(
                    ObjectAnimator.ofFloat(view, View.SCALE_X, 1.4f, 1f),
                    ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.4f, 1f)
            )
            interpolator = AccelerateDecelerateInterpolator()
            duration = 600
        }

        val puttingAnim = ObjectAnimator.ofPropertyValuesHolder(
                view,
                animationProperty.rotation,
                animationProperty.translationX,
                animationProperty.translationY
        ).apply {
            duration = 1000
        }

        AnimatorSet().apply {
            playSequentially(bounceAnim, puttingAnim)
            start()
        }
    }
}