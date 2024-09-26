package br.com.sistemadereparo.lcmbluetooth.util

import java.util.UUID

object BluetoothConst {
     const val REQUEST_BLUETOOTH_PERMISSION = 1
     const val REQUEST_ENABLE_BLUETOOTH = 2
      val MY_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB") // Example UUID for SPP

}