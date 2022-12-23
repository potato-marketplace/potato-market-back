# 5조 미니 프로젝트 (SA) [감자마켓] #
![Group 1 (1)](https://user-images.githubusercontent.com/117730606/209360870-19c8ba6d-f26c-41ed-9698-ffbd49efbb33.png)




1. [프로젝트 소개](#1-프로젝트-소개)
2. [기능 명세](#2-주요-기능)
3. [서비스 아키텍처](#3-서비스-아키텍쳐)
4. [기술 스택](#4-기술-스택)
5. [트러블 슈팅](#5-트러블-슈팅)
6. [팀 노션](#6-팀-노션)
7. [깃 허브](#7-깃-허브)
8. [팀원 정보](#8-팀원-정보)



## 1. 프로젝트 소개
### 프로젝트 이름 : **감자마켓** - **감**사합니다 **자**알쓰겠습니다-!(가제)
- 프로젝트 설명 : 중고 물품 거래

## 2. 기능 명세

1. 회원

- Id, nickname, password를 입력하여 회원가입
- Id와 nickname은 중복 확인 검사
- Id는 몇자이상 뭐뭐포함 / nickname은 몇자이상 뭐뭐포함 / password는 몇자이상 뭐뭐포함
- 상품 등록, 댓글 등록은 회원만 가능
- 비회원은 게시글 조회만 가능

2. 상품

- 회원이 중고 거래 할 상품을 등록하고, 댓글 또는 대댓글로 거래가 성사되는 시스템
- title, image, nickname, content, price, createAt, modifiedAt으로 중고 거래 할 상품 작성
- 상품 작성 시, 이미지 파일 여러개를 첨부할 수 있음
- 이미지는 aws S3에 저장
- 상품 작성 후, 상세 조회 페이지로 이동
- 메인 페이지에서는 title, image, nickname, price, createAt만 출력
- 3x4, 혹은 4x4 형식으로 상품 썸네일 이미지 출력
- title, image, content, price 작성자에 한해 수정 가능
- 마찬가지로 작성자에 한해 삭제 가능
- 상세 조회 페이지에서는 모든 필드 조회

3. 댓글

- 마음에 드는 상품에 대해 댓글을 남겨 거래를 제안
- ninkname, content로 댓글 작성
- 댓글에 대한 대댓글도 가능


## 3. 서비스 아키텍처
![image](https://user-images.githubusercontent.com/117730606/209357235-53285c3c-d4bc-46e9-be8e-b457ddc91747.png)

## 4. 기술 스택
- Frontend : React, Redux, Redux Toolkit, Axios
- Backend  : Springboot, Spring Security, JWT, AWS S3, RDS

## 5.트러블 슈팅

💪 어려웠지만, 극복 해냄!


  |   | 트러블 슈팅 |
|--|--|
| [Frontend] | ![image](https://user-images.githubusercontent.com/117730606/209359166-dbd1631b-f690-4e98-b963-722e74ef5e19.png) |
| [Frontend] | ![image](https://user-images.githubusercontent.com/117730606/209359236-ac066a00-7794-4ce5-b0ef-4a93fcf2700c.png) |
| [backend] | ![image](https://user-images.githubusercontent.com/117730606/209359434-4820d2c2-2054-4ab7-8a4c-a26af42a30fc.png) |
| [backend] | ![image](https://user-images.githubusercontent.com/117730606/209359503-0296a166-d9e5-45f6-8c18-ed2ede3482f4.png) |
| [backend] | ![image](https://user-images.githubusercontent.com/117730606/209359566-bfea7d54-291a-418b-b67c-945a76a93469.png) |
| [backend] | ![image](https://user-images.githubusercontent.com/117730606/209359601-419d501b-dc06-4b17-8e84-2d68bca06bbf.png) |


## 6. 팀 노션
https://www.notion.so/eunsolan/5-SA-6d09ae3df1b442d493baeccfd4dc071e

## 7. 깃 허브
- Forntend : https://github.com/potato-marketplace/potato-market-front
- Backend : https://github.com/potato-marketplace/potato-market-back


## 8. 팀원 정보
| 이름 | 깃 허브 |
|--|--|
| 안은솔 | https://github.com/eunsol-an |
| 김현빈 | https://github.com/kimmy199535 |
| 정다솔 | https://github.com/ssori0421 |
| 김아영 | https://github.com/isladaisy |
| 김인광 | https://github.com/ingwang-kim |
| 홍윤재 | https://github.com/PigletHong |




