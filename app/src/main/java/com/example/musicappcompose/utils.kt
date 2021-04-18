package com.example.musicappcompose

fun map(value: Float, low1: Float, high1: Float, low2: Float, high2: Float): Float {
    return low2 + (value - low1) * (high2 - low2) / (high1 - low1)
}

@Suppress("NOTHING_TO_INLINE")
inline fun map(value: Float, from: ClosedFloatingPointRange<Float>, to: ClosedFloatingPointRange<Float>): Float {
    return map(value, from.start, from.endInclusive, to.start, to.endInclusive)
}
