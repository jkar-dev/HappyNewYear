package com.jkapps.newyear

class Tree(private val animationProvider: AnimationPropertyProvider) {
    private val decorations = mutableMapOf<Decoration, Int>()

    fun tryToDecorate(decoration: Decoration, animationProperty: (AnimationProperties) -> Unit) {
        if (decorations.keys.size == MAX_NUMBER_OF_DECORATION) return
        if (decorations[decoration] == MAX_NUMBER_OF_DECORATION - 1) return
        val anim: AnimationProperties =
            if (isOnTree(decoration)) { getShiftDecorationAnim(decoration) }
            else { getPuttingDecorationAnim(decoration) }
        animationProperty.invoke(anim)
    }

    private fun isOnTree(d: Decoration) : Boolean {
        return decorations.containsKey(d)
    }

    private fun getShiftDecorationAnim(d : Decoration) : AnimationProperties {
        var position = decorations[d]!!.plus(1)
        while (decorations.containsValue(position)) {
            position++
        }
        decorations[d] = position
        return getAnimation(position)
    }

    private fun getPuttingDecorationAnim(d : Decoration) : AnimationProperties {
        for (i in 0 until MAX_NUMBER_OF_DECORATION) {
            if (!decorations.values.contains(i)) {
                decorations[d] = i
                return getAnimation(i)
            }
        }
        throw IllegalStateException("All decoration have already been put on the tree")
    }

    private fun getAnimation(position : Int) : AnimationProperties {
        return animationProvider.getAnimationProperty(position)
    }

    companion object {
        private const val MAX_NUMBER_OF_DECORATION = 5
    }
}

enum class Decoration {
    CANDY_CANE,
    GINGERBREAD,
    STOCKING,
    WREATH,
    STAR
}