package br.com.sistemadereparo.lcmbluetooth.repository

import android.bluetooth.BluetoothDevice
import br.com.sistemadereparo.lcmbluetooth.util.BluetoothHelper
import java.util.UUID

class BluetoothRepositoryImpl: BluetoothRepository  {

    override fun getPairedDevices(): Set<BluetoothDevice> {
        return BluetoothHelper.getPairedDevices()
    }

    override fun connectToDevice(device: BluetoothDevice, uuid: UUID): Boolean {
        return BluetoothHelper.connectToDevice(device, uuid)
    }
}