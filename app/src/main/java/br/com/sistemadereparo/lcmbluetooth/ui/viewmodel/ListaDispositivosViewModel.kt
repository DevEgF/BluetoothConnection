package br.com.sistemadereparo.lcmbluetooth.ui.viewmodel

import android.bluetooth.BluetoothAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.sistemadereparo.lcmbluetooth.model.BluetoothDeviceModel
import br.com.sistemadereparo.lcmbluetooth.repository.BluetoothRepository
import java.util.UUID

class ListaDispositivosViewModel(private val repository: BluetoothRepository) : ViewModel() {

    private val _pairedDevices = MutableLiveData<Set<BluetoothDeviceModel>>()
    val pairedDevices: LiveData<Set<BluetoothDeviceModel>> = _pairedDevices


    fun getPairedDevices(){
        val devices = repository.getPairedDevices()
        _pairedDevices.value = devices.map { device->
            // Fazer a requisição de permissões
            BluetoothDeviceModel(device.name, device.address)
        }.toSet()
    }
    fun connectToDevice(device: BluetoothDeviceModel, uuid: UUID): Boolean{

        val bluetoothDevice =BluetoothAdapter.getDefaultAdapter().getRemoteDevice(device.address)
        return repository.connectToDevice(bluetoothDevice,uuid)


    }

}