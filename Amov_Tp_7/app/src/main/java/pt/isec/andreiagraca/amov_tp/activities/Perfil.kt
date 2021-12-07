package pt.isec.andreiagraca.amov_tp.activities

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import pt.isec.andreiagraca.amov_tp.databinding.ActivityEscolhemodoBinding
import pt.isec.andreiagraca.amov_tp.databinding.ActivityPerfilBinding

class Perfil : AppCompatActivity() {
    lateinit var b: ActivityPerfilBinding

    companion object {
        private const val REQ_PERM_CODE = 1234
    }

    private var imagePath : String? = null

    private var permissionsGranted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(b.root)
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
            b.imagem.isEnabled = permissionsGranted
        }
    }
}