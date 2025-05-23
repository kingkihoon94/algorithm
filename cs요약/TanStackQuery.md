# TanStack Query 개요 및 장단점

TanStack Query는 서버 상태 관리의 복잡성을 극복하기 위해 사용하는 라이브러리입니다. 여기서 "서버 상태"란 서버에서 제공하는 데이터로, 클라이언트에서 직접 수정할 수 없고 비동기 네트워크 요청을 통해 가져오거나 갱신해야 하는 데이터를 의미합니다.

## 주요 장점

### 1. 효율적인 캐싱 처리

* 동일한 데이터를 반복적으로 요청하지 않아 **네트워크 비용을 절감**할 수 있습니다.
* **캐싱된 데이터를 즉시 제공**하여 사용자 경험이 향상됩니다.

### 2. 비동기 데이터 관리 간소화

* 데이터의 가져오기(fetch), 갱신(refetch), 무효화(invalidate) 등을 **선언적으로 처리**할 수 있어 코드가 간결해집니다.

### 3. 에러 및 로딩 상태 관리 단순화

* `useQuery()`, `useMutation()` 훅을 통해 **로딩, 성공, 실패 상태**를 명확하게 처리할 수 있습니다.

> ✅ TanStack Query는 서버 상태 관리에서 발생하는 복잡한 문제를 해결하고 개발자가 **비즈니스 로직에 더 집중**할 수 있도록 도와줍니다.

## 단점 및 한계 🤔

### 1. 캐싱 전략 설정의 복잡성

* `staleTime`, `gcTime` 등의 옵션 설정에 따라 **데이터 신선도와 요청 빈도**가 달라지므로 신중하게 설계해야 합니다.
* 잘못 설정하면 **오래된 데이터 노출** 또는 **불필요한 네트워크 요청**이 발생할 수 있습니다.

### 2. 초기 학습 곡선 존재

* Query Key 설계, 캐시 무효화 전략 등 다양한 개념을 이해해야 하며, 초보자에게는 **진입 장벽**이 될 수 있습니다.

### 3. 클라이언트 상태와의 통합 한계

* 서버 상태와 클라이언트 상태가 강하게 연결된 구조에서는 **TanStack Query만으로 한계**가 있으며, 이 경우 Redux, Zustand 등의 별도 상태 관리 라이브러리와의 **병행 사용**이 필요할 수 있습니다.

## 결론

TanStack Query는 서버 상태 관리의 복잡도를 낮추고, 선언적이고 간결한 API를 통해 네트워크 데이터를 효율적으로 처리할 수 있게 해주는 강력한 도구입니다. 다만, **적절한 설정과 사용법에 대한 학습**이 선행되어야 하며, 복잡한 애플리케이션에서는 **다른 상태 관리 도구와의 조합**이 필요할 수 있습니다.
