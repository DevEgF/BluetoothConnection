package br.com.sistemadereparo.lcmbluetooth.ui.view

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import br.com.sistemadereparo.lcmbluetooth.databinding.ActivityListaDispositivosBinding
import br.com.sistemadereparo.lcmbluetooth.factory.BluetoothViewModelFactory
import br.com.sistemadereparo.lcmbluetooth.model.BluetoothDeviceModel
import br.com.sistemadereparo.lcmbluetooth.repository.BluetoothRepositoryImpl
import br.com.sistemadereparo.lcmbluetooth.ui.viewmodel.ListaDispositivosViewModel
import br.com.sistemadereparo.lcmbluetooth.ui.viewmodel.SharedViewModel
import br.com.sistemadereparo.lcmbluetooth.util.BluetoothConst.MY_UUID
import br.com.sistemadereparo.lcmbluetooth.util.BluetoothConst.REQUEST_BLUETOOTH_PERMISSION
import java.util.UUID

class ListaDispositivosActivity : AppCompatActivity() {


    private val listaDispositivosViewModel: ListaDispositivosViewModel by viewModels {
        BluetoothViewModelFactory(BluetoothRepositoryImpl())
    }

    private val sharedViewModel: SharedViewModel by viewModels()

    private val binding by lazy {
        ActivityListaDispositivosBinding.inflate(layoutInflater)
    }

    private lateinit var listViewDispositivos: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        listViewDispositivos = binding.listViewDevices

        val dispositivoAdapter = ArrayAdapter<BluetoothDeviceModel>(
            this,
            R.layout.simple_list_item_1,
            ArrayList()
            
        )
        listViewDispositivos.adapter= dispositivoAdapter

        listViewDispositivos.setOnItemClickListener{ _, _, position, _ ->
            val dispositivoSelcionado = dispositivoAdapter.getItem(position)

            if (dispositivoSelcionado!=null){
                val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB") // Example UUID for SPP
                val conectado = listaDispositivosViewModel.connectToDevice(dispositivoSelcionado, MY_UUID)

                if (conectado){
                    Toast.makeText(this, "Conectado ao ${dispositivoSelcionado.nome}", Toast.LENGTH_SHORT).show()


                    val intent = Intent(this, TesteLcmActivity::class.java)
                    intent.putExtra("conectado",conectado)
                    startActivity(intent)
                }else {
                    Toast.makeText(this, "Conexão falhou ${dispositivoSelcionado.nome}", Toast.LENGTH_SHORT).show()
                    // Handle failed connection
                }
            }

        }


        listaDispositivosViewModel.getPairedDevices()
        requestBluetoothPermissions()
        observeDevices()
    }




    private fun requestBluetoothPermissions() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
            android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.BLUETOOTH,
                    android.Manifest.permission.BLUETOOTH_CONNECT,
                    android.Manifest.permission.BLUETOOTH_ADMIN,
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQUEST_BLUETOOTH_PERMISSION
            )
        }
    }



    private fun observeDevices() {
        listaDispositivosViewModel.pairedDevices.observe(this, { devices ->
            (listViewDispositivos.adapter as ArrayAdapter<BluetoothDeviceModel>).apply {
                clear()
                addAll(devices)
            }
        })
    }




}