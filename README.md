
# ⚖️ Load Balancing Architecture

## 🧩 개요
본 서비스는 **AWS EC2 인스턴스 두 대(Spring1, Spring2)** 를 대상으로 **Nginx Load Balancer**를 구성하여 트래픽을 분산 처리하는 구조로 설계되었습니다.  
클라이언트(Web, Mobile) 요청은 `Route53`을 통해 도메인으로 접근하며, Nginx가 각 Spring 서버로 요청을 분배합니다.

---

## ☁️ 인프라 구성도

![슬라이드3](https://github.com/user-attachments/assets/21b71112-ea81-4039-90f5-0a85f7ff6d22)

---

## ⚙️ 구성 요소

| 구성 요소 | 역할 |
|------------|------|
| **Route53** | 도메인 라우팅 및 트래픽 전달 |
| **Nginx (EC2)** | 리버스 프록시 및 로드 밸런서 역할 수행 |
| **Spring Boot (EC2 x2)** | 애플리케이션 서버 (Docker 기반 실행) |
| **PostgreSQL** | 주요 데이터 저장소 |
| **Redis** | 세션 및 캐시 관리 |
| **Amazon S3** | 정적 파일(이미지 등) 저장 |
| **GitHub Actions** | CI/CD 자동 배포 파이프라인 구성 |
| **Git-Secret** | AWS 인증 정보 및 환경 변수 암호화 관리 |

---

## ⚖️ 로드밸런싱

###  🔽 Nginx 설치 
```
# 설치
sudo apt install nginx

# 파일 만들기
sudo vim /etc/nginx/sites-available/[앱 이름]
```

### 🔧 Nginx 설정
```
/etc/nginx/sites-available/[앱 이름]-loadbalancer
```

``` nginx
upstream [앱 이름] {
	# 요청을 서버 순서대로 균등하게 분배하는 방식
        least_conn;
	# 차례대로 할꺼면 아무것도 안쓰기

	# 클라이언트 IP를 해싱하여 항상 같은 서버로 요청 전달
	      # ip_hash;

        server ipaddress1:80;  # 첫 번째 EC2
        server ipaddress2:80;  # 두 번째 EC2
}

server {
        listen 80;

        location / {
                proxy_pass http://[앱 이름];
                proxy_set_header Host $host;
                proxy_set_header X-Real-IP $remote_addr;
    }
}
```

--- 
✔️ 적용/상태 확인

```
# 기존 링크 삭제
sudo rm /etc/nginx/sites-enabled/default

# 만든 파일 링크 걸어주기
sudo ln -s /etc/nginx/sites-available/[이름] /etc/nginx/sites-enabled/

# 확인
ls -l /etc/nginx/sites-enabled/

# 설정한 파일에 문제 체크
sudo nginx -t
```

## 🔄 트래픽 흐름

1. 사용자가 **Web(Chrome)** 또는 **Mobile(React Native)** 앱을 통해 요청 전송  
2. `Route53`이 요청을 **Nginx 로드밸런서**로 전달  
3. Nginx가 `least_conn` 방식으로 두 EC2(Spring Boot 서버) 중 하나로 요청 분배  
4. 각 서버는 Docker 컨테이너 내에서 Spring Boot 애플리케이션 실행  
5. 애플리케이션은 **PostgreSQL**, **Redis**, **S3** 등 외부 리소스와 연동  
6. 응답은 Nginx를 거쳐 클라이언트로 반환  

---

## 🧱 CI/CD 파이프라인

1. 개발자가 로컬에서 코드 수정 후 GitHub에 **push**  
2. **GitHub Actions**가 트리거되어 `deploy.yml` 실행  
3. `Dockerfile`을 기반으로 이미지 빌드 및 배포  
4. `Git-Secret`을 사용해 민감한 환경 변수 및 AWS IAM Key를 안전하게 관리  

> 배포는 두 개의 EC2(Spring1, Spring2)에 동일하게 반영되어  
> Nginx 로드밸런싱 환경에서 무중단 서비스가 가능하도록 구성되어 있습니다.

---

## 🧩 확장성 및 장애 대응

- 새로운 EC2(Spring) 인스턴스 추가 시, Nginx `upstream` 블록에 서버 정보를 추가하는 것만으로 확장이 가능합니다.
- 특정 서버 장애 발생 시, Nginx가 자동으로 요청을 다른 서버로 우회하여 **서비스 중단 최소화**가 가능합니다.
- Stateless 구조로 설계되어, **세션은 Redis**에 저장되어 서버 간 공유가 가능합니다.

---

## 🛠️ 사용 기술 스택

| 영역 | 기술 |
|------|------|
| Infra | AWS EC2, Route53, S3, IAM |
| Backend | Spring Boot, Spring Security, JWT |
| Database | PostgreSQL |
| Cache | Redis |
| Proxy / LB | Nginx |
| CI/CD | GitHub Actions, Docker |
| Client | React Native, Web (Chrome) |

---

## 📈 기대 효과

- ✅ **부하 분산**을 통한 안정적인 트래픽 처리를 할 수 있습니다. 
- ✅ **서버 장애 시 자동 우회**로 가용성 향상이 가능 합니다.
- ✅ **CI/CD 자동화**로 배포 속도 및 일관성을 확보 할 수 있습니다.
- ✅ **Stateless 아키텍처**로 확장성을 강화 할 수 있습니다.


##  🛠️ Trouble Shooting

#### **( 1 )** 개인 API 키 및 시크릿 노출

🌩문제 상황🌩  
기존 코드에서는 개인 API 키와 시크릿 키가 하드코딩되어 있어 보안상 노출될 위험이 있었습다.

<img width="1364" height="894" alt="로드밸런싱 트러블 슈팅2_보안" src="https://github.com/user-attachments/assets/ed8fe743-cabe-47cf-98c7-f00be08057cb" />

🚀해결 방법🚀

개인 키와 시크릿 키를 yml 파일에 분리하여 저장하고
<img width="706" height="403" alt="로드밸런싱_트러블 슈팅_yml수정" src="https://github.com/user-attachments/assets/9a7a49c5-59f3-4b14-a88d-619d325b924a" />

${sms.api.access} 및 ${sms.api.secret} 형태로 값을 주입하도록 변경하였습니다.
<img width="1331" height="883" alt="로드밸런싱_트러블 슈팅_service" src="https://github.com/user-attachments/assets/4da7e9d5-0b84-4b67-924b-431b8ba15a51" />



#### **( 2 )** EC2 배포 중 exit code 255 오류

🌩문제 상황🌩  

<img width="1479" height="206" alt="로드밸런싱 트러블 슈팅1" src="https://github.com/user-attachments/assets/217e9df5-6601-41e4-a5eb-267f059952b1" />


🚨문제 원인🚨  
GitHub Actions에서 EC2로 배포 시 필요한 **환경 변수(`SMS_ACCESS`, `SMS_SECRET_KEY`)**가 등록되지 않아,
SSH 키 파일 생성 단계에서 비어 있는 값이 입력되어 인증이 실패했습니다.   
즉, `SMS_ACCESS`, `SMS_SECRET_KEY` 시크릿이 누락되어 키 파일이 제대로 만들어지지 않은 상태입니다.   

🚀해결 방법🚀   
GitHub 저장소의 Secrets에 sms_access 추가

경로: Settings > Secrets and variables > Actions

<img width="885" height="770" alt="스크린샷 2025-11-10 155928" src="https://github.com/user-attachments/assets/295b0512-1740-44cd-8203-ea90ddb2e550" />


#### **( 3 )** GitHub Actions

🌩문제 상황🌩  

Spring Boot 애플리케이션 실행 중 다음 오류 발생:
<img width="1519" height="603" alt="로드밸런싱 트러블 슈팅2_시크릿키미등록" src="https://github.com/user-attachments/assets/05a710ed-e521-4d32-920c-08e6e209186e" />

🚨문제 원인🚨  

Spring은 ${SMS_ACCESS}, ${SMS_SECRET_KEY} 값을 환경 변수에서 찾도록 설정되어 있습니다.   
환경 변수 이름 불일치로 인해 Spring이 필요한 값을 주입받지 못했습니다.
<img width="893" height="793" alt="전" src="https://github.com/user-attachments/assets/2d2c8b2f-5184-48c4-8b4b-1a280317b345" />

🚀해결 방법🚀   

GitHub Actions / Docker 환경 변수 이름을 Spring에서 참조하는 이름과 동일하게 변경하였습니다.
<img width="934" height="794" alt="스크린샷 2025-11-10 154448" src="https://github.com/user-attachments/assets/bfa2f462-9b00-4048-9877-7ce729c8d6f7" />

### 그 결과, 페이지를 정상적으로 불러올 수 있었습니다.

<img width="1919" height="988" alt="스크린샷 2025-11-10 162549" src="https://github.com/user-attachments/assets/fd295cc6-2c05-42fb-8d28-8eaedb0cbe97" />


