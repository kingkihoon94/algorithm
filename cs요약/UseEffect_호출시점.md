# React useEffect 요약

`useEffect`는 컴포넌트의 생명주기(마운트, 업데이트, 언마운트)에 따라 동작하는 훅입니다.

## 🔹 마운트 시 (처음 렌더링 후 1회)
```js
useEffect(() => {
  // 초기 작업(API 호출 등)
}, []);
```

## 🔹 업데이트 시 (의존성 변경될 때)

```js
useEffect(() => {
  // 상태나 props 변경 시 실행
  return () => {
    // 이전 값 기준 정리 작업
  };
}, [value]);
```

## 🔹 렌더링마다 실행 (의존성 생략)

```js
useEffect(() => {
  // 매 렌더링마다 실행
});
```

## 🔹 언마운트 시 (클린업)

```js
useEffect(() => {
  const id = setInterval(() => console.log('작업 중'), 1000);
  return () => {
    clearInterval(id); // 정리
  };
}, []);
```

## ✅ 요약
[] → 마운트 시 1회 실행

[dep] → dep 변경 시 실행 + 이전 값 기준 클린업

생략 시 → 매 렌더링마다 실행

return 함수 → 언마운트 또는 다음 실행 전 정리