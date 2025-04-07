# ✅ CommonJS vs ES Module (ESM)

CommonJS와 ES Module(ESM)은 자바스크립트에서 모듈을 관리하고 불러오는 두 가지 주요 방식입니다.

---

## 📌 CommonJS

- **정의**  
  주로 Node.js 환경에서 사용되며, 모듈을 **동기적으로 불러옵니다.**  
  모듈이 로드될 때까지 다음 코드가 실행되지 않는 방식입니다.  

- **특징**  
  - `require` 키워드를 사용해 모듈을 가져오고, `module.exports`를 통해 내보냅니다.  
  - 주로 서버측에서 사용되지만, 클라이언트 환경에서도 번들러를 통해 사용할 수 있습니다.  

- **사용 예시**  
```javascript
// CommonJS 예제
const moduleA = require('./moduleA');

module.exports = {
  sayHello: () => console.log('Hello, CommonJS!')
};
```

---

## 📌 ES Module (ESM)

- **정의**  
  자바스크립트의 **공식 표준 모듈 시스템**으로, ECMAScript 2015(ES6)부터 도입되었습니다.  
  브라우저와 Node.js 환경에서 모두 사용할 수 있으며, 모듈을 **비동기적으로 로드합니다.**  

- **특징**  
  - `import` 키워드를 사용하고, `export`를 사용하여 내보냅니다.  
  - 정적 분석이 가능하여 트리 쉐이킹과 같은 최적화 작업에도 유리합니다.  

- **사용 예시**  
```javascript
// ESM 예제
import { sayHello } from './moduleA';

export const greet = () => console.log('Hello, ESM!');
```

---

## 📊 차이점 비교

| 상황                              | CommonJS                     | ES Module (ESM)                   |
|---------------------------------|-----------------------------|----------------------------------|
| 모듈 로딩 방식                   | 동기적 로딩                  | 비동기적 로딩                     |
| 가져오기 방식                    | `require()` 사용             | `import` 사용                    |
| 내보내기 방식                    | `module.exports` 사용         | `export` 사용                    |
| 사용 환경                        | 주로 서버 측에서 사용         | 서버와 브라우저 모두에서 사용 가능 |
| 최적화                           | 트리 쉐이킹 지원 X           | ✅ 트리 쉐이킹 지원               |

---

## 📌 Node.js에서의 ESM 지원 증가

Node.js는 버전 12부터 네이티브로 ESM을 지원하기 시작했습니다. 브라우저와 서버 간의 모듈 호환성을 위해 풀스택 애플리케이션 개발에서도 ESM이 많이 사용되고 있습니다. 특히 ESM은 **비동기적 로딩과 트리 쉐이킹 같은 최적화 작업에 유리하다는 점에서 점점 더 선호되고 있습니다.**

