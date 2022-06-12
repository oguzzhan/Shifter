package com.ozzy.shifter.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ozzy.shifter.R
import com.ozzy.shifter.model.ShifterResult
import com.ozzy.shifter.util.handleErrorCode

@Composable
fun Loader() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorDisplay(
    errorObject: ShifterResult.Error<*>?
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .height(75.dp)
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.ic_baseline_error_outline_24),
            contentDescription = ""
        )

        val errorMessage = errorObject?.let { error ->
            error.errorMessage ?: run {
                error.errorCode?.let { errorCode ->
                    val context = LocalContext.current
                    errorCode.handleErrorCode(context)
                }
            }
        } ?: stringResource(id = R.string.default_error_message)

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            text = errorMessage,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun EmptyScreenDisplay() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .height(75.dp)
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.ic_baseline_search_24),
            contentDescription = ""
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            text = stringResource(R.string.empty_list_message),
            textAlign = TextAlign.Center
        )
    }
}