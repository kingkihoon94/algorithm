# Promise의 `resolve()`와 `fulfilled` 차이점

`resolve()`와 `fulfilled`는 JavaScript의 Promise에서 비동기 처리 시 사용되는 값들이지만, **분명한 차이점이 존재**합니다.

- `resolve()`는 **Promise를 성공적으로 완료시키는 함수**
- `fulfilled`는 **해당 Promise가 완료된 상태를 의미**

## ✅ resolve()

`resolve()`는 Promise가 성공적으로 끝났을 때 결과 값을 넘겨주는 함수입니다.

예를 들어 어떤 비동기 작업이 잘 끝났을 때, `resolve()`를 호출해서  
> "이 작업이 끝났고 결과는 이거다"  
라고 전달하게 됩니다.

```js
new Promise((resolve, reject) => {
  resolve('완료');
});
```

이렇게 `resolve()`가 호출되면, 이 Promise의 상태는 `fulfilled`로 바뀌게 됩니다.

즉, **`resolve()`는 `fulfilled` 상태로 전환시키는 역할을 합니다.**

## ✅ fulfilled

`fulfilled`는 Promise가 성공적으로 완료된 상태를 말합니다.  
`resolve()`가 호출되면 자동으로 이 상태로 전환됩니다.

| 함수        | 의미                            |
|-------------|---------------------------------|
| `resolve()` | 성공적으로 끝났다는 '행위'       |
| `fulfilled` | 그 결과로 나타나는 '상태'        |

---

## ❓ resolve()가 실패할 수도 있나요?

**아니요, `resolve()`는 실패하는 상황이 없습니다.**  
`resolve()`는 Promise를 `fulfilled` 상태로 만드는 함수로, **성공적인 결과를 전달**할 때 사용됩니다.

- 실패 상황에서는 `resolve()`가 아니라 `reject()`가 호출됩니다.

```js
new Promise((resolve, reject) => {
  if (성공) resolve('성공');
  else reject('실패');
});
```

---

## 🔄 then()과 catch()의 역할

- `then()` : `resolve()`된 값(즉, fulfilled 상태의 값)을 처리
- `catch()` : `reject()`된 에러(즉, rejected 상태)를 처리

```js
fetch('/api/data')
  .then(response => response.json()) // resolve() 처리
  .catch(error => console.error(error)); // reject() 처리
```

---

## 📌 요약

| 구분        | 설명                                     |
|-------------|------------------------------------------|
| `resolve()` | Promise를 성공적으로 종료시키는 함수     |
| `fulfilled` | Promise가 성공적으로 종료된 상태         |
| `reject()`  | Promise를 실패 상태로 만드는 함수         |
| `rejected`  | Promise가 실패한 상태                     |
