package com.shkurta.log.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.shkurta.log.ui.components.LogItem
import com.shkurta.log.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    onNewLogClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val logs by viewModel.logs.collectAsState()

    Scaffold(
        topBar = {
            HomeTopBar(
                title = "Σημερα"
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNewLogClick,
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding
        ) {
            items(logs) { log ->
                LogItem(
                    log = log,
                    onDeleteClick = { viewModel.deleteLog(log) }
                )
                if (log != logs.last()) {
                    HorizontalDivider()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    title: String
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title.uppercase(),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    )
}