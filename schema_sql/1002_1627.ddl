-- 생성자 Oracle SQL Developer Data Modeler 24.3.1.347.1153
--   위치:        2025-10-02 16:27:54 KST
--   사이트:      Oracle Database 21c
--   유형:      Oracle Database 21c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE SEQUENCE ad_info_a_no_seq START WITH 1 NOCACHE ORDER;

CREATE SEQUENCE m_option_o_no_seq START WITH 1 NOCACHE ORDER;

CREATE SEQUENCE order_items_oi_prod_qty_seq START WITH 1 NOCACHE ORDER;

CREATE SEQUENCE product_order_po_no_seq START WITH 1 NOCACHE ORDER;

CREATE SEQUENCE recruitment_r_no_seq START WITH 1 NOCACHE ORDER;

CREATE SEQUENCE user_point_up_no_seq START WITH 1 NOCACHE ORDER;

CREATE SEQUENCE version_v_no_seq START WITH 1 NOCACHE ORDER;

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
    a_file       BLOB,
    a_reg_date   TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp
)
LOGGING;

ALTER TABLE ad_info ADD CONSTRAINT ad_info_pk PRIMARY KEY ( a_no );

CREATE TABLE board (
    b_id       VARCHAR2(20) NOT NULL,
    b_u_id     VARCHAR2(20) NOT NULL,
    b_bt_type  VARCHAR2(20) NOT NULL,
    b_title    VARCHAR2(100) NOT NULL,
    b_body     VARCHAR2(1000),
    b_state    VARCHAR2(20),
    b_listener VARCHAR2(20),
    b_answer   VARCHAR2(500),
    b_view     NUMBER(6),
    b_file     BLOB,
    b_reg_date TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp
)
LOGGING;

ALTER TABLE board ADD CONSTRAINT board_pk PRIMARY KEY ( b_id );

CREATE TABLE board_type (
    bt_type VARCHAR2(20) NOT NULL,
    bt_name VARCHAR2(20)
)
LOGGING;

ALTER TABLE board_type ADD CONSTRAINT board_type_pk PRIMARY KEY ( bt_type );

CREATE TABLE cart_items (
    ci_u_id      VARCHAR2(20) NOT NULL,
    ci_p_pid     VARCHAR2(20) NOT NULL,
    ci_amount    NUMBER,
    ci_tot_price NUMBER(10)
)
LOGGING;

ALTER TABLE cart_items ADD CONSTRAINT cart_items_pk PRIMARY KEY ( ci_u_id,
                                                                  ci_p_pid );

CREATE TABLE category_links (
    cl_p_pid VARCHAR2(20) NOT NULL,
    cl_pc_id VARCHAR2(10) NOT NULL
)
LOGGING;

ALTER TABLE category_links ADD CONSTRAINT category_links_pk PRIMARY KEY ( cl_p_pid,
                                                                          cl_pc_id );

CREATE TABLE coupon (
    c_id           VARCHAR2(10) NOT NULL,
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
)
LOGGING;

ALTER TABLE coupon ADD CONSTRAINT coupon_pk PRIMARY KEY ( c_id );

CREATE TABLE coupon_type (
    ct_type VARCHAR2(20) NOT NULL
)
LOGGING;

ALTER TABLE coupon_type ADD CONSTRAINT coupon_type_pk PRIMARY KEY ( ct_type );

CREATE TABLE m_terms (
    t_name VARCHAR2(50) NOT NULL,
    t_body CLOB
)
LOGGING;

ALTER TABLE m_terms ADD CONSTRAINT terms_pk PRIMARY KEY ( t_name );

CREATE TABLE order_items (
    oi_po_no            NUMBER NOT NULL,
    oi_p_pid            VARCHAR2(20) NOT NULL,
    oi_s_u_id           VARCHAR2(20) NOT NULL,
    oi_prod_qty         NUMBER,
    oi_tot_price        NUMBER(10),
    oi_tracking_company VARCHAR2(20),
    oi_tracking_num     VARCHAR2(20),
    oi_etc              VARCHAR2(300),
    oi_del_status       VARCHAR2(50)
)
LOGGING;

ALTER TABLE order_items ADD CONSTRAINT order_items_pk PRIMARY KEY ( oi_po_no,
                                                                    oi_p_pid );

CREATE TABLE point_history (
    uh_no           VARCHAR2(50) NOT NULL,
    uh_u_id         VARCHAR2(50) NOT NULL,
    uh_text         VARCHAR2(200),
    uh_change_point NUMBER,
    uh_change_date  NUMBER,
    uh_dday         TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp
)
LOGGING;

ALTER TABLE point_history ADD CONSTRAINT point_history_pk PRIMARY KEY ( uh_no );

CREATE TABLE product_category (
    pc_id    VARCHAR2(10) NOT NULL,
    pc_name  VARCHAR2(20),
    pc_path  VARCHAR2(50),
    pc_lever VARCHAR2(10)
)
LOGGING;

ALTER TABLE product_category ADD CONSTRAINT product_category_pk PRIMARY KEY ( pc_id );

CREATE TABLE product_detail (
    pd_p_pid         VARCHAR2(20) NOT NULL,
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
)
LOGGING;

ALTER TABLE product_detail ADD CONSTRAINT product_detail_pk PRIMARY KEY ( pd_p_pid );

CREATE TABLE product_item (
    p_pid            VARCHAR2(20) NOT NULL,
    p_name           VARCHAR2(20) NOT NULL,
    p_desc           VARCHAR2(200),
    p_price          NUMBER NOT NULL,
    p_discount       NUMBER,
    p_point          NUMBER(10),
    p_stock_quantity NUMBER(5),
    p_delivery_price NUMBER,
    p_regdate        TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp,
    p_seller_id      VARCHAR2(20) NOT NULL,
    p_pc_id          VARCHAR2(10) NOT NULL,
    p_iamge_list     BLOB,
    p_image_main     BLOB,
    p_image_detail   BLOB,
    p_detail_info    BLOB,
    p_view_count     NUMBER
)
LOGGING;

ALTER TABLE product_item ADD CONSTRAINT product_item_pk PRIMARY KEY ( p_pid );

CREATE TABLE product_option (
    po_no    NUMBER(10) NOT NULL,
    po_p_pid VARCHAR2(20) NOT NULL,
    po_name  VARCHAR2(20)
)
LOGGING;

ALTER TABLE product_option ADD CONSTRAINT product_option_pk PRIMARY KEY ( po_p_pid,
                                                                          po_no );

CREATE TABLE product_order (
    po_no             NUMBER NOT NULL,
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
    po_request_note   VARCHAR2(100)
)
LOGGING;

ALTER TABLE product_order ADD CONSTRAINT product_order_pk PRIMARY KEY ( po_no );

CREATE TABLE product_review (
    pr_no       VARCHAR2(50) NOT NULL,
    pr_po_no    NUMBER NOT NULL,
    pr_p_pid    VARCHAR2(20) NOT NULL,
    pr_u_id     VARCHAR2(20) NOT NULL,
    pr_star     NUMBER(2, 1),
    pr_body     VARCHAR2(500),
    pr_reg_date TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp
)
LOGGING;

ALTER TABLE product_review ADD CONSTRAINT product_review_pk PRIMARY KEY ( pr_no );

CREATE TABLE product_tags (
    pt_name  VARCHAR2(20) NOT NULL,
    pt_state VARCHAR2(10) DEFAULT 'ACTIVE',
    pt_path  VARCHAR2(50)
)
LOGGING;

ALTER TABLE product_tags ADD CONSTRAINT priduct_tags_pk PRIMARY KEY ( pt_name );

CREATE TABLE recruitment (
    r_no         NUMBER NOT NULL,
    r_dept       VARCHAR2(50),
    r_career     VARCHAR2(50),
    r_type       VARCHAR2(20),
    r_title      VARCHAR2(50),
    r_status     VARCHAR2(20),
    r_start_date DATE,
    r_end_date   DATE,
    r_reg_date   TIMESTAMP WITH TIME ZONE DEFAULT systimestamp
)
LOGGING;

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
)
LOGGING;

ALTER TABLE seller_info ADD CONSTRAINT seller_info_pk PRIMARY KEY ( s_u_id );

CREATE TABLE tags_links (
    tl_p_pid   VARCHAR2(20) NOT NULL,
    tl_pt_name VARCHAR2(20) NOT NULL
)
LOGGING;

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
)
LOGGING;

ALTER TABLE u_user ADD CONSTRAINT m_user_pk PRIMARY KEY ( u_id );

CREATE TABLE user_coupon (
    uc_u_id    VARCHAR2(20) NOT NULL,
    uc_c_id    VARCHAR2(10) NOT NULL,
    uc_po_no   NUMBER NOT NULL,
    uc_status  VARCHAR2(20),
    uc_use_day TIMESTAMP WITH LOCAL TIME ZONE
)
LOGGING;

CREATE TABLE user_point (
    up_u_id      VARCHAR2(20) NOT NULL,
    up_now_point NUMBER
)
LOGGING;

ALTER TABLE user_point ADD CONSTRAINT user_point_pk PRIMARY KEY ( up_u_id );

CREATE TABLE version (
    v_no                  NUMBER(10) NOT NULL,
    v_vname               VARCHAR2(50),
    v_uploader            VARCHAR2(20) NOT NULL,
    v_reg_date            TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp,
    v_body                VARCHAR2(500),
    v_title               VARCHAR2(50),
    v_sub_title           VARCHAR2(100),
    v_header_logo         BLOB,
    v_footer_logo         BLOB,
    v_favicon             BLOB,
    v_company             VARCHAR2(20),
    v_ceo                 VARCHAR2(20),
    v_seller_reg_no       VARCHAR2(50),
    v_online_sales_reg_no VARCHAR2(50),
    v_addr                VARCHAR2(200),
    v_tel                 VARCHAR2(20),
    v_act_time            VARCHAR2(100),
    v_email               VARCHAR2(50),
    v_manager_phone       VARCHAR2(50),
    v_copylight           VARCHAR2(50)
)
LOGGING;

ALTER TABLE version ADD CONSTRAINT version_pk PRIMARY KEY ( v_no );

ALTER TABLE order_items
    ADD CONSTRAINT "..P_PID "
        FOREIGN KEY ( oi_p_pid )
            REFERENCES product_item ( p_pid )
            NOT DEFERRABLE;

ALTER TABLE user_coupon
    ADD CONSTRAINT "..u-id "
        FOREIGN KEY ( uc_u_id )
            REFERENCES u_user ( u_id )
            NOT DEFERRABLE;

ALTER TABLE cart_items
    ADD CONSTRAINT ".P_PID "
        FOREIGN KEY ( ci_p_pid )
            REFERENCES product_item ( p_pid )
            NOT DEFERRABLE;

ALTER TABLE product_option
    ADD CONSTRAINT ".p_pid. "
        FOREIGN KEY ( po_p_pid )
            REFERENCES product_item ( p_pid )
            NOT DEFERRABLE;

ALTER TABLE board
    ADD CONSTRAINT bt_type
        FOREIGN KEY ( b_bt_type )
            REFERENCES board_type ( bt_type )
            NOT DEFERRABLE;

ALTER TABLE user_coupon
    ADD CONSTRAINT "c_id. "
        FOREIGN KEY ( uc_c_id )
            REFERENCES coupon ( c_id )
            NOT DEFERRABLE;

ALTER TABLE coupon
    ADD CONSTRAINT "ct_type "
        FOREIGN KEY ( c_ct_type )
            REFERENCES coupon_type ( ct_type )
            NOT DEFERRABLE;

ALTER TABLE tags_links
    ADD CONSTRAINT p_pid
        FOREIGN KEY ( tl_p_pid )
            REFERENCES product_item ( p_pid )
            NOT DEFERRABLE;

ALTER TABLE product_detail
    ADD CONSTRAINT "P_PID.  "
        FOREIGN KEY ( pd_p_pid )
            REFERENCES product_item ( p_pid )
                ON DELETE CASCADE
            NOT DEFERRABLE;

ALTER TABLE category_links
    ADD CONSTRAINT "p_pid .  "
        FOREIGN KEY ( cl_p_pid )
            REFERENCES product_item ( p_pid )
            NOT DEFERRABLE;

ALTER TABLE product_review
    ADD CONSTRAINT "P_PID . . "
        FOREIGN KEY ( pr_p_pid )
            REFERENCES product_item ( p_pid )
            NOT DEFERRABLE;

ALTER TABLE product_item
    ADD CONSTRAINT pc_id
        FOREIGN KEY ( p_pc_id )
            REFERENCES product_category ( pc_id )
            NOT DEFERRABLE;

ALTER TABLE category_links
    ADD CONSTRAINT "pc_id   "
        FOREIGN KEY ( cl_pc_id )
            REFERENCES product_category ( pc_id )
            NOT DEFERRABLE;

ALTER TABLE order_items
    ADD CONSTRAINT po_no
        FOREIGN KEY ( oi_po_no )
            REFERENCES product_order ( po_no )
            NOT DEFERRABLE;

ALTER TABLE product_review
    ADD CONSTRAINT "PO_NO  "
        FOREIGN KEY ( pr_po_no )
            REFERENCES product_order ( po_no )
            NOT DEFERRABLE;

ALTER TABLE user_coupon
    ADD CONSTRAINT "po_no. "
        FOREIGN KEY ( uc_po_no )
            REFERENCES product_order ( po_no )
            NOT DEFERRABLE;

ALTER TABLE tags_links
    ADD CONSTRAINT pt_name
        FOREIGN KEY ( tl_pt_name )
            REFERENCES product_tags ( pt_name )
            NOT DEFERRABLE;

ALTER TABLE order_items
    ADD CONSTRAINT s_u_id
        FOREIGN KEY ( oi_s_u_id )
            REFERENCES seller_info ( s_u_id )
            NOT DEFERRABLE;

ALTER TABLE coupon
    ADD CONSTRAINT "s_u_id ."
        FOREIGN KEY ( c_s_u_id )
            REFERENCES seller_info ( s_u_id )
            NOT DEFERRABLE;

ALTER TABLE product_item
    ADD CONSTRAINT u_id
        FOREIGN KEY ( p_seller_id )
            REFERENCES u_user ( u_id )
            NOT DEFERRABLE;

ALTER TABLE seller_info
    ADD CONSTRAINT "U_ID "
        FOREIGN KEY ( s_u_id )
            REFERENCES u_user ( u_id )
            NOT DEFERRABLE;

ALTER TABLE user_point
    ADD CONSTRAINT "u_id ."
        FOREIGN KEY ( up_u_id )
            REFERENCES u_user ( u_id )
            NOT DEFERRABLE;

ALTER TABLE cart_items
    ADD CONSTRAINT "u_id . "
        FOREIGN KEY ( ci_u_id )
            REFERENCES u_user ( u_id )
            NOT DEFERRABLE;

ALTER TABLE point_history
    ADD CONSTRAINT "U_ID .."
        FOREIGN KEY ( uh_u_id )
            REFERENCES u_user ( u_id )
            NOT DEFERRABLE;

ALTER TABLE product_review
    ADD CONSTRAINT "U_ID . . "
        FOREIGN KEY ( pr_u_id )
            REFERENCES u_user ( u_id )
            NOT DEFERRABLE;

ALTER TABLE version
    ADD CONSTRAINT "u_id .v1"
        FOREIGN KEY ( v_uploader )
            REFERENCES u_user ( u_id )
            NOT DEFERRABLE;

ALTER TABLE board
    ADD CONSTRAINT "u_id .v2"
        FOREIGN KEY ( b_u_id )
            REFERENCES u_user ( u_id )
            NOT DEFERRABLE;

ALTER TABLE product_order
    ADD CONSTRAINT "U_ID v1"
        FOREIGN KEY ( po_u_id )
            REFERENCES u_user ( u_id )
            NOT DEFERRABLE;

CREATE OR REPLACE TRIGGER ad_info_a_no_trg BEFORE
    INSERT ON ad_info
    FOR EACH ROW
    WHEN ( new.a_no IS NULL )
BEGIN
    :new.a_no := ad_info_a_no_seq.nextval;
END;
/

CREATE OR REPLACE TRIGGER order_items_oi_prod_qty_trg BEFORE
    INSERT ON order_items
    FOR EACH ROW
    WHEN ( new.oi_prod_qty IS NULL )
BEGIN
    :new.oi_prod_qty := order_items_oi_prod_qty_seq.nextval;
END;
/

CREATE OR REPLACE TRIGGER product_order_po_no_trg BEFORE
    INSERT ON product_order
    FOR EACH ROW
    WHEN ( new.po_no IS NULL )
BEGIN
    :new.po_no := product_order_po_no_seq.nextval;
END;
/

CREATE OR REPLACE TRIGGER recruitment_r_no_trg BEFORE
    INSERT ON recruitment
    FOR EACH ROW
    WHEN ( new.r_no IS NULL )
BEGIN
    :new.r_no := recruitment_r_no_seq.nextval;
END;
/

CREATE OR REPLACE TRIGGER version_v_no_trg BEFORE
    INSERT ON version
    FOR EACH ROW
    WHEN ( new.v_no IS NULL )
BEGIN
    :new.v_no := version_v_no_seq.nextval;
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
-- CREATE SEQUENCE                          7
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
