package com.hye.presentation

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.hye.domain.repository.datastore.PreferencesDataStoreRepository
import com.hye.presentation.ui.model.SettingViewModel
import com.hye.presentation.ui.screen.MainScreen
import com.hye.presentation.ui.theme.KLangComposePJTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: PreferencesDataStoreRepository

    private val settingViewModel : SettingViewModel by viewModels()

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //앱 시작 시 지정된 언어 적용  setContent 전에
        lifecycleScope.launch{
            val saveLanguage = preferences.getLanguage().first()
            applyLanguage(saveLanguage.code)
        }
        
        setContent {
            KLangComposePJTheme {
             MainScreen()
            }
        }

        lifecycleScope.launch{
            settingViewModel.languageChangeEvent.collect{
                recreate()
            }
        }
    }

    private fun applyLanguage(languageCode: String){
        Log.d("MainActivity", "applyLanguage 호출: $languageCode")
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(resources.configuration)
        config.setLocale(locale)

        createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)
        Log.d("MainActivity", "언어 적용 완료")
    }
}


