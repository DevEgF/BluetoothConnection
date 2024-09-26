package br.com.sistemadereparo.lcmbluetooth.util

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import java.io.IOException
import java.util.Arrays
import java.util.UUID

object BluetoothHelper {
    private var bluetoothSocket: BluetoothSocket? = null

    fun getPairedDevices(): Set<BluetoothDevice> {
        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        return bluetoothAdapter?.bondedDevices ?: emptySet()
        //criar verificações
    }

    fun connectToDevice(device: BluetoothDevice, uuid: UUID): Boolean {
        bluetoothSocket = try {
            device.createRfcommSocketToServiceRecord(uuid)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
        return bluetoothSocket != null
    }

    fun getBluetoothSocket(): BluetoothSocket? {
        return bluetoothSocket
    }



    fun sendData(socket: BluetoothSocket?, data: ByteArray): Boolean {
        return try {
            socket?.outputStream?.write(data)
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    fun receiveData(socket: BluetoothSocket?): ByteArray? {
        return try {
            val buffer = ByteArray(1024)
            val bytes = socket?.inputStream?.read(buffer)
            if (bytes != null && bytes > 0) {
                Arrays.copyOf(buffer, bytes)
            } else {
                null
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}