package com.hye.presentation.ui.screen.tab

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hye.presentation.R
import com.hye.domain.model.AppLanguage
import com.hye.presentation.model.nameResId
import com.hye.presentation.ui.model.SettingViewModel
import com.hye.presentation.ui.model.SharedViewModel
import com.hye.presentation.ui.theme.KLangComposePJTheme

@Composable
fun MyPageTabScreen(
    onNavigateToMyPageScreen: () -> Unit,
    sharedViewModel: SharedViewModel,
    settingViewModel: SettingViewModel,

) {

    val totalWordCount by sharedViewModel.totalWordCount.collectAsStateWithLifecycle()

    KLangComposePJTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
            color = MaterialTheme.colorScheme.background
        ) {

            Column() {
 //학습 현황
                Text(
                    text = stringResource(R.string.study_word),
                    modifier = Modifier
                        .padding(start = 32.dp, end = 16.dp, bottom = 0.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = Bold
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(100.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    TotalGoalSection(totalWordCount.toString())
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        thickness = 1.dp,
                    )
                    TotalPointSection()

                }
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = stringResource(R.string.setting),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 16.dp, bottom = 0.dp),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = Bold
                )
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    TodayGoalSection(10)
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.background
                    )
                    LanguageSettingSection(settingViewModel)
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.background

                    )
                    AlarmSettingSection()
                }
            }
        }
    }

}

@Composable
fun TotalGoalSection(totalWord: String) {
Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.Start
){
    Text(
        text = stringResource(R.string.vocabulary_learned),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    textAlign = TextAlign.Start,
    style = MaterialTheme.typography.bodyMedium,
    fontWeight = Bold
    )
    Text(
        text = totalWord,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, bottom = 16.dp),
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = Bold
    )
}
}

@Composable
fun TotalPointSection() {

}

@Composable
fun TodayGoalSection(todayGoal: Int) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.today_words),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = Bold

        )
        Text(
            text = todayGoal.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 16.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = Bold
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSettingSection(
    settingViewModel: SettingViewModel,
    currentLanguage: AppLanguage = AppLanguage.ENGLISH,
    selectedLanguage: AppLanguage = AppLanguage.ENGLISH

) {

    val currentLanguage by settingViewModel.currentLanguage.collectAsState()
    //val radioOptions = listOf("English", "Korean")
    var selectedLanguage by remember { mutableStateOf(AppLanguage.ENGLISH)}

    LaunchedEffect(currentLanguage){
        selectedLanguage = currentLanguage
    }
    val context :Context = LocalContext.current
    var showBottomSheet by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                selectedLanguage = currentLanguage
                showBottomSheet = true
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {

        Text(
            text = stringResource(R.string.language_setting),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = Bold
        )
        Text(
            text = stringResource(currentLanguage.nameResId),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 16.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = Bold
        )

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                containerColor = MaterialTheme.colorScheme.surface,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "언어 설정",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = Bold
                    )
                    Column(
                        modifier= Modifier.selectableGroup()
                    ){
                        AppLanguage.entries.forEach { language->
                            Row(
                                modifier= Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .selectable(
                                        selected = (language == selectedLanguage),
                                        onClick = {
                                            selectedLanguage = language
                                        },
                                        role = Role.RadioButton
                                    )
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                              RadioButton(
                                  selected = (language == selectedLanguage),
                                  onClick = null
                              )
                                Text(
                                    text = stringResource(language.nameResId),
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(start = 16.dp)
                                )
                            }
                        }
                    }



                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        onClick = {
                                settingViewModel.updateLanguage(selectedLanguage)
                                showBottomSheet = false

                        }
                    ){
                        Text(stringResource(R.string.save))

                    }
                }

            }
        }
    }
}




@Composable
fun AlarmSettingSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        var checked by remember { mutableStateOf(false) }
        Text(
            text = stringResource(R.string.alarm_setting),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = Bold
        )

        Switch(
            modifier = Modifier.scale(0.8f),
            checked = checked,
            onCheckedChange = {
                checked = it
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.background,
                uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                uncheckedTrackColor = MaterialTheme.colorScheme.background,
                uncheckedBorderColor = MaterialTheme.colorScheme.background,
            )
        )
    }
}
