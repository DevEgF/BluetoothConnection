package br.com.sistemadereparo.lcmbluetooth.ui.viewmodel

import android.bluetooth.BluetoothSocket
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    private val _bluetoothSocket = MutableLiveData<BluetoothSocket>()
    val bluetoothSocket: LiveData<BluetoothSocket>
        get() = _bluetoothSocket

    fun setBluetoothSocket(socket: BluetoothSocket) {
        _bluetoothSocket.value = socket
    }
}