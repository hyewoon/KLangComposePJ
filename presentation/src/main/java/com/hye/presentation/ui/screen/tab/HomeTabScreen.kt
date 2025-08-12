package com.hye.presentation.ui.screen.tab


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hye.presentation.ui.component.home.AttendanceCard
import com.hye.presentation.ui.component.home.DailyQuestCard
import com.hye.presentation.ui.component.home.TodayStudyCard
import com.hye.presentation.ui.model.HomeViewModel
import com.hye.presentation.ui.model.SharedViewModel

import com.hye.presentation.ui.theme.KLangComposePJTheme


@Composable
fun HomeTabScreen(onNavigateToTodayStudy: () -> Unit,
                  homeViewModel : HomeViewModel,
                  sharedViewModel: SharedViewModel,
                  snackBarHostState: SnackbarHostState
                  ) {
    KLangComposePJTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                TodayStudyCard(onNavigateToTodayStudy)
                AttendanceCard()
                DailyQuestCard()

            }
        }
    }
}



