-- ■■■■■■■■■■■■■■■■■
-- ■ 유저 ■■■■■■■■■■■■
-- ■■■■■■■■■■■■■■■■■
CREATE TABLE m_user (
    u_id          VARCHAR2(12) NOT NULL,
    u_pw          VARCHAR2(12) NOT NULL,
    u_name        VARCHAR2(10) NOT NULL,
    u_birth       DATE,
    u_gender      VARCHAR2(10),
    u_mail        VARCHAR2(50),
    u_phone       VARCHAR2(20),
    u_postal      VARCHAR2(10),
    u_base_addr   VARCHAR2(50),
    u_detail_addr VARCHAR2(50),
    u_type        VARCHAR2(10),
    u_create_day  TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp
);

ALTER TABLE m_user ADD CONSTRAINT m_user_pk PRIMARY KEY ( u_id );


-- ■■■■■■■■■■■■■■■■■■■■■■■
-- ■ 상품 카테고리 ■■■■■■■■■■■■■
-- ■■■■■■■■■■■■■■■■■■■■■■■
CREATE TABLE product_category (
    pc_id    VARCHAR2(10) NOT NULL,
    pc_name  VARCHAR2(20),
    pc_path  VARCHAR2(50),
    pc_state VARCHAR2(10) DEFAULT 'ACTIVE'
);

ALTER TABLE product_category ADD CONSTRAINT product_category_pk PRIMARY KEY ( pc_id );


-- ■■■■■■■■■■■■■■■■■■■■■■■
-- ■ 상품 태그 ■■■■■■
-- ■■■■■■■■■■■■■■■■■■■■■■■
CREATE TABLE priduct_tags (
    pt_name  VARCHAR2(20) NOT NULL,
    pt_state VARCHAR2(10) DEFAULT 'ACTIVE'
);

ALTER TABLE priduct_tags ADD CONSTRAINT priduct_tags_pk PRIMARY KEY ( pt_name );

-- ■■■■■■■■■■■■■■■■■■■■■■
-- ■ 상품 연결(상품 - 태그) ■■■■■■
-- ■■■■■■■■■■■■■■■■■■■■■■






-- ■■■■■■■■■■■■■■■■■■■■■■■
-- ■  ■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■■■■■■■■■■■■■■


-- ■■■■■■■■■■■■■■■■■■■■■■■
-- ■  ■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■■■■■■■■■■■■■■


-- ■■■■■■■■■■■■■■■■■■■■■■■
-- ■  ■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■■■■■■■■■■■■■■


-- ■■■■■■■■■■■■■■■■■■■■■■■
-- ■  ■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■■■■■■■■■■■■■■


-- ■■■■■■■■■■■■■■■■■■■■■■■
-- ■  ■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■■■■■■■■■■■■■■


-- ■■■■■■■■■■■■■■■■■■■■■■■
-- ■  ■■■■■■■■■■■■■■■■■
-- ■■■■■■■■■■■■■■■■■■■■■■■