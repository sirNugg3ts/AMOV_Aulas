package pt.isec.a2018019825.aula1

import android.app.Application
import android.util.Log

class Application : Application() {

    private var _valor: Int = 0

    val valor: Int
    get() {_valor++;return _valor}

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG,"Aplicacao.onCreate")
    }
}