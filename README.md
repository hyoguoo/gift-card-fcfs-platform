# Gift Card FCFS Platform

Gift Card FCFS Platform은 **요청이 집중되는 환경**에서도 분산된 서비스 간 흐름의 안정성을 유지할 수 있도록 설계되었습니다.  
기프트 카드 구매 과정에서 발생할 수 있는 재고 처리 경쟁과 오류 상황에 대응하기 위해, Redis와 Kafka 기반의 분산 환경 주문 및 결제 흐름을 구현하는 것을 목표로 하였습니다.

_이미지가 표시되지 않거나 오류 페이지가 나타날 경우, 브라우저를 새로고침(F5) 해주세요._

<br>

## 🚀 프로젝트 목표

1. **집중 요청 처리 안정성 확보**: 짧은 시간 내 몰리는 사용자 요청 상황에서 재고 처리의 정확성과 서비스 안정성 유지
2. **신뢰 가능한 재고 관리**: Redis와 Kafka를 활용해 빠르고 일관된 재고 상태 유지
3. **트랜잭션 흐름 복원력 확보**: SAGA 기반 보상 트랜잭션 설계를 통해 오류 발생 시 상태 복구 가능하도록 구성

<br>

## 🏗 프로젝트 아키텍처

![Architecture Diagram](https://github.com/user-attachments/assets/aad20f89-69af-489d-b291-d5c9289991d9)

### 🛠 사용 기술 스택

- Java 21
- Spring Boot 3.3.5
- MySQL 8.0.33
- Redis 7.0.11
- Kafka
- Spring Cloud 2023.0.3

<details>
<summary>📌 스택 선정 이유</summary>

- Redis: 실시간 재고 관리 및 활성 사용자 상태 캐싱으로 **빠르고 안정적인 데이터 접근** 제공 가능
- Kafka: 높은 트래픽을 처리하며 **서비스 간 데이터 동기화 및 트랜잭션 관리**에 적합
- Spring Cloud: 모든 서비스가 Spring 기반으로 개발되고 있어 기존 기술 스택과의 높은 호환성과 생산성을 제공하여 선택

</details>

### 🧩 주요 서비스

|      **서비스**      |             **역할 요약**             |
|:-----------------:|:---------------------------------:|
|    API Gateway    | 인증 처리, 사용자 활성 상태 확인 (JWT + Redis) |
|   User Service    |    로그인 상태 Redis 캐싱, 사용자 정보 관리     |
| Gift Card Service |   기프트 카드 생성 / 재고 관리 / 사용 내역 관리    |
|   Order Service   |            주문 생성 및 관리             |
|  Payment Service  |        결제 처리와 외부 결제 API 연동        |

<br>

## 🔑 핵심 주요 기능

### 📦 1. **재고 관리**

![Stock Management Diagram](https://github.com/user-attachments/assets/8f7e6f5a-1e74-49f8-baab-20b2c8f015e6)

- **원자적 재고 감소 및 보상 트랜잭션**: Redis와 Lua Script를 활용해 **재고 감소를 원자적으로 처리**
- **Kafka 기반 재고 이벤트 전파**: 재고 변동 시 Kafka를 통해 이벤트를 발행하고, gift-card 서비스는 이를 수신하여 DB의 재고 데이터를 Redis 캐시와 동기화

<details>
<summary>📌 Redis + Lua Script vs 분산락</summary>

|         **방식**         | **장점**                                                                             | **단점**                                                                            |
|:----------------------:|:-----------------------------------------------------------------------------------|:----------------------------------------------------------------------------------|
| **Redis + Lua Script** | - 원자적 연산 가능: Lua Script로 재고 감소와 검증을 한 번에 처리<br>- 고성능: 단일 Redis 인스턴스에서 처리되므로 속도가 빠름 | - 단일 실패 지점: Redis가 단일 장애 지점(SPOF)이 될 수 있음<br>- 복잡한 복구: Lua Script 오류 발생 시 디버깅 어려움 |
|        **분산락**         | - 명시적 락 관리 가능: 재고 감소 처리에서 충돌을 최소화                                                  | - 높은 지연 시간: 락 획득과 해제 과정에서 성능 저하 가능<br>- 복잡성 증가: 분산락 구현 및 관리가 복잡                   |

- 대규모 트래픽 처리와 높은 성능을 위해 Redis + Lua Script를 선택
- [성능 비교](#재고-관리-전략-성능-비교-테스트)

</details>

<br>

### 🔄 2. **SAGA 기반 트랜잭션 복구 흐름**

- **Kafka 기반 이벤트 트리거**: 결제 및 재고 감소와 같은 이벤트를 Kafka를 통해 각 서비스로 전파하며, 서비스들은 이벤트에 맞춰 상태를 업데이트하여 **최종 트랜잭션 결과를 일관되게 유지**
- **보상 트랜잭션 수행**: 결제 실패 시 Order Info 실패 상태로 변경, Redis 재고 복구, 주문 취소와 같은 보상 트랜잭션을 수행하여 트랜잭션의 **안정성과 일관성 확보**

<details>
<summary>📌 Orchestration vs Choreography</summary>

|      **방식**       | **장점**                                                              | **단점**                                                                  |
|:-----------------:|:--------------------------------------------------------------------|:------------------------------------------------------------------------|
| **Orchestration** | - 중앙 제어: 중앙 서비스가 전체 트랜잭션 흐름을 관리<br>- 디버깅 용이: 트랜잭션 상태를 한 곳에서 모니터링 가능 | - 단일 실패 지점: 오케스트레이터 장애 시 트랜잭션 진행 불가<br>- 복잡성 증가: 오케스트레이터의 로직이 복잡해질 수 있음 |
| **Choreography**  | - 분산 처리: 각 서비스가 독립적으로 이벤트를 처리<br>- 확장성: 서비스 간 결합도가 낮아 유연한 확장이 가능    | - 디버깅 어려움: 트랜잭션 상태 추적이 어려움<br>- 복잡성 증가: 이벤트 흐름을 이해하고 관리하기 어려움           |

- 현재 서비스 간 직접적으로 SAGA 패턴을 활용하여 Choreography 방식을 채택
- 비록 디버깅과 상태 추적이 어려울 수 있지만, 각 서비스 간 이벤트 흐름과 상태 전파를 명확히 설계하여 보완

</details>

<br>

### 🔗 3. **서비스 간 데이터 동기화**

![Data Synchronization Diagram](https://github.com/user-attachments/assets/37d695dd-b757-4553-9e40-a54a98e7ec0a)

- **Kafka 기반 데이터 동기화**: Kafka 이벤트 메시징 시스템을 사용하여 공통 데이터가 필요한 **여러 서비스 간 데이터 동기화 구현**
- **변경 이벤트 기반 업데이트**: 각 서비스에서 데이터베이스 상태가 변경될 때마다 이벤트를 발행하며, 이를 통해 다른 서비스들이 필요한 업데이트를 수신해 데이터 일관성 유지
- **기프트 카드 구매 정보 동기화 예시**
    1. GiftCard 서비스에서 사용자의 구매 발생 시 Kafka 이벤트 발행
    2. 이벤트를 구독한 User 서비스는 해당 이벤트를 수신하여, 해당 서비스의 DB에 반영

<details>
<summary>📌CDC(Change Data Capture) vs Kafka 기반 이벤트</summary>

|      **방식**      | **장점**                                                                     | **단점**                                                                            |
|:----------------:|:---------------------------------------------------------------------------|:----------------------------------------------------------------------------------|
|     **CDC**      | - 실시간 동기화 가능: DB 로그 기반으로 데이터 변경 사항 감지<br>- 변경 최소화: 기존 DB 구조에 직접적인 변경 필요 없음 | - 초기 설정 복잡: CDC 도구 설치 및 설정이 복잡할 수 있음<br>- 성능 이슈: DB 변경 로그를 지속적으로 모니터링하므로 DB 부하 증가 |
| **Kafka 기반 이벤트** | - 유연성: 이벤트를 자유롭게 설계 가능하며, 특정 서비스에 최적화 가능<br>- 확장성: 대규모 트래픽을 안정적으로 처리 가능    | - 이벤트 손실 가능성: 구성 관리 실패 시 데이터 손실 위험<br>- 초기 구현 비용: 이벤트 설계 및 처리 로직 구축 필요            |

- Kafka의 확장성과 유연성 덕분에 대규모 트래픽을 처리할 수 있으며, 데이터 동기화에도 적합하다 판단하여 Kafka 기반 이벤트 방식을 선택

</details>

<br>

### 🔐 4. **API Gateway 기반 사용자 인증**

![API Gateway Diagram](https://github.com/user-attachments/assets/b22ab787-5f94-48a2-be3a-c4e5fd9cbd77)

- 문제: 모든 요청마다 User 서비스에 요청하여 인증 처리 시 부하 증가 및 응답 지연 문제 발생 가능성 존재
- 해결 방향: API 게이트웨이에서 JWT 디코딩 + Redis 조회를 통해 빠르게 사용자 상태를 검증하여 별도 서비스 호출 없이 인증 처리

<details>
<summary>📌 API 게이트웨이 직접 처리 방식 vs User Service 통해 조회</summary>

|         **방식**         | **장점**                                                                                                   | **단점**                                                                                        |
|:----------------------:|:---------------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------|
| **API 게이트웨이 직접 처리 방식** | - User Service 부하 감소: 모든 요청이 User Service를 경유하지 않음<br>- 실시간 상태 반영 가능: Redis 캐시를 업데이트하여 로그아웃이나 비활성화 상태 반영 | - Redis 캐시의 추가 관리 필요: 캐시 만료나 동기화 문제 발생 가능<br>- 데이터 정합성 이슈 가능: Redis와 DB 간 동기화 실패 시 데이터 불일치 가능 |
| **User Service 직접 조회** | - 데이터 정확성 보장: 항상 최신 데이터 조회 가능<br>- 캐시 동기화 문제 없음: Redis를 사용하지 않으므로 관리 필요 없음                               | - 높은 부하: 모든 요청이 User Service를 경유하여 성능 저하 가능<br>- 확장성 부족: 트래픽 증가 시 병목 현상 발생 가능                 |

- Redis 기반 인증 처리가 더 높은 성능을 제공하며, **별도 서비스 호출하지 않고 처리할 수 있어** 대규모 트래픽 환경에 적합하다고 판단

</details>

<br>

### 🛡 5. **Feign Client 및 Error Decoder를 통한 예외 처리 세분화**

- **Feign Client를 통한 서비스 간 통신 표준화**: Feign Client를 사용해 내부 서비스 간 통신을 표준화된 인터페이스로 구현하여 서비스 간의 상호작용을 간결하고 일관되게 유지
- **Error Decoder를 통한 예외 처리 세분화**: 서비스 호출 시 발생하는 오류를 Error Decoder로 각 도메인에 맞는 커스텀 예외로 변환

<br>

## 🧪 테스트 및 성능 검증

<details>
<summary>📌 테스트 사양</summary>

- **서버 사양**: M2 Pro 6/8 Core Apple Avalanche 3.49 GHz + 4 Core Apple Blizzard 2.42 GHz / 32GB
    - Docker 컨테이너 기반 / Spring Boot 서비스에 대해 CPU 최대 사용량 1.0, 메모리 최대 사용량 1.0GB 제한
- **부하 발생 장비 사양**: AMD Ryzen 5 7600 6-Core Processor 12-CPUs ~3.8GHz / 16GB

</details>

### **재고 관리 전략 성능 비교 테스트**

<details>
<summary>📌 테스트 구성</summary>

- **테스트 도구**: JMeter
- **기준 데이터**: 주문 요청 60,000건 / 재고 수량 50,000건
- **테스트 설정**:
    - **Number of Threads**: 6,000
    - **Loop Count**: 10
    - **Ramp-up Period**: 20

</details>

- **테스트 목표**: 분산락 방식, Lua Script 기반 재고 관리 등 다양한 전략에 대해 **대규모 요청을 처리량을 비교**하여 **최적의 재고 관리 방식 선정**
- **테스트 시나리오**:
    1. 주문 생성 요청
    2. 1-3s 대기
    3. 결제 요청(토스 승인 요청은 Mock 객체로 100-300ms 네트워크 지연 시뮬레이션)
- **테스트 결과**:

![Performance Test Result](https://github.com/user-attachments/assets/67da1821-3fb4-4afb-844d-9c7e4d558d8f)

|    테스트 항목    | **분산락 전체 범위 | **분산락 최소 범위 | Lua Script | Lua Script - **Server 2EA |
|:------------:|:-----------:|:-----------:|:----------:|:-------------------------:|
|  총 소요 시간(s)  |    2863     |     195     |    191     |            193            |
| 평균 응답 시간(ms) |    18915    |    1795     |    1701    |           2344            |
|     TPS      |    20.97    |   307.02    |   313.99   |          310.94           |
|   **처리 누락    |      0      |    1109     |    674     |           3228            |
|  **승인 요청 누락  |      0      |    1991     |    2098    |           2878            |
|  **락 획득 실패   |    53219    |     23      |     0      |             0             |
|   **재고 실패    |      0      |    6877     |    7228    |           3894            |
|      성공      |    6781     |    50000    |   50000    |           50000           |
|  재고 대비 성공률   |   13.56%    |   100.00%   |  100.00%   |          100.00%          |

<details>
<summary>📌 ** 설명</summary>

**분산락 전체 범위: 결제 승인 요청 로직 전체 범위에 분산락 적용  
**분산락 최소 범위: 결제 승인 요청 로직 중 재고 감소 로직에만 분산락 적용  
**Server 2EA: 주문 및 승인을 처리하는 `Order Service`, `Payment Service`를 2대 서버로 테스트  
**처리 누락: 서버에서 요청 받지 못한 수
**승인 요청 누락: 주문 생성 요청으로 결제 정보가 생성 됐지만 해당 주문 건에 승인 요청 오지 않은 수
**락 획득 실패: 분산락을 획득하지 못한 수  
**재고 실패: 재고 부족으로 결제 승인 요청이 실패한 수

</details>

- **Lua Script 기반 처리**: 평균 응답 시간 3376ms, TPS 119.31로 가장 효율적인 성능 제공
    - 분산락 전체 범위 대비: **평균 응답 시간 91.0% 감소**(18,915ms → 1,701ms) / **TPS 1,397.3% 증가**(20.97 → 313.99)
    - 분산락 최소 범위 대비: **평균 응답 시간 5.3% 감소**(1,795ms → 1,701ms) / **TPS 2.3% 증가**(307.02 → 313.99)
- **락 경쟁 제거**: Lua Script 사용 시 락 경쟁이 발생하지 않아 **락 획득 실패 건수 최대 53219건 → 0건으로 제거**
- **재고 대비 성공률**: 모든 Lua Script 테스트 항목에서 100% 성공률 유지
- **Server 1EA → 2EA 성능 변화**: 서버 추가 시 평균 응답 시간이 증가(1701ms → 2344ms, 37.8% 증가) / TPS는 유의미한 변화 없음(313.99 → 310.94)

#### 결론

**Lua Script 기반 재고 관리** 방식 선택

- **성능 최적화**: 대기로 인한 락 획득 실패 케이스를 제거해 평균 **응답 시간 단축 및 초당 처리 속도 증가의 이점** 존재
- **안정성 확보**: 애플리케이션 레벨의 락 관리 없이 Redis에서 요청을 순차적으로 처리하여 **처리 누락 감소 및 일관된 성능 유지 기대** 가능
- **간결한 구현**: Redis의 원자적 작업 처리로 애플리케이션 레벨에서 복잡한 락 관리 불필요 및 **코드 복잡도 감소**

<details>
<summary>📌 서버 2EA에서의 성능 저하</summary>

초당 처리 속도가 아주 약간 낮고, 평균 응답 시간이 더 높은 결과를 보이는데, 아래와 같은 이유로 인한 것으로 추측

- **로컬 테스트 환경의 한계**: CPU 및 메모리가 제한적이므로 새로운 서버가 추가되어도 물리적인 리소스 제한으로 성능 향상이 제한됨
- **Redis 자체 성능의 한계**: 싱글 스레드로 동작하면서 원자적으로 실행되기 때문에 단일 Redis 서버에서 처리 가능한 요청 수가 한계 존재

이러한 결과는 테스트 환경의 제약에서 비롯된 것으로 보이며, 실제 분산 환경에서 추가적인 검증이 필요

</details>

<br>

## 💳 메인 기능 플로우 - 주문 및 결제

### 1. 주문 생성 단계

![Event Flow Diagram](https://github.com/user-attachments/assets/554ca29f-efb3-4719-90e2-cd65d8459814)

1. **Gift Card 조회**: gift-card 서비스에서의 동기적 gift card 정보 조회
2. **주문 정보 저장**: `PENDING` 상태로 주문 정보 저장
3. **결제 요청**: payment 서비스에 동기적 요청 전송 및 `READY` 상태로 결제 이벤트 저장

### 2. 주문 승인 요청 단계

![Event Flow Diagram](https://github.com/user-attachments/assets/d73d7203-27df-4a16-b547-e716faf0bb5b)

1. **결제 이벤트 조회**: 저장된 payment event 정보 불러오기
2. **결제 상태 갱신**: 결제 이벤트의 `IN_PROGRESS` 상태로의 변경
3. **재고 감소 처리**:
    - **Redis 재고 감소**: 원자적 Redis 재고 확인 및 차감
    - **재고 감소 성공 시**:
        - **메시지 발행**: gift-card 서비스로의 메시지 발행 및 DB 재고 차감
    - **재고 감소 실패 시**:
        - **결제 실패 처리**: `FAIL` 상태로의 payment event 변경 및 주문 실패 메시지 발행 및 order 서비스에서 `FAILED` 상태로의 업데이트
4. **토스 결제 정보 조회 및 교차 검증**:
    - 검증 실패 시:
        - **재고 복구**: Redis 재고 복구 및 gift-card 서비스로의 메시지 발행을 통한 DB 재고 증가
5. **토스 결제 승인 요청**:
    - **재시도 가능 실패 시**: `UNKNOWN` 상태로의 payment event 변경
    - **재시도 불가능 실패 시**:
        - **재고 복구 및 결제 실패 처리**: Redis 재고 복구 및 gift-card 서비스로의 메시지 발행을 통한 DB 재고 복구, `FAIL` 상태로의 payment event 변경 및 주문 실패
          메시지 발행을 통한 order 서비스의 `FAILED` 상태로의 업데이트
6. **결제 승인 성공 시**:
    - **결제 완료 처리**: `DONE` 상태로의 payment event 변경 및 주문 성공 메시지 발행을 통한 order 서비스의 `COMPLETED` 상태로의 업데이트
    - **기프트 카드 구매 데이터 동기화**:
        - gift-card 서비스로의 메시지 수신을 통한 `gift_card_user` 테이블 데이터 저장 및 user 서비스로의 메시지 발행을 통한 `user_gift_card` 데이터 동기화

<br>

## 🚧 프로젝트 한계점 및 개선 방향

### 1. Redis 단일 장애 지점(SPOF) 및 성능 한계

- Redis를 단일 노드로 구성했기 때문에 장애 발생 시 서비스 전체가 중단될 위험이 존재하며, 높은 트래픽 처리 시 성능 한계에 도달할 수 있음
- **개선 방향**: Redis Cluster 또는 Sentinel을 도입하여 가용성을 확보하고, Sharding을 통해 요청을 분산 처리하여 성능 한계를 극복

### 2. 결제 완료 후 사용자 경험 딜레이

- Kafka를 통해 결제 완료 후 메시지를 발행하여 사용자에게 기프트 카드가 발행되기까지 딜레이가 발생
- **개선 방향**: Kafka Consumer에서 Batch 처리와 Bulk Insert를 도입하여 대량 데이터 처리 속도 개선

### 3. 장애 대응 부족

- 장애 상황에서 서비스 간 트랜잭션 흐름을 정확히 추적할 수 없어 복구에 시간이 걸릴 수 있음
- **개선 방향**: 중앙집중식 로깅 시스템(예: ELK 스택)과 분산 트레이싱 도구(예: Zipkin, Jaeger)를 도입하여 서비스 간 이벤트 흐름과 장애 원인을 실시간으로 추적 가능하게 개선

### 4. 로컬 Docker 환경의 한계

- 프로젝트 테스트 환경이 로컬 Docker 컨테이너로 구성되어 실제 대규모 분산 환경을 완전히 시뮬레이션하지 못함
- **개선 방향**: 클라우드 기반 인프라를 활용하여 분산 환경과 확장성을 보다 현실적으로 검증할 수 있는 테스트 환경 구축
