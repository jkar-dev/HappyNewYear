package com.jkapps.newyear

import android.animation.PropertyValuesHolder
import android.view.View
import com.jkapps.newyear.entity.AnimationProperties
import com.jkapps.newyear.entity.Dimension
import com.jkapps.newyear.entity.Point

class AnimationPropertyProvider(buttonWidth: Float, offset: Point, treeDimension: Dimension) {
    private val translationX: List<PropertyValuesHolder> = listOf(
        PropertyValuesHolder.ofFloat(View.X, offset.x + (treeDimension.width * 0.32).toFloat()),
        PropertyValuesHolder.ofFloat(View.X, offset.x + (treeDimension.width * 0.57).toFloat()),
        PropertyValuesHolder.ofFloat(View.X, offset.x + (treeDimension.width * 0.52).toFloat()),
        PropertyValuesHolder.ofFloat(View.X, offset.x + (treeDimension.width * 0.39).toFloat()),
        PropertyValuesHolder.ofFloat(View.X,offset.x + (treeDimension.width * 0.5).toFloat() - buttonWidth / 2)
    )
    private val translationY: List<PropertyValuesHolder> = listOf(
        PropertyValuesHolder.ofFloat(View.Y, offset.y + (treeDimension.height * 0.57).toFloat()),
        PropertyValuesHolder.ofFloat(View.Y, offset.y + (treeDimension.height * 0.56).toFloat()),
        PropertyValuesHolder.ofFloat(View.Y, offset.y + (treeDimension.height * 0.39).toFloat()),
        PropertyValuesHolder.ofFloat(View.Y, offset.y + (treeDimension.height * 0.25).toFloat()),
        PropertyValuesHolder.ofFloat(View.Y, offset.y - (treeDimension.height * 0.07).toFloat())
    )
    private val rotation: List<PropertyValuesHolder> = listOf(
        PropertyValuesHolder.ofFloat(View.ROTATION, 28f),
        PropertyValuesHolder.ofFloat(View.ROTATION, -11f),
        PropertyValuesHolder.ofFloat(View.ROTATION, 5f),
        PropertyValuesHolder.ofFloat(View.ROTATION, -5f),
        PropertyValuesHolder.ofFloat(View.ROTATION, 0f)
    )


    fun getAnimationProperty(index: Int): AnimationProperties {
        return AnimationProperties(rotation[index], translationX[index], translationY[index])
    }
}