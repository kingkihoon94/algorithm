
# 데이터 속성(data-*)의 개념과 활용법

## 데이터 속성이란?

데이터 속성은 **사용자 정의 데이터(custom data)**를 HTML 요소에 저장하기 위해 사용되는 속성입니다.  
HTML5부터 도입되었으며, `data-`로 시작하는 속성을 통해 정의됩니다.

### 예시

```html
<div data-user-id="12345" data-role="admin"></div>
```

위 예시에서 `data-user-id`와 `data-role`은 **데이터 속성**입니다.

---

## 자바스크립트에서 데이터 속성 접근

자바스크립트에서는 `dataset` 객체를 통해 데이터 속성에 접근할 수 있습니다.  
중요한 점은 HTML 속성명이 JavaScript에서는 **camelCase**로 변환된다는 것입니다.

### 접근 예시

```js
const element = document.querySelector('div');
console.log(element.dataset.userId); // "12345"
console.log(element.dataset.role);   // "admin"
```

- `data-user-id` → `dataset.userId`
- `data-role` → `dataset.role`

---

## CSS에서 데이터 속성 활용

CSS에서도 **데이터 속성**을 사용할 수 있습니다.  
`attr()` 함수나 **속성 선택자(attribute selector)**를 통해 스타일 조건을 지정할 수 있습니다.

### `attr()` 함수 사용 예시

```css
article::before {
  content: attr(data-parent);
}
```

### 속성 선택자 사용 예시

```css
article[data-columns="3"] {
  width: 400px;
}
```

---

## 데이터 속성은 언제 사용하나요? 🤔

데이터 속성은 다음과 같은 상황에서 유용하게 사용할 수 있습니다:

- **DOM 요소에 데이터를 바인딩**하고, JavaScript에서 쉽게 접근하고자 할 때
- **이벤트 처리 시** 특정 데이터를 함께 전달하고자 할 때
- **UI 요소를 동적으로 변경**해야 할 때 기준 값으로 사용

### 예시: 버튼 클릭 시 데이터 전달

```html
<button data-product-id="987">장바구니 추가</button>
```

```js
button.addEventListener('click', (e) => {
  const productId = e.currentTarget.dataset.productId;
  addToCart(productId);
});
```

---

## 요약

| 구분        | 내용 |
|-------------|------|
| **정의**     | HTML 요소에 사용자 정의 데이터를 저장하는 속성 |
| **형식**     | `data-속성명="값"` |
| **JS 접근**  | `element.dataset.속성명` (camelCase) |
| **CSS 사용** | `attr()`, 속성 선택자 가능 |
| **활용 예**  | 이벤트 처리, UI 조작, 데이터 바인딩 등 |
