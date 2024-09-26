package br.com.sistemadereparo.lcmbluetooth.broadcast

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BroadcastReceiverBluetoothStatus(private val listener: BluetoothStatusListener) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == BluetoothAdapter.ACTION_STATE_CHANGED) {
            val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
            when (state) {
                BluetoothAdapter.STATE_ON -> {
                    listener.onBluetoothEnabled(true)
                    Toast.makeText(context, "Bluetooth Broadcast ON", Toast.LENGTH_LONG).show()
                }
                BluetoothAdapter.STATE_OFF -> {
                    listener.onBluetoothEnabled(false)
                    Toast.makeText(context, "Bluetooth Broadcast OFF", Toast.LENGTH_LONG).show()

                }
            }
        }
    }

    interface BluetoothStatusListener {
        fun onBluetoothEnabled(enabled: Boolean)
    }

}