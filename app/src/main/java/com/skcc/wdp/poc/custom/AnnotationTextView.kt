package com.skcc.wdp.poc.custom

import android.content.Context
import android.graphics.Typeface
import android.text.*
import android.text.Annotation
import android.text.style.MetricAffectingSpan
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.skcc.wdp.poc.R


class AnnotationTextView : TextView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        setInit()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setInit()
    }

    private fun setInit() {
        if (text is SpannedString) {
            val titleText = text as SpannedString

            val annotations = titleText.getSpans(0, titleText.length, Annotation::class.java)
            var spannableString = SpannableString(titleText)

            for (annotation in annotations) {
                if (annotation.key == "font") {
                    val typeface = when (annotation.value) {
                        "b" -> ResourcesCompat.getFont(context, R.font.font_b)
                        "l" -> ResourcesCompat.getFont(context, R.font.font_l)
                        "r" -> ResourcesCompat.getFont(context, R.font.font_r)
                        else -> null
                    }

                    typeface?.run {
                        val start = titleText.getSpanStart(annotation)
                        val end = titleText.getSpanEnd(annotation)

                        spannableString.setSpan(
                            CustomTypefaceSpan(this),
                            start,
                            end,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }
                }
            }
            text = spannableString
        } else {
            Log.i("sgim", "msg 2. $text")
        }
    }

    class CustomTypefaceSpan(private val typeface: Typeface?) : MetricAffectingSpan() {
        override fun updateDrawState(paint: TextPaint) {
            paint.typeface=typeface
        }

        override fun updateMeasureState(paint: TextPaint) {
            paint.typeface=typeface
        }
    }
}