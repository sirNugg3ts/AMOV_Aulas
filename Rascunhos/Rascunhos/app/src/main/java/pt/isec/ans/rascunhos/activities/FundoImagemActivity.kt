package pt.isec.ans.rascunhos.activities

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import pt.isec.ans.rascunhos.R
import pt.isec.ans.rascunhos.databinding.ActivityFundoImagemBinding
import pt.isec.ans.rascunhos.utils.ImageUtils
import java.io.File

class FundoImagemActivity : AppCompatActivity() {
    companion object {
        const val ESCOLHER = 1
        const val CAPTURAR = 2

        const val TIPO_PARAM = "tipo"

        private const val REQ_PERM_CODE = 1234
    }

    private var imagePath: String? = null
    private var permissionsGranted = false
    private var modo = ESCOLHER
    lateinit var b : ActivityFundoImagemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityFundoImagemBinding.inflate(layoutInflater)
        setContentView(b.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        modo = intent.getIntExtra(TIPO_PARAM, ESCOLHER)

        if (modo == ESCOLHER) {
            b.btnImagem.apply {
                text = getString(R.string.btn_escolher_imagem)
                setOnClickListener {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.setType("image/*")
                    //startActivityForResult(intent, 10)
                    startActivityForResult.launch(intent)
                }
            }
        } else {
            b.btnImagem.apply {
                text = getString(R.string.btn_nova_fotografia)
                setOnClickListener {
                    val folder = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    val imageFile = File.createTempFile("image",".img",folder)
                    imagePath = imageFile.absolutePath
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                        //val fileUri = Uri.fromFile(File(imagePath))
                        val fileUri = FileProvider.getUriForFile(
                            this@FundoImagemActivity,
                            "pt.isec.ans.prepa4_a.android.fileprovider",
                            imageFile//File(imagePath!!)
                        )
                        putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
                    }
                    //startActivityForResult(intent, 20)
                    startActivityForResultForFoto.launch(intent)
                }
            }
        }

        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE), REQ_PERM_CODE)
        } else
            permissionsGranted = true
    }

    private var startActivityForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult() ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val resultIntent = result.data
            val uri = resultIntent?.data?.apply {
                val cursor = contentResolver.query(this,
                    arrayOf(MediaStore.Images.ImageColumns.DATA), null, null, null)
                if (cursor != null && cursor.moveToFirst())
                    imagePath = cursor.getString(0)
                updatePreview()
            }
        }
    }

    var startActivityForResultForFoto = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult() ) { result ->
        if (result.resultCode != Activity.RESULT_OK)
            imagePath = null
        updatePreview()
        }
    /*override fun onActivityResult(requestCode: Int, resultCode: Int, info: Intent?)
    {
        if (requestCode == 10 && resultCode == Activity.RESULT_OK && info != null) {
            val uri = info.data?.apply {
                val cursor = contentResolver.query(this,
                    arrayOf(MediaStore.Images.ImageColumns.DATA), null, null, null)
                if (cursor != null && cursor.moveToFirst())
                    imagePath =  cursor.getString(0)
                updatePreview()
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, info)
    }*/

    private fun updatePreview() {
        if (imagePath != null) {
            ImageUtils.setPic(b.frPreview,imagePath!!)
        } else {
            b.frPreview.background = ResourcesCompat.getDrawable(resources,
            android.R.drawable.ic_menu_report_image,null)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQ_PERM_CODE) {
            permissionsGranted =
                (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                 ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            b.btnImagem.isEnabled = permissionsGranted
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_criar,menu)
        return true
    }

    //Não realizado em aula:
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.mnCriar)?.isVisible = permissionsGranted
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnCriar) {
            if (b.edTitulo.text.trim().isEmpty()) {
                Snackbar.make(b.edTitulo,
                    R.string.msg_titulo_vazio,
                    Snackbar.LENGTH_LONG).show()
                b.edTitulo.requestFocus()
                return true;
            }
            //TODO: os passos seguintes apenas deverão ser realizados
            //      se o utilizador escolheu/capturou uma imagem
            if (imagePath == null) {
                //TODO: snackbar a dar erro
                return true
            }
            val intent = Intent(this, AreaDesenhoActivity::class.java)
            intent.putExtra(AreaDesenhoActivity.TITULO_PARAM,b.edTitulo.text.toString())
            //TODO: Passar imagem seleccionada
            intent.putExtra(AreaDesenhoActivity.IMAGEM_PARAM, imagePath)
            startActivity(intent)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}