package pt.isec.a2018019825.aula1

object Object {
    private var _valor = 1000

    init {
        _valor = 100000
    }

    val valor : Int
    get() {_valor--;return _valor}
}