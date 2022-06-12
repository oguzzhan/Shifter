package com.ozzy.shifter.ui.shiftList

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ozzy.shifter.R
import com.ozzy.shifter.model.Shift
import com.ozzy.shifter.ui.EmptyScreenDisplay
import com.ozzy.shifter.ui.ErrorDisplay
import com.ozzy.shifter.ui.Loader

@Composable
fun ShiftList() {

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            val context = LocalContext.current

            ExtendedFloatingActionButton(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_filter_list_24),
                        contentDescription = null
                    )
                },
                text = {
                    Text(text = stringResource(R.string.filters))
                },
                onClick = {
                    Toast.makeText(context, "FilterClicked", Toast.LENGTH_SHORT).show()
                }
            )
        },
        content = {
            ShiftListContent(paddingValues = it)
        },
        bottomBar = {
            ShiftListBottomBar()
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

    if (viewModel.isLoading) {
        Loader()
    } else if (viewModel.shiftListError == null) {
        if (viewModel.shiftList.isEmpty()) {
            EmptyScreenDisplay()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(paddingValues),
            ) {
                groupedItems.forEach { (date, itemList) ->
                    stickyHeader {
                        ListHeader(text = date)
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
        }
    } else {
        ErrorDisplay(
            viewModel.shiftListError
        )
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

@Composable
fun ShiftListBottomBar() {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
    ) {
        OutlinedButton(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.teal_700),
                contentColor = colorResource(id = R.color.teal_700),
            ),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .weight(1f)
                .padding(12.dp, 0.dp, 12.dp, 0.dp),
            onClick = {
                Toast.makeText(context, "Subscribe Clicked", Toast.LENGTH_SHORT).show()
            }
        ) {
            BottomBarButtonText(text = stringResource(R.string.subscribe))
        }
        OutlinedButton(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.white),
                contentColor = colorResource(id = R.color.white),
            ),
            border = BorderStroke(1.dp, colorResource(id = R.color.black)),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .weight(1f)
                .padding(12.dp, 0.dp, 12.dp, 0.dp),
            onClick = {
                Toast.makeText(context, "Login Clicked", Toast.LENGTH_SHORT).show()
            }
        ) {
            BottomBarButtonText(text = stringResource(R.string.login))
        }
    }
}

@Composable
fun BottomBarButtonText(text: String) {
    Text(
        modifier = Modifier.padding(8.dp),
        text = text,
        color = colorResource(id = R.color.black),
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ListHeader(text: String?) {
    Box(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(colorResource(id = R.color.white))
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text ?: "",
            color = colorResource(id = R.color.black)
        )
    }
}