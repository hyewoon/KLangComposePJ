

# 🪅KLang- 외국인을 위한 한국어 단어 학습 앱 </br>      (with COMPOSE)
### 😸 기존 xml기반 프로젝트를  **ComposeUI/UX**와 **Hilt**를 적용해 마이그레이션을 진행하고 있습니다.  

|기술 |적용사항 |
|------------------------|------|
| **Compose UI/UX** | UI를 component 단위로 관리하고, 상태변수를 통해 UI와 stateHolder를 명확히 분리한 MVVM패턴 구현|
| **animation in Compose** |xml기반 프로젝트에서 MotionLayout을 이용해 애니메이션 효과를 준 것을 Compose Animation(setContentSize())을 활용해 더 간결한 코드로 구현|
| **androidView 적용** | Compose에서 기존 안드로이드 View를 상속받은 CustomView를 적용하기 위해 AndroidView()활용함 |
| **Hilt(의존성 주입)** | Factory패턴을 이용한 수동 의존성 주입에서 hilt 의존성 주입을 통해 app을 통합적으로 관리(HiltModule, Hilt Navigation, Hilt viewModel적용) |



## 1️⃣ 프로젝트 개요

* **KLang** 은 한국어능력시험인 TOPIK '초급' 단어를 학습 할 수 있는 앱입니다.

## 2️⃣ KLang 이란


* 학습 길잡이인 캐릭터 'K(케이)'😸와 함께 언어(Lang:랑)을 학습한다는 의미를 담은 **KLang(케이랑)** 을 앱의 이름으로 선정하였습니다.
* 사용자가 **학습목표를 설정**하고, 학습한 만큼 **포인트를 획득** 할 수 있는 앱입니다.
* 신뢰할 수 있는 단어데이터베이스인 **'한국어기초사전'API**에서 제공하는 단어 정보와 예문을 학습 할 수 있습니다.


## 3️⃣ 기술 스택

| skills | 설명 |
|------------------------|------|
| **kotlin** |kotlin2.0 적용  |
| **Compose UI/UX** |recomposition통해 상태관리|
| **MVVM 아키텍처 패턴** | 멀티모듈 적용 |
| **ViewModel & StateFlow** | 상태변수를 이용한 UI와 StateHolder 분리|
| **RoomDB** | SSOT(Single Source Of Truth)와 Offline First 구현 |
| **MLKit DigitalInk** | 손글씨 인식 기능을 onDevice로 구현 |
| **TTS/STT** | 인식 기능 전반 개발 |
| **DataStore** | 설정 및 전역 변수 관리 |
| **DI** | Hilt를 이용한 의존성 주입 |
| **Serverless Firebase Firestore** | 데이터 저장 및 동기화 구현 |
| **Navigation Graph** | 앱 화면 전환 |


</br>

## 4️⃣ KLang 핵심기능


![image](https://github.com/user-attachments/assets/f0b6d8eb-8934-4585-96b4-333258bce120)



 ✅ **오늘의 단어 학습** 
  - 예문, 듣기, 말하기, 쓰기 기능을 통해 사용자가 직접 참여하는 학습
  - 북마크 기능을 통해 나만의 단어장 만들기

 <img src="https://github.com/user-attachments/assets/2dfc4851-6a7b-4667-9241-ac11e414d79e" width="250" hspace="5" vspace="5">



✅ **단어와 놀기 : MLKit 손글씨 인식기능**
* 사용자가 손글씨를 입력하면 그에 해당하는 한글 단어를 비교해서 보여줌

<table width="90%">
  <tr>
    <td width="45%" align="center">
      <img src="https://github.com/user-attachments/assets/f4a753c4-a239-47aa-bff0-b5f4b0f475d5"  width="250" hspace="5" vspace="5">
    </td>
    <td width="45%" align="center">
      <img src="https://github.com/user-attachments/assets/7275c65a-471d-4db1-96c5-bc2c5efae2d9"  width="250" hspace="5" vspace="5">
    </td>
  </tr>
</table>

## 5️⃣ COMPOSE 적용 과정 
