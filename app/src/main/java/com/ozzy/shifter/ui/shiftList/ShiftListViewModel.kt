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
    val shiftList = mutableStateListOf<Shift>()

    var shiftListError: ShifterResult.Error<*>? by mutableStateOf(null)
        private set

    var isLoading: Boolean by mutableStateOf(false)

    private val shiftListFilterHelper = ShiftListFilterHelper()

    suspend fun fetchShiftList() {
        shiftListError = null

        viewModelScope.launch {
            shiftListFilterHelper.setFilterForToday()

            repository.fetchShiftList(shiftListFilterHelper.filterMap).collect { result ->
                when (result) {
                    is ShifterResult.Response -> {
                        shiftList.addAll(result.response.listResponse)
                        isLoading = false
                    }
                    is ShifterResult.Loading -> {
                        isLoading = true
                    }
                    is ShifterResult.Error -> {
                        shiftListError = result
                        isLoading = false
                    }
                }
            }
        }
    }
}