package br.com.sistemadereparo.lcmbluetooth.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.sistemadereparo.lcmbluetooth.repository.BluetoothHabilitadoRepository
import br.com.sistemadereparo.lcmbluetooth.repository.BluetoothRepository
import br.com.sistemadereparo.lcmbluetooth.ui.viewmodel.ListaDispositivosViewModel
import br.com.sistemadereparo.lcmbluetooth.ui.viewmodel.TesteLcmViewModel

class TesteLcmViewModelFactory(private val repository: BluetoothHabilitadoRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TesteLcmViewModel::class.java)) {
            return TesteLcmViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}