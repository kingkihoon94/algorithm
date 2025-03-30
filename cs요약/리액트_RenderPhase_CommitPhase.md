# React의 Render Phase와 Commit Phase

리액트의 렌더링 과정은 크게 두 가지 단계로 나눌 수 있습니다: **Render Phase**와 **Commit Phase**입니다.

## Render Phase
리액트가 변화된 **상태**나 **props**에 따라 어떤 UI가 변경되어야 할지를 결정하는 단계입니다.  
- **DOM을 업데이트하지 않고**, 변경사항을 **가상 DOM에서 계산하여 비교**합니다.  
- 순수하게 계산 과정이기 때문에, **성능에 영향을 주지 않도록 중단되거나 다시 실행될 수 있습니다.**  
- **React 18의 Concurrent Mode**를 통해 **비동기적으로 처리될 수도 있습니다.**  

## Commit Phase
실제로 변화된 UI를 **DOM에 반영하는 단계**입니다.  
- 리액트는 **가상 DOM에서 계산된 결과를 실제 DOM에 적용**하고, 변화된 UI를 브라우저에 렌더링합니다.  
- DOM 업데이트 이후에는 **useEffect**와 같은 사이드 이펙트를 발생시키는 훅들이 실행됩니다.  

### 요약
- **Render Phase:** 변화된 UI를 결정하는 **계산 과정** (중단 가능, 비동기 처리 가능)  
- **Commit Phase:** 계산된 결과를 실제로 DOM에 **반영하는 과정** (useEffect 실행 포함)  

---

## Render Phase와 Commit Phase가 동기화될 때의 특징

동기화될 때의 특징은 크게 두 가지로 설명할 수 있습니다: **단계적 진행**과 **병목 관리**입니다.

### 1. 단계적 진행
- Render Phase가 완료되면 리액트는 즉시 Commit Phase를 실행하지 않습니다.  
- 다른 높은 우선순위 작업이 있다면 **먼저 처리한 후** 나중에 Commit Phase를 실행할 수 있습니다.  
- 이러한 단계적 진행을 통해 React는 **동기화가 필요한 작업을 효율적으로 관리하여 사용자 경험을 개선**합니다.  

### 2. 병목 관리
- Render Phase에서 모든 변경 사항이 **Fiber Tree에 준비된 상태**로 Commit Phase로 넘어갑니다.  
- Render와 Commit 단계의 **일관성이 유지되며**, UI가 정확하게 동기화되고 **불필요한 재렌더링을 방지**합니다.  

---

React의 Render Phase와 Commit Phase는 각각의 역할을 분리하여 **성능 최적화**와 **유연한 렌더링**을 가능하게 해줍니다.  
특히 **Concurrent Mode**의 도입으로 인해 Render Phase가 비동기로 처리될 수 있어 **사용자 경험이 더욱 향상**되었습니다.
