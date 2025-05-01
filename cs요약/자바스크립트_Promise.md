# JavaScript Promise 요약

`Promise`는 비동기 작업의 **성공(Fulfilled)** 또는 **실패(Rejected)** 결과를 나중에 사용할 수 있도록 하는 객체입니다.  
비동기 작업의 완료 여부를 "약속"해주는 개념이라 볼 수 있습니다.

---

## 🔹 콜백 지옥 vs Promise

- 전통적인 비동기 처리: 콜백 함수 사용 → 중첩 구조로 인한 **콜백 지옥**
- Promise: 코드 흐름을 **명확히** 하고, **가독성 향상**

---

## 🔹 Promise의 상태 (3가지)

1. **Pending**: 대기 중 (아직 완료되지 않음)
2. **Fulfilled**: 성공 → `resolve()` 호출
3. **Rejected**: 실패 → `reject()` 호출

> 상태는 한 번 결정되면 **다시 바뀌지 않음**

---

## 🔹 기본 사용법
```js
const promise = new Promise((resolve, reject) => {
  // 비동기 작업 수행
  if (성공) {
    resolve('성공 결과');
  } else {
    reject('에러 발생');
  }
});

promise
  .then(result => console.log(result))
  .catch(error => console.error(error));
```

## 🔹 병렬 처리
Promise.all([p1, p2]): 모든 작업 성공 시 완료

Promise.allSettled([p1, p2]): 모두 완료될 때까지 대기 (성공/실패 상관없음)

## ❗ Promise의 단점
1. 복잡한 에러 처리
단일 체인은 간단하지만, 중첩되면 catch 블록 분리가 어려워질 수 있음

2. 콜백 지옥 완전 해결은 아님
then().then().then() 형태로 중첩되면 여전히 가독성 저하

👉 async/await 사용 시 훨씬 직관적인 비동기 흐름 가능

## ✅ 요약
Promise는 콜백보다 가독성 좋음

3가지 상태: Pending → Fulfilled / Rejected

에러 처리 및 중첩 시 복잡성 ↑ → async/await로 개선 가능
