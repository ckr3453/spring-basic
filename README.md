# spring-basic
스프링 핵심 원리 - 기본편 강의 예제 (출처 : [스프링 핵심 원리 - 기본 / 김영한](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%95%B5%EC%8B%AC-%EC%9B%90%EB%A6%AC-%EA%B8%B0%EB%B3%B8%ED%8E%B8))

## 비즈니스 요구사항과 설계
- 회원
  - 회원을 가입하고 조회할 수 있다.
  - 회원은 일반과 VIP 두 가지 등급이 있다.
  - 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)
- 주문과 할인 정책
  - 회원은 상품을 주문할 수 있다.
  - 회원 등급에 따라 할인 정책을 적용할 수 있다.
  - 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라. (나중에 변경 될 수 있다.)
  - 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수 도 있다. (미확정)
  
## 회원 도메인 설계
- 회원 도메인 요구사항
  - 회원을 가입하고 조회할 수 있다.
  - 회원은 일반과 VIP 두 가지 등급이 있다.
  - 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)

**회원 도메인 협력 관계**

![image](https://user-images.githubusercontent.com/36228833/184093472-af6fce15-d12d-4564-b8b9-dd716ad02907.png)

**회원 클래스 다이어그램**

![image](https://user-images.githubusercontent.com/36228833/184093616-850b86af-c53c-4f6b-8211-b6f713610d9b.png)

**회원 객체 다이어그램**

![image](https://user-images.githubusercontent.com/36228833/184093690-b8c11311-8e38-4916-9f47-f1c5e36dbae0.png)

## 주문과 할인 도메인 설계
- 주문과 할인 정책
  - 회원은 상품을 주문할 수 있다.
  - 회원 등급에 따라 할인 정책을 적용할 수 있다.
  - 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라. (나중에 변경 될 수 있다.)
  - 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수 도 있다. (미확정)
  
**주문 도메인 협력, 역할, 책임**

![image](https://user-images.githubusercontent.com/36228833/184354707-b14658e4-3bf5-4db7-b88a-0274c4af9814.png)

**1. 주문 생성:** 클라이언트는 주문 서비스에 주문 생성을 요청한다.<br/>
**2. 회원 조회:** 할인을 위해서는 회원 등급이 필요하다. 그래서 주문 서비스는 회원 저장소에서 회원을 조회한다.<br/>
**3. 할인 적용:** 주문 서비스는 회원 등급에 따른 할인 여부를 할인 정책에 위임한다.<br/>
**4. 주문 결과 반환:** 주문 서비스는 할인 결과를 포함한 주문 결과를 반환한다.

> 참고: 실제로는 주문 데이터를 DB에 저장하겠지만, 예제가 너무 복잡해 질 수 있어서 생략하고, 단순히 주문 결과를 반환한다.

**주문 도메인 전체**

![image](https://user-images.githubusercontent.com/36228833/184354963-2b940b9c-bb6b-41f8-a789-be652b73d833.png)

**역할과 구현을 분리**해서 자유롭게 구현 객체를 조립할 수 있게 설계했다. 덕분에 회원 저장소는 물론이고, 할인 정책도 유연하게 변경할 수 있다.

**주문 도메인 클래스 다이어그램**

![image](https://user-images.githubusercontent.com/36228833/184355283-46d14bf6-e50b-43e7-b321-6cdbfa8ddc36.png)

**주문 도메인 객체 다이어그램1**

![image](https://user-images.githubusercontent.com/36228833/184355341-93c7fff1-3f6e-4e3f-b2e4-9334f253d5bf.png)

회원을 메모리에서 조회하고, 정액 할인 정책(고정 금액)을 지원해도 주문 서비스를 변경하지 않아도 된다. 역할들의 협력 관계를 그대로 재사용 할 수 있다.

**주문 도메인 객체 다이어그램2**

![image](https://user-images.githubusercontent.com/36228833/184355373-164c6b80-b0ff-40d8-9f67-644450173223.png)

회원을 메모리가 아닌 실제 DB에서 조회하고, 정률 할인 정책(주문 금액에 따라 % 할인)을 지원해도 주문 서비스를 변경하지 않아도 된다.
협력 관계를 그대로 재사용 할 수 있다.

### 새로운 할인 정책 개발

**기존 요구사항 변경**

할인 정책을 [고정 금액 할인](#비즈니스-요구사항과-설계)이 아닌 금액당 할인하는 정률% 할인으로 변경해야함. 이를 위해 기존의 FixDiscountPolicy를 대체할 RateDiscountPolicy를 구현

![image](https://user-images.githubusercontent.com/36228833/184383609-7de0b82b-209c-4ce4-8a14-da0ef3ccdb8a.png)

### 새로운 할인 정책 적용과 문제점
**방금 추가한 할인 정책을 적용해보자.**

할인 정책을 변경하려면 클라이언트인 OrderServiceImpl 코드를 고쳐야 한다.
```java
public class OrderServiceImpl implements OrderService {
  // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
  private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
}
```
**문제점 발견**

- 우리는 역할과 구현을 충실하게 분리했다. -> OK 
- 다형성도 활용하고, 인터페이스와 구현 객체를 분리했다. -> OK 
- OCP, DIP 같은 객체지향 설계 원칙을 충실히 준수했다 
  - 그렇게 보이지만 사실은 아니다.
- DIP: 주문서비스 클라이언트( OrderServiceImpl )는 DiscountPolicy 인터페이스에 의존하면서 DIP를 지킨 것 같은데?
  - 클래스 의존관계를 분석해 보자. 추상(인터페이스) 뿐만 아니라 **구체(구현) 클래스에도 의존**하고 있다.
    - 추상(인터페이스) 의존: DiscountPolicy
    - 구체(구현) 클래스: FixDiscountPolicy , RateDiscountPolicy
- OCP: 변경하지 않고 확장할 수 있다고 했는데!
  - **지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다!** 따라서 **OCP를 위반**한다.
  
**왜 클라이언트 코드를 변경해야 할까?**

클래스 다이어그램으로 의존관계를 분석해보자.

**기대했던 의존관계**

![image](https://user-images.githubusercontent.com/36228833/184388955-250c4c1e-a434-4364-8887-f02acca96377.png)

지금까지 단순히 DiscountPolicy 인터페이스만 의존한다고 생각했다.

**실제 의존관계**

![image](https://user-images.githubusercontent.com/36228833/184389100-2b3c7753-0839-442a-8b73-dd468173f64e.png)


잘보면 클라이언트인 OrderServiceImpl 이 DiscountPolicy 인터페이스 뿐만 아니라 FixDiscountPolicy 인 구체 클래스도 함께 의존하고 있다. 
실제 코드를 보면 의존하고 있다! -> **DIP 위반**

**정책 변경**

![image](https://user-images.githubusercontent.com/36228833/184390319-2a0278e9-bd18-4b7f-9184-cdb78f012252.png)

**중요!:** 그래서 FixDiscountPolicy 를 RateDiscountPolicy 로 변경하는 순간 OrderServiceImpl 의소스 코드도 함께 변경해야 한다! **OCP 위반**

**어떻게 문제를 해결할 수 있을까?**

- 클라이언트 코드인 OrderServiceImpl 은 DiscountPolicy 의 인터페이스 뿐만 아니라 구체 클래스도 함께 의존한다.
- 그래서 구체 클래스를 변경할 때 클라이언트 코드도 함께 변경해야 한다.
- **DIP 위반** -> 추상에만 의존하도록 변경(인터페이스에만 의존)
- DIP를 위반하지 않도록 인터페이스에만 의존하도록 의존관계를 변경하면 된다.

**인터페이스에만 의존하도록 설계를 변경하자**

![image](https://user-images.githubusercontent.com/36228833/184390383-4fc39b13-09a1-4763-a41c-588409a52407.png)

**인터페이스에만 의존하도록 코드 변경**

```java
public class OrderServiceImpl implements OrderService { 
  //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
  private DiscountPolicy discountPolicy;
}
```

- 인터페이스에만 의존하도록 설계와 코드를 변경했다.
- **그런데 구현체가 없는데 어떻게 코드를 실행할 수 있을까?**
- 실제 실행을 해보면 NPE(null pointer exception)가 발생한다.

**해결방안**
- 이 문제를 해결하려면 누군가가 클라이언트인 OrderServiceImpl 에 DiscountPolicy 의 구현 객체를 대신 생성하고 주입해주어야 한다.

## 관심사의 분리
- 애플리케이션을 하나의 공연이라 생각해보자. 각각의 인터페이스를 배역(배우 역할)이라 생각하자. 그런데! 실제 배역 맞는 배우를 선택하는 것은 누가 하는가?
- 로미오와 줄리엣 공연을 하면 로미오 역할을 누가 할지 줄리엣 역할을 누가 할지는 배우들이 정하는게 아니다. 이전 코드는 마치 로미오 역할(인터페이스)을 하는 레오나르도 디카프리오(구현체, 배우)가 줄리엣 역할(인터페이스)을 하는 여자 주인공(구현체, 배우)을 직접 초빙하는 것과 같다. 디카프리오는 공연도 해야하고 동시에 여자 주인공도 공연에 직접 초빙해야 하는 **다양한 책임**을 가지고 있다.

**관심사를 분리하자**

- 배우는 본인의 역할인 배역을 수행하는 것에만 집중해야 한다.
- 디카프리오는 어떤 여자 주인공이 선택되더라도 똑같이 공연을 할 수 있어야 한다.
- 공연을 구성하고, 담당 배우를 섭외하고, 역할에 맞는 배우를 지정하는 책임을 담당하는 별도의 **공연 기획자**가 나올시점이다.
- 공연 기획자를 만들고, 배우와 공연 기획자의 책임을 확실히 분리하자.

### AppConfig 등장
- 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, **구현 객체를 생성**하고, **연결**하는 책임을 가지는 별도의 설정 클래스를 만들자.
- AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.

**그림 - 클래스 다이어그램**

![image](https://user-images.githubusercontent.com/36228833/184391074-424110f9-2384-4956-98d5-288b37c208cf.png)

- 객체의 생성과 연결은 AppConfig 가 담당한다.
- **DIP 완성:** MemberServiceImpl 은 MemberRepository 인 추상에만 의존하면 된다. 이제 구체 클래스를 몰라도 된다.
- **관심사의 분리:** 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.

**그림 - 회원 객체 인스턴스 다이어그램

![image](https://user-images.githubusercontent.com/36228833/184391280-b26474e0-547c-4f77-bc66-0f5866ae8a08.png)

- appConfig 객체는 memoryMemberRepository 객체를 생성하고 그 참조값을 memberServiceImpl 을생성하면서 생성자로 전달한다.
- 클라이언트인 memberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI(Dependency Injection) 우리말로 의존관계 주입 또는 의존성 주입이라 한다.

**정리**
- AppConfig를 통해서 관심사를 확실하게 분리했다.
- 배역, 배우를 생각해보자.
- AppConfig는 공연 기획자다.
- AppConfig는 구체 클래스를 선택한다. 배역에 맞는 담당 배우를 선택한다. 애플리케이션이 어떻게 동작해야 할지 전체 구성을 책임진다.
- 이제 각 배우들은 담당 기능을 실행하는 책임만 지면 된다.
- OrderServiceImpl 은 기능을 실행하는 책임만 지면 된다.
