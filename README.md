

# 🪅KLang- 외국인을 위한 한국어 단어 학습 앱 </br>      (with COMPOSE)
### 😸 xml기반 프로젝트를  **ComposeUI/UX**와 **Hilt**를 적용해 마이그레이션을 진행하고 있습니다.  
 (기존 프로젝트 확인은 <a href="https://github.com/hyewoon/KLangComposePJ">여기에서</a>)


|기술 |적용사항 |
|------------------------|------|
| **Compose UI/UX** | UI를 component 단위로 관리하고, 상태변수를 통해 UI와 stateHolder를 명확히 분리한 MVVM패턴 구현|
| **Animation in Compose** |xml기반 프로젝트에서 MotionLayout을 이용해 애니메이션 효과를 준 것을 Compose Animation( setContentSize()) 통해 더 간결한 코드로 구현|
| **androidView</br>(Using Views in Compose)** |안드로이드 View를 상속받은 CustomView를 Compose에 적용하기 위해 AndroidView()활용 |
| **Hilt(의존성 주입)** | 수동 의존성 주입에서 hilt 의존성 주입을 통해 멀티모듈 app을 통합적으로 관리(HiltModule, Hilt Navigation, hiltviewModel적용) |

🚩 구현 예정 사항 
|기능 |진행도 |상태 |
|------------------------|------|-----|
| STT(SpeechToText) |🔸▫️▫️▫️▫️ | UI 및 STTMananger 이용해 구현 중 |
| Preference 적용한 목표설정 및 포인트 관리 기능  |▫️▫️▫️▫️▫️ |Preference 연결 |
| 학습탭 관련 세부 기능(듣기,말하기,사전,쓰기) | 🔸🔸▫️▫️▫️ |쓰기 완료, 나머지 기능 연결 중 |
| MLKit 손글씨 인식 정확도 개선|🔸🔸🔸▫️▫️|기존 로직 수정 중 |




## 1️⃣ 프로젝트 개요

* **KLang** 은 외국인을 위한 한국어 단어 학습앱입니다.
* 학습 길잡이인 캐릭터 'K(케이)'😸와 함께 언어(Lang:랑)을 학습한다는 의미를 담은 **KLang(케이랑)** 을 앱의 이름으로 선정하였습니다.
* 신뢰할 수 있는 단어데이터베이스인 **'한국어기초사전'API**에서 제공하는 단어 정보와 예문을 학습 할 수 있습니다.

 
## 2️⃣ 개발인원
|역할  |이름 |
|------------------------|------|
|기획,개발|김혜운( 1인 개발) |
|디자인|박혜민|


 ## 4️⃣ 아키텍쳐
 ### 시스템 아키텍쳐
 <img width="1031" height="593" alt="image" src="https://github.com/user-attachments/assets/d5bd66da-6198-41f1-8cb4-8e466501c6d7" />

 ### Clean Architechure 레이어구조
<img width="449" height="659" alt="image" src="https://github.com/user-attachments/assets/7ce3cd5a-0e40-40a5-bc44-8b37d9f4bc47" />



## 5️⃣ 기술 스택

| skills | 설명 |
|------------------------|------|
| **kotlin** |kotlin2.0 적용  |
| **Compose UI/UX** |recomposition을 통한 UI자동 업데이트|
| **MVVM 아키텍처 패턴** | 멀티모듈 적용 |
| **ViewModel & StateFlow** | 상태변수를 이용한 UI와 StateHolder 분리|
| **RoomDB** | SSOT(Single Source Of Truth)와 Offline First 구현 |
| **MLKit DigitalInk** | 손글씨 인식 기능을 onDevice로 구현 |
| **TTS/STT** | 음성 및 텍스트 인식 |
| **DataStore** | 설정 및 전역 변수 관리 |
| **Hilt(DI)** | Hilt를 이용한 의존성 주입 |
| **Serverless Firebase Firestore** | 데이터 저장 및 동기화 구현 |
| **Navigation Graph** | 앱 화면 전환 |


</br>

## 6️⃣ KLang 핵심기능


![image](https://github.com/user-attachments/assets/f0b6d8eb-8934-4585-96b4-333258bce120)



 ✅ **오늘의 단어 학습** 
  - 예문, 듣기, 말하기, 쓰기 기능을 통해 사용자가 직접 참여하는 학습
  - 북마크 기능을 통해 나만의 단어장 만들기


![Screen_recording_20251015_142642](https://github.com/user-attachments/assets/3a0322b5-3297-4cf0-93e6-bec056bb75bf)


✅ **단어와 놀기 : MLKit 손글씨 인식기능**
* 사용자가 손글씨를 입력하면 그에 해당하는 한글 단어를 비교해서 보여줌
* 손글씨 정확성 높이기 위한 리팩토링 진행중 🚩

![Screen_recording_20251015_135629](https://github.com/user-attachments/assets/844113af-b594-46f0-a4cb-b72194e018d0)
