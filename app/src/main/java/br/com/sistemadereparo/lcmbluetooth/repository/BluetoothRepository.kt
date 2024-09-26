package br.com.sistemadereparo.lcmbluetooth.repository

import android.bluetooth.BluetoothDevice
import java.util.UUID

interface BluetoothRepository {
    fun getPairedDevices(): Set<BluetoothDevice>
    fun connectToDevice(device: BluetoothDevice, uuid: UUID): Boolean
}