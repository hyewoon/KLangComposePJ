

# KLang(케이랑)- 외국인을 위한 한국어 단어 학습 앱 </br>     
### v1 :xml기반 프로젝트 v2:**Compose** 기반 프로젝트를 진행하고 있습니다.  
 (기존 프로젝트 확인은 <a href="https://github.com/hyewoon/KLangPJ">여기에서</a>)

<br> </br>
## 1️⃣ 프로젝트 개요

* **KLang** 은 외국인을 위한 한국어 단어 학습앱입니다.
* 학습 길잡이인 캐릭터 'K(케이)'😸와 함께 언어(Lang:랑)을 학습한다는 의미를 담은 **KLang(케이랑)** 을 앱의 이름으로 선정하였습니다.
* 신뢰할 수 있는 단어데이터베이스인 **'한국어기초사전'API**에서 제공하는 단어 정보와 예문을 학습 할 수 있습니다.
 <br> </br>

## 2️⃣ KLang 핵심기능

<table>
  <tr>
    <th>손글씨 인식</th>
    <th>오늘의 단어 학습</th>
    <th>단어 검색</th>
  </tr>
  <tr>
    <td ><p align="center"><img src="https://github.com/user-attachments/assets/844113af-b594-46f0-a4cb-b72194e018d0" width="220"/></p></td>
    <td><p align="center"><img src="https://github.com/user-attachments/assets/3a0322b5-3297-4cf0-93e6-bec056bb75bf" width="220"/></p></td>
    <td><p align="center"><img src="https://github.com/user-attachments/assets/aed37869-f626-4b9f-b1e1-30cbd4341eba" width="220"/></p>></td>
   </div>
  </tr>
  <tr>
    <td>손글씨를 입력하면 
     해당 한글 단어를 비교해서 보여줌</td>
    <td>예문, 듣기, 말하기, 쓰기로 학습하고 
     북마크로 나만의 단어장 만들기</td>
    <td>한국어기초사전 API로 상세 예문 확인</td>
  </tr>
</table>

 
<br> </br>
## 3️⃣ 아키텍쳐
 <img width="752" height="344" alt="image" src="https://github.com/user-attachments/assets/a4ebfefc-b456-4619-9344-718c076cf609" />
" />


<br> </br>
## 4️⃣ 기술적 고민

<details>
<summary><b>1. ML Kit 자원 초기화 위치와 해제 시점</b></summary>

**문제**
- Fragment의 onViewCreated()에서 초기화하자, 첫 진입 시 인식에 실패하고 재진입 후에만 정상 동작

**원인**
- 첫 사용 시 인식 모델 다운로드와 Recognizer 준비에 시간이 걸리고, 준비 전에 입력이 들어옴
- 화면이 재생성될 때마다 초기화가 반복됨

**해결**
- 초기화를 ViewModel로 옮겨 ViewModel 생존 기간 동안 한 번만 수행
- Compose 전환 후 ViewModel이 백스택에 남으며 자원을 계속 점유하는 문제 → 5분 미사용 시, 백그라운드 진입 시 Recognizer 해제

**결과**
- 한 번 준비된 이후 재진입 시 바로 인식
- Profiler 검증 결과 메모리 사용량 약 160MB에서 128MB로 감소

**남은 과제**
- 첫 진입 준비 시간은 그대로이므로, 초기화 상태(준비 중·완료·실패)를 UI에 표시하는 방식으로 보완 예정

</details>

<details>
<summary><b>2. Room 데이터 조회: Flow 대신 suspend를 선택한 이유</b></summary>

**문제** / **판단** / **결과** 순서로 작성

</details>

<details>
<summary><b>3. 오프라인 학습을 위한 저장 전략</b></summary>

**문제** / **해결** / **다음 단계(WorkManager)** 순서로 작성

</details>
 
<br> </br>

## 5️⃣ 기술 스택
| Skills | 설명 |
|:---:|---|
| **Kotlin** | Kotlin 2.0 적용 |
| **Jetpack Compose** | 상태 기반 선언형 UI 구현, Layout Inspector로 불필요한 리컴포지션 확인 및 개선 |
| **Architecture** | MVVM + UI-Domain-Data Layer 분리, 의존성 역전 원칙 적용 (XML·Compose 버전에서 Domain·Data Layer 재사용) |
| **ViewModel & StateFlow** | UiState로 화면 상태를 통합 관리하고 UI와 State Holder 분리 |
| **Coroutines & Flow** | 비동기 처리, 데이터 특성에 따라 Flow와 suspend 함수를 구분해 적용 |
| **Hilt** | 의존성 주입 |
| **Room · Firestore** | Firestore의 학습 데이터를 Room에 저장하는 Offline First 구조 (SSOT) |
| **ML Kit Digital Ink** | 온디바이스 손글씨 인식, 생명주기에 맞춘 자원 초기화·해제 |




</br>




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



