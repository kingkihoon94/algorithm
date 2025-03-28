# ✅ Controlled Component vs Uncontrolled Component

## 📌 Controlled Component

- **정의**  
  리액트의 상태(`state`)를 통해 입력 값을 제어하는 컴포넌트입니다.

- **특징**
  - 입력 요소의 `value`를 상태와 **동기화**
  - `onChange` 이벤트 핸들러를 통해 상태를 업데이트
  - 입력 값이 항상 리액트의 상태에 저장되어 있음

- **장점**
  - 값 검증 및 조건부 렌더링이 쉬움
  - 입력 값을 자유롭게 조작 가능
  - 복잡한 폼 로직을 구현하기에 적합

- **사용 예시**
  - 실시간 유효성 검사
  - 입력에 따라 UI 변경이 필요한 경우

---

## 📌 Uncontrolled Component

- **정의**  
  입력 값을 리액트 상태로 관리하지 않고, DOM 자체에서 직접 제어하는 방식입니다.

- **특징**
  - 입력 요소의 값을 `ref`를 통해 직접 접근
  - 입력 값은 DOM 내부에 저장됨
  - 컴포넌트 상태 업데이트가 필요 없음

- **장점**
  - 상태 업데이트에 따른 렌더링 비용 없음
  - 간단한 로직에서 빠르고 가볍게 사용 가능

- **사용 예시**
  - 단순 입력 폼
  - 제출 시에만 입력 값을 읽어오는 경우

---

## 📊 어떤 상황에서 사용하면 좋을까? 🤔

| 상황 | 적합한 방식 |
|------|-------------|
| 실시간 유효성 검사가 필요한 경우 | ✅ **Controlled Component** |
| 입력 값으로 조건부 UI를 렌더링할 경우 | ✅ **Controlled Component** |
| 제출 시에만 입력 값을 가져오면 되는 단순 폼 | ✅ **Uncontrolled Component** |
| 성능 최적화가 중요한 경우 | ✅ **Uncontrolled Component** |
