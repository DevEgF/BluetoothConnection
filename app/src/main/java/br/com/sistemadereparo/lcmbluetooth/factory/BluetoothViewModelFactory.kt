package br.com.sistemadereparo.lcmbluetooth.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.sistemadereparo.lcmbluetooth.repository.BluetoothRepository
import br.com.sistemadereparo.lcmbluetooth.ui.viewmodel.ListaDispositivosViewModel

class BluetoothViewModelFactory(private val repository: BluetoothRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListaDispositivosViewModel::class.java)) {
            return ListaDispositivosViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}