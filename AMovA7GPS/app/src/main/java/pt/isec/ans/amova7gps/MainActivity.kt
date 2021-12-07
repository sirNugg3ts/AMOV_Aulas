package pt.isec.ans.amova7gps

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import pt.isec.ans.amova7gps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),OnMapReadyCallback {
    private var fineLocationPermission = false
    private var coarseLocationPermission = false

    private lateinit var b : ActivityMainBinding
   // lateinit var lm : LocationManager
   private lateinit var floc : FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        (supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment)?.getMapAsync(this)
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

       // lm = getSystemService(LOCATION_SERVICE) as LocationManager

        floc = FusedLocationProviderClient(this)

        floc.lastLocation.addOnSuccessListener { location ->
            currentLocation = location
        }


        fineLocationPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        coarseLocationPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!fineLocationPermission && !coarseLocationPermission)
            requestLocationPermissions.launch(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            )
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    private val requestLocationPermissions = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        fineLocationPermission = permissions.get(Manifest.permission.ACCESS_FINE_LOCATION) ?: false
        coarseLocationPermission =
            permissions.get(Manifest.permission.ACCESS_COARSE_LOCATION) ?: false
        startLocationUpdates()
    }

    private var locationEnabled = false

    @SuppressLint("MissingPermission")
    fun startLocationUpdates() {
        if (locationEnabled || (!fineLocationPermission && !coarseLocationPermission))
            return

       /* currentLocation = lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
                                ?: Location("AMOV").apply {
            latitude = -12.34  // valores para teste na aula
            longitude = 43.21  //   colocar em vez de PASSIVE, por exemplo, "AMOV"
        }

        if (fineLocationPermission)
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,10f,locationCallback)
        else // coarseLocationPermission é true
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,10f,locationCallback)*/

        floc.requestLocationUpdates(locReq,locationCallback,null)

        locationEnabled = true
    }

    fun stopLocationUpdates() {
        if (!locationEnabled)
            return
        //lm.removeUpdates(locationCallback)
        floc.removeLocationUpdates(locationCallback)

        locationEnabled = false
    }

    val locReq = LocationRequest.create().apply {
        interval = 4000
        fastestInterval = 2000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        maxWaitTime = 10000
    }
    var locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
            p0?.locations?.forEach { location ->
                currentLocation = location
            }
        }
    }

  /*  val locationCallback = LocationListener { location ->
        currentLocation = location
    }*/

    private var currentLocation = Location("")
        get() = field
        set(value) {
            field = value
            b.tvLat.text = String.format("%10.5f",value.latitude)
            b.tvLon.text = String.format("%10.5f",value.longitude)
        }

    val ISEC = LatLng(40.1925, -8.4115)
    val DEIS = LatLng(40.1925, -8.4128)

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap) {
        if(fineLocationPermission || coarseLocationPermission)
            map.isMyLocationEnabled = true
        map.mapType = GoogleMap.MAP_TYPE_HYBRID
        map.uiSettings.isCompassEnabled = true
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isZoomGesturesEnabled = true
        val cp = CameraPosition.Builder().target(ISEC).zoom(17f)
            .bearing(0f).tilt(0f).build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cp))
        map.addCircle(
            CircleOptions().center(ISEC).radius(150.0)
            .fillColor(Color.argb(128, 128, 128, 128))
            .strokeColor(Color.rgb(128, 0, 0)).strokeWidth(4f))
        val mo = MarkerOptions().position(ISEC).title("ISEC-IPC")
            .snippet("Instituto Superior de Engenharia de Coimbra")
        val isec = map.addMarker(mo)
        isec?.showInfoWindow()
        map.addMarker(MarkerOptions().position(DEIS).title("DEIS-ISEC"))
    }
}