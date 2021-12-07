package pt.isec.ans.rascunhos.views

import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.drawToBitmap
import pt.isec.ans.rascunhos.R
import pt.isec.ans.rascunhos.modelo.Linha
import pt.isec.ans.rascunhos.modelo.Rascunho
import pt.isec.ans.rascunhos.utils.ImageUtils

class AreaDesenho(context: Context) : View(context),GestureDetector.OnGestureListener {
    companion object {
        const val TAG_AD = "AreaDesenho"
    }
    var rascunho : Rascunho = Rascunho( resources.getString(R.string.str_no_name),
        arrayListOf(Linha(Path(),Color.BLACK)))

    private val desenho : ArrayList<Linha>
        get() = rascunho.linhas
    private val pathAtual : Path
        get() = desenho.last().path

    constructor(context: Context, rascunho: Rascunho) : this(context) {
        this.rascunho = rascunho
        setBackgroundColor(rascunho.corFundo)
//        // if rascunho.imagemfundo != null
//        rascunho.imagemFundo?.run {
//            ImageUtils.setPic(this@AreaDesenho,this)
//        }
    }

    //Inicialmente a linha é preta
    private val paint = Paint(Paint.DITHER_FLAG or Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        strokeWidth = 4.0f
        style = Paint.Style.FILL_AND_STROKE
    }

    var corLinha : Int = Color.BLACK
        set(value) {
            field = value
            rascunho.addLinha(value)
        }

    val preview : Bitmap
        get() = this.drawToBitmap()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for(linha in desenho)
            canvas?.drawPath(linha.path, paint.apply {color = linha.cor})
    }

    private val gestureDetector : GestureDetector by lazy {
        GestureDetector(context,this)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (gestureDetector.onTouchEvent(event))
            return true
        return super.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent?): Boolean {
        Log.i(TAG_AD, "onDown: ${e!!.x}  ${e.y} $corLinha")
        pathAtual.moveTo(e!!.x,e.y)
        invalidate() // opcional aqui
        return true
    }

    override fun onShowPress(e: MotionEvent?) {
        Log.i(TAG_AD, "onShowPress: ${e!!.x}  ${e.y}")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Log.i(TAG_AD, "onSingleTapUp: ${e!!.x}  ${e.y}")
        return false
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        Log.i(TAG_AD, "onScroll: ${e2!!.x}  ${e2.y}")
        pathAtual.lineTo(e2!!.x, e2.y)
        pathAtual.moveTo(e2.x,e2.y)
        invalidate()
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        Log.i(TAG_AD, "onLongPress: ${e!!.x}  ${e.y}")
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        Log.i(TAG_AD, "onFling:  ${e2!!.x}  ${e2.y}")
        return false
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // if rascunho.imagemfundo != null
        rascunho.imagemFundo?.run {
            ImageUtils.setPic(this@AreaDesenho,this)
        }
    }
}