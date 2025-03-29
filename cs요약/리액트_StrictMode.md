# React의 Strict Mode란?

React의 `StrictMode`는 **개발 중에 발생할 수 있는 잠재적인 문제를 사전에 감지하고 예방하기 위해** 사용되는 도구입니다. 이는 프로덕션 빌드에는 포함되지 않으며, 오직 **개발 환경에서만 작동**합니다.

---

## 주요 기능

### 1. 오래된 라이프사이클 메서드 감지
React에서는 더 이상 사용이 권장되지 않는 라이프사이클 메서드가 있습니다. 예를 들면:
- `componentWillMount()`
- `componentWillReceiveProps()`

이러한 메서드들이 코드에 포함된 경우, `StrictMode`는 경고를 발생시켜 **최신 React API 사용을 유도**합니다.

---

### 2. 의도치 않은 부수 효과(Side Effects) 감지
React는 **예측 가능한 렌더링**과 **순수 함수 기반 컴포넌트**를 지향합니다. 이를 검증하기 위해 `StrictMode`는 다음과 같은 함수/훅을 **두 번 실행**합니다:
- `useEffect()`
- `useState()`
- `useMemo()`
- `useCallback()` 등

✅ **목적**: 동일한 결과가 두 번 나오는지 확인하여 컴포넌트가 부수 효과 없이 순수하게 동작하는지 검증

> ⚠️ 만약 두 번째 실행에서 오류가 발생한다면, 해당 컴포넌트에는 부적절한 사이드 이펙트가 포함되어 있을 수 있습니다.

---

## 성능에 영향은 없을까요? 🤔

코드가 두 번 실행되면 성능 문제가 생길 수 있다는 우려가 있지만, **걱정할 필요는 없습니다.**

- `StrictMode`의 이러한 동작은 **오직 개발 환경에서만 적용됩니다.**
- **프로덕션 빌드에서는 정상적으로 한 번만 실행**되므로, 실제 사용자 경험에는 영향을 주지 않습니다.

---

## 결론

`React.StrictMode`는 다음을 가능하게 해주는 **개발 도우미**입니다:

- 비권장 API 및 오래된 패턴 감지
- 부수 효과가 있는 코드를 사전에 파악
- 미래의 React 업데이트에 대비한 안전한 코드 작성

```jsx
import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
