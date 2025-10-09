package com.hye.data.repository

import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.vision.digitalink.DigitalInkRecognition
import com.google.mlkit.vision.digitalink.DigitalInkRecognitionModel
import com.google.mlkit.vision.digitalink.DigitalInkRecognitionModelIdentifier
import com.google.mlkit.vision.digitalink.DigitalInkRecognizer
import com.google.mlkit.vision.digitalink.DigitalInkRecognizerOptions
import com.google.mlkit.vision.digitalink.Ink
import com.google.mlkit.vision.digitalink.Ink.Point
import com.google.mlkit.vision.digitalink.Ink.Stroke
import com.hye.domain.model.mlkit.HandWritingAnalysis
import com.hye.domain.model.mlkit.HandWritingPoint
import com.hye.domain.model.mlkit.HandWritingStroke
import com.hye.domain.repository.mlkit.MLKitRepository
import com.hye.domain.result.AppResult
import com.hye.data.validate.HandWritingValidator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/*
* mklit digital Ink
* strokes-> Ink -> recognizer(Ink)
*
* */
class MLKitRepositoryImpl @Inject constructor(
    private val handwriteValid: HandWritingValidator
) : MLKitRepository {
    private var cleanupJob: Job? = null

    private var remoteModelManager = RemoteModelManager.getInstance()
    private var recognizer: DigitalInkRecognizer? = null
    private var model: DigitalInkRecognitionModel? = null
    private var isInitialized = false
    private var lastUsedTime = 0L
   // private val CLEANUP_DELAY = 5 * 60 * 1000L // 5분

    // 개발/테스트 단계
    private val CLEANUP_DELAY = 30 * 1000L // 30초
   // private val AUTO_RELEASE_DELAY = 60 * 1000L // 1분
   // private val AUTO_RELEASE_DELAY = 120 * 1000L // 2분



    /**
     * Ink 객체 build & 결과 값 받기
     */ override suspend fun initialize() {
        setUpRecognizer()
    }


    override suspend fun recognize(strokes: List<HandWritingStroke>) = flow<AppResult<HandWritingAnalysis>> {
        val ink = convertToInk(strokes).getOrThrow()
        val currentRecognizer = recognizer ?: throw Exception("Recognizer not initialized")
        val result = currentRecognizer.recognize(ink).await()
        lastUsedTime = System.currentTimeMillis()
        //결과값 리스트로 반환
        val candidateTexts = result.candidates.map{it.text}

        val bestResult = result.candidates.firstOrNull()?.text ?: "인식 실패"
        val firstCandidate = result.candidates.firstOrNull()

        result.candidates.forEachIndexed { index, candidate ->
            println("Candidate $index: ${candidate.text} with score: ${candidate.score}")
        }

        // 유효성 검사
        val analysis = handwriteValid.validateHandWriting(candidateTexts)

        val candidate = try {
            firstCandidate?.javaClass?.getDeclaredMethod("getScore")
                ?.invoke(firstCandidate) as? Number
        } catch (e: NoSuchMethodException) {
            println("score 필드 없음")
            null

        }

        emit(AppResult.Success(analysis))
        scheduleCleanUp()


    }.catch {
        emit(AppResult.Failure(Throwable("Unknown error")))
    }


    private fun scheduleCleanUp() {
        cleanupJob?.cancel()
        GlobalScope.launch {
            delay(CLEANUP_DELAY)
            if (System.currentTimeMillis() - lastUsedTime >= CLEANUP_DELAY) {
                recognizer?.close()
                recognizer = null
                isInitialized = false
            }

        }
    }

    private fun convertToInk(strokes: List<HandWritingStroke>) = runCatching {
        Ink.builder().apply {
            strokes.forEach { stroke ->
                addStroke(convertToStroke(stroke))
            }
        }.build()
    }

    private fun convertToStroke(stroke: HandWritingStroke) = Stroke.builder().apply {
        stroke.pointData.forEach { point ->
            addPoint(convertToPoint(point))

        }
    }.build()


    private fun convertToPoint(point: HandWritingPoint) = Point.create(
        point.x, point.y, point.timestamp
    )


    /**
     * recognizer -> viewModel에서 인식
     */
   private suspend fun setUpRecognizer() {
       //이미 초기화 되었는지 확인
       if (isInitialized && recognizer != null) return

        val modelIdentifier = DigitalInkRecognitionModelIdentifier.fromLanguageTag("ko")
            ?: throw Exception("지원되지 않는 언어입니다.")

        val model = DigitalInkRecognitionModel.builder(modelIdentifier).build()
        downloadModelAndSetUpRecognizer(model)
    }

    /**
     * 모델객체 얻기
     */
    private suspend fun downloadModelAndSetUpRecognizer(model: DigitalInkRecognitionModel) = runCatching {

        val currentModel = model ?: throw Exception("Model initialization failed")

        val isDownloaded = remoteModelManager.isModelDownloaded(currentModel).await()

        if(!isDownloaded) {
            remoteModelManager.download(currentModel, DownloadConditions.Builder().build()).await()
        }

        if(recognizer == null) {
            recognizer = DigitalInkRecognition.getClient(
                DigitalInkRecognizerOptions.builder(currentModel).build()
            )
        }
        isInitialized = true

    }.onFailure {
        it.printStackTrace()
        isInitialized = false
        recognizer?.close() //실패시에만 해제
        recognizer = null

    }
}

