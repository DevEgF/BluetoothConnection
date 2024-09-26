package br.com.sistemadereparo.lcmbluetooth.ui.viewmodel

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.sistemadereparo.lcmbluetooth.repository.BluetoothHabilitadoRepository
import br.com.sistemadereparo.lcmbluetooth.util.BluetoothHelper
import java.util.UUID


class TesteLcmViewModel(private val bluetoothRepository: BluetoothHabilitadoRepository) : ViewModel() {

    private val _bluetoothEnabled = MutableLiveData<Boolean>()
    val bluetoothEnabled: LiveData<Boolean>
        get() = _bluetoothEnabled

    private val _receivedData = MutableLiveData<ByteArray>()
    val receivedData: LiveData<ByteArray>
        get() = _receivedData

    init {
        _bluetoothEnabled.value = false
    }

    fun checkBluetoothStatus() {
        _bluetoothEnabled.value = bluetoothRepository.bluetoothEstaHabilitado()
    }

    fun sendData(socket: BluetoothSocket, data: ByteArray): Boolean {
        val success = BluetoothHelper.sendData(socket, data)
        if (!success) {
            // Handle failure to send data
        }
        return success
    }

    fun receiveData(socket: BluetoothSocket?): ByteArray? {
      return BluetoothHelper.receiveData(socket)
    }
}