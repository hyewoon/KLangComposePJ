package com.hye.klangcomposepj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.hye.klangcomposepj.ui.screen.MainScreen
import com.hye.klangcomposepj.ui.theme.KLangComposePJTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { // setContentView와 대응 반드시 @Composable -> Unit 타입의 컴포즈UI를호출 해야함
            //여기서는 반환값이 없는 고차함수 호출해야 한다-> Greet()호출
            KLangComposePJTheme {
             MainScreen()
            }
        }
    }
}


