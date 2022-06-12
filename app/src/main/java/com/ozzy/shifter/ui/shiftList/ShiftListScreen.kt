package com.ozzy.shifter.ui.shiftList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ozzy.shifter.R
import com.ozzy.shifter.model.Job
import com.ozzy.shifter.model.Price
import com.ozzy.shifter.model.Shift


@Composable
fun ShiftList() {
    Scaffold(
        content = {
            ShiftListContent(paddingValues = it)
        },
        bottomBar = {
            BottomAppBar {
                Text("BottomAppBar")
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShiftListContent(
    viewModel: ShiftListViewModel = hiltViewModel(),
    paddingValues: PaddingValues
) {
    LaunchedEffect(Unit, block = {
        viewModel.fetchShiftList()
    })

    val groupedItems = viewModel.shiftList.groupBy {
        it.getStartDate()
    }

    if (viewModel.shiftListErrorMessage.isEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues),
        ) {
            groupedItems.forEach { (date, itemList) ->
                stickyHeader {
                    Text(date ?: "")
                }

                items(
                    items = itemList,
                    key = {
                        it.id ?: 0
                    }
                ) { contact ->
                    ShiftItem(contact)
                }
            }
        }
    } else {
        Text(text = viewModel.shiftListErrorMessage)
    }
}

@Composable
fun ShiftItem(shift: Shift) {
    Column(
        Modifier.padding(12.dp)
    ) {
        JobImage(
            imageUrl = shift.job?.project?.client?.links?.heroImage,
            price = shift.earningsPerHour?.getAmountWithCurrencySymbol()
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = shift.job?.category?.name ?: "",
            color = colorResource(id = R.color.purple_500),
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = shift.job?.project?.client?.name ?: "",
            color = colorResource(id = R.color.black),
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = shift.getStartEndTimeText() ?: ""
        )
    }

}

@Composable
fun JobImage(
    imageUrl: String?,
    price: String? = null
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .placeholder(R.drawable.placeholder)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.app_name),
            modifier = Modifier
                .fillMaxWidth()
        )

        if (!price.isNullOrEmpty()) {
            Card(
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                ),
                backgroundColor = colorResource(R.color.white),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            ) {
                Text(
                    text = price,
                    modifier = Modifier
                        .padding(12.dp, 6.dp, 12.dp, 6.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewScreen() {
    ShiftItem(
        shift = Shift(
            earningsPerHour = Price(12f, "EUR"),
            job = Job(category = Job.Category(name = "Test")),
            endsAt = "2022-06-12T20:00:00+02:00",
            startsAt = "2022-06-13T05:00:00+02:00"
        )
    )
}