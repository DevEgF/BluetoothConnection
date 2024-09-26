package br.com.sistemadereparo.lcmbluetooth.repository

import android.bluetooth.BluetoothAdapter

class BluetoothHabilitadoRepositoryImpl : BluetoothHabilitadoRepository {
    override fun bluetoothEstaHabilitado(): Boolean {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        return bluetoothAdapter?.isEnabled ?: false
    }
}
