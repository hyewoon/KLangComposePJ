package com.hye.klangcomposepj.nav_graph

sealed class ScreenRoutDef(val routeName: String) {

    //top-level
    sealed class TopLevel(routeName: String) : ScreenRoutDef(routeName){
        data object HomeTab : TopLevel("home")
        data object GameTab : TopLevel("game")
        data object MyPageTab : TopLevel("mypage")
    }


    //HomeTab Flow(second-level)
    sealed class HomeFlow(routName: String): ScreenRoutDef(routName){
        data object TodayStudyScreen : HomeFlow("today_study")

    }
    //GameTab Flow(second-level)
    sealed class GameFlow(routName: String): ScreenRoutDef(routName){
        data object DrawScreen : GameFlow("draw")
        data object SearchScreen : GameFlow("search")
        data object VocabularyScreen : GameFlow("vocabulary")
        data object TextToSpeechScreen : GameFlow("tts")
        data object SpeechToTextScreen : GameFlow("stt")
    }

    //TodayStudyFlow(third-level)
    sealed class TodayStudyFlow(routName: String):ScreenRoutDef(routName){
        data object ListenScreen : TodayStudyFlow("listen")
        data object DictionaryScreen : TodayStudyFlow("dictionary")
        data object SpeechScreen : TodayStudyFlow("speech")
        data object WriteScreen : TodayStudyFlow("write")
    }




}