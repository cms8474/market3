-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■  U_USER ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼  
INSERT ALL
  INTO u_user (u_id, u_pw, u_name, u_birth, u_gender, u_mail, u_phone, u_postal, u_base_addr, u_detail_addr, u_type, u_point, u_rank, u_status) 
  VALUES ('user01', 'hashed_password_123', '일반1', TO_DATE('1995-03-15', 'YYYY-MM-DD'), '남', 'user01@example.com', '010-1111-1111', '12345', '서울특별시 강남구 테헤란로', '101동 101호', '일반', 10000, 'BRONZE', '정상')
  
  INTO u_user (u_id, u_pw, u_name, u_birth, u_gender, u_mail, u_phone, u_postal, u_base_addr, u_detail_addr, u_type, u_point, u_rank, u_status) 
  VALUES ('user02', 'hashed_password_123', '일반2', TO_DATE('2001-11-20', 'YYYY-MM-DD'), '여', 'user02@example.com', '010-2222-2222', '23456', '부산광역시 해운대구 센텀중앙로', '202동 202호', '일반', 10000, 'BRONZE', '정상')
  
  INTO u_user (u_id, u_pw, u_name, u_birth, u_gender, u_mail, u_phone, u_postal, u_base_addr, u_detail_addr, u_type, u_point, u_rank, u_status) 
  VALUES ('user03', 'hashed_password_123', '일반중지3', TO_DATE('2001-11-20', 'YYYY-MM-DD'), '여', 'user03@example.com', '010-2222-2222', '23456', '부산광역시 해운대구 센텀중앙로', '202동 202호', '일반', 10000, 'BRONZE', '중지')
  
  INTO u_user (u_id, u_pw, u_name, u_birth, u_gender, u_mail, u_phone, u_postal, u_base_addr, u_detail_addr, u_type, u_point, u_rank, u_status) 
  VALUES ('user04', 'hashed_password_123', '일반휴면4', TO_DATE('2001-11-20', 'YYYY-MM-DD'), '여', 'user04@example.com', '010-2222-2222', '23456', '부산광역시 해운대구 센텀중앙로', '202동 202호', '일반', 10000, 'BRONZE', '휴면')
  
  INTO u_user (u_id, u_pw, u_name, u_birth, u_gender, u_mail, u_phone, u_postal, u_base_addr, u_detail_addr, u_type, u_point, u_rank, u_status) 
  VALUES ('user05', 'hashed_password_123', '일반탈퇴5', TO_DATE('2001-11-20', 'YYYY-MM-DD'), '여', 'user05@example.com', '010-2222-2222', '23456', '부산광역시 해운대구 센텀중앙로', '202동 202호', '일반', 10000, 'BRONZE', '탈퇴')
  
  INTO u_user (u_id, u_pw, u_name, u_birth, u_gender, u_mail, u_phone, u_postal, u_base_addr, u_detail_addr, u_type, u_point, u_rank, u_status) 
  VALUES ('seller01', 'hashed_password_123', '판매1', TO_DATE('1988-07-01', 'YYYY-MM-DD'), '남', 'seller01@example.com', '010-3333-3333', '34567', '인천광역시 연수구 송도과학로', '상가 A동 10호', '판매자', 10000, 'SELLER', '정상')
  
  INTO u_user (u_id, u_pw, u_name, u_birth, u_gender, u_mail, u_phone, u_postal, u_base_addr, u_detail_addr, u_type, u_point, u_rank, u_status) 
  VALUES ('seller02', 'hashed_password_123', '판매2', TO_DATE('1992-12-25', 'YYYY-MM-DD'), '여', 'seller02@example.com', '010-4444-4444', '45678', '경기도 성남시 분당구 판교역로', 'B빌딩 5층', '판매자', 10000, 'SELLER', '정상')
  
  INTO u_user (u_id, u_pw, u_name, u_birth, u_gender, u_mail, u_phone, u_postal, u_base_addr, u_detail_addr, u_type, u_point, u_rank, u_status) 
  VALUES ('seller03', 'hashed_password_123', '판매3', TO_DATE('1990-12-25', 'YYYY-MM-DD'), '여', 'seller03@example.com', '010-4444-4445', '45678', '경기도 성남시 분당구 판교역로', 'B빌딩 5층', '판매자', 10000, 'SELLER', '휴면')
  
  INTO u_user (u_id, u_pw, u_name, u_birth, u_gender, u_mail, u_phone, u_postal, u_base_addr, u_detail_addr, u_type, u_point, u_rank, u_status) 
  VALUES ('admin01', 'hashed_password_123', '관리1', TO_DATE('1994-06-23', 'YYYY-MM-DD'), '남', 'minstest22@gmail.com', '010-9999-9999', '00001', '서울특별시 종로구 세종대로', '정부서울청사', '관리자', 9999999, 'ADMIN', '정상')
  
  INTO u_user (u_id, u_pw, u_name, u_birth, u_gender, u_mail, u_phone, u_postal, u_base_addr, u_detail_addr, u_type, u_point, u_rank, u_status) 
  VALUES ('admin02', 'hashed_password_123', '관리2', TO_DATE('1985-05-05', 'YYYY-MM-DD'), '여', 'admin02@example.com', '010-8888-8888', '00002', '세종특별자치시 도움6로', '정부세종청사', '관리자', 9999999, 'ADMIN', '정상')
SELECT * FROM DUAL;


commit;
//SELECT * FROM u_user;
select * from AD_INFO;
//SELECT * FROM SELLER_INFO;
//DELETE FROM u_user where U_ID = 'k1017';
//DELETE FROM SELLER_INFO where S_U_ID = 'k1017';
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲


-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■  product_category ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
INSERT ALL
  INTO product_category (pc_id, pc_name, pc_lever) VALUES ('100000', '패션', '대')
  INTO product_category (pc_id, pc_name, pc_lever) VALUES ('100001', '의류', '중')
  INTO product_category (pc_id, pc_name, pc_lever) VALUES ('100002', '뷰티', '중')
  INTO product_category (pc_id, pc_name, pc_lever) VALUES ('200000', '가전', '대')
  INTO product_category (pc_id, pc_name, pc_lever) VALUES ('200001', '디지털', '중')
  INTO product_category (pc_id, pc_name, pc_lever) VALUES ('300000', '식품', '대')
  INTO product_category (pc_id, pc_name, pc_lever) VALUES ('300001', '생필품', '중')
  INTO product_category (pc_id, pc_name, pc_lever) VALUES ('400000', '홈', '대')
  INTO product_category (pc_id, pc_name, pc_lever) VALUES ('400001', '문구', '중')
  INTO product_category (pc_id, pc_name, pc_lever) VALUES ('400002', '취미', '중')
SELECT * FROM DUAL;

//SELECT * FROM product_category;
//DELETE FROM product_category;

-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲

-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■  board_type ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
INSERT ALL
  INTO board_type (bt_type, bt_name) VALUES ('noti00', 'notice')            -- ■공지
  INTO board_type (bt_type, bt_name) VALUES ('noti01', '고객서비스')
  INTO board_type (bt_type, bt_name) VALUES ('noti02', '안전거래')
  INTO board_type (bt_type, bt_name) VALUES ('noti03', '위해상품')
  INTO board_type (bt_type, bt_name) VALUES ('noti04', '이벤트당첨')
  INTO board_type (bt_type, bt_name) VALUES ('faq00', 'faq')                -- ■자주묻는질문
  INTO board_type (bt_type, bt_name) VALUES ('faq10', '회원')              --회원
  INTO board_type (bt_type, bt_name) VALUES ('faq11', '가입')
  INTO board_type (bt_type, bt_name) VALUES ('faq12', '탈퇴')
  INTO board_type (bt_type, bt_name) VALUES ('faq13', '회원정보')
  INTO board_type (bt_type, bt_name) VALUES ('faq14', '로그인')
  INTO board_type (bt_type, bt_name) VALUES ('faq20', '쿠폰 / 이벤트')      --쿠폰/이벤트
  INTO board_type (bt_type, bt_name) VALUES ('faq21', '쿠폰 / 할인혜택')
  INTO board_type (bt_type, bt_name) VALUES ('faq22', '포인트')
  INTO board_type (bt_type, bt_name) VALUES ('faq23', '제휴')
  INTO board_type (bt_type, bt_name) VALUES ('faq24', '이벤트')
  INTO board_type (bt_type, bt_name) VALUES ('faq30', '주문 / 결제')        --주문/결제
  INTO board_type (bt_type, bt_name) VALUES ('faq31', '상품')
  INTO board_type (bt_type, bt_name) VALUES ('faq32', '결제')
  INTO board_type (bt_type, bt_name) VALUES ('faq33', '구매내역')
  INTO board_type (bt_type, bt_name) VALUES ('faq34', '영수증/증빙')
  INTO board_type (bt_type, bt_name) VALUES ('faq40', '배송')               --배송
  INTO board_type (bt_type, bt_name) VALUES ('faq41', '배송상태/기간')
  INTO board_type (bt_type, bt_name) VALUES ('faq42', '배송정보확인/변경')
  INTO board_type (bt_type, bt_name) VALUES ('faq43', '해외배송')
  INTO board_type (bt_type, bt_name) VALUES ('faq44', '당일배송')
  INTO board_type (bt_type, bt_name) VALUES ('faq45', '해외직구')
  INTO board_type (bt_type, bt_name) VALUES ('faq50', '취소 / 반품 / 교환')  --취소/반품/교환
  INTO board_type (bt_type, bt_name) VALUES ('faq51', '반품신청 / 철회')
  INTO board_type (bt_type, bt_name) VALUES ('faq52', '반품정보확인 / 변경')
  INTO board_type (bt_type, bt_name) VALUES ('faq53', '교환AS신청 / 철회')
  INTO board_type (bt_type, bt_name) VALUES ('faq54', '교환정보확인 / 변경')
  INTO board_type (bt_type, bt_name) VALUES ('faq55', '취소신청 / 철회')
  INTO board_type (bt_type, bt_name) VALUES ('faq56', '취소확인 / 환불정보')
  INTO board_type (bt_type, bt_name) VALUES ('faq60', '여행 / 숙박 / 항공')   --여행/숙박/항공
  INTO board_type (bt_type, bt_name) VALUES ('faq61', '여행 / 숙박')
  INTO board_type (bt_type, bt_name) VALUES ('faq62', '항공')
  INTO board_type (bt_type, bt_name) VALUES ('faq70', '안전거래')           --안전거래
  INTO board_type (bt_type, bt_name) VALUES ('faq71', '서비스 이용규칙 위반')
  INTO board_type (bt_type, bt_name) VALUES ('faq72', '지식재산권침해')
  INTO board_type (bt_type, bt_name) VALUES ('faq73', '법령 및 정책위반 상품')
  INTO board_type (bt_type, bt_name) VALUES ('faq74', '게시물 정책위반')
  INTO board_type (bt_type, bt_name) VALUES ('faq75', '직거래 / 외부거래유도')
  INTO board_type (bt_type, bt_name) VALUES ('faq76', '표시광고')
  INTO board_type (bt_type, bt_name) VALUES ('faq77', '청소년 위해상품 / 이미지')
  INTO board_type (bt_type, bt_name) VALUES ('qna00', 'QNA')               --■문의
  INTO board_type (bt_type, bt_name) VALUES ('qna10', '회원')              --회원
  INTO board_type (bt_type, bt_name) VALUES ('qna11', '가입')
  INTO board_type (bt_type, bt_name) VALUES ('qna12', '탈퇴')
  INTO board_type (bt_type, bt_name) VALUES ('qna13', '회원정보')
  INTO board_type (bt_type, bt_name) VALUES ('qna14', '로그인')
  INTO board_type (bt_type, bt_name) VALUES ('qna20', '쿠폰 / 이벤트')      --쿠폰/이벤트
  INTO board_type (bt_type, bt_name) VALUES ('qna21', '쿠폰 / 할인혜택')
  INTO board_type (bt_type, bt_name) VALUES ('qna22', '포인트')
  INTO board_type (bt_type, bt_name) VALUES ('qna23', '제휴')
  INTO board_type (bt_type, bt_name) VALUES ('qna24', '이벤트')
  INTO board_type (bt_type, bt_name) VALUES ('qna30', '주문 / 결제')        --주문/결제
  INTO board_type (bt_type, bt_name) VALUES ('qna31', '상품')
  INTO board_type (bt_type, bt_name) VALUES ('qna32', '결제')
  INTO board_type (bt_type, bt_name) VALUES ('qna33', '구매내역')
  INTO board_type (bt_type, bt_name) VALUES ('qna34', '영수증/증빙')
  INTO board_type (bt_type, bt_name) VALUES ('qna40', '배송')              --배송
  INTO board_type (bt_type, bt_name) VALUES ('qna41', '배송상태/기간')
  INTO board_type (bt_type, bt_name) VALUES ('qna42', '배송정보확인/변경')
  INTO board_type (bt_type, bt_name) VALUES ('qna43', '해외배송')
  INTO board_type (bt_type, bt_name) VALUES ('qna44', '당일배송')
  INTO board_type (bt_type, bt_name) VALUES ('qna45', '해외직구')
  INTO board_type (bt_type, bt_name) VALUES ('qna50', '취소 / 반품 / 교환')  --취소/반품/교환
  INTO board_type (bt_type, bt_name) VALUES ('qna51', '반품신청 / 철회')
  INTO board_type (bt_type, bt_name) VALUES ('qna52', '반품정보확인 / 변경')
  INTO board_type (bt_type, bt_name) VALUES ('qna53', '교환AS신청 / 철회')
  INTO board_type (bt_type, bt_name) VALUES ('qna54', '교환정보확인 / 변경')
  INTO board_type (bt_type, bt_name) VALUES ('qna55', '취소신청 / 철회')
  INTO board_type (bt_type, bt_name) VALUES ('qna56', '취소확인 / 환불정보')
  INTO board_type (bt_type, bt_name) VALUES ('qna60', '여행 / 숙박 / 항공')  --여행/숙박/항공
  INTO board_type (bt_type, bt_name) VALUES ('qna61', '여행 / 숙박')
  INTO board_type (bt_type, bt_name) VALUES ('qna62', '항공')
  INTO board_type (bt_type, bt_name) VALUES ('qna70', '안전거래')          --안전거래
  INTO board_type (bt_type, bt_name) VALUES ('qna71', '서비스 이용규칙 위반')
  INTO board_type (bt_type, bt_name) VALUES ('qna72', '지식재산권침해')
  INTO board_type (bt_type, bt_name) VALUES ('qna73', '법령 및 정책위반 상품')
  INTO board_type (bt_type, bt_name) VALUES ('qna74', '게시물 정책위반')
  INTO board_type (bt_type, bt_name) VALUES ('qna75', '직거래 / 외부거래유도')
  INTO board_type (bt_type, bt_name) VALUES ('qna76', '표시광고')
  INTO board_type (bt_type, bt_name) VALUES ('qna77', '청소년 위해상품 / 이미지')
  INTO board_type (bt_type, bt_name) VALUES ('ver00', 'version')        --■버전
SELECT * FROM DUAL;

//SELECT * FROM board_type;
//DELETE FROM board_type;



-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲

-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ COUPON_TYPE   ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
INSERT ALL
  INTO coupon_type (ct_type) VALUES ('개별상품할인')
  INTO coupon_type (ct_type) VALUES ('주문상품할인')
  INTO coupon_type (ct_type) VALUES ('배송비무료')
SELECT * FROM DUAL;

//SELECT * FROM coupon_type;
//DELETE FROM coupon_type;


-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲

-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ PRODUCT_TAGS   ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
INSERT ALL
  INTO product_tags (pt_name, pt_state) VALUES ('히트상품', 'active')
  INTO product_tags (pt_name, pt_state) VALUES ('추천상품', 'active')
  INTO product_tags (pt_name, pt_state) VALUES ('최신상품', 'active')
  INTO product_tags (pt_name, pt_state) VALUES ('인기상품', 'active')
  INTO product_tags (pt_name, pt_state) VALUES ('할인상품', 'active')
SELECT * FROM DUAL;

//SELECT * FROM product_tags;
//DELETE FROM product_tags;

-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲

-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ SELLER_INFO   ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
INSERT ALL
  INTO seller_info (s_u_id, s_company_name, s_seller_no, s_sales_reg_num, s_tel, s_fax, s_state, s_seller_type) 
  VALUES ('seller01', '(주)그린상회', '123-45-67890', '2025-서울강남-01234', '02-1111-1111', '02-1111-1112', '운영중', '법인사업자')
  
  INTO seller_info (s_u_id, s_company_name, s_seller_no, s_sales_reg_num, s_tel, s_fax, s_state, s_seller_type) 
  VALUES ('seller02', '셀러투 상사', '234-56-78901', '2025-부산해운-05678', '051-2222-2222', '051-2222-2223', '운영준비', '개인사업자')
  
  INTO seller_info (s_u_id, s_company_name, s_seller_no, s_sales_reg_num, s_tel, s_fax, s_state, s_seller_type) 
  VALUES ('seller03', '무한상회', '234-56-88888', '2025-부산해운-05111', '051-3333-3333', '051-3333-3334', '운영중지', '개인사업자')
SELECT * FROM DUAL;

//SELECT * FROM seller_info;
//DELETE FROM seller_info;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲



-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ PRODUCT_ITEM   ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
BEGIN
  -- seller01 : 패션/가전 (10개)
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('베이직 반팔 티셔츠', '코튼 100%', 19900, 10, 200, 100, 3000, 'seller01', '100001', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('오버핏 청바지', '데님 전문', 59000, 15, 600, 100, 3000, 'seller01', '100001', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('수분 촉촉 크림', '뷰티 전문가', 25000, 0, 250, 100, 0, 'seller01', '100002', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('소가죽 벨트', '수제작 전문', 45000, 5, 450, 100, 3000, 'seller01', '100001', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('미니 공기청정기', '클린 가전', 89000, 20, 900, 100, 0, 'seller01', '200001', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('무선청소기 V1', '강력 흡입', 210000, 10, 2100, 100, 0, 'seller01', '200001', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('게이밍 마우스', '프로게이머 에디션', 65000, 0, 650, 100, 3000, 'seller01', '200001', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('기계식 키보드', '타건감 GOOD', 110000, 10, 1100, 100, 3000, 'seller01', '200001', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('선명한 4K 모니터', '고화질 전문', 320000, 5, 3200, 100, 0, 'seller01', '200001', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('데일리 립스틱', '인생컬러', 18000, 0, 180, 100, 3000, 'seller01', '100002', 0);

  -- seller02 : 식품/홈 (10개)
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('유기농 ABC 주스', '건강한 주스', 22000, 0, 220, 100, 3000, 'seller02', '300001', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('고소한 견과류 믹스', '매일 한봉', 34000, 10, 340, 100, 3000, 'seller02', '300001', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('친환경 주방세제', '안심 세제', 9900, 5, 100, 100, 3000, 'seller02', '300001', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('부드러운 각티슈', '먼지없는 티슈', 15000, 0, 150, 100, 3000, 'seller02', '300001', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('암막 커튼 세트', '꿀잠 필수템', 78000, 10, 780, 100, 0, 'seller02', '400002', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('푹신한 메모리폼 베개', '숙면 베개', 42000, 0, 420, 100, 3000, 'seller02', '400002', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('부드러운 만년필', '사각사각 필기감', 35000, 5, 350, 100, 3000, 'seller02', '400001', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('다이어리 꾸미기 세트', '다꾸 용품', 18000, 0, 180, 100, 3000, 'seller02', '400001', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('입문용 통기타', '최고의 취미', 150000, 10, 1500, 100, 0, 'seller02', '400002', 0);
  INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
  VALUES ('수채화 물감 24색', '전문가용', 55000, 0, 550, 100, 3000, 'seller02', '400002', 0);
END;
/



//SELECT * FROM PRODUCT_ITEM;
//DELETE FROM PRODUCT_ITEM;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲


-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ PRODUCT_DETAIL  ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
BEGIN
  -- 1단계: 전체 상품 중 약 30%(6개)에 대해 '사업자 유형', '브랜드', '원산지' 값을 채웁니다.
  -- 나머지 컬럼은 DEFAULT 값으로 자동 입력됩니다.
  INSERT INTO product_detail (
      pd_p_pid,
      pd_s_seller_type,
      pd_brand,
      pd_origin
  )
  SELECT
      pi.p_pid,
      si.s_seller_type,
      -- CASE 문을 사용해 상품 이름에 따라 '브랜드'를 동적으로 생성
      CASE
          WHEN pi.p_name LIKE '%티셔츠%' OR pi.p_name LIKE '%청바지%' THEN '데일리웨어'
          WHEN pi.p_name LIKE '%모니터%' OR pi.p_name LIKE '%키보드%' THEN 'ProDisplay'
          WHEN pi.p_name LIKE '%주스%' OR pi.p_name LIKE '%견과류%' THEN '오가닉푸드'
          ELSE '자체브랜드'
      END AS pd_brand,
      -- CASE 문을 사용해 카테고리 ID에 따라 '원산지'를 동적으로 생성
      CASE
          WHEN pi.p_pc_id LIKE '3%' THEN '국내산' -- 식품 카테고리
          WHEN pi.p_pc_id LIKE '2%' THEN '중국'   -- 가전/디지털 카테고리
          ELSE '대한민국'
      END AS pd_origin
  FROM
      (SELECT * FROM product_item WHERE ROWNUM <= 6) pi
  JOIN
      seller_info si ON pi.p_seller_id = si.s_u_id;

  -- 2단계: 나머지 70%(14개) 상품에 대해 더 다양한 상세 정보를 채웁니다. (이전 쿼리와 동일)
  INSERT INTO product_detail (
      pd_p_pid, 
      pd_s_seller_type, 
      pd_brand, 
      pd_origin,
      pd_care, 
      pd_warranty_info, 
      pd_as, 
      pd_phone
  )
  SELECT
      pi.p_pid,
      si.s_seller_type,
      CASE
          WHEN pi.p_name LIKE '%티셔츠%' OR pi.p_name LIKE '%청바지%' THEN '데일리웨어'
          WHEN pi.p_name LIKE '%모니터%' OR pi.p_name LIKE '%키보드%' THEN 'ProDisplay'
          WHEN pi.p_name LIKE '%주스%' OR pi.p_name LIKE '%견과류%' THEN '오가닉푸드'
          ELSE '자체브랜드'
      END,
      CASE
          WHEN pi.p_pc_id LIKE '3%' THEN '국내산'
          WHEN pi.p_pc_id LIKE '2%' THEN '중국'
          ELSE '대한민국'
      END,
      CASE
          WHEN pi.p_pc_id LIKE '1%' THEN '첫 세탁은 드라이클리닝을 권장합니다.'
          ELSE '제품 상세페이지 참조'
      END,
      CASE
          WHEN pi.p_pc_id LIKE '2%' THEN '구매일로부터 1년 무상 보증 (소비자 과실 제외)'
          ELSE '소비자분쟁해결기준에 따름'
      END,
      si.s_company_name,
      si.s_tel
  FROM
      product_item pi
  JOIN
      seller_info si ON pi.p_seller_id = si.s_u_id
  WHERE
      pi.p_pid NOT IN (SELECT pd_p_pid FROM product_detail);
END;
/




//SELECT * FROM PRODUCT_DETAIL;
//DELETE FROM PRODUCT_DETAIL;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲


-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ PRODUCT_OPTION   ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
DECLARE
    -- seller01 상품들의 PID를 저장할 변수 선언
    v_pid_tshirt        VARCHAR2(50);
    v_pid_jeans         VARCHAR2(50);
    v_pid_cream         VARCHAR2(50);
    v_pid_belt          VARCHAR2(50);
    v_pid_purifier      VARCHAR2(50);
    v_pid_vacuum        VARCHAR2(50);
    v_pid_mouse         VARCHAR2(50);
    v_pid_keyboard      VARCHAR2(50);
    v_pid_monitor       VARCHAR2(50);
    v_pid_lipstick      VARCHAR2(50);

    -- seller02 상품들의 PID를 저장할 변수 선언
    v_pid_juice         VARCHAR2(50);
    v_pid_nuts          VARCHAR2(50);
    v_pid_detergent     VARCHAR2(50);
    v_pid_tissue        VARCHAR2(50);
    v_pid_curtain       VARCHAR2(50);
    v_pid_pillow        VARCHAR2(50);
    v_pid_pen           VARCHAR2(50);
    v_pid_deco_set      VARCHAR2(50);
    v_pid_guitar        VARCHAR2(50);
    v_pid_paint         VARCHAR2(50);
BEGIN
    -- 각 상품 이름으로 P_PID를 조회하여 변수에 할당
    SELECT p_pid INTO v_pid_tshirt FROM product_item WHERE p_name = '베이직 반팔 티셔츠';
    SELECT p_pid INTO v_pid_jeans FROM product_item WHERE p_name = '오버핏 청바지';
    SELECT p_pid INTO v_pid_cream FROM product_item WHERE p_name = '수분 촉촉 크림';
    SELECT p_pid INTO v_pid_belt FROM product_item WHERE p_name = '소가죽 벨트';
    SELECT p_pid INTO v_pid_purifier FROM product_item WHERE p_name = '미니 공기청정기';
    SELECT p_pid INTO v_pid_vacuum FROM product_item WHERE p_name = '무선청소기 V1';
    SELECT p_pid INTO v_pid_mouse FROM product_item WHERE p_name = '게이밍 마우스';
    SELECT p_pid INTO v_pid_keyboard FROM product_item WHERE p_name = '기계식 키보드';
    SELECT p_pid INTO v_pid_monitor FROM product_item WHERE p_name = '선명한 4K 모니터';
    SELECT p_pid INTO v_pid_lipstick FROM product_item WHERE p_name = '데일리 립스틱';
    
    SELECT p_pid INTO v_pid_juice FROM product_item WHERE p_name = '유기농 ABC 주스';
    SELECT p_pid INTO v_pid_nuts FROM product_item WHERE p_name = '고소한 견과류 믹스';
    SELECT p_pid INTO v_pid_detergent FROM product_item WHERE p_name = '친환경 주방세제';
    SELECT p_pid INTO v_pid_tissue FROM product_item WHERE p_name = '부드러운 각티슈';
    SELECT p_pid INTO v_pid_curtain FROM product_item WHERE p_name = '암막 커튼 세트';
    SELECT p_pid INTO v_pid_pillow FROM product_item WHERE p_name = '푹신한 메모리폼 베개';
    SELECT p_pid INTO v_pid_pen FROM product_item WHERE p_name = '부드러운 만년필';
    SELECT p_pid INTO v_pid_deco_set FROM product_item WHERE p_name = '다이어리 꾸미기 세트';
    SELECT p_pid INTO v_pid_guitar FROM product_item WHERE p_name = '입문용 통기타';
    SELECT p_pid INTO v_pid_paint FROM product_item WHERE p_name = '수채화 물감 24색';

    -- ■■■■■■■■■■■■■■■■■■■■■■ 옵션 데이터 INSERT 시작 ■■■■■■■■■■■■■■■■■■■■■■
    
    -- 1. 베이직 반팔 티셔츠 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_tshirt, '색상', '블랙');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_tshirt, '색상', '화이트');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_tshirt, '사이즈', 'M');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_tshirt, '사이즈', 'L');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION, POP_ADD_PRICE) VALUES (v_pid_tshirt, '사이즈', 'XL', 2000);

    -- 2. 오버핏 청바지 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_jeans, '색상', '인디고');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_jeans, '색상', '블랙');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_jeans, '사이즈', '28');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_jeans, '사이즈', '30');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_jeans, '사이즈', '32');

    -- 3. 수분 촉촉 크림 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_cream, '타입', '건성용');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_cream, '타입', '지성용');

    -- 4. 소가죽 벨트 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_belt, '색상', '블랙');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_belt, '색상', '브라운');

    -- 5. 미니 공기청정기 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_purifier, '색상', '화이트');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION, POP_ADD_PRICE) VALUES (v_pid_purifier, '필터', '추가필터', 15000);
    
    -- 6. 무선청소기 V1 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_vacuum, '색상', '실버');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION, POP_ADD_PRICE) VALUES (v_pid_vacuum, '패키지', '액세서리 풀패키지', 50000);

    -- 7. 게이밍 마우스 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_mouse, '연결방식', '유선');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_mouse, '연결방식', '무선');

    -- 8. 기계식 키보드 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_keyboard, '축 타입', '청축');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_keyboard, '축 타입', '갈축');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_keyboard, '축 타입', '적축');

    -- 9. 선명한 4K 모니터 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_monitor, '크기', '27인치');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION, POP_ADD_PRICE) VALUES (v_pid_monitor, '크기', '32인치', 120000);

    -- 10. 데일리 립스틱 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_lipstick, '색상', '로즈핑크');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_lipstick, '색상', '코랄오렌지');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_lipstick, '색상', '리얼레드');

    -- 11. 유기농 ABC 주스 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_juice, '수량', '10팩');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_juice, '수량', '30팩');

    -- 12. 고소한 견과류 믹스 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_nuts, '포장', '벌크');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_nuts, '포장', '개별포장');

    -- 13. 친환경 주방세제 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_detergent, '향', '레몬');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_detergent, '향', '무향');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION, POP_ADD_PRICE) VALUES (v_pid_detergent, '용량', '1L(리필)', 4000);

    -- 14. 부드러운 각티슈 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_tissue, '수량', '3개입');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_tissue, '수량', '6개입');

    -- 15. 암막 커튼 세트 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_curtain, '색상', '차콜');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_curtain, '색상', '베이지');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_curtain, '사이즈', '소(140x230cm)');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION, POP_ADD_PRICE) VALUES (v_pid_curtain, '사이즈', '대(200x230cm)', 25000);

    -- 16. 푹신한 메모리폼 베개 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_pillow, '타입', '기본형');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_pillow, '타입', '경추형');

    -- 17. 부드러운 만년필 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_pen, '펜촉 굵기', 'EF촉');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_pen, '펜촉 굵기', 'F촉');

    -- 18. 다이어리 꾸미기 세트 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_deco_set, '구성', '스티커팩');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION, POP_ADD_PRICE) VALUES (v_pid_deco_set, '구성', '풀세트(스티커+마테)', 8000);

    -- 19. 입문용 통기타 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_guitar, '타입', '어쿠스틱');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION, POP_ADD_PRICE) VALUES (v_pid_guitar, '패키지', '풀 패키지(튜너+가방+피크)', 30000);

    -- 20. 수채화 물감 24색 옵션
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_paint, '타입', '튜브형');
    INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_pid_paint, '타입', '고체형');

END;
/



//SELECT * FROM PRODUCT_ITEM;
//SELECT * FROM PRODUCT_OPTION;
//DELETE FROM PRODUCT_OPTION;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲


-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ version  ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
INSERT INTO version (
    v_vname, 
    v_uploader, 
    v_body, 
    v_title, 
    v_sub_title,
    v_company, 
    v_ceo, 
    v_seller_reg_no, 
    v_online_sales_reg_no,
    v_addr, 
    v_tel, 
    v_act_time, 
    v_email, 
    v_manager_phone, 
    v_copylight
) VALUES (
    'v1.0.0 Release', 
    'admin01', 
    '사이트 정식 오픈 버전입니다. 기본 기능이 포함되어 있습니다.',
    'Green Market', 
    '신선하고 안전한 온라인 마켓', 
    '(주)그린커머스', 
    '홍길동',
    '123-45-67890', 
    '2025-부산해운-00123', 
    '부산광역시 해운대구 센텀중앙로 78',
    '051-123-4567', 
    '평일 09:00 ~ 18:00 (점심시간 12:00 ~ 13:00)',
    'contact@greenmarket.com', 
    '010-1234-5678 (담당자: 김철수)',
    'Copyright © Green Commerce Inc. All rights reserved.'
);

//SELECT * FROM version;
//DELETE FROM version;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲


-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■  PRODUCT_TAGS ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
INSERT ALL
  INTO product_tags (pt_name) VALUES ('히트상품')
  INTO product_tags (pt_name) VALUES ('추천상품')
  INTO product_tags (pt_name) VALUES ('최신상품')
  INTO product_tags (pt_name) VALUES ('인기상품')
  INTO product_tags (pt_name) VALUES ('할인상품')
SELECT * FROM DUAL;



//SELECT * FROM PRODUCT_TAGS;
//DELETE FROM PRODUCT_TAGS;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲


-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ TAGS_LINKS   ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
DECLARE
    -- 루프를 돌면서 상품의 PID와 순번(ROWNUM)을 가져오는 커서(Cursor)를 정의합니다.
    CURSOR product_cursor IS
        SELECT p_pid, ROWNUM AS rn
        FROM (SELECT p_pid FROM product_item ORDER BY p_pid);
BEGIN
    -- 20개 상품 전체를 대상으로 루프를 실행합니다.
    FOR item IN product_cursor LOOP

        -- 1. 상위 30% (6개)의 상품에 대해서는 태그를 2개씩 연결합니다.
        IF item.rn <= 6 THEN
            -- 순번에 따라 다른 태그 조합을 부여합니다.
            IF item.rn IN (1, 4) THEN
                INSERT INTO tags_links (tl_p_pid, tl_pt_name) VALUES (item.p_pid, '히트상품');
                INSERT INTO tags_links (tl_p_pid, tl_pt_name) VALUES (item.p_pid, '추천상품');
            ELSIF item.rn IN (2, 5) THEN
                INSERT INTO tags_links (tl_p_pid, tl_pt_name) VALUES (item.p_pid, '최신상품');
                INSERT INTO tags_links (tl_p_pid, tl_pt_name) VALUES (item.p_pid, '인기상품');
            ELSE -- item.rn IN (3, 6)
                INSERT INTO tags_links (tl_p_pid, tl_pt_name) VALUES (item.p_pid, '할인상품');
                INSERT INTO tags_links (tl_p_pid, tl_pt_name) VALUES (item.p_pid, '히트상품');
            END IF;
            
        -- 2. 나머지 70% (14개)의 상품에 대해서는 태그를 1개씩 연결합니다.
        ELSE
            -- MOD 함수를 사용해 5개의 태그를 순서대로 하나씩 부여합니다.
            -- 이렇게 하면 모든 태그가 최소 1번 이상 사용되는 것이 보장됩니다.
            IF MOD(item.rn, 5) = 0 THEN
                INSERT INTO tags_links (tl_p_pid, tl_pt_name) VALUES (item.p_pid, '히트상품');
            ELSIF MOD(item.rn, 5) = 1 THEN
                INSERT INTO tags_links (tl_p_pid, tl_pt_name) VALUES (item.p_pid, '추천상품');
            ELSIF MOD(item.rn, 5) = 2 THEN
                INSERT INTO tags_links (tl_p_pid, tl_pt_name) VALUES (item.p_pid, '최신상품');
            ELSIF MOD(item.rn, 5) = 3 THEN
                INSERT INTO tags_links (tl_p_pid, tl_pt_name) VALUES (item.p_pid, '인기상품');
            ELSE -- MOD(item.rn, 5) = 4
                INSERT INTO tags_links (tl_p_pid, tl_pt_name) VALUES (item.p_pid, '할인상품');
            END IF;
        END IF;

    END LOOP;
END;
/




//SELECT * FROM TAGS_LINKS;
//DELETE FROM TAGS_LINKS;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲


-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ CART_ITEMS   ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
DECLARE
    -- user01이 담을 상품들의 PID와 가격을 저장할 변수
    v_pid_tshirt    VARCHAR2(50);
    v_price_tshirt  NUMBER;
    v_pid_jeans     VARCHAR2(50);
    v_price_jeans   NUMBER;
    v_pid_cream     VARCHAR2(50);
    v_price_cream   NUMBER;
    
    -- user02가 담을 상품들의 PID와 가격을 저장할 변수
    v_pid_belt      VARCHAR2(50);
    v_price_belt    NUMBER;
    v_pid_purifier  VARCHAR2(50);
    v_price_purifier NUMBER;
BEGIN
    -- 1. 상품 이름으로 상품 ID(p_pid)와 가격(p_price)을 조회하여 변수에 저장합니다.
    SELECT p_pid, p_price INTO v_pid_tshirt, v_price_tshirt FROM product_item WHERE p_name = '베이직 반팔 티셔츠';
    SELECT p_pid, p_price INTO v_pid_jeans, v_price_jeans FROM product_item WHERE p_name = '오버핏 청바지';
    SELECT p_pid, p_price INTO v_pid_cream, v_price_cream FROM product_item WHERE p_name = '수분 촉촉 크림';
    
    SELECT p_pid, p_price INTO v_pid_belt, v_price_belt FROM product_item WHERE p_name = '소가죽 벨트';
    SELECT p_pid, p_price INTO v_pid_purifier, v_price_purifier FROM product_item WHERE p_name = '미니 공기청정기';

    -- 2. 조회된 상품 정보를 바탕으로 user01의 장바구니 데이터를 추가합니다.
    INSERT INTO cart_items (ci_u_id, ci_p_pid, ci_amount, ci_tot_price)
    VALUES ('user01', v_pid_tshirt, 1, v_price_tshirt);
    
    INSERT INTO cart_items (ci_u_id, ci_p_pid, ci_amount, ci_tot_price)
    VALUES ('user01', v_pid_jeans, 1, v_price_jeans);
    
    INSERT INTO cart_items (ci_u_id, ci_p_pid, ci_amount, ci_tot_price)
    VALUES ('user01', v_pid_cream, 1, v_price_cream);

    -- 3. 조회된 상품 정보를 바탕으로 user02의 장바구니 데이터를 추가합니다.
    INSERT INTO cart_items (ci_u_id, ci_p_pid, ci_amount, ci_tot_price)
    VALUES ('user02', v_pid_belt, 1, v_price_belt);
    
    INSERT INTO cart_items (ci_u_id, ci_p_pid, ci_amount, ci_tot_price)
    VALUES ('user02', v_pid_purifier, 1, v_price_purifier);
END;
/





//SELECT * FROM CART_ITEMS;
//DELETE FROM CART_ITEMS;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲


-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ COUPON   ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
BEGIN
  -- seller01이 발급하는 쿠폰 3종
  INSERT INTO coupon (c_ct_type, c_name, c_text, c_s_u_id, c_amount, c_used, c_status, c_start_day, c_end_day, c_dis_money, c_dis_delivery)
  VALUES ('개별상품할인', '티셔츠 10% 할인', 'seller01의 의류 상품에만 적용', 'seller01', 100, 0, '발급가능', SYSDATE, SYSDATE + 30, '10%', NULL);
  
  INSERT INTO coupon (c_ct_type, c_name, c_text, c_s_u_id, c_amount, c_used, c_status, c_start_day, c_end_day, c_dis_money, c_dis_delivery)
  VALUES ('주문상품할인', '5만원 이상 5천원 할인', 'seller01 상품 5만원 이상 구매 시', 'seller01', 100, 0, '발급가능', SYSDATE, SYSDATE + 30, '5000', NULL);
  
  INSERT INTO coupon (c_ct_type, c_name, c_text, c_s_u_id, c_amount, c_used, c_status, c_start_day, c_end_day, c_dis_money, c_dis_delivery)
  VALUES ('배송비무료', 'seller01 무료배송 쿠폰', '배송비를 할인해드립니다', 'seller01', 100, 0, '발급가능', SYSDATE, SYSDATE + 30, NULL, '무료');

  -- seller02가 발급하는 쿠폰 3종
  INSERT INTO coupon (c_ct_type, c_name, c_text, c_s_u_id, c_amount, c_used, c_status, c_start_day, c_end_day, c_dis_money, c_dis_delivery)
  VALUES ('개별상품할인', '견과류 1000원 할인', '고소한 견과류 믹스 상품 전용', 'seller02', 100, 0, '발급가능', SYSDATE, SYSDATE + 60, '1000', NULL);
  
  INSERT INTO coupon (c_ct_type, c_name, c_text, c_s_u_id, c_amount, c_used, c_status, c_start_day, c_end_day, c_dis_money, c_dis_delivery)
  VALUES ('주문상품할인', '3만원 이상 15% 할인', 'seller02 상품 3만원 이상 구매 시', 'seller02', 100, 0, '발급가능', SYSDATE, SYSDATE + 60, '15%', NULL);
  
  INSERT INTO coupon (c_ct_type, c_name, c_text, c_s_u_id, c_amount, c_used, c_status, c_start_day, c_end_day, c_dis_money, c_dis_delivery)
  VALUES ('배송비무료', 'seller02 상품 무료배송', '주문 금액과 상관없이 배송비 무료', 'seller02', 100, 0, '발급가능', SYSDATE, SYSDATE + 60, NULL, '무료');
END;
/





//SELECT * FROM COUPON;
//DELETE FROM COUPON;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲

-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ PRODUCT_ORDER   ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
DECLARE
    -- 조회 및 계산 결과를 저장할 변수 선언
    v_price_tshirt      NUMBER;
    v_discount_tshirt   NUMBER;
    v_point_tshirt      NUMBER;
    
    v_price_jeans       NUMBER;
    v_discount_jeans    NUMBER;
    v_point_jeans       NUMBER;
    
    v_recipient         u_user.u_name%TYPE;
    v_phone             u_user.u_phone%TYPE;
    v_postal            u_user.u_postal%TYPE;
    v_base_addr         u_user.u_base_addr%TYPE;
    v_detail_addr       u_user.u_detail_addr%TYPE;
    
    v_sum_price         NUMBER;
    v_total_discount    NUMBER;
    v_total_price       NUMBER;
    v_get_point         NUMBER;
    
BEGIN
    -- 1. 주문할 상품들의 정보를 PRODUCT_ITEM 테이블에서 조회
    SELECT p_price, p_discount, p_point INTO v_price_tshirt, v_discount_tshirt, v_point_tshirt
    FROM product_item WHERE p_name = '베이직 반팔 티셔츠';
    
    SELECT p_price, p_discount, p_point INTO v_price_jeans, v_discount_jeans, v_point_jeans
    FROM product_item WHERE p_name = '오버핏 청바지';

    -- 2. 주문자(user01)의 배송 관련 정보를 U_USER 테이블에서 조회
    SELECT u_name, u_phone, u_postal, u_base_addr, u_detail_addr
    INTO v_recipient, v_phone, v_postal, v_base_addr, v_detail_addr
    FROM u_user WHERE u_id = 'user01';

    -- 3. 조회된 정보를 바탕으로 주문 금액 계산
    v_sum_price := v_price_tshirt + v_price_jeans; -- 총 상품금액
    v_total_discount := TRUNC(v_price_tshirt * v_discount_tshirt / 100) + TRUNC(v_price_jeans * v_discount_jeans / 100); -- 총 할인금액
    v_get_point := v_point_tshirt + v_point_jeans; -- 총 적립 포인트
    v_total_price := (v_sum_price - v_total_discount) + 3000; -- 전체 주문 금액 (상품가 - 할인 + 배송비)

    -- 4. 계산된 최종 값으로 PRODUCT_ORDER 테이블에 데이터 삽입
    INSERT INTO product_order (
        po_u_id, po_pay_type, po_pay_detail, po_recipient, po_re_phone,
        po_item_count, po_sum_price, po_discount, po_delivery_price,
        po_pri_discount, po_tot_price, po_get_point, po_state,
        po_postal, po_base_addr, po_detail_addr, po_request_note
    ) VALUES (
        'user01', '신용카드', '현대카드 (1234-****-****-5678)', v_recipient, v_phone,
        2, v_sum_price, v_total_discount, 3000,
        0, v_total_price, v_get_point, '결제완료',
        v_postal, v_base_addr, v_detail_addr, '부재 시 문 앞에 놓아주세요.'
    );
END;
/


//SELECT * FROM PRODUCT_ORDER;
//SELECT * FROM ORDER_ITEMS;
//DELETE FROM PRODUCT_ORDER;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲


-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ ORDER_ITEMS   ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
DECLARE
    -- 방금 생성한 user01의 가장 최신 주문번호를 저장할 변수
    v_po_no           product_order.po_no%TYPE;
    
    -- 주문한 상품들의 정보를 저장할 변수
    v_pid_tshirt      product_item.p_pid%TYPE;
    v_seller_tshirt   product_item.p_seller_id%TYPE;
    v_price_tshirt    product_item.p_price%TYPE;
    
    v_pid_jeans       product_item.p_pid%TYPE;
    v_seller_jeans    product_item.p_seller_id%TYPE;
    v_price_jeans     product_item.p_price%TYPE;

BEGIN
    -- 1. user01의 가장 최근 주문 번호(po_no)를 조회하여 변수에 저장합니다.
    --    (같은 유저가 여러 번 주문했을 경우를 대비해 MAX()를 사용)
    SELECT MAX(po_no) INTO v_po_no 
    FROM product_order 
    WHERE po_u_id = 'user01';

    -- 2. 주문한 상품들의 상세 정보(상품ID, 판매자ID, 가격)를 조회하여 변수에 저장합니다.
    SELECT p_pid, p_seller_id, p_price INTO v_pid_tshirt, v_seller_tshirt, v_price_tshirt
    FROM product_item WHERE p_name = '베이직 반팔 티셔츠';
    
    SELECT p_pid, p_seller_id, p_price INTO v_pid_jeans, v_seller_jeans, v_price_jeans
    FROM product_item WHERE p_name = '오버핏 청바지';

    -- 3. 조회된 정보를 바탕으로 ORDER_ITEMS 테이블에 각 상품에 대한 데이터를 개별적으로 추가합니다.
    -- '베이직 반팔 티셔츠' 주문 내역
    INSERT INTO order_items (
        oi_po_no, oi_p_pid, oi_s_u_id, oi_prod_qty, oi_tot_price, 
        oi_tracking_company, oi_tracking_num, oi_etc, oi_del_status
    ) VALUES (
        v_po_no, v_pid_tshirt, v_seller_tshirt, 1, v_price_tshirt,
        NULL, NULL, NULL, '상품준비중'
    );
    
    -- '오버핏 청바지' 주문 내역
    INSERT INTO order_items (
        oi_po_no, oi_p_pid, oi_s_u_id, oi_prod_qty, oi_tot_price, 
        oi_tracking_company, oi_tracking_num, oi_etc, oi_del_status
    ) VALUES (
        v_po_no, v_pid_jeans, v_seller_jeans, 1, v_price_jeans,
        NULL, NULL, NULL, '상품준비중'
    );
END;
/

//SELECT * FROM ORDER_ITEMS;
//DELETE FROM ORDER_ITEMS;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲



-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲

-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ 결제완료 시뮬레이션 USER01 // product_order & product_item ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
DECLARE
    -- 조회 및 계산 결과를 저장할 변수 선언
    v_po_no             product_order.po_no%TYPE;
    
    v_recipient         u_user.u_name%TYPE;
    v_phone             u_user.u_phone%TYPE;
    v_postal            u_user.u_postal%TYPE;
    v_base_addr         u_user.u_base_addr%TYPE;
    v_detail_addr       u_user.u_detail_addr%TYPE;
    
    -- 상품1: 기계식 키보드
    v_pid_keyboard      product_item.p_pid%TYPE;
    v_seller_keyboard   product_item.p_seller_id%TYPE;
    v_price_keyboard    product_item.p_price%TYPE;
    v_discount_keyboard product_item.p_discount%TYPE;
    v_point_keyboard    product_item.p_point%TYPE;
    
    -- 상품2: 게이밍 마우스
    v_pid_mouse         product_item.p_pid%TYPE;
    v_seller_mouse      product_item.p_seller_id%TYPE;
    v_price_mouse       product_item.p_price%TYPE;
    v_discount_mouse    product_item.p_discount%TYPE;
    v_point_mouse       product_item.p_point%TYPE;
    
    -- 계산용 변수
    v_sum_price         NUMBER;
    v_total_discount    NUMBER;
    v_total_price       NUMBER;
    v_get_point         NUMBER;
    
BEGIN
    -- 1. 주문자(user01)의 배송 관련 정보를 U_USER 테이블에서 조회합니다.
    SELECT u_name, u_phone, u_postal, u_base_addr, u_detail_addr
    INTO v_recipient, v_phone, v_postal, v_base_addr, v_detail_addr
    FROM u_user WHERE u_id = 'user01';

    -- 2. 주문할 상품들의 정보를 PRODUCT_ITEM 테이블에서 조회합니다.
    SELECT p_pid, p_seller_id, p_price, p_discount, p_point 
    INTO v_pid_keyboard, v_seller_keyboard, v_price_keyboard, v_discount_keyboard, v_point_keyboard
    FROM product_item WHERE p_name = '기계식 키보드';
    
    SELECT p_pid, p_seller_id, p_price, p_discount, p_point 
    INTO v_pid_mouse, v_seller_mouse, v_price_mouse, v_discount_mouse, v_point_mouse
    FROM product_item WHERE p_name = '게이밍 마우스';

    -- 3. 조회된 정보를 바탕으로 주문 요약 정보를 계산합니다.
    v_sum_price := v_price_keyboard + v_price_mouse;
    v_total_discount := TRUNC(v_price_keyboard * v_discount_keyboard / 100) + TRUNC(v_price_mouse * v_discount_mouse / 100);
    v_get_point := v_point_keyboard + v_point_mouse;
    v_total_price := (v_sum_price - v_total_discount) + 3000; -- 배송비 3000원

    -- 4. 계산된 값으로 PRODUCT_ORDER 테이블에 주문 요약 정보를 추가합니다. (po_state를 '배송완료'로 설정)
    INSERT INTO product_order (
        po_u_id, po_pay_type, po_pay_detail, po_recipient, po_re_phone,
        po_item_count, po_sum_price, po_discount, po_delivery_price,
        po_pri_discount, po_tot_price, po_get_point, po_state,
        po_postal, po_base_addr, po_detail_addr, po_request_note
    ) VALUES (
        'user01', '네이버페이', 'point/card', v_recipient, v_phone,
        2, v_sum_price, v_total_discount, 3000,
        0, v_total_price, v_get_point, '배송완료',
        v_postal, v_base_addr, v_detail_addr, '경비실에 맡겨주세요.'
    );

    -- 5. 방금 생성된 주문의 번호(po_no)를 다시 조회하여 변수에 저장합니다.
    SELECT MAX(po_no) INTO v_po_no 
    FROM product_order 
    WHERE po_u_id = 'user01';

    -- 6. 조회된 주문 번호를 사용하여 ORDER_ITEMS 테이블에 개별 상품 내역을 추가합니다.
    -- (oi_del_status를 '배송완료'로 설정하고, 운송장 정보 추가)
    INSERT INTO order_items (
        oi_po_no, oi_p_pid, oi_s_u_id, oi_prod_qty, oi_tot_price, 
        oi_tracking_company, oi_tracking_num, oi_etc, oi_del_status
    ) VALUES (
        v_po_no, v_pid_keyboard, v_seller_keyboard, 1, v_price_keyboard,
        'CJ대한통운', '123456789012', NULL, '배송완료'
    );
    
    INSERT INTO order_items (
        oi_po_no, oi_p_pid, oi_s_u_id, oi_prod_qty, oi_tot_price, 
        oi_tracking_company, oi_tracking_num, oi_etc, oi_del_status
    ) VALUES (
        v_po_no, v_pid_mouse, v_seller_mouse, 1, v_price_mouse,
        'CJ대한통운', '123456789012', '키보드와 합포장', '배송완료'
    );
    
END;
/





//SELECT * FROM PRODUCT_ORDER; -- 주문 요약 정보 확인(전체)
//SELECT * FROM ORDER_ITEMS; -- 주문 상세 내역 확인 (전체)
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲



-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ USER01 거래 시뮬레이션 // POINT_HISTORY & USER_POINT  ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
DECLARE
    -- 조회 및 계산 결과를 저장할 변수 선언
    v_po_no               product_order.po_no%TYPE;
    v_get_point           product_order.po_get_point%TYPE;
    v_current_points      u_user.u_point%TYPE;
    v_new_total_points    u_user.u_point%TYPE;
BEGIN
    -- 1. user01의 가장 최근 주문 번호를 조회합니다.
    SELECT MAX(po_no) INTO v_po_no 
    FROM product_order 
    WHERE po_u_id = 'user01';

    -- 2. 해당 주문으로 획득한 포인트를 조회합니다.
    SELECT po_get_point INTO v_get_point 
    FROM product_order 
    WHERE po_no = v_po_no;

    -- 3. user01의 현재 포인트를 U_USER 테이블에서 조회합니다.
    SELECT u_point INTO v_current_points 
    FROM u_user 
    WHERE u_id = 'user01';
    
    -- 4. 새로 획득한 포인트를 더해 최종 포인트를 계산합니다.
    v_new_total_points := v_current_points + v_get_point;

    -- 5. POINT_HISTORY 테이블에 포인트 적립 이력을 추가합니다. (uh_po_no 포함)
    INSERT INTO point_history (uh_u_id, uh_po_no, uh_text, uh_change_point)
    VALUES ('user01', v_po_no, '구매 적립', v_get_point);
    
    -- 6. USER_POINT 테이블에 user01의 최종 포인트를 업데이트합니다.
    MERGE INTO user_point up
    USING (SELECT 'user01' AS u_id FROM dual) new_data
    ON (up.up_u_id = new_data.u_id)
    WHEN MATCHED THEN
        UPDATE SET up.up_now_point = v_new_total_points
    WHEN NOT MATCHED THEN
        INSERT (up_u_id, up_now_point) VALUES ('user01', v_new_total_points);

    -- 7. (권장) U_USER 테이블의 포인트 정보도 일관성을 위해 함께 업데이트합니다.
    UPDATE u_user
    SET u_point = v_new_total_points
    WHERE u_id = 'user01';

END;
/


//SELECT * FROM POINT_HISTORY;
//SELECT * FROM USER_POINT;
//DELETE FROM POINT_HISTORY;
//DELETE FROM USER_POINT;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲



-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■  USER01 거래 시뮬레이션 ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
DECLARE
    -- user01의 가장 최근 주문번호와 사용한 쿠폰 ID를 저장할 변수
    v_po_no             product_order.po_no%TYPE;
    v_coupon_id         coupon.c_id%TYPE;
    v_delivery_price    product_order.po_delivery_price%TYPE;
BEGIN
    -- 1. user01의 가장 최근 주문 번호('배송완료' 건)를 조회합니다.
    SELECT MAX(po_no) INTO v_po_no 
    FROM product_order 
    WHERE po_u_id = 'user01';

    -- 2. user01이 사용할 '배송비무료' 쿠폰의 ID를 조회합니다.
    -- (seller01이 발행한 배송비무료 쿠폰 중 하나를 선택)
    SELECT c_id INTO v_coupon_id
    FROM coupon
    WHERE c_s_u_id = 'seller01' AND c_ct_type = '배송비무료' AND ROWNUM = 1;

    -- 3. 해당 주문의 배송비(할인될 금액)를 조회합니다.
    SELECT po_delivery_price INTO v_delivery_price
    FROM product_order
    WHERE po_no = v_po_no;

    -- 4. USER_COUPON 테이블에 쿠폰 사용 내역을 추가합니다.
    INSERT INTO user_coupon (uc_u_id, uc_c_id, uc_po_no, uc_status, uc_use_day)
    VALUES ('user01', v_coupon_id, v_po_no, '사용완료', SYSTIMESTAMP);

    -- 5. PRODUCT_ORDER 테이블의 주문 정보에 쿠폰 할인을 반영하여 업데이트합니다.
    UPDATE product_order
    SET 
        po_pri_discount = po_pri_discount + v_delivery_price, -- 추가 할인액에 배송비만큼 더해 줌
        po_tot_price = po_tot_price - v_delivery_price      -- 최종 결제액에서 배송비만큼 차감
    WHERE
        po_no = v_po_no;
END;
/


//SELECT * FROM USER_COUPON;
//SELECT * FROM PRODUCT_ORDER;
//select * FROM order_ITEMs;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲

-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ USER01 구매 시뮬레이션 // PRODUCT_REVIEW  ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
DECLARE
    -- user01의 가장 최근 주문번호와 리뷰를 작성할 상품들의 ID를 저장할 변수
    v_po_no         product_order.po_no%TYPE;
    v_pid_keyboard  product_item.p_pid%TYPE;
    v_pid_mouse     product_item.p_pid%TYPE;
BEGIN
    -- 1. 리뷰를 작성할 주문 건의 번호(PO_NO)를 조회합니다.
    SELECT MAX(po_no) INTO v_po_no 
    FROM product_order 
    WHERE po_u_id = 'user01';

    -- 2. 해당 주문에 포함된 각 상품의 ID(P_PID)를 조회합니다.
    SELECT p_pid INTO v_pid_keyboard FROM product_item WHERE p_name = '기계식 키보드';
    SELECT p_pid INTO v_pid_mouse FROM product_item WHERE p_name = '게이밍 마우스';

    -- 3. 조회된 정보를 바탕으로 각 상품에 대한 리뷰를 1개씩 추가합니다.
    -- (pr_no는 트리거가, pr_reg_date는 DEFAULT 옵션이 자동으로 처리)

    -- '기계식 키보드' 리뷰
    INSERT INTO product_review (pr_po_no, pr_p_pid, pr_u_id, pr_star, pr_body)
    VALUES (v_po_no, v_pid_keyboard, 'user01', 5.0, '타건감이 정말 좋고 디자인도 깔끔해서 만족합니다. 배송도 빨랐어요.');
    
    -- '게이밍 마우스' 리뷰
    INSERT INTO product_review (pr_po_no, pr_p_pid, pr_u_id, pr_star, pr_body)
    VALUES (v_po_no, v_pid_mouse, 'user01', 4.5, '그립감이 뛰어나고 DPI 설정도 간편해서 좋네요. 클릭 소리가 조금 큰 건 아쉽습니다.');

END;
/




SELECT * FROM PRODUCT_REVIEW;
//DELETE FROM PRODUCT_REVIEW;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲





-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ RECRUITMENT  ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
INSERT ALL
  INTO recruitment (r_dept, r_career, r_type, r_title, r_status, r_start_date, r_end_date)
  VALUES ('IT개발팀', '경력 (5년 이상)', '정규직', '[경력] Spring 기반 백엔드 개발자 채용', '모집중', TO_DATE('2025-10-15', 'YYYY-MM-DD'), TO_DATE('2025-11-14', 'YYYY-MM-DD'))
  
  INTO recruitment (r_dept, r_career, r_type, r_title, r_status, r_start_date, r_end_date)
  VALUES ('마케팅팀', '신입/경력 (1년 이하)', '인턴', '2025년 하반기 마케팅팀 신입 인턴 모집', '모집마감', TO_DATE('2025-09-01', 'YYYY-MM-DD'), TO_DATE('2025-09-30', 'YYYY-MM-DD'))
SELECT * FROM DUAL;




//SELECT * FROM RECRUITMENT;
//DELETE FROM RECRUITMENT;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲



-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ M_TERMS  ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
INSERT ALL
  INTO m_terms (t_name, t_body) VALUES ('구매회원 약관', '구매회원 약관 (임시) 내용입니다.')
  INTO m_terms (t_name, t_body) VALUES ('판매회원 약관', '판매회원 약관 (임시) 내용입니다.')
  INTO m_terms (t_name, t_body) VALUES ('전자금융거래 약관', '전자금융거래 약관 (임시) 내용입니다.')
  INTO m_terms (t_name, t_body) VALUES ('위치정보 이용약관', '위치정보 이용약관 (임시) 내용입니다.')
  INTO m_terms (t_name, t_body) VALUES ('개인정보 처리방침', '개인정보 처리방침 (임시) 내용입니다.')
SELECT * FROM DUAL;



SELECT * FROM M_TERMS;
//DELETE FROM M_TERMS;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲





-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ 더미 추가 1020 2차  ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
DECLARE
    -- ■■■■■■■■■■■■■■■■■■■■■■ 사전 데이터 정의 ■■■■■■■■■■■■■■■■■■■■■■
    TYPE v_array IS VARRAY(15) OF VARCHAR2(100);

    -- 카테고리별 고유 상품명 (각 13개)
    v_fashion_names v_array := v_array('에센셜 오버핏 셔츠', '이지케어 스트레이트 팬츠', '소프트 울 가디건', '3-Layer 방수 자켓', '빈티지 워싱 데님 팬츠', '실크 블라우스', '로고 자수 스웨트셔츠', 'A라인 코튼 스커트', '초경량 구스다운 베스트', '옥스포드 스트라이프 셔츠', '테이퍼드핏 치노 팬츠', '메리노울 터틀넥', '코듀로이 자켓');
    v_digital_names v_array := v_array('TWS 노이즈캔슬링 이어폰', 'NVMe 외장 SSD 2TB', '4K 스마트 빔 프로젝터', 'GaN PD 고속충전기 100W', 'FHD 프라이버시 웹캠', '썬더볼트4 도킹 스테이션', '알루미늄 태블릿 스탠드', '3in1 무선충전 거치대', '방수 포터블 스피커', '스마트 LED 모니터 조명', '인체공학 버티컬 마우스', '무선 기계식 키보드', '27인치 QHD IPS 모니터');
    v_food_names v_array := v_array('항공직송 생체리 500g', '무항생제 한우 채끝살', '제주 흑돼지 목살 구이용', '새벽 수확 유기농 채소 믹스', '수제 패션후르츠 청', '냉압착 엑스트라버진 올리브유', '우리밀 짜장라면 멀티팩', '발아현미 즉석밥 12개입', '프리미엄 견과류 선물세트', 'NFC 착즙 사과주스', '저염 백명란젓', '국산콩 재래식 된장', '유기농 공정무역 원두');
    v_home_names v_array := v_array('60수 고밀도 순면 침구세트', '경추サポート 메모리폼 베개', '북유럽풍 원목 거실장', '플라워가든 디퓨저 세트', '스마트 온도조절 전기포트', '원두커피 그라인더', '분리수거 휴지통 30L', '타공없음 강력 욕실 코너선반', '내열 실리콘 조리도구 5종', '무소음 인테리어 벽시계', '전동 와인 오프너', '프리미엄 극세사 샤워가운', '규조토 발매트');
    
    -- 카테고리별 고유 브랜드명 (각 13개)
    v_fashion_brands v_array := v_array('ModeModern', 'DailyFit', 'WarmWeave', 'StormGuard', 'RetroDenim', 'SilkyWay', 'LogoWorks', 'SkirtStyle', 'FeatherLite', 'ClassicStripe', 'UrbanChino', 'NeckWarm', 'Cordy');
    v_digital_brands v_array := v_array('AudioZen', 'TerraDrive', 'ViewMagic', 'ChargePro', 'SecureCam', 'DockMaster', 'StandFirm', 'TripleCharge', 'AquaBeat', 'DeskGlow', 'ErgoClick', 'TypeWireless', 'PixelPerfect');
    v_food_brands v_array := v_array('CherryFarm', 'PrimeBeef', 'JejuGold', 'MorningGreen', 'FruitElixir', 'OliveEssence', 'RealNoodle', 'VitaRice', 'NutGift', 'ApplePress', 'SaltyDelight', 'JangArtisan', 'BeanEthic');
    v_home_brands v_array := v_array('DreamLinen', 'NeckSupport', 'NordicWood', 'GardenScent', 'SmartBoil', 'GrindMaster', 'EcoBin', 'NoDrillShelf', 'HeatProof', 'SilentTime', 'VinoOpen', 'PlushRobe', 'DiatomDry');

    -- ■■■■■■■■■■■■■■■■■■■■■■ 변수 및 로직 시작 ■■■■■■■■■■■■■■■■■■■■■■
    v_p_pid           product_item.p_pid%TYPE;
    v_p_name          product_item.p_name%TYPE;
    v_p_brand         product_detail.pd_brand%TYPE;
    v_seller          VARCHAR2(10);
    v_pc_id           product_item.p_pc_id%TYPE;
BEGIN
    -- 50개의 상품을 만들기 위해 루프 실행
    FOR i IN 1..50 LOOP
        -- 1. 루프 카운터(i)를 기준으로 카테고리, 판매자, 상품명, 브랜드를 순서대로 할당 (중복 방지)
        IF i BETWEEN 1 AND 13 THEN
            v_seller := 'seller01'; v_pc_id := '100001'; -- 의류
            v_p_name := v_fashion_names(i); v_p_brand := v_fashion_brands(i);
        ELSIF i BETWEEN 14 AND 26 THEN
            v_seller := 'seller01'; v_pc_id := '200001'; -- 디지털
            v_p_name := v_digital_names(i-13); v_p_brand := v_digital_brands(i-13);
        ELSIF i BETWEEN 27 AND 39 THEN
            v_seller := 'seller02'; v_pc_id := '300000'; -- 식품
            v_p_name := v_food_names(i-26); v_p_brand := v_food_brands(i-26);
        ELSE -- 40 to 50
            v_seller := 'seller02'; v_pc_id := '400000'; -- 홈
            v_p_name := v_home_names(i-39); v_p_brand := v_home_brands(i-39);
        END IF;

        -- 2. PRODUCT_ITEM 테이블에 상품 기본 정보 INSERT (가격, 재고 등은 랜덤 생성)
        INSERT INTO product_item (p_name, p_desc, p_price, p_discount, p_point, p_stock_quantity, p_delivery_price, p_seller_id, p_pc_id, p_view_count) 
        VALUES (v_p_name, '자동 생성된 상품 설명 (' || v_p_brand || ')', TRUNC(DBMS_RANDOM.VALUE(10, 500)) * 1000, TRUNC(DBMS_RANDOM.VALUE(0, 4)) * 5, TRUNC(DBMS_RANDOM.VALUE(10, 500)), TRUNC(DBMS_RANDOM.VALUE(50, 200)), CASE WHEN DBMS_RANDOM.VALUE > 0.7 THEN 3000 ELSE 0 END, v_seller, v_pc_id, 0);

        -- 3. 방금 INSERT된 상품의 p_pid를 조회
        SELECT p_pid INTO v_p_pid FROM product_item WHERE p_name = v_p_name;
        
        -- 4. PRODUCT_DETAIL 테이블에 상품 상세 정보 INSERT
        INSERT INTO product_detail (pd_p_pid, pd_s_seller_type, pd_brand, pd_origin)
        VALUES (v_p_pid, CASE v_seller WHEN 'seller01' THEN '법인사업자' ELSE '개인사업자' END, v_p_brand, '상세페이지 참조');
        
        -- 5. PRODUCT_OPTION 테이블에 카테고리별 기본 옵션 INSERT
        IF v_pc_id = '100001' THEN
            INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_p_pid, '색상', '블랙');
            INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_p_pid, '사이즈', 'L');
        ELSIF v_pc_id = '200001' THEN
            INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_p_pid, '색상', '실버');
            INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION, POP_ADD_PRICE) VALUES (v_p_pid, '보증', '추가 1년 보증', 20000);
        ELSIF v_pc_id = '300000' THEN
            INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_p_pid, '포장', '기본포장');
        ELSE -- '400000'
            INSERT INTO product_option (POP_P_PID, POP_NAME, POP_SELECTION) VALUES (v_p_pid, '색상', '베이지');
        END IF;

        -- 6. TAGS_LINKS 테이블에 태그 1~2개 랜덤 연결
        INSERT INTO tags_links (tl_p_pid, tl_pt_name) VALUES (v_p_pid, 
            CASE MOD(i, 5) WHEN 0 THEN '히트상품' WHEN 1 THEN '추천상품' WHEN 2 THEN '최신상품' WHEN 3 THEN '인기상품' ELSE '할인상품' END);
        IF DBMS_RANDOM.VALUE > 0.6 THEN
            INSERT INTO tags_links (tl_p_pid, tl_pt_name) VALUES (v_p_pid, 
                CASE MOD(i+2, 5) WHEN 0 THEN '히트상품' WHEN 1 THEN '추천상품' WHEN 2 THEN '최신상품' WHEN 3 THEN '인기상품' ELSE '할인상품' END);
        END IF;

    END LOOP;
END;
/



//SELECT * FROM PRODUCT_ITEM;
//DELETE FROM PRODUCT_ITEM;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲



-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■ ADMIN01에게 테스트 쿠폰 전달  ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼
INSERT INTO USER_COUPON (uc_u_id, uc_c_id, uc_status)
VALUES ('admin01', 'seller02_76001458', '사용가능');

SELECT * FROM USER_COUPON;
SELECT * FROM U_USER;
SELECT * FROM PRODUCT_ORDER;
SELECT * FROM PRODUCT_ITEM;
SELECT * FROM ORDER_ITEMS;
select * from version;

SELECT * FROM board;
SELECT * FROM board_type;
SELECT * FROM m_terms;
SELECT * FROM POINT_HISTORY;
SELECT * FROM PRODUCT_ITEM;
//DELETE FROM PRODUCT_ITEM;
commit;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲



-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■   ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼


//SELECT * FROM RECRUITMENT;
//DELETE FROM PRODUCT_ITEM;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲


-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■   ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼


//SELECT * FROM RECRUITMENT;
//DELETE FROM PRODUCT_ITEM;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲


-- ■■■■■■■■■■■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■   ■■■■■■■■■■■
-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼


//SELECT * FROM RECRUITMENT;
//DELETE FROM PRODUCT_ITEM;
-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲


