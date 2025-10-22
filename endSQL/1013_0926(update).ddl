-- 생성자 Oracle SQL Developer Data Modeler 24.3.1.347.1153
--   위치:        2025-10-13 09:25:33 KST
--   사이트:      Oracle Database 21c
--   유형:      Oracle Database 21c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE ad_info (
    a_no         NUMBER NOT NULL,
    a_name       VARCHAR2(50),
    a_size       VARCHAR2(50),
    a_back_color VARCHAR2(50),
    a_link       VARCHAR2(200),
    a_location   VARCHAR2(20),
    a_start_date DATE,
    a_end_date   DATE,
    a_start_time DATE,
    a_end_time   DATE,
    a_file       VARCHAR2(100),
    a_reg_date   TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp
);

ALTER TABLE ad_info ADD CONSTRAINT ad_info_pk PRIMARY KEY ( a_no );

CREATE TABLE board (
    b_id       VARCHAR2(50) NOT NULL,
    b_u_id     VARCHAR2(20) NOT NULL,
    b_bt_type  VARCHAR2(20) NOT NULL,
    b_title    VARCHAR2(100) NOT NULL,
    b_body     VARCHAR2(1000),
    b_state    VARCHAR2(20),
    b_listener VARCHAR2(20),
    b_answer   VARCHAR2(500),
    b_view     NUMBER(6),
    b_file     VARCHAR2(100),
    b_reg_date TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp
);

ALTER TABLE board ADD CONSTRAINT board_pk PRIMARY KEY ( b_id );

CREATE TABLE board_type (
    bt_type VARCHAR2(20) NOT NULL,
    bt_name VARCHAR2(50)
);

ALTER TABLE board_type ADD CONSTRAINT board_type_pk PRIMARY KEY ( bt_type );

CREATE TABLE cart_items (
    ci_u_id      VARCHAR2(20) NOT NULL, -- 현재 로그인한 계정의 U_ID
    ci_p_pid     VARCHAR2(50) NOT NULL, -- 장바구니에 담긴 제품의 P_PID
    ci_op01     VARCHAR2(100),  -- 제품상세에서 선택한 옵션의 POP_NO
    ci_op02     VARCHAR2(100),  -- 제품상세에서 선택한 옵션의 POP_NO
    ci_amount    NUMBER,  -- 제품상세에서 선택한 제품의 구매 수량
    ci_tot_price NUMBER(10) -- 제품상세 페이지에서 출력된 금액
);

ALTER TABLE cart_items ADD CONSTRAINT cart_items_pk PRIMARY KEY ( ci_u_id,
                                                                  ci_p_pid );

CREATE TABLE category_links (
    cl_p_pid VARCHAR2(50) NOT NULL,
    cl_pc_id VARCHAR2(10) NOT NULL
);

ALTER TABLE category_links ADD CONSTRAINT category_links_pk PRIMARY KEY ( cl_p_pid,
                                                                          cl_pc_id );

CREATE TABLE coupon (
    c_id           VARCHAR2(30) NOT NULL,
    c_ct_type      VARCHAR2(20) NOT NULL,
    c_name         VARCHAR2(30),
    c_text         VARCHAR2(50),
    c_s_u_id       VARCHAR2(20) NOT NULL,
    c_amount       NUMBER(10),
    c_used         NUMBER(10),
    c_status       VARCHAR2(20),
    c_start_day    DATE,
    c_end_day      DATE,
    c_dis_money    VARCHAR2(50),
    c_dis_delivery VARCHAR2(20)
);

ALTER TABLE coupon ADD CONSTRAINT coupon_pk PRIMARY KEY ( c_id );

CREATE TABLE coupon_type (
    ct_type VARCHAR2(20) NOT NULL
);

ALTER TABLE coupon_type ADD CONSTRAINT coupon_type_pk PRIMARY KEY ( ct_type );

CREATE TABLE m_terms (
    t_name VARCHAR2(50) NOT NULL,
    t_body CLOB
);

ALTER TABLE m_terms ADD CONSTRAINT terms_pk PRIMARY KEY ( t_name );

CREATE TABLE order_items (
    oi_po_no            VARCHAR2(50) NOT NULL,
    oi_p_pid            VARCHAR2(50) NOT NULL,
    oi_s_u_id           VARCHAR2(20) NOT NULL,
    oi_prod_qty         NUMBER,
    oi_tot_price        NUMBER(10),
    oi_tracking_company VARCHAR2(20),
    oi_tracking_num     VARCHAR2(20),
    oi_etc              VARCHAR2(300),
    oi_del_status       VARCHAR2(50)
);

ALTER TABLE order_items ADD CONSTRAINT order_items_pk PRIMARY KEY ( oi_po_no,
                                                                    oi_p_pid );

CREATE TABLE point_history (
    uh_no           VARCHAR2(50) NOT NULL,
    uh_u_id         VARCHAR2(20) NOT NULL,
    uh_po_no        VARCHAR2(50) NOT NULL,
    uh_text         VARCHAR2(200),
    uh_change_point NUMBER,
    uh_change_date  NUMBER,
    uh_dday         TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp
);

ALTER TABLE point_history ADD CONSTRAINT point_history_pk PRIMARY KEY ( uh_no );
ALTER TABLE point_history ADD CONSTRAINT fk_point_history_po_no FOREIGN KEY (uh_po_no) REFERENCES product_order(po_no);

CREATE TABLE product_category (
    pc_id    VARCHAR2(10) NOT NULL,
    pc_name  VARCHAR2(20),
    pc_path  VARCHAR2(50),
    pc_lever VARCHAR2(10)
);

ALTER TABLE product_category ADD CONSTRAINT product_category_pk PRIMARY KEY ( pc_id );

CREATE TABLE product_detail (
    pd_p_pid         VARCHAR2(50) NOT NULL,
    pd_state         VARCHAR2(20) DEFAULT '새상품' NOT NULL,
    pd_tax           VARCHAR2(20) DEFAULT '과세상품' NOT NULL,
    pd_receipt       VARCHAR2(200) DEFAULT '발행가능신용카드전표, 온라인현금영수증',
    pd_s_seller_type VARCHAR2(20),
    pd_brand         VARCHAR2(200) DEFAULT '미입력',
    pd_origin        VARCHAR2(200) DEFAULT '미입력',
    pd_maker         VARCHAR2(200) DEFAULT '상세정보 직접입력',
    pd_manuf_country VARCHAR2(200) DEFAULT '상세정보 직접입력',
    pd_care          VARCHAR2(500) DEFAULT '상세정보 직접입력',
    pd_manuf_date    VARCHAR2(50) DEFAULT '상세정보 직접입력',
    pd_warranty_info VARCHAR2(200) DEFAULT '상세정보 직접입력',
    pd_as            VARCHAR2(200) DEFAULT '상세정보 직접입력',
    pd_phone         VARCHAR2(50) DEFAULT '상세정보 직접입력'
);

ALTER TABLE product_detail ADD CONSTRAINT product_detail_pk PRIMARY KEY ( pd_p_pid );

CREATE TABLE product_item (
    p_pid            VARCHAR2(50) NOT NULL,
    p_name           VARCHAR2(50) NOT NULL,
    p_desc           VARCHAR2(200),
    p_price          NUMBER NOT NULL,
    p_discount       NUMBER,
    p_point          NUMBER(10),
    p_stock_quantity NUMBER(5),
    p_delivery_price NUMBER,
    p_regdate        TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp,
    p_seller_id      VARCHAR2(20) NOT NULL,
    p_pc_id          VARCHAR2(10) NOT NULL,
    p_iamge_list     VARCHAR2(100),
    p_image_main     VARCHAR2(100),
    p_image_detail   VARCHAR2(100),
    p_detail_info    VARCHAR2(100),
    p_view_count     NUMBER
);

ALTER TABLE product_item ADD CONSTRAINT product_item_pk PRIMARY KEY ( p_pid );


CREATE TABLE PRODUCT_OPTION (
    POP_NO              VARCHAR2(100) NOT NULL,
    POP_P_PID           VARCHAR2(50) NOT NULL,
    POP_NAME            VARCHAR2(50) NOT NULL,
    POP_SELECTION       VARCHAR2(50) NOT NULL,
    POP_ADD_PRICE       NUMBER DEFAULT 0,
    POP_STOCK           NUMBER(5) DEFAULT 999,
    CONSTRAINT PRODUCT_OPTION_PK PRIMARY KEY (POP_NO)
);

ALTER TABLE PRODUCT_OPTION
ADD CONSTRAINT FK_PRODUCT_OPTION_PID
FOREIGN KEY (POP_P_PID) REFERENCES PRODUCT_ITEM(P_PID)
ON DELETE CASCADE;

CREATE TABLE product_order (
    po_no             VARCHAR2(50) NOT NULL,
    po_u_id           VARCHAR2(20) NOT NULL,
    po_pay_type       VARCHAR2(50),
    po_pay_detail     VARCHAR2(50),
    po_recipient      VARCHAR2(20),
    po_re_phone       VARCHAR2(20),
    po_item_count     NUMBER(3),
    po_sum_price      NUMBER(10),
    po_discount       NUMBER(10),
    po_delivery_price NUMBER(10),
    po_pri_discount   NUMBER(10),
    po_tot_price      NUMBER(10),
    po_get_point      NUMBER(10),
    po_state          VARCHAR2(20),
    po_postal         VARCHAR2(10),
    po_base_addr      VARCHAR2(50),
    po_detail_addr    VARCHAR2(50),
    po_request_note   VARCHAR2(100),
    po_orderDate      TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp
);

ALTER TABLE product_order ADD CONSTRAINT product_order_pk PRIMARY KEY ( po_no );

CREATE TABLE product_review (
    pr_no       VARCHAR2(100) NOT NULL,
    pr_po_no    VARCHAR2(50) NOT NULL,
    pr_p_pid    VARCHAR2(50) NOT NULL,
    pr_u_id     VARCHAR2(20) NOT NULL,
    pr_star     NUMBER(2, 1),
    pr_body     VARCHAR2(500),
    pr_reg_date TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp,
    pr_images1  VARCHAR2(100),
    pr_images2  VARCHAR2(100),
    pr_images3  VARCHAR2(100)
);

ALTER TABLE product_review ADD CONSTRAINT product_review_pk PRIMARY KEY ( pr_no );

CREATE TABLE product_tags (
    pt_name  VARCHAR2(20) NOT NULL,
    pt_state VARCHAR2(10) DEFAULT 'ACTIVE',
    pt_path  VARCHAR2(50)
);

ALTER TABLE product_tags ADD CONSTRAINT priduct_tags_pk PRIMARY KEY ( pt_name );

CREATE TABLE recruitment (
    r_no         NUMBER NOT NULL,
    r_dept       VARCHAR2(50),
    r_career     VARCHAR2(50),
    r_type       VARCHAR2(20),
    r_title      VARCHAR2(100),
    r_status     VARCHAR2(20),
    r_start_date DATE,
    r_end_date   DATE,
    r_reg_date   TIMESTAMP WITH TIME ZONE DEFAULT systimestamp
);

ALTER TABLE recruitment ADD CONSTRAINT recruitment_pk PRIMARY KEY ( r_no );

CREATE TABLE seller_info (
    s_u_id          VARCHAR2(20) NOT NULL,
    s_company_name  VARCHAR2(20),
    s_seller_no     VARCHAR2(50),
    s_sales_reg_num VARCHAR2(50),
    s_tel           VARCHAR2(20),
    s_fax           VARCHAR2(20),
    s_state         VARCHAR2(20),
    s_seller_type   VARCHAR2(20)
);

ALTER TABLE seller_info ADD CONSTRAINT seller_info_pk PRIMARY KEY ( s_u_id );

CREATE TABLE tags_links (
    tl_p_pid   VARCHAR2(50) NOT NULL,
    tl_pt_name VARCHAR2(20) NOT NULL
);

ALTER TABLE tags_links ADD CONSTRAINT product_links_pk PRIMARY KEY ( tl_p_pid,
                                                                     tl_pt_name );

CREATE TABLE u_user (
    u_id            VARCHAR2(20) NOT NULL,
    u_pw            VARCHAR2(100) NOT NULL,
    u_name          VARCHAR2(50) NOT NULL,
    u_birth         DATE,
    u_gender        VARCHAR2(10),
    u_mail          VARCHAR2(50),
    u_phone         VARCHAR2(20),
    u_postal        VARCHAR2(10),
    u_base_addr     VARCHAR2(50),
    u_detail_addr   VARCHAR2(50),
    u_type          VARCHAR2(10),
    u_create_day    TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp,
    u_point         NUMBER,
    u_rank          VARCHAR2(20),
    u_status        VARCHAR2(10),
    u_last_login_at TIMESTAMP WITH LOCAL TIME ZONE
);

ALTER TABLE u_user ADD CONSTRAINT m_user_pk PRIMARY KEY ( u_id );

CREATE TABLE user_coupon (
    uc_u_id    VARCHAR2(20) NOT NULL,
    uc_c_id    VARCHAR2(30) NOT NULL,
    uc_po_no   VARCHAR2(50),
    uc_status  VARCHAR2(20),
    uc_use_day TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp
);

ALTER TABLE user_coupon ADD CONSTRAINT user_coupon_pk PRIMARY KEY (uc_u_id, uc_c_id);

CREATE TABLE user_point (
    up_u_id      VARCHAR2(20) NOT NULL,
    up_now_point NUMBER
);

ALTER TABLE user_point ADD CONSTRAINT user_point_pk PRIMARY KEY ( up_u_id );

CREATE TABLE version (
    v_no                  NUMBER(10) NOT NULL,
    v_vname               VARCHAR2(50),
    v_rear               VARCHAR2(100),
    v_uploader            VARCHAR2(20) NOT NULL,
    v_reg_date            TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp,
    v_body                VARCHAR2(500),
    v_title               VARCHAR2(50),
    v_sub_title           VARCHAR2(100),
    v_header_logo         VARCHAR2(100),
    v_footer_logo         VARCHAR2(100),
    v_favicon             VARCHAR2(100),
    v_company             VARCHAR2(20),
    v_ceo                 VARCHAR2(20),
    v_seller_reg_no       VARCHAR2(50),
    v_online_sales_reg_no VARCHAR2(50),
    v_addr                VARCHAR2(200),
    v_tel                 VARCHAR2(20),
    v_act_time            VARCHAR2(100),
    v_email               VARCHAR2(50),
    v_manager_phone       VARCHAR2(50),
    v_copylight           VARCHAR2(100)
);

ALTER TABLE version ADD CONSTRAINT version_pk PRIMARY KEY ( v_no );

ALTER TABLE order_items
    ADD CONSTRAINT "..P_PID " FOREIGN KEY ( oi_p_pid )
        REFERENCES product_item ( p_pid );

ALTER TABLE user_coupon
    ADD CONSTRAINT "..u-id " FOREIGN KEY ( uc_u_id )
        REFERENCES u_user ( u_id );

ALTER TABLE cart_items
    ADD CONSTRAINT ".P_PID " FOREIGN KEY ( ci_p_pid )
        REFERENCES product_item ( p_pid );

/*
ALTER TABLE product_option
    ADD CONSTRAINT ".p_pid. " FOREIGN KEY ( pop_p_pid )
        REFERENCES product_item ( p_pid );
*/
ALTER TABLE board
    ADD CONSTRAINT ".u_id   " FOREIGN KEY ( b_u_id )
        REFERENCES u_user ( u_id );

ALTER TABLE board
    ADD CONSTRAINT bt_type FOREIGN KEY ( b_bt_type )
        REFERENCES board_type ( bt_type );

ALTER TABLE user_coupon
    ADD CONSTRAINT "c_id. " FOREIGN KEY ( uc_c_id )
        REFERENCES coupon ( c_id );

ALTER TABLE coupon
    ADD CONSTRAINT "ct_type " FOREIGN KEY ( c_ct_type )
        REFERENCES coupon_type ( ct_type );

ALTER TABLE tags_links
    ADD CONSTRAINT p_pid FOREIGN KEY ( tl_p_pid )
        REFERENCES product_item ( p_pid );

ALTER TABLE product_detail
    ADD CONSTRAINT "P_PID. "
        FOREIGN KEY ( pd_p_pid )
            REFERENCES product_item ( p_pid )
                ON DELETE CASCADE;

ALTER TABLE category_links
    ADD CONSTRAINT "p_pid . " FOREIGN KEY ( cl_p_pid )
        REFERENCES product_item ( p_pid );

ALTER TABLE product_review
    ADD CONSTRAINT "P_PID . . " FOREIGN KEY ( pr_p_pid )
        REFERENCES product_item ( p_pid );

ALTER TABLE product_item
    ADD CONSTRAINT pc_id FOREIGN KEY ( p_pc_id )
        REFERENCES product_category ( pc_id );

ALTER TABLE category_links
    ADD CONSTRAINT "pc_id " FOREIGN KEY ( cl_pc_id )
        REFERENCES product_category ( pc_id );

ALTER TABLE order_items
    ADD CONSTRAINT po_no FOREIGN KEY ( oi_po_no )
        REFERENCES product_order ( po_no );

ALTER TABLE product_review
    ADD CONSTRAINT "PO_NO " FOREIGN KEY ( pr_po_no )
        REFERENCES product_order ( po_no );

ALTER TABLE user_coupon
    ADD CONSTRAINT "po_no. " FOREIGN KEY ( uc_po_no )
        REFERENCES product_order ( po_no );

ALTER TABLE tags_links
    ADD CONSTRAINT pt_name FOREIGN KEY ( tl_pt_name )
        REFERENCES product_tags ( pt_name );

ALTER TABLE order_items
    ADD CONSTRAINT s_u_id FOREIGN KEY ( oi_s_u_id )
        REFERENCES seller_info ( s_u_id );

ALTER TABLE coupon
    ADD CONSTRAINT "s_u_id ." FOREIGN KEY ( c_s_u_id )
        REFERENCES seller_info ( s_u_id );

ALTER TABLE product_item
    ADD CONSTRAINT u_id FOREIGN KEY ( p_seller_id )
        REFERENCES u_user ( u_id );

ALTER TABLE seller_info
    ADD CONSTRAINT "U_ID " FOREIGN KEY ( s_u_id )
        REFERENCES u_user ( u_id );

ALTER TABLE user_point
    ADD CONSTRAINT "u_id ." FOREIGN KEY ( up_u_id )
        REFERENCES u_user ( u_id );

ALTER TABLE cart_items
    ADD CONSTRAINT "u_id . " FOREIGN KEY ( ci_u_id )
        REFERENCES u_user ( u_id );

ALTER TABLE point_history
    ADD CONSTRAINT "U_ID .." FOREIGN KEY ( uh_u_id )
        REFERENCES u_user ( u_id );

ALTER TABLE product_review
    ADD CONSTRAINT "U_ID . . " FOREIGN KEY ( pr_u_id )
        REFERENCES u_user ( u_id );

ALTER TABLE version
    ADD CONSTRAINT "u_id .v1" FOREIGN KEY ( v_uploader )
        REFERENCES u_user ( u_id );

ALTER TABLE product_order
    ADD CONSTRAINT "U_ID v1" FOREIGN KEY ( po_u_id )
        REFERENCES u_user ( u_id );

CREATE SEQUENCE ad_info_a_no_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER ad_info_a_no_trg BEFORE
    INSERT ON ad_info
    FOR EACH ROW
    WHEN ( new.a_no IS NULL )
BEGIN
    :new.a_no := ad_info_a_no_seq.nextval;
END;
/

CREATE SEQUENCE order_items_oi_prod_qty_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER order_items_oi_prod_qty_trg BEFORE
    INSERT ON order_items
    FOR EACH ROW
    WHEN ( new.oi_prod_qty IS NULL )
BEGIN
    :new.oi_prod_qty := order_items_oi_prod_qty_seq.nextval;
END;
/


CREATE SEQUENCE recruitment_r_no_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER recruitment_r_no_trg BEFORE
    INSERT ON recruitment
    FOR EACH ROW
    WHEN ( new.r_no IS NULL )
BEGIN
    :new.r_no := recruitment_r_no_seq.nextval;
END;
/

CREATE SEQUENCE version_v_no_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER version_v_no_trg BEFORE
    INSERT ON version
    FOR EACH ROW
    WHEN ( new.v_no IS NULL )
BEGIN
    :new.v_no := version_v_no_seq.nextval;
END;
/


CREATE OR REPLACE TRIGGER trg_point_history_uh_no
BEFORE INSERT ON point_history
FOR EACH ROW
DECLARE
    v_next_seq NUMBER;
BEGIN
    -- 1. 현재 추가되는 유저 ID(uh_u_id)에 해당하는 기존 포인트 내역 중 가장 큰 번호를 찾습니다.
    --    - SUBSTR와 INSTR을 사용해 'ID_' 뒷부분의 숫자만 추출하여 최댓값을 구합니다.
    --    - NVL 함수를 사용하여 해당 유저의 첫 번째 내역일 경우 0을 반환합니다.
    SELECT NVL(MAX(TO_NUMBER(SUBSTR(uh_no, INSTR(uh_no, '_') + 1))), 0) + 1
    INTO v_next_seq
    FROM point_history
    WHERE uh_u_id = :new.uh_u_id;

    -- 2. :new.uh_no에 '유저ID_000' 형식으로 값을 할당합니다.
    --    - LPAD 함수를 사용해 v_next_seq 값을 3자리로 만들고, 빈자리는 0으로 채웁니다. (예: 1 -> 001, 12 -> 012)
    :new.uh_no := :new.uh_u_id || '_' || LPAD(v_next_seq, 3, '0');
END;
/

CREATE OR REPLACE TRIGGER trg_coupon_c_id
BEFORE INSERT ON coupon
FOR EACH ROW
DECLARE
    v_candidate_cid VARCHAR2(30);
    v_count         NUMBER;
BEGIN
    LOOP
        -- 1. '발급자ID_랜덤숫자8자리' 형식의 후보 ID를 생성합니다.
        --    DBMS_RANDOM.VALUE를 사용하여 10000000 ~ 99999999 사이의 난수를 만듭니다.
        v_candidate_cid := :new.c_s_u_id || '_' || TRUNC(DBMS_RANDOM.VALUE(10000000, 99999999));

        -- 2. 생성된 후보 ID가 COUPON 테이블에 이미 존재하는지 확인합니다.
        SELECT COUNT(*)
        INTO v_count
        FROM coupon
        WHERE c_id = v_candidate_cid;

        -- 3. 존재하지 않으면 (count가 0이면) 루프를 종료합니다.
        EXIT WHEN v_count = 0;

    -- 4. 만약 중복된 ID가 있다면 루프를 다시 실행하여 새로운 랜덤 ID를 생성합니다.
    END LOOP;

    -- 5. 유일성이 확인된 ID를 새로 추가될 행의 c_id 컬럼에 할당합니다.
    :new.c_id := v_candidate_cid;
END;
/

CREATE OR REPLACE TRIGGER trg_product_order_po_no
BEFORE INSERT ON product_order
FOR EACH ROW
DECLARE
    v_next_seq NUMBER;
BEGIN
    -- 1. 현재 주문하는 유저(po_u_id)의 기존 주문 내역에서 가장 큰 시퀀스 번호를 찾습니다.
    --    'ID_' 뒷부분의 숫자만 추출하여 최댓값을 구하고, 없으면 0을 반환합니다.
    SELECT NVL(MAX(TO_NUMBER(SUBSTR(po_no, INSTR(po_no, '_') + 1))), 0) + 1
    INTO v_next_seq
    FROM product_order
    WHERE po_u_id = :new.po_u_id;

    -- 2. :new.po_no에 '유저ID_0000' 형식으로 값을 할당합니다.
    --    LPAD 함수로 v_next_seq 값을 4자리로 만들고, 빈자리는 0으로 채웁니다. (예: 1 -> 0001)
    :new.po_no := :new.po_u_id || '_' || LPAD(v_next_seq, 4, '0');
END;
/


CREATE OR REPLACE TRIGGER trg_product_item_p_pid
BEFORE INSERT ON product_item
FOR EACH ROW
DECLARE
    v_candidate_pid VARCHAR2(50);
    v_count         NUMBER;
BEGIN
    LOOP
        -- 1. '판매자ID_카테고리ID_랜덤숫자8자리' 형식의 후보 ID를 생성합니다.
        v_candidate_pid := :new.p_seller_id || '_' || :new.p_pc_id || '_' || TRUNC(DBMS_RANDOM.VALUE(10000000, 99999999));

        -- 2. 생성된 후보 ID가 PRODUCT_ITEM 테이블에 이미 존재하는지 확인합니다.
        SELECT COUNT(*)
        INTO v_count
        FROM product_item
        WHERE p_pid = v_candidate_pid;

        -- 3. 존재하지 않으면 (count가 0이면) 루프를 종료합니다.
        EXIT WHEN v_count = 0;

    -- 4. 만약 ID가 중복되면, 루프를 다시 실행하여 새로운 랜덤 ID를 생성하고 검사합니다.
    END LOOP;

    -- 5. 유일성이 확인된 ID를 새로 추가될 행의 p_pid 컬럼에 최종 할당합니다.
    :new.p_pid := v_candidate_pid;
END;
/


CREATE OR REPLACE TRIGGER trg_product_review_pr_no
BEFORE INSERT ON product_review
FOR EACH ROW
DECLARE
    v_next_seq NUMBER;
BEGIN
    -- 1. 현재 리뷰를 작성하는 유저(pr_u_id)가 동일한 제품(pr_p_pid)에 대해
    --    작성한 기존 리뷰 중 가장 큰 순번을 찾습니다.
    SELECT NVL(MAX(TO_NUMBER(SUBSTR(pr_no, INSTR(pr_no, '_', 1, 2) + 1))), 0) + 1
    INTO v_next_seq
    FROM product_review
    WHERE pr_u_id = :new.pr_u_id AND pr_p_pid = :new.pr_p_pid;

    -- 2. :new.pr_no에 '유저ID_제품ID_00' 형식으로 값을 할당합니다.
    --    LPAD 함수로 v_next_seq 값을 2자리로 만들고, 빈자리는 0으로 채웁니다. (예: 1 -> 01)
    :new.pr_no := :new.pr_u_id || '_' || :new.pr_p_pid || '_' || LPAD(v_next_seq, 2, '0');
END;
/

CREATE OR REPLACE TRIGGER trg_board_b_id
BEFORE INSERT ON board
FOR EACH ROW
DECLARE
    v_next_seq NUMBER;
BEGIN
    -- 1. 동일한 게시판(b_bt_type)에 동일한 유저(b_u_id)가 작성한
    --    기존 게시글 중 가장 큰 순번을 찾습니다.
    SELECT NVL(MAX(TO_NUMBER(SUBSTR(b_id, INSTR(b_id, '_', 1, 2) + 1))), 0) + 1
    INTO v_next_seq
    FROM board
    WHERE b_bt_type = :new.b_bt_type AND b_u_id = :new.b_u_id;

    -- 2. :new.b_id에 '게시판타입_유저ID_0000' 형식으로 값을 할당합니다.
    --    LPAD 함수로 v_next_seq 값을 4자리로 만들고, 빈자리는 0으로 채웁니다. (예: 1 -> 0001)
    :new.b_id := :new.b_bt_type || '_' || :new.b_u_id || '_' || LPAD(v_next_seq, 4, '0');
END;
/

CREATE OR REPLACE TRIGGER TRG_PRODUCT_OPTION_POP_NO
BEFORE INSERT ON PRODUCT_OPTION
FOR EACH ROW
DECLARE
    v_next_seq NUMBER;
BEGIN
    -- 1. 현재 추가되는 상품(POP_P_PID)의 기존 옵션들 중 가장 큰 순번을 찾습니다.
    --    - SUBSTR와 INSTR을 사용해 'PID_' 뒷부분의 숫자만 추출하여 최댓값을 구합니다.
    --    - NVL 함수를 사용하여 해당 상품의 첫 번째 옵션일 경우 0을 반환합니다.
    SELECT NVL(MAX(TO_NUMBER(SUBSTR(pop_no, INSTR(pop_no, '_', -1) + 1))), 0) + 1
    INTO v_next_seq
    FROM PRODUCT_OPTION
    WHERE POP_P_PID = :new.POP_P_PID;

    -- 2. :new.POP_NO에 '상품PID_000' 형식으로 값을 할당합니다.
    --    - LPAD 함수를 사용해 v_next_seq 값을 3자리로 만들고, 빈자리는 0으로 채웁니다. (예: 1 -> 001)
    :new.POP_NO := :new.POP_P_PID || '_' || LPAD(v_next_seq, 3, '0');
END;
/

-- Oracle SQL Developer Data Modeler 요약 보고서: 
-- 
-- CREATE TABLE                            24
-- CREATE INDEX                             0
-- ALTER TABLE                             51
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           5
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          5
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
