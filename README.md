

# 🪅KLang- 외국인을 위한 한국어 단어 학습 앱 </br>      (with COMPOSE)
### v1 :xml기반 프로젝트 v2:**Compose** 기반 프로젝트를 진행하고 있습니다.  
 (기존 프로젝트 확인은 <a href="https://github.com/hyewoon/KLangComposePJ">여기에서</a>)


## ✨compose  회고

 **💡왜 컴포넌트를 쪼갤까?** </br>
 처음에는 '재사용성'정도를 생각했는데, **불필요한 recompostion을 방지** 하기 위해서 였습니다. </br>
 **상황**:   Card 내부에 있는 북마크 버튼을 클릭하면 카드 전체가 다시 그려짐(Layout Inspector로 확인)</br> 
 **개선** : 북마크 버튼 별도 컴포넌트로 분리/ UiState 전체를 넘기는 것이 아니라 필요한 속성만 넘기기</br>

 **💡Best Practice는?**</br>
 **파라미터는 필요한 것만 primitive 타입으로 넘기기**</br>
 객체 전체를 넘기면 필요한 속성이 아닌, 관련없는 다른 속성이 변경되어도 recompositon이 발생합니다.</br>

 **💡깨달은점**</br>
 화면을 단순히 xml에서 Compose로 바꾸는 것이 목표가 아니라. **Compose의 동작 원리를 이해하고 그에 맞게 설계**해야함을 깨달았습니다. </br>
 

 |기술 |적용사항 |
|------------------------|------|
| **Animation in Compose** |xml기반 프로젝트에서 MotionLayout을 이용해 애니메이션 효과를 준 것을 Compose Animation( setContentSize()) 통해 더 간결한 코드로 구현|
| **androidView</br>(Using Views in Compose)** |안드로이드 View를 상속받은 CustomView를 Compose에 적용하기 위해 AndroidView()활용 |
</br>

## 1️⃣ 프로젝트 개요

* **KLang** 은 외국인을 위한 한국어 단어 학습앱입니다.
* 학습 길잡이인 캐릭터 'K(케이)'😸와 함께 언어(Lang:랑)을 학습한다는 의미를 담은 **KLang(케이랑)** 을 앱의 이름으로 선정하였습니다.
* 신뢰할 수 있는 단어데이터베이스인 **'한국어기초사전'API**에서 제공하는 단어 정보와 예문을 학습 할 수 있습니다.


 ## 2️⃣ 개발인원
|역할  |이름 |
|------------------------|------|
|기획,개발|김혜운( 1인 개발) |
|디자인|박혜민|


### 🚩 구현 예정 사항 
|기능 |진행도 |상태 |완료여부 |
|------------------------|------|-----|-----|
| MLKit 손글씨 인식 정확도 개선|🔸🔸🔸▫️▫️|기존 로직 수정 중 | |
 


 ## 4️⃣ 아키텍쳐
 ### 시스템 아키텍쳐
 <img width="1031" height="593" alt="image" src="https://github.com/user-attachments/assets/d5bd66da-6198-41f1-8cb4-8e466501c6d7" />


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

✅ **단어와 놀기 : 검색 기능**

* 궁금한 한국어를 검색하면 한국어기초사전 API 통해 상세 예문을 확인


![search](https://github.com/user-attachments/assets/aed37869-f626-4b9f-b1e1-30cbd4341eba)

