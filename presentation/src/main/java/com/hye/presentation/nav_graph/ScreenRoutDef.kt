package com.hye.presentation.nav_graph

import kotlinx.serialization.Serializable

/*
* 직렬화해서 router 수정
* */
@Serializable
sealed interface ScreenRoutDef{
    //top_level
    @Serializable
    sealed interface TopLevel : ScreenRoutDef {
        @Serializable
        data object HomeTab : TopLevel
        @Serializable
        data object GameTab : TopLevel
        @Serializable
        data object MyPageTab : TopLevel
    }

    //HomeTab Flow(second-level)
    @Serializable
    sealed interface HomeFlow : ScreenRoutDef {
        @Serializable
        data object HomeFlowGraph : HomeFlow  // 추가 필요
        @Serializable
        data object TodayStudyScreen : HomeFlow

    }
    @Serializable
    sealed interface GameFlow: ScreenRoutDef {
        @Serializable
        data object GameFlowGraph : GameFlow  // 추가 필요
        @Serializable
        data object DrawScreen : GameFlow
        @Serializable
        data object SearchScreen : GameFlow
        @Serializable
        data object TextToSpeechScreen : GameFlow
        @Serializable
        data object SpeechToTextScreen : GameFlow
        @Serializable
        data object VocabularyScreen : GameFlow
        @Serializable
        data object VocabularyScreenGraph : GameFlow //추가 필요
        @Serializable
        data class DetailVocabularyScreen(val documentId: String) : GameFlow

    }
    @Serializable
    sealed interface TodayStudyFlow : ScreenRoutDef {
        @Serializable
        data object TodayStudyFlowGraph : TodayStudyFlow  // 추가 필요
        @Serializable
        data object ListenScreen : TodayStudyFlow
        @Serializable
        data object DictionaryScreen : TodayStudyFlow
        @Serializable
        data object SpeechScreen : TodayStudyFlow
        @Serializable
        data object WriteScreen : TodayStudyFlow
    }


}