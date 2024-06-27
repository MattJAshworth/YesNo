package xyz.mattjashworth.yesno.YesNoButton

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapePathModel
import com.google.gson.Gson
import com.google.gson.JsonParser
import xyz.mattjashworth.yesno.R
import xyz.mattjashworth.yesno.YesNoResult


class YesNoButton(context: Context, attributeSet: AttributeSet) : ConstraintLayout(context, attributeSet) {

    private lateinit var yesButton: MaterialButton
    private lateinit var noButton: MaterialButton
    private var unselectedColor: ColorStateList
    private var yesColor: ColorStateList
    private var noColor: ColorStateList



    private var onClickListener: OnClickListener? = null

    private var state: YesNoResult? = null

    init {

        val root = inflate(context, R.layout.yesno_button, this)

        val ta: TypedArray = context.obtainStyledAttributes(attributeSet, R.styleable.YesNoButton)
        unselectedColor = ta.getColorStateList(R.styleable.YesNoButton_UnselectedColor) ?: context.getColorStateList(R.color.unselected_blue)
        yesColor = ta.getColorStateList(R.styleable.YesNoButton_YesButtonColor) ?: context.getColorStateList(R.color.yes)
        noColor = ta.getColorStateList(R.styleable.YesNoButton_NoButtonColor) ?: context.getColorStateList(R.color.no)
        val cornerRadius = ta.getInt(R.styleable.YesNoButton_CornerRadius, 0)
        ta.recycle()

        val shapeLeft = GradientDrawable()
        shapeLeft.shape = GradientDrawable.RECTANGLE
        shapeLeft.cornerRadii = floatArrayOf(cornerRadius.toFloat(), cornerRadius.toFloat(), 0f, 0f
            , 0f, 0f, cornerRadius.toFloat(), cornerRadius.toFloat())

        val shapeRight = GradientDrawable()
        shapeRight.shape = GradientDrawable.RECTANGLE
        shapeRight.cornerRadii = floatArrayOf(0f, 0f, cornerRadius.toFloat(), cornerRadius.toFloat()
            , cornerRadius.toFloat(), cornerRadius.toFloat(), 0f, 0f)

        yesButton = root.findViewById<MaterialButton>(R.id.btn_yes)
        noButton = root.findViewById<MaterialButton>(R.id.btn_no)

        yesButton.background = shapeLeft
        noButton.background = shapeRight

        yesButton.backgroundTintList = unselectedColor
        noButton.backgroundTintList = unselectedColor

        yesButton.setOnClickListener {
            update(YesNoResult.Yes, context)
        }

        noButton.setOnClickListener {
            update(YesNoResult.No, context)
        }

    }

    private fun update(newState: YesNoResult, ctx: Context) {

        when(newState) {
            YesNoResult.Yes -> {
                if (state == newState) {
                    //Unselect
                    state = null
                    onClickListener?.onClick(YesNoResult.None)
                    yesButton.backgroundTintList = unselectedColor
                } else {
                    if (state == YesNoResult.No)
                        noButton.backgroundTintList = unselectedColor
                    state = newState
                    onClickListener?.onClick(YesNoResult.Yes)
                    yesButton.backgroundTintList = yesColor
                }
            }
            YesNoResult.No -> {
                if (state == newState) {
                    //Unselect
                    state = null
                    onClickListener?.onClick(YesNoResult.None)
                    noButton.backgroundTintList = unselectedColor
                } else {
                    if (state == YesNoResult.Yes)
                        yesButton.backgroundTintList = unselectedColor
                    state = newState
                    onClickListener?.onClick(YesNoResult.No)
                    noButton.backgroundTintList = noColor
                }
            } else -> return
        }
    }

    fun setOnYesNoClickListener(onItemSelectedListener: OnClickListener) {
        this.onClickListener = onItemSelectedListener
    }

    interface OnClickListener {
        fun onClick(model: YesNoResult)
    }
}