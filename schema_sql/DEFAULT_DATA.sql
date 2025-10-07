-- ■■■■■■■■■■■■■■
-- ■ 유저 ■■■■■■■■■
-- ■■■■■■■■■■■■■■
INSERT ALL INTO m_user 
(
    u_id,
    u_pw,
    u_name,
    u_birth,
    u_gender,
    u_mail,
    u_phone,
    u_postal,
    u_base_addr,
    u_detail_addr,
    u_type,
    u_create_day
) 
    VALUES 
    (
        'TEST_USER01',
        '1234ASDF',
        '홍길동',
        TO_DATE('1990-05-15', 'YYYY-MM-DD'),
        'M',
        'hong@example.com',
        '010-1234-5555',
        '03033',
        '서울시 종로구',
        '세종로 1번지',
        '일반',
        SYSTIMESTAMP
    )
    INTO M_USER(u_id, u_pw, u_name, u_birth, u_gender, u_mail, u_phone, u_postal, u_base_addr, u_detail_addr, u_type, u_create_day)
    VALUES 
        (
            'TEST_USER02',
            '1234ASDF',
            '홍유진',
            TO_DATE('1990-05-15', 'YYYY-MM-DD'),
            'F',
            'hongU@example.com',
            '010-1234-5000',
            '03033',
            '서울시 종로구',
            '세종로 1번지',
            '판매',
            SYSTIMESTAMP
        )
    INTO M_USER(u_id, u_pw, u_name, u_birth, u_gender, u_mail, u_phone, u_postal, u_base_addr, u_detail_addr, u_type, u_create_day)
    VALUES 
        (
            'MANAGER',
            'QWER1234!',
            '관리자',
            TO_DATE('1990-05-15', 'YYYY-MM-DD'),
            'F',
            'manager@example.com',
            '010-1234-1000',
            '03033',
            '서울시 종로구',
            '세종로 1번지',
            '관리자',
            SYSTIMESTAMP
        )
    SELECT 1 FROM DUAL;


-- ■■■■■■■■■■■■■■
-- ■ 상품 카테고리 ■■■■
-- ■■■■■■■■■■■■■■
INSERT ALL INTO product_category 
(
    pc_id,
    pc_name
    -- pc_path
    -- pc_state
)   
    VALUES 
        (
            '101000',
            '패션'
            -- '/PATH'
            -- 'ACTIVE'
        )
    INTO product_category 
        (
            pc_id,
            pc_name
        )   
    VALUES 
        (
            '101001',
            '의류'
        )
    INTO product_category 
        (
            pc_id,
            pc_name
        )   
    VALUES 
        (
            '101002',
            '뷰티'
        )
    INTO product_category 
        (
            pc_id,
            pc_name
        )   
    VALUES 
        (
            '102000',
            '가전'
        )
    INTO product_category 
        (
            pc_id,
            pc_name
        )   
    VALUES 
        (
            '102001',
            '디지털'
        )
    INTO product_category 
        (
            pc_id,
            pc_name
        )   
    VALUES 
        (
            '103000',
            '식품'
        )
    INTO product_category 
        (
            pc_id,
            pc_name
        )
    VALUES 
        (
            '103001',
            '생필품'
        )
    INTO product_category 
        (
            pc_id,
            pc_name
        )   
    VALUES 
        (
            '104000',
            '홈'
        )
    INTO product_category 
        (
            pc_id,
            pc_name
        )   
    VALUES
        (
            '104001',
            '문구'
        )
    INTO product_category 
        (
            pc_id,
            pc_name
        )
    VALUES 
        (
            '104002',
            '취미'
        )
    SELECT 1 FROM DUAL;


-- ■■■■■■■■■■■■■■
-- ■ 상품 태그 ■■■■■■
-- ■■■■■■■■■■■■■■  
INSERT ALL INTO priduct_tags
(
        PT_NAME
        --PT_STATE
) 
    VALUES
        (
            '히트상품' -- 상품 트리거: 출시 1개월 내 판매 건수 5 이상
        )
    INTO priduct_tags
        (
            PT_NAME
        )
    VALUES
        (
            '추천상품' -- 상품 트리거: 리뷰 1건 이상
        )
    INTO priduct_tags
        (
            PT_NAME
        )
    VALUES
        (
            '최신상품' -- 상품 트리거: 출시 1개월 이내
        )
    INTO priduct_tags
        (
            PT_NAME
        )
    VALUES
        (
            '인기상품' -- 상품 트리거: 판매 건수 5 이상(출시 기간 무관)
        )
    INTO priduct_tags
        (
            PT_NAME
        )
    VALUES
        (
            '할인상품' -- 상품 트리거: 할인율 1% 이상 
        )
    SELECT 1 FROM DUAL;






-- ■■■■■■■■■■■■■■
-- ■ 조회 ■■■■■■■■■
-- ■■■■■■■■■■■■■■
-- 유저
SELECT * FROM m_user;

-- 상품 카테고리
SELECT * FROM product_category;
SELECT * FROM product_category WHERE PC_ID LIKE '101%'; -- 대분류: 패션 || 중분류: 의류, 뷰티

-- 상품 태그
SELECT * FROM priduct_tags;


