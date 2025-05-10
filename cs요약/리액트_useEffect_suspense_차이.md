
# Suspense와 기존 로딩 상태 관리 방식의 차이

## 기존 방식: `useEffect()`와 `loading` 상태 변수

기존 방식에서는 데이터를 불러올 때 `useEffect()` 훅을 사용하고,  
로딩 상태를 관리하기 위해 `isLoading`이라는 **별도의 상태 변수**를 만들어야 합니다.

예시:

```jsx
const [data, setData] = useState(null);
const [isLoading, setIsLoading] = useState(true);

useEffect(() => {
  fetchData().then(res => {
    setData(res);
    setIsLoading(false);
  });
}, []);
```

- 데이터 로딩 중: `isLoading`을 `true`로 설정
- 데이터 완료 후: `isLoading`을 `false`로 변경
- 렌더링 조건: `isLoading` 여부에 따라 로딩 UI를 조건부로 표시

이 방식은 **간단한 상황**에서는 충분하지만,  
**여러 개의 비동기 데이터**를 다룰 경우 **조건부 렌더링 로직이 복잡해질 수 있음**.

---

## Suspense: 선언적인 로딩 상태 관리

`Suspense`는 로딩 중인 컴포넌트를 직접 렌더링하지 않고,  
`fallback` 속성으로 로딩 UI를 **정의**합니다.

```jsx
<Suspense fallback={<Loading />}>
  <MyComponent />
</Suspense>
```

- 데이터가 준비될 때까지 `fallback`으로 지정한 컴포넌트만 렌더링됨
- 데이터가 준비되면 `Suspense`에 감싸진 컴포넌트를 자연스럽게 표시

### ✅ 장점

- **로딩 상태를 선언적으로 관리**할 수 있어 코드가 단순해짐
- 유지보수가 쉬움
- 반복적인 `isLoading` 조건문 제거 가능

---

## Suspense의 단점은? 🤔

### 1. 중첩 사용 시 UI 일관성 문제

- 여러 개의 `Suspense` 컴포넌트를 **트리 구조로 중첩**하면,
- 각각의 `Suspense`가 **독립적으로 로딩 상태를 관리**
- → 데이터 준비 시점이 다를 수 있어 **여러 번 로딩 화면(fallback)이 표시될 수 있음**
- → UI가 **비일관적**이 되기 쉬움

**대응 방법**:  
→ 트리 구조와 데이터 로딩 흐름을 **신중하게 설계**해야 함

---

### 2. Promise 기반 비동기 작업만 지원

- `Suspense`는 **Promise 기반**의 비동기 작업만 지원
- `fetch()` 요청을 바로 사용할 수 없음
- → 추가적인 라이브러리(ex. React Query, SWR) 또는  
  Suspense와 호환되는 **custom data fetching 래퍼** 필요

---

> `Suspense`는 선언적이고 깔끔한 로딩 관리 방식을 제공하지만,  
> 적절한 구조 설계와 함께 사용하는 것이 중요합니다.
