-- 고객
DROP TABLE IF EXISTS `CUSTOMER` RESTRICT;

-- 게시글
DROP TABLE IF EXISTS `BOARD` RESTRICT;

-- 카테고리
DROP TABLE IF EXISTS `CATEGORY` RESTRICT;

-- 상품
DROP TABLE IF EXISTS `PRODUCT` RESTRICT;

-- 주문
DROP TABLE IF EXISTS `ORDER` RESTRICT;

-- 장바구니내역
DROP TABLE IF EXISTS `CART` RESTRICT;

-- 브랜드
DROP TABLE IF EXISTS `BRAND` RESTRICT;

-- 창고
DROP TABLE IF EXISTS `WAREHOUSE` RESTRICT;

-- 주문상세내역
DROP TABLE IF EXISTS `ORDER_DETAIL` RESTRICT;

-- 리뷰
DROP TABLE IF EXISTS `REVIEW` RESTRICT;

-- 관심상품
DROP TABLE IF EXISTS `INTERESTED_ITEM` RESTRICT;

-- 상품상세정보
DROP TABLE IF EXISTS `PRODUCT_DETAIL` RESTRICT;

-- 상품이미지
DROP TABLE IF EXISTS `PRODUCT_IMAGE` RESTRICT;

-- 상품이미지분류
DROP TABLE IF EXISTS `PRODUCT_IMAGE_CATEGORY` RESTRICT;

-- 게시판_카테고리
DROP TABLE IF EXISTS `BOARD_CATEGORY` RESTRICT;

-- 옵션설정-템플릿
DROP TABLE IF EXISTS `OPTION_TEMPLATE` RESTRICT;

-- 결제
DROP TABLE IF EXISTS `PAYMENT` RESTRICT;

-- 약관동의서
DROP TABLE IF EXISTS `TERMS_OF_USE_TEMPLATE` RESTRICT;

-- 약관동의여부
DROP TABLE IF EXISTS `CUSTOMER_AND_TERMS_OF_USE` RESTRICT;

-- 주소
DROP TABLE IF EXISTS `CUSTOMER_ADDRESS` RESTRICT;

-- 고객
CREATE TABLE `CUSTOMER` (
                            `NO`             INT UNSIGNED           NOT NULL COMMENT '고객번호', -- 고객번호
                            `NAME`           VARCHAR(20)            NULL     COMMENT '이름', -- 이름
                            `EMAIL`          BLOB                   NULL     COMMENT '이메일', -- 이메일
                            `PASSWORD`       BLOB                   NOT NULL COMMENT '패스워드', -- 패스워드
                            `PHONE`          BLOB                   NULL     COMMENT '전화번호', -- 전화번호
                            `GENDER`         ENUM('M','F')          NULL     COMMENT '성별', -- 성별
                            `USE_FL`         ENUM('Y','N')          NOT NULL COMMENT '사용여부', -- 사용여부
                            `AUTH_GRADE`     VARCHAR(20) NULL     COMMENT '회원권한', -- 회원권한
                            `RECOMMENDER_ID` INT UNSIGNED           NULL     COMMENT '추천회원' -- 추천회원
)
    COMMENT '고객';

-- 고객
ALTER TABLE `CUSTOMER`
    ADD CONSTRAINT `PK_CUSTOMER` -- 고객 기본키
        PRIMARY KEY (
                     `NO` -- 고객번호
            );

ALTER TABLE `CUSTOMER`
    MODIFY COLUMN `NO` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '고객번호';

-- 게시글
CREATE TABLE `BOARD` (
                         `NO`                INT UNSIGNED  NOT NULL COMMENT '게시글번호', -- 게시글번호
                         `TITLE`             VARCHAR(100)  NOT NULL COMMENT '제목', -- 제목
                         `CONTENT`           TEXT          NOT NULL COMMENT '내용', -- 내용
                         `GROUP_NO`          INTEGER       NOT NULL COMMENT '그룹번호', -- 그룹번호
                         `ORDER_NO`          INTEGER       NOT NULL COMMENT '오더번호', -- 오더번호
                         `DEPTH`             INTEGER       NOT NULL COMMENT '깊이', -- 깊이
                         `REGISTER_DT`       DATETIME      NOT NULL COMMENT '등록일자', -- 등록일자
                         `DELETE_DT`         DATETIME      NULL     COMMENT '삭제일자', -- 삭제일자
                         `USE_FL`            ENUM('Y','N') NOT NULL COMMENT '활성여부', -- 활성여부
                         `CUSTOMER_NO`       INT UNSIGNED  NOT NULL COMMENT '고객번호', -- 고객번호
                         `BOARD_CATEGORY_NO` INT UNSIGNED  NOT NULL COMMENT '게시판번호' -- 게시판번호
)
    COMMENT '게시글';

-- 게시글
ALTER TABLE `BOARD`
    ADD CONSTRAINT `PK_BOARD` -- 게시글 기본키
        PRIMARY KEY (
                     `NO` -- 게시글번호
            );

ALTER TABLE `BOARD`
    MODIFY COLUMN `NO` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '게시글번호';

-- 카테고리
CREATE TABLE `CATEGORY` (
                            `NO`        INTEGER      NOT NULL COMMENT '카테고리번호', -- 카테고리번호
                            `NAME`      VARCHAR(100) NOT NULL COMMENT '카테고리명', -- 카테고리명
                            `PARENT_NO` INTEGER      NULL     COMMENT '상위카테고리' -- 상위카테고리
)
    COMMENT '카테고리';

-- 카테고리
ALTER TABLE `CATEGORY`
    ADD CONSTRAINT `PK_CATEGORY` -- 카테고리 기본키
        PRIMARY KEY (
                     `NO` -- 카테고리번호
            );

ALTER TABLE `CATEGORY`
    MODIFY COLUMN `NO` INTEGER NOT NULL AUTO_INCREMENT COMMENT '카테고리번호';

-- 상품
CREATE TABLE `PRODUCT` (
                           `NO`          INT UNSIGNED  NOT NULL COMMENT '상품번호', -- 상품번호
                           `NAME`        VARCHAR(100)  NULL     COMMENT '상품명', -- 상품명
                           `DESCRIPTION` TEXT          NULL     COMMENT '상세정보', -- 상세정보
                           `STATUS`      VARCHAR(30)   NULL     COMMENT '판매상태', -- 판매상태
                           `USE_FL`      ENUM('Y','N') NULL     COMMENT '진열여부', -- 진열여부
                           `LIKE_CNT`    INTEGER       NULL     COMMENT '추천수', -- 추천수
                           `REGISTER_DT` DATETIME      NULL     COMMENT '등록일자', -- 등록일자
                           `CATEGORY_NO` INTEGER       NULL     COMMENT '카테고리번호', -- 카테고리번호
                           `BRAND_NO`    INT UNSIGNED  NULL     COMMENT '브랜드번호' -- 브랜드번호
)
    COMMENT '상품';

-- 상품
ALTER TABLE `PRODUCT`
    ADD CONSTRAINT `PK_PRODUCT` -- 상품 기본키
        PRIMARY KEY (
                     `NO` -- 상품번호
            );

ALTER TABLE `PRODUCT`
    MODIFY COLUMN `NO` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '상품번호';

-- 주문
CREATE TABLE `ORDER` (
                         `NO`               INT UNSIGNED NOT NULL COMMENT '주문번호', -- 주문번호
                         `PRICE`            INT UNSIGNED NOT NULL COMMENT '결제금액', -- 결제금액
                         `ADDRESS`          BLOB         NOT NULL COMMENT '배송지 주소', -- 배송지 주소
                         `RECEIVER_NM`      BLOB         NOT NULL COMMENT '수취인', -- 수취인
                         `PHONE_NO`         BLOB         NOT NULL COMMENT '수취인 전화번호', -- 수취인 전화번호
                         `DELEVERY_STATUS`  VARCHAR(20)  NULL     COMMENT '배송상태', -- 배송상태
                         `SHIPPING_METHOD`  VARCHAR(20)  NULL     COMMENT '배송방법', -- 배송방법
                         `CUSTOMER_REQUEST` VARCHAR(300) NULL     COMMENT '요청사항', -- 요청사항
                         `CUSTOMER_NO`      INT UNSIGNED NOT NULL COMMENT '고객번호', -- 고객번호
                         `PAYMENT_NO`       INT UNSIGNED NOT NULL COMMENT '결제번호' -- 결제번호
)
    COMMENT '주문';

-- 주문
ALTER TABLE `ORDER`
    ADD CONSTRAINT `PK_ORDER` -- 주문 기본키
        PRIMARY KEY (
                     `NO` -- 주문번호
            );

ALTER TABLE `ORDER`
    MODIFY COLUMN `NO` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '주문번호';

-- 장바구니내역
CREATE TABLE `CART` (
                        `NO`                INT UNSIGNED  NOT NULL COMMENT '장바구니내역번호', -- 장바구니내역번호
                        `CUSTOMER_NO`       INT UNSIGNED  NOT NULL COMMENT '고객번호', -- 고객번호
                        `PRODUCT_DETAIL_NO` INT UNSIGNED  NOT NULL COMMENT '상품상세번호', -- 상품상세번호
                        `COUNT`             INTEGER       NOT NULL COMMENT '수량', -- 수량
                        `REGISTER_DT`       DATETIME      NULL     COMMENT '등록일자', -- 등록일자
                        `DELETE_DT`         DATETIME      NULL     COMMENT '완료일자', -- 완료일자
                        `ORDERED CART`      ENUM('Y','N') NULL     COMMENT '주문여부' -- 주문여부
)
    COMMENT '장바구니내역';

-- 장바구니내역
ALTER TABLE `CART`
    ADD CONSTRAINT `PK_CART` -- 장바구니내역 기본키
        PRIMARY KEY (
                     `NO` -- 장바구니내역번호
            );

ALTER TABLE `CART`
    MODIFY COLUMN `NO` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '장바구니내역번호';

-- 브랜드
CREATE TABLE `BRAND` (
                         `NO`          INT UNSIGNED  NOT NULL COMMENT '브랜드번호', -- 브랜드번호
                         `NAME`        VARCHAR(100)  NOT NULL COMMENT '브랜드명', -- 브랜드명
                         `ADDRESS`     VARCHAR(100)  NULL     COMMENT '주소', -- 주소
                         `PHONE_NO`    VARCHAR(20)   NULL     COMMENT '전화번호', -- 전화번호
                         `REGISTER_DT` DATETIME      NULL     COMMENT '등록일자', -- 등록일자
                         `DELETE_DT`   DATETIME      NULL     COMMENT '삭제일자', -- 삭제일자
                         `USE_FL`      ENUM('Y','N') NOT NULL COMMENT '활성여부' -- 활성여부
)
    COMMENT '브랜드';

-- 브랜드
ALTER TABLE `BRAND`
    ADD CONSTRAINT `PK_BRAND` -- 브랜드 기본키
        PRIMARY KEY (
                     `NO` -- 브랜드번호
            );

ALTER TABLE `BRAND`
    MODIFY COLUMN `NO` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '브랜드번호';

-- 창고
CREATE TABLE `WAREHOUSE` (
                             `NO`             INT UNSIGNED  NOT NULL COMMENT '창고번호', -- 창고번호
                             `CODE`           VARCHAR(30)   NULL     COMMENT '창고코드', -- 창고코드
                             `LOCATION`       VARCHAR(100)  NULL     COMMENT '창고위치', -- 창고위치
                             `USE_FL`         ENUM('Y','N') NOT NULL COMMENT '사용여부', -- 사용여부
                             `PHONE_NO`       VARCHAR(20)   NULL     COMMENT '창고전화번호', -- 창고전화번호
                             `USE_PERCENTAGE` INT           NULL     COMMENT '창고점유율', -- 창고점유율
                             `REGISTER_DT`    DATETIME      NULL     COMMENT '등록일자' -- 등록일자
)
    COMMENT '창고';

-- 창고
ALTER TABLE `WAREHOUSE`
    ADD CONSTRAINT `PK_WAREHOUSE` -- 창고 기본키
        PRIMARY KEY (
                     `NO` -- 창고번호
            );

ALTER TABLE `WAREHOUSE`
    MODIFY COLUMN `NO` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '창고번호';

-- 주문상세내역
CREATE TABLE `ORDER_DETAIL` (
                                `NO`             INT UNSIGNED       NOT NULL COMMENT '주문상세내역번호', -- 주문상세내역번호
                                `ORDER_NO`       INT UNSIGNED       NOT NULL COMMENT '주문번호', -- 주문번호
                                `ITEM_DETAIL_NO` INT UNSIGNED       NOT NULL COMMENT '상품상세번호', -- 상품상세번호
                                `PRICE`            INTEGER NULL     COMMENT '가격', -- 가격
                                `ORDER_ITEM_CNT` INTEGER            NOT NULL COMMENT '수량' -- 수량
)
    COMMENT '주문상세내역';

-- 주문상세내역
ALTER TABLE `ORDER_DETAIL`
    ADD CONSTRAINT `PK_ORDER_DETAIL` -- 주문상세내역 기본키
        PRIMARY KEY (
                     `NO` -- 주문상세내역번호
            );

ALTER TABLE `ORDER_DETAIL`
    MODIFY COLUMN `NO` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '주문상세내역번호';

-- 리뷰
CREATE TABLE `REVIEW` (
                          `NO`             INT UNSIGNED NOT NULL COMMENT '리뷰번호', -- 리뷰번호
                          `TITLE`          VARCHAR(100) NOT NULL COMMENT '제목', -- 제목
                          `CONTENT`        TEXT         NOT NULL COMMENT '내용', -- 내용
                          `SCORE`          INT          NOT NULL COMMENT '평점', -- 평점
                          `REGISTER_DT`    DATETIME     NOT NULL COMMENT '등록일자', -- 등록일자
                          `DELETE_DT`      DATETIME     NULL     COMMENT '삭제일자', -- 삭제일자
                          `CUSTOMER_NO`    INT UNSIGNED NOT NULL COMMENT '고객번호', -- 고객번호
                          `ITEM_DETAIL_NO` INT UNSIGNED NOT NULL COMMENT '상품상세번호' -- 상품상세번호
)
    COMMENT '리뷰';

-- 리뷰
ALTER TABLE `REVIEW`
    ADD CONSTRAINT `PK_REVIEW` -- 리뷰 기본키
        PRIMARY KEY (
                     `NO` -- 리뷰번호
            );

ALTER TABLE `REVIEW`
    MODIFY COLUMN `NO` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '리뷰번호';

-- 관심상품
CREATE TABLE `INTERESTED_ITEM` (
                                   `CUSTOMER_NO` INT UNSIGNED  NOT NULL COMMENT '고객번호', -- 고객번호
                                   `NO`          INT UNSIGNED  NULL     COMMENT '상품상세번호', -- 상품상세번호
                                   `REGISTER_DT` DATETIME      NOT NULL COMMENT '등록일자', -- 등록일자
                                   `DELETE_DT`   DATETIME      NULL     COMMENT '삭제일자', -- 삭제일자
                                   `USE_FL`      ENUM('Y','N') NOT NULL COMMENT '활성여부' -- 활성여부
)
    COMMENT '관심상품';

-- 상품상세정보
CREATE TABLE `PRODUCT_DETAIL` (
                                  `NO`             INT UNSIGNED             NOT NULL COMMENT '상품상세번호', -- 상품상세번호
                                  `PRODUCT_NO`     INT UNSIGNED             NOT NULL COMMENT '상품번호', -- 상품번호
                                  `PRODUCT_OPTION` VARCHAR(200)             NOT NULL COMMENT '상품옵션', -- 상품옵션
                                  `PRICE`          INTEGER                  NOT NULL COMMENT '상품가격', -- 상품가격
                                  `STOCK_CD`       ENUM('STOCK','NO_STOCK') NOT NULL COMMENT '재고분류코드', -- 재고분류코드
                                  `STOCK_CNT`      INTEGER                  NULL     COMMENT '재고수', -- 재고수
                                  `WAREHOUSE_NO`   INT UNSIGNED             NULL     COMMENT '창고번호' -- 창고번호
)
    COMMENT '상품상세정보';

-- 상품상세정보
ALTER TABLE `PRODUCT_DETAIL`
    ADD CONSTRAINT `PK_PRODUCT_DETAIL` -- 상품상세정보 기본키
        PRIMARY KEY (
                     `NO` -- 상품상세번호
            );

ALTER TABLE `PRODUCT_DETAIL`
    MODIFY COLUMN `NO` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '상품상세번호';

-- 상품이미지
CREATE TABLE `PRODUCT_IMAGE` (
                                 `NO`                     INT UNSIGNED  NOT NULL COMMENT '상품이미지번호', -- 상품이미지번호
                                 `PRODUCT_NO`                INT UNSIGNED  NULL     COMMENT '상품번호', -- 상품번호
                                 `URL`                    VARCHAR(200)  NULL     COMMENT 'URL', -- URL
                                 `REGISTER_DT`            DATETIME      NULL     COMMENT '등록일자', -- 등록일자
                                 `USE_FL`                 ENUM('Y','N') NULL     COMMENT '활성여부', -- 활성여부
                                 `PRODUCT_IMAGE_CATEGORY_NO` INT UNSIGNED  NULL     COMMENT '상품이미지분류번호' -- 상품이미지분류번호
)
    COMMENT '상품이미지';

-- 상품이미지
ALTER TABLE `PRODUCT_IMAGE`
    ADD CONSTRAINT `PK_PRODUCT_IMAGE` -- 상품이미지 기본키
        PRIMARY KEY (
                     `NO` -- 상품이미지번호
            );

-- 상품이미지분류
CREATE TABLE `PRODUCT_IMAGE_CATEGORY` (
                                          `NO`          INT UNSIGNED  NOT NULL COMMENT '상품이미지분류번호', -- 상품이미지분류번호
                                          `NAME`        VARCHAR(100)  NOT NULL COMMENT '상품이미지분류명', -- 상품이미지분류명
                                          `REGISTER_DT` DATETIME      NULL     COMMENT '등록일자', -- 등록일자
                                          `DELETE_DT`   DATETIME      NULL     COMMENT '삭제일자', -- 삭제일자
                                          `USE_FL`      ENUM('Y','N') NOT NULL COMMENT '활성여부' -- 활성여부
)
    COMMENT '상품이미지분류';

-- 상품이미지분류
ALTER TABLE `PRODUCT_IMAGE_CATEGORY`
    ADD CONSTRAINT `PK_PRODUCT_IMAGE_CATEGORY` -- 상품이미지분류 기본키
        PRIMARY KEY (
                     `NO` -- 상품이미지분류번호
            );

ALTER TABLE `PRODUCT_IMAGE_CATEGORY`
    MODIFY COLUMN `NO` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '상품이미지분류번호';

-- 게시판_카테고리
CREATE TABLE `BOARD_CATEGORY` (
                                  `NO`          INT UNSIGNED  NOT NULL COMMENT '게시판번호', -- 게시판번호
                                  `NAME`        TEXT          NOT NULL COMMENT '게시판명', -- 게시판명
                                  `REGISTER_DT` DATETIME      NOT NULL COMMENT '등록일자', -- 등록일자
                                  `DELETE_DT`   DATETIME      NULL     COMMENT '삭제일자', -- 삭제일자
                                  `USE_FL`      ENUM('Y','N') NOT NULL COMMENT '활성여부' -- 활성여부
)
    COMMENT '게시판_카테고리';

-- 게시판_카테고리
ALTER TABLE `BOARD_CATEGORY`
    ADD CONSTRAINT `PK_BOARD_CATEGORY` -- 게시판_카테고리 기본키
        PRIMARY KEY (
                     `NO` -- 게시판번호
            );

ALTER TABLE `BOARD_CATEGORY`
    MODIFY COLUMN `NO` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '게시판번호';

-- 옵션설정-템플릿
CREATE TABLE `OPTION_TEMPLATE` (
                                   `NO`          INT UNSIGNED  NOT NULL COMMENT '옵션설정템플릿번호', -- 옵션설정템플릿번호
                                   `NAME`        VARCHAR(30)   NOT NULL COMMENT '옵션세트명', -- 옵션세트명
                                   `DESCRIPTION` VARCHAR(200)  NULL     COMMENT '옵션세트설명', -- 옵션세트설명
                                   `OPTION_TYPE` VARCHAR(100)  NOT NULL COMMENT '옵션설정', -- 옵션설정
                                   `USE_FL`      ENUM('Y','N') NOT NULL COMMENT '사용여부', -- 사용여부
                                   `CUSTOMER_NO` INT UNSIGNED  NOT NULL COMMENT '고객번호' -- 고객번호
)
    COMMENT '옵션설정-템플릿';

-- 옵션설정-템플릿
ALTER TABLE `OPTION_TEMPLATE`
    ADD CONSTRAINT `PK_OPTION_TEMPLATE` -- 옵션설정-템플릿 기본키
        PRIMARY KEY (
                     `NO` -- 옵션설정템플릿번호
            );

ALTER TABLE `OPTION_TEMPLATE`
    MODIFY COLUMN `NO` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '옵션설정템플릿번호';

-- 결제
CREATE TABLE `PAYMENT` (
                           `NO`      INT UNSIGNED NOT NULL COMMENT '결제번호', -- 결제번호
                           `METHOD`  VARCHAR(50)  NULL     COMMENT '결제수단', -- 결제수단
                           `CARD_NO` VARCHAR(30)  NULL     COMMENT '카드번호' -- 카드번호
)
    COMMENT '결제';

-- 결제
ALTER TABLE `PAYMENT`
    ADD CONSTRAINT `PK_PAYMENT` -- 결제 기본키
        PRIMARY KEY (
                     `NO` -- 결제번호
            );

ALTER TABLE `PAYMENT`
    MODIFY COLUMN `NO` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '결제번호';

-- 약관동의서
CREATE TABLE `TERMS_OF_USE_TEMPLATE` (
                                         `NO`           INT UNSIGNED  NOT NULL COMMENT '약관동의서번호', -- 약관동의서번호
                                         `TITLE`        VARCHAR(100)  NOT NULL COMMENT '약관동의서명', -- 약관동의서명
                                         `CONTENT`      TEXT          NOT NULL COMMENT '약관동의서내용', -- 약관동의서내용
                                         `USE_FL`       ENUM('Y','N') NULL     COMMENT '활성여부', -- 활성여부
                                         `NECESSARY_FL` ENUM('Y','N') NULL     COMMENT '필수여부', -- 필수여부
                                         `REGISTER_DT`  DATETIME      NOT NULL COMMENT '등록일자', -- 등록일자
                                         `DELETE_DT`    DATETIME      NULL     COMMENT '폐기일자' -- 폐기일자
)
    COMMENT '약관동의서';

-- 약관동의서
ALTER TABLE `TERMS_OF_USE_TEMPLATE`
    ADD CONSTRAINT `PK_TERMS_OF_USE_TEMPLATE` -- 약관동의서 기본키
        PRIMARY KEY (
                     `NO` -- 약관동의서번호
            );

ALTER TABLE `TERMS_OF_USE_TEMPLATE`
    MODIFY COLUMN `NO` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '약관동의서번호';

-- 약관동의여부
CREATE TABLE `CUSTOMER_AND_TERMS_OF_USE` (
                                             `CUSTOMER_NO`     INT UNSIGNED  NOT NULL COMMENT '고객번호', -- 고객번호
                                             `TERMS_OF_USE_NO` INT UNSIGNED  NOT NULL COMMENT '약관동의서번호', -- 약관동의서번호
                                             `AGREEMENT_FL`    ENUM('Y','N') NOT NULL COMMENT '동의여부', -- 동의여부
                                             `AGREEMENT_DT`    DATETIME      NOT NULL COMMENT '동의일자' -- 동의일자
)
    COMMENT '약관동의여부';

# ALTER TABLE `CUSTOMER_AND_TERMS_OF_USE`
#     MODIFY COLUMN `CUSTOMER_NO` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '고객번호';

-- 주소
CREATE TABLE `CUSTOMER_ADDRESS` (
                                    `NO`          INT UNSIGNED                NOT NULL COMMENT '주소번호', -- 주소번호
                                    `ADDRESS`     VARCHAR(100)                NOT NULL COMMENT '주소지', -- 주소지
                                    `PHONE_NO`    VARCHAR(20)                 NOT NULL COMMENT '전화번호', -- 전화번호
                                    `ADDR_CD`     ENUM('PRIMARY','SECONDARY') NOT NULL COMMENT '주소분류코드', -- 주소분류코드
                                    `REGISTER_DT` DATETIME                    NOT NULL COMMENT '등록일자', -- 등록일자
                                    `CUSTOMER_NO` INT UNSIGNED                NOT NULL COMMENT '고객번호' -- 고객번호
)
    COMMENT '주소';

-- 주소
ALTER TABLE `CUSTOMER_ADDRESS`
    ADD CONSTRAINT `PK_CUSTOMER_ADDRESS` -- 주소 기본키
        PRIMARY KEY (
                     `NO` -- 주소번호
            );

ALTER TABLE `CUSTOMER_ADDRESS`
    MODIFY COLUMN `NO` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '주소번호';

# -- 고객
# ALTER TABLE `CUSTOMER`
#     ADD CONSTRAINT `FK_CUSTOMER_TO_CUSTOMER` -- 고객 -> 고객
#         FOREIGN KEY (
#                      `RECOMMENDER_ID` -- 추천회원
#             )
#             REFERENCES `CUSTOMER` ( -- 고객
#                                    `NO` -- 고객번호
#                 );
#
# -- 게시글
# ALTER TABLE `BOARD`
#     ADD CONSTRAINT `FK_CUSTOMER_TO_BOARD` -- 고객 -> 게시글
#         FOREIGN KEY (
#                      `CUSTOMER_NO` -- 고객번호
#             )
#             REFERENCES `CUSTOMER` ( -- 고객
#                                    `NO` -- 고객번호
#                 );
#
# -- 게시글
# ALTER TABLE `BOARD`
#     ADD CONSTRAINT `FK_BOARD_CATEGORY_TO_BOARD` -- 게시판_카테고리 -> 게시글
#         FOREIGN KEY (
#                      `BOARD_CATEGORY_NO` -- 게시판번호
#             )
#             REFERENCES `BOARD_CATEGORY` ( -- 게시판_카테고리
#                                          `NO` -- 게시판번호
#                 );
#
# -- 카테고리
# ALTER TABLE `CATEGORY`
#     ADD CONSTRAINT `FK_CATEGORY_TO_CATEGORY` -- 카테고리 -> 카테고리
#         FOREIGN KEY (
#                      `PARENT_NO` -- 상위카테고리
#             )
#             REFERENCES `CATEGORY` ( -- 카테고리
#                                    `NO` -- 카테고리번호
#                 );
#
# -- 상품
# ALTER TABLE `PRODUCT`
#     ADD CONSTRAINT `FK_CATEGORY_TO_PRODUCT` -- 카테고리 -> 상품
#         FOREIGN KEY (
#                      `CATEGORY_NO` -- 카테고리번호
#             )
#             REFERENCES `CATEGORY` ( -- 카테고리
#                                    `NO` -- 카테고리번호
#                 );
#
# -- 상품
# ALTER TABLE `PRODUCT`
#     ADD CONSTRAINT `FK_BRAND_TO_PRODUCT` -- 브랜드 -> 상품
#         FOREIGN KEY (
#                      `BRAND_NO` -- 브랜드번호
#             )
#             REFERENCES `BRAND` ( -- 브랜드
#                                 `NO` -- 브랜드번호
#                 );
#
# -- 주문
# ALTER TABLE `ORDER`
#     ADD CONSTRAINT `FK_CUSTOMER_TO_ORDER` -- 고객 -> 주문
#         FOREIGN KEY (
#                      `CUSTOMER_NO` -- 고객번호
#             )
#             REFERENCES `CUSTOMER` ( -- 고객
#                                    `NO` -- 고객번호
#                 );
#
# -- 주문
# ALTER TABLE `ORDER`
#     ADD CONSTRAINT `FK_PAYMENT_TO_ORDER` -- 결제 -> 주문
#         FOREIGN KEY (
#                      `PAYMENT_NO` -- 결제번호
#             )
#             REFERENCES `PAYMENT` ( -- 결제
#                                   `NO` -- 결제번호
#                 );
#
# -- 장바구니내역
# ALTER TABLE `CART`
#     ADD CONSTRAINT `FK_CUSTOMER_TO_CART` -- 고객 -> 장바구니내역
#         FOREIGN KEY (
#                      `CUSTOMER_NO` -- 고객번호
#             )
#             REFERENCES `CUSTOMER` ( -- 고객
#                                    `NO` -- 고객번호
#                 );
#
# -- 장바구니내역
# ALTER TABLE `CART`
#     ADD CONSTRAINT `FK_PRODUCT_DETAIL_TO_CART` -- 상품상세정보 -> 장바구니내역
#         FOREIGN KEY (
#                      `PRODUCT_DETAIL_NO` -- 상품상세번호
#             )
#             REFERENCES `PRODUCT_DETAIL` ( -- 상품상세정보
#                                          `NO` -- 상품상세번호
#                 );
#
# -- 주문상세내역
# ALTER TABLE `ORDER_DETAIL`
#     ADD CONSTRAINT `FK_ORDER_TO_ORDER_DETAIL` -- 주문 -> 주문상세내역
#         FOREIGN KEY (
#                      `ORDER_NO` -- 주문번호
#             )
#             REFERENCES `ORDER` ( -- 주문
#                                 `NO` -- 주문번호
#                 );
#
# -- 주문상세내역
# ALTER TABLE `ORDER_DETAIL`
#     ADD CONSTRAINT `FK_PRODUCT_DETAIL_TO_ORDER_DETAIL` -- 상품상세정보 -> 주문상세내역
#         FOREIGN KEY (
#                      `ITEM_DETAIL_NO` -- 상품상세번호
#             )
#             REFERENCES `PRODUCT_DETAIL` ( -- 상품상세정보
#                                          `NO` -- 상품상세번호
#                 );
#
# -- 리뷰
# ALTER TABLE `REVIEW`
#     ADD CONSTRAINT `FK_CUSTOMER_TO_REVIEW` -- 고객 -> 리뷰
#         FOREIGN KEY (
#                      `CUSTOMER_NO` -- 고객번호
#             )
#             REFERENCES `CUSTOMER` ( -- 고객
#                                    `NO` -- 고객번호
#                 );
#
# -- 리뷰
# ALTER TABLE `REVIEW`
#     ADD CONSTRAINT `FK_PRODUCT_DETAIL_TO_REVIEW` -- 상품상세정보 -> 리뷰
#         FOREIGN KEY (
#                      `ITEM_DETAIL_NO` -- 상품상세번호
#             )
#             REFERENCES `PRODUCT_DETAIL` ( -- 상품상세정보
#                                          `NO` -- 상품상세번호
#                 );
#
# -- 관심상품
# ALTER TABLE `INTERESTED_ITEM`
#     ADD CONSTRAINT `FK_CUSTOMER_TO_INTERESTED_ITEM` -- 고객 -> 관심상품
#         FOREIGN KEY (
#                      `CUSTOMER_NO` -- 고객번호
#             )
#             REFERENCES `CUSTOMER` ( -- 고객
#                                    `NO` -- 고객번호
#                 );
#
# -- 관심상품
# ALTER TABLE `INTERESTED_ITEM`
#     ADD CONSTRAINT `FK_PRODUCT_DETAIL_TO_INTERESTED_ITEM` -- 상품상세정보 -> 관심상품
#         FOREIGN KEY (
#                      `NO` -- 상품상세번호
#             )
#             REFERENCES `PRODUCT_DETAIL` ( -- 상품상세정보
#                                          `NO` -- 상품상세번호
#                 );
#
# -- 상품상세정보
# ALTER TABLE `PRODUCT_DETAIL`
#     ADD CONSTRAINT `FK_WAREHOUSE_TO_PRODUCT_DETAIL` -- 창고 -> 상품상세정보
#         FOREIGN KEY (
#                      `WAREHOUSE_NO` -- 창고번호
#             )
#             REFERENCES `WAREHOUSE` ( -- 창고
#                                     `NO` -- 창고번호
#                 );
#
# -- 상품상세정보
# ALTER TABLE `PRODUCT_DETAIL`
#     ADD CONSTRAINT `FK_PRODUCT_TO_PRODUCT_DETAIL` -- 상품 -> 상품상세정보
#         FOREIGN KEY (
#                      `PRODUCT_NO` -- 상품번호
#             )
#             REFERENCES `PRODUCT` ( -- 상품
#                                   `NO` -- 상품번호
#                 );
#
# -- 상품이미지
# ALTER TABLE `PRODUCT_IMAGE`
#     ADD CONSTRAINT `FK_PRODUCT_TO_PRODUCT_IMAGE` -- 상품 -> 상품이미지
#         FOREIGN KEY (
#                      `PRODUCT_NO` -- 상품번호
#             )
#             REFERENCES `PRODUCT` ( -- 상품
#                                   `NO` -- 상품번호
#                 );
#
# -- 상품이미지
# ALTER TABLE `PRODUCT_IMAGE`
#     ADD CONSTRAINT `FK_PRODUCT_IMAGE_CATEGORY_TO_PRODUCT_IMAGE` -- 상품이미지분류 -> 상품이미지
#         FOREIGN KEY (
#                      `PRODUCT_IMAGE_CATEGORY_NO` -- 상품이미지분류번호
#             )
#             REFERENCES `PRODUCT_IMAGE_CATEGORY` ( -- 상품이미지분류
#                                                  `NO` -- 상품이미지분류번호
#                 );
#
# -- 옵션설정-템플릿
# ALTER TABLE `OPTION_TEMPLATE`
#     ADD CONSTRAINT `FK_CUSTOMER_TO_OPTION_TEMPLATE` -- 고객 -> 옵션설정-템플릿
#         FOREIGN KEY (
#                      `CUSTOMER_NO` -- 고객번호
#             )
#             REFERENCES `CUSTOMER` ( -- 고객
#                                    `NO` -- 고객번호
#                 );
#
# -- 약관동의여부
# ALTER TABLE `CUSTOMER_AND_TERMS_OF_USE`
#     ADD CONSTRAINT `FK_CUSTOMER_TO_CUSTOMER_AND_TERMS_OF_USE` -- 고객 -> 약관동의여부
#         FOREIGN KEY (
#                      `CUSTOMER_NO` -- 고객번호
#             )
#             REFERENCES `CUSTOMER` ( -- 고객
#                                    `NO` -- 고객번호
#                 );
#
# -- 약관동의여부
# ALTER TABLE `CUSTOMER_AND_TERMS_OF_USE`
#     ADD CONSTRAINT `FK_TERMS_OF_USE_TEMPLATE_TO_CUSTOMER_AND_TERMS_OF_USE` -- 약관동의서 -> 약관동의여부
#         FOREIGN KEY (
#                      `TERMS_OF_USE_NO` -- 약관동의서번호
#             )
#             REFERENCES `TERMS_OF_USE_TEMPLATE` ( -- 약관동의서
#                                                 `NO` -- 약관동의서번호
#                 );
#
# -- 주소
# ALTER TABLE `CUSTOMER_ADDRESS`
#     ADD CONSTRAINT `FK_CUSTOMER_TO_CUSTOMER_ADDRESS` -- 고객 -> 주소
#         FOREIGN KEY (
#                      `CUSTOMER_NO` -- 고객번호
#             )
#             REFERENCES `CUSTOMER` ( -- 고객
#                                    `NO` -- 고객번호
#                 );