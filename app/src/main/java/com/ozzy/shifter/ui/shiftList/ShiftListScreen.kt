package com.ozzy.shifter.ui.shiftList

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ozzy.shifter.model.Shift


@Composable
fun ShiftList(
    viewModel: ShiftListViewModel = hiltViewModel()
) {
    Scaffold(
        content = {
            ShiftListContent(viewModel, it)
        },
        bottomBar = {
            BottomAppBar {
                Text("BottomAppBar")
            }
        }
    )
}

@Composable
fun ShiftListContent(
    viewModel: ShiftListViewModel = hiltViewModel(),
    paddingValues: PaddingValues
) {
    LaunchedEffect(Unit, block = {
        viewModel.fetchShiftList()
    })

    if (viewModel.shiftListErrorMessage.isEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues),
        ) {
            items(viewModel.shiftList) { shift ->
                ShiftItem(shift = shift)
            }
        }
    } else {
        Text(text = viewModel.shiftListErrorMessage)
    }
}

@Composable
fun ShiftItem(shift: Shift) {
    Text(text = shift.id ?: "")
}