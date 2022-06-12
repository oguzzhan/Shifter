package com.ozzy.shifter.ui.shiftList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozzy.shifter.data.ShifterRepository
import com.ozzy.shifter.model.Shift
import com.ozzy.shifter.model.ShifterResult
import com.ozzy.shifter.util.ShiftListFilterHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShiftListViewModel @Inject constructor(
    private val repository: ShifterRepository
) : ViewModel() {

    private val _shiftList = mutableStateListOf<Shift>()
    val shiftList: List<Shift>
        get() = _shiftList

    var shiftListErrorMessage: String by mutableStateOf("")
        private set

    private val shiftListFilterHelper = ShiftListFilterHelper()

    fun fetchShiftList() {
        viewModelScope.launch {
            shiftListFilterHelper.setFilterForToday()

            repository.fetchShiftList(shiftListFilterHelper.filterMap).collect { result ->
                when (result) {
                    is ShifterResult.ShifterResponse -> {
                        _shiftList.addAll(result.response.listResponse)
                    }
                    is ShifterResult.Loading -> {
                        // Show Spinner
                    }
                    is ShifterResult.Error -> {
                        shiftListErrorMessage = result.errorMessage
                    }
                }
            }
        }
    }
}