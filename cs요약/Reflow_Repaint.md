# 🎨 Reflow와 Repaint의 차이점 (프론트엔드 렌더링 성능 관련)

웹 페이지가 렌더링되는 과정에서 브라우저는 **Reflow**와 **Repaint**라는 중요한 작업을 수행합니다. 이 두 작업을 이해하면 성능 최적화에 큰 도움이 됩니다.

---

## 🔁 Reflow란?

- **정의**: 브라우저가 요소의 **레이아웃(위치, 크기)** 을 다시 계산하는 과정
- **발생 시점**:
  - DOM 구조 변경
  - CSS 레이아웃 관련 속성 변경 (ex. `width`, `height`, `margin`, `padding`, `border` 등)
- **영향 범위**: 변경된 요소뿐만 아니라 **자식 요소와 관련된 부모 요소까지 모두 영향**
- **비용**: 매우 **비싼 작업** (브라우저가 레이아웃을 다시 그려야 하므로)

> 💡 예시:  
> 어떤 요소의 `width` 값을 JavaScript로 변경하면, 해당 요소와 그 자식 요소 모두의 위치를 브라우저가 다시 계산해야 함

---

## 🎨 Repaint란?

- **정의**: 레이아웃은 그대로 두고, 요소의 **시각적 스타일(색상, 배경 등)** 만 다시 그리는 작업
- **발생 시점**:
  - 색상, 배경, 글자색 등 시각적인 변경 (ex. `background-color`, `color` 등)
- **영향 범위**: 해당 요소만 영향
- **비용**: **Reflow보다는 덜 비쌈**, 그래도 빈번하면 성능 저하 가능

> 💡 예시:  
> 버튼의 배경색을 `hover` 상태에서 바꾸면 repaint 발생 (레이아웃에는 변화 없음)

---

## ⚡ 성능 최적화를 위한 3가지 방법

### 1. Reflow 유발 최소화

- 레이아웃에 영향을 주는 속성은 **가능한 초기에만 설정**
- 자바스크립트로 DOM을 조작할 때는 **한꺼번에 변경**하고, **layout thrashing** 방지
- 반복적으로 DOM 접근 시 `documentFragment` 사용 등도 고려

---

### 2. CSS 애니메이션 최적화

- **Reflow를 유발하지 않는 속성**만 애니메이션에 사용
  - ✅ `transform`, `opacity`
  - ❌ `width`, `height`, `top`, `left`
- GPU 가속을 유도해 성능 향상 가능

---

### 3. `will-change` 속성 사용

- 브라우저에 **미리 변경될 속성**을 알려, 최적화된 준비가 가능하도록 함

```css
.my-box {
  will-change: transform;
}
```

- ❗ 주의: **남용 금지**, 너무 많은 요소에 사용하면 메모리 사용량 증가

---

## 📝 정리

| 항목        | Reflow                           | Repaint                          |
|-------------|----------------------------------|----------------------------------|
| 대상        | 레이아웃 (위치, 크기) 변경       | 스타일 (색상, 배경 등) 변경      |
| 성능 영향   | 매우 큼                          | 중간                              |
| 예시        | `width`, `height`, `margin` 등 변경 | `background-color`, `color` 등 변경 |
| 최적화 방법 | 레이아웃 변경 최소화             | GPU 가속 활용 (transform, opacity 사용) |

---
