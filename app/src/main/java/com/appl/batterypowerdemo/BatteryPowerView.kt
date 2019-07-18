package com.appl.batterypowerdemo


import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView

class BatteryPowerView : ImageView {

    private val LOW_BATTERY_VALUE = 40
    private var electricity = 0f
    private val paint = Paint()

    private val normalColor = Color.parseColor("#485065")
    private val lowPowerColor = Color.parseColor("#FF3600")

    constructor(context: Context?, attributes: AttributeSet?) : super(context, attributes)
    constructor(context: Context?) : this(context, null)

    init {
        setImageResource(R.mipmap.icon_normal_power)
        paint.color = normalColor
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val scale = measuredHeight / 29

        canvas?.drawRect(
            5f * scale,
            5f * scale,
            (measuredWidth - 9f * scale) * electricity,
            measuredHeight - 5f * scale,
            paint
        )
    }

    fun setPower(electricity: Int) {
        when {
            electricity < 0 -> this.electricity = 0f
            electricity > 100 -> this.electricity = 1f
            else -> this.electricity = electricity / 100.0f
        }
        if (electricity < LOW_BATTERY_VALUE) {
            setImageResource(R.mipmap.icon_low_power)
            paint.color = lowPowerColor
        } else {
            setImageResource(R.mipmap.icon_normal_power)
            paint.color = normalColor
        }
        postInvalidate()
    }


}