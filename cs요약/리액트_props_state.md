## ✅ props란?

- **부모 컴포넌트가 자식 컴포넌트에 전달하는 데이터**입니다.
- **읽기 전용**이며, 자식 컴포넌트는 해당 값을 **직접 수정할 수 없습니다**.
- props를 사용하면 컴포넌트 간 데이터 흐름이 예측 가능하고, 재사용성이 높아집니다.


```jsx
function ChildComponent(props) {
  props.name = "New Name"; // ❌ 오류 발생 가능 (props는 불변)
  return <div>{props.name}</div>;
}
```

## ✅ state란?

- **컴포넌트 내부에서 관리되는 동적인 데이터**입니다.
- `state`가 변경되면 해당 컴포넌트는 **자동으로 다시 렌더링**되어 UI가 업데이트됩니다.
- 주로 **사용자 입력**, **네트워크 요청 응답** 등에 따라 변하는 데이터를 관리할 때 사용됩니다.

---

## ❓ 왜 `props`는 자식 컴포넌트에서 변하지 않을까요? 🤔

- 리액트는 **단방향 데이터 흐름**(one-way data flow)을 따릅니다.
- `props`는 **부모 → 자식 방향으로만** 데이터가 흐릅니다.
- 자식 컴포넌트가 `props`를 수정할 수 없다면,
  - **데이터 흐름을 쉽게 추적**할 수 있고
  - **예측 가능한 상태 관리**가 가능합니다.
- `props`는 **불변(immutable)**이기 때문에,
  - **디버깅이 쉬워지고**
  - **재사용성과 캡슐화**가 강화됩니다.

---

## ❓ 자식 컴포넌트에서 `props`를 변경하고 싶다면? 🧐

- **부모 컴포넌트에서 상태를 관리**하고,
- **상태 변경 함수(`setState`)를 자식 컴포넌트에 전달**해야 합니다.
- 이 패턴을 **"상태 끌어올리기(Lifting State Up)"** 라고 합니다.

```jsx
// 부모 컴포넌트
function Parent() {
  const [name, setName] = useState("React");

  return <Child name={name} setName={setName} />;
}

// 자식 컴포넌트
function Child({ name, setName }) {
  return (
    <div>
      <p>{name}</p>
      <button onClick={() => setName("New Name")}>Change Name</button>
    </div>
  );
}
```

- 이렇게 하면 데이터는 여전히 **단방향으로 흐르고**,
- 상태는 **부모 컴포넌트가 중앙에서 제어**하게 되어  
  **일관성 있는 데이터 관리**가 가능합니다.




