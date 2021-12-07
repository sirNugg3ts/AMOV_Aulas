package pt.isec.ans.rascunhos.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import pt.isec.ans.rascunhos.R
import pt.isec.ans.rascunhos.databinding.ActivityHistoricoBinding
import pt.isec.ans.rascunhos.modelo.Rascunhos

class HistoricoActivity : AppCompatActivity() {
    lateinit var b : ActivityHistoricoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityHistoricoBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.lvHistorico.adapter = HistoricoAdapter();

//        b.lvHistorico.setOnItemClickListener { parent, view, position, id ->
//            //quando clicas num desenho da lista no historico
//        }
    }

    class HistoricoAdapter : BaseAdapter() {
        override fun getCount(): Int = Rascunhos.lista.size

        override fun getItem(p0: Int): Any = Rascunhos.lista[p0]

        override fun getItemId(p0: Int): Long = p0.toLong()

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val view = LayoutInflater.from(p2!!.context)
                .inflate(R.layout.item_historico, p2, false)
            val rascunho = Rascunhos.lista[p0]
            view.findViewById<TextView>(R.id.tvTitulo).text = rascunho.titulo
            view.findViewById<TextView>(R.id.tvInfo).text =
                rascunho.imagemFundo ?: String.format("%8X",rascunho.corFundo)
            view.findViewById<ImageView>(R.id.imgPreview).setImageBitmap(rascunho.preview)
            return view
        }

    }
}