## useEffect vs useLayoutEffect

`useEffect`와 `useLayoutEffect`는 모두 렌더링 후 실행되지만, **타이밍**과 **용도**가 다릅니다.

### useEffect
- 렌더링 후 **비동기** 실행 (화면이 그려진 후)
- API 호출, 이벤트 리스너 등록 등 화면에 직접 영향 없는 작업에 적합

```tsx
useEffect(() => {
  fetchData().then(data => setData(data));
}, []);
```

### useLayoutEffect
- 렌더링 후 **DOM 업데이트 직전 동기** 실행
- DOM 크기 측정, 위치 조정 등 레이아웃 조작에 적합

```tsx
useLayoutEffect(() => {
  const height = ref.current.offsetHeight;
  setHeight(height);
}, []);
```

### 정리

| 구분 | useEffect | useLayoutEffect |
|------|-----------|-----------------|
| 실행 시점 | 렌더링 후 (비동기) | 렌더링 후 DOM 업데이트 직전 (동기) |
| 용도 | API 호출, 이벤트 등록 등 | 레이아웃 조정, 깜빡임 방지 등 |
| 성능 | 부담 적음 | 과도하면 렌더링 지연 가능 |

> 보통은 `useEffect`를 사용하고, 레이아웃 조작이 필요한 경우에만 `useLayoutEffect`를 사용하세요.
