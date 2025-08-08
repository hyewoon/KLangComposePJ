package com.hye.presentation.viewmodel

import com.hye.domain.usecase.LoadStudyWordUseCase
import com.hye.presentation.ui.screen.model.GameViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

/*
* TDD: Test-Driven_Development : 테스트 코드-> 실제 코드 작성
* 실제 ViewModel의 구조와 일치하게 , mlkit, stt,tts를 하나의 testViewModel에서 테스트
*
* red-green-refactor 과정 거침
* */
class GameViewModelTest {

    @Test
    fun `초기상태에서 mlkit 인식된 텍스트는 빈문자열이여야 한다`() {

        //Given
        val mockUseCase = null
        val viewModel = GameViewModel(useCase = mockUseCase)

        //when
        val result = viewModel.recognizedText.value

        //then
        //assertEquals(expected, actual)
        assertEquals("", result)
    }

    @Test
    fun `초기상태에서 인식 중 상태는 false여야 한다`() {

        //given
        val mockUseCase = null
        val viewModel = GameViewModel(useCase = mockUseCase)

        //when
        val result = viewModel.isRecognizing.value

        //then
        assertEquals(false, result)
    }

    @Test
    fun `인식 시작하면 로딩 상태가 true가 된다`(){
        //given
        val mockUseCase = null
        val viewModel = GameViewModel(useCase = mockUseCase)

        //when
        viewModel.startRecognizing()

        //then
        assertEquals(true, viewModel.isRecognizing.value)

    }

    @Test
    fun `인식이 완료되면 결과 텍스트가 업데이트 되어야한다`(){
        //given
        val mockUseCase = null
        val viewModel = GameViewModel(useCase = mockUseCase)
        val expectedResult = "안녕하세요"

        //when
        viewModel.completeRecognition(expectedResult)

        //then
        assertEquals(expectedResult, viewModel.recognizedText.value)

    }

    @Test
    fun `인식이 완료되면 로딩 상태가 false가 된다`(){
        //given
        val mockUseCase = null
        val viewModel = GameViewModel(useCase = mockUseCase)

        //인식 시작해서 로딩 상태 true
        viewModel.startRecognizing() //인식시작해서 로딩 상태

        //when
        viewModel.completeRecognition("인식 완료") //인식 완료

        //then
        assertEquals(false, viewModel.isRecognizing.value)

    }
}

