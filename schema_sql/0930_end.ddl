-- 생성자 Oracle SQL Developer Data Modeler 24.3.1.347.1153
--   위치:        2025-09-30 17:58:34 KST
--   사이트:      Oracle Database 21c
--   유형:      Oracle Database 21c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE SEQUENCE product_order_po_no_seq START WITH 1 NOCACHE ORDER;

CREATE TABLE board (
    b_id       NUMBER NOT NULL,
    b_u_id     VARCHAR2(12) NOT NULL,
    b_type     VARCHAR2(20),
    b_title    VARCHAR2(100) NOT NULL,
    b_body     VARCHAR2(1000),
    b_state    VARCHAR2(20),
    b_listener VARCHAR2(20)
)
LOGGING;

ALTER TABLE board ADD CONSTRAINT board_pk PRIMARY KEY ( b_id );

CREATE TABLE coupon (
    c_p_id           VARCHAR2(12) NOT NULL,
    c_id             VARCHAR2(10) NOT NULL,
    c_type           VARCHAR2(20),
    c_po_no          NUMBER,
    c_discount_money NUMBER,
    c_text           VARCHAR2(50),
    c_requirements   VARCHAR2(50),
    c_state          VARCHAR2(20),
    c_dday           TIMESTAMP
)
LOGGING;

ALTER TABLE coupon ADD CONSTRAINT coupon_pk PRIMARY KEY ( c_p_id,
                                                          c_id );

CREATE TABLE faq (
    f_id         NUMBER NOT NULL,
    f_board_type VARCHAR2(20),
    f_f_type     NUMBER NOT NULL,
    f_title      VARCHAR2(50),
    f_body       VARCHAR2(500),
    f_view       NUMBER,
    f_reg_date   TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp
)
LOGGING;

ALTER TABLE faq ADD CONSTRAINT faq_pk PRIMARY KEY ( f_id );

CREATE TABLE faq_type (
    f_type NUMBER NOT NULL,
    f_name VARCHAR2(20)
)
LOGGING;

ALTER TABLE faq_type ADD CONSTRAINT faq_type_pk PRIMARY KEY ( f_type );

CREATE TABLE m_user (
    u_id            VARCHAR2(12) NOT NULL,
    u_pw            VARCHAR2(12) NOT NULL,
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
    u_state         VARCHAR2(10),
    u_last_login_at TIMESTAMP WITH LOCAL TIME ZONE
)
LOGGING;

ALTER TABLE m_user ADD CONSTRAINT m_user_pk PRIMARY KEY ( u_id );

CREATE TABLE order_items (
    oi_po_no    NUMBER NOT NULL,
    oi_po_p_pid VARCHAR2(20) NOT NULL,
    oi_prod_qty NUMBER
)
LOGGING;

ALTER TABLE order_items ADD CONSTRAINT order_items_pk PRIMARY KEY ( oi_po_p_pid,
                                                                    oi_po_no );

CREATE TABLE priduct_tags (
    pt_name  VARCHAR2(20) NOT NULL,
    pt_state VARCHAR2(10) DEFAULT 'ACTIVE'
)
LOGGING;

ALTER TABLE priduct_tags ADD CONSTRAINT priduct_tags_pk PRIMARY KEY ( pt_name );

CREATE TABLE product_category (
    pc_id    VARCHAR2(10) NOT NULL,
    pc_name  VARCHAR2(20),
    pc_path  VARCHAR2(50),
    pc_state VARCHAR2(10) DEFAULT 'ACTIVE'
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
    pd_maker         VARCHAR2(200) DEFAULT '미입력',
    pd_manuf_country VARCHAR2(200) DEFAULT '미입력',
    pd_care          VARCHAR2(500) DEFAULT '미입력',
    pd_manuf_date    VARCHAR2(50) DEFAULT '미입력',
    pd_warranty_info VARCHAR2(200) DEFAULT '미입력',
    pd_as            VARCHAR2(200) DEFAULT '미입력',
    pd_phone         VARCHAR2(50) DEFAULT '미입력'
)
LOGGING;

ALTER TABLE product_detail ADD CONSTRAINT product_detail_pk PRIMARY KEY ( pd_p_pid );

CREATE TABLE product_item (
    p_pid            VARCHAR2(20) NOT NULL,
    p_name           VARCHAR2(20) NOT NULL,
    p_desc           VARCHAR2(200),
    p_price          NUMBER NOT NULL,
    p_discount       NUMBER,
    p_delivery_price NUMBER,
    p_regdate        TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp,
    p_seller_id      VARCHAR2(12) NOT NULL,
    p_pc_id          VARCHAR2(10) NOT NULL,
    p_iamge_list     BLOB,
    p_image_main     BLOB,
    p_image_detail   BLOB,
    p_detail_info    BLOB,
    p_view_count     NUMBER
)
LOGGING;

ALTER TABLE product_item ADD CONSTRAINT product_item_pk PRIMARY KEY ( p_pid );

CREATE TABLE product_links (
    pl_p_pid   VARCHAR2(20) NOT NULL,
    pl_pt_name VARCHAR2(20) NOT NULL
)
LOGGING;

ALTER TABLE product_links ADD CONSTRAINT product_links_pk PRIMARY KEY ( pl_p_pid,
                                                                        pl_pt_name );

CREATE TABLE product_order (
    po_no               NUMBER NOT NULL,
    po_p_pid            VARCHAR2(20) NOT NULL,
    po_u_id             VARCHAR2(12) NOT NULL,
    po_pay_type         VARCHAR2(50),
    po_pay_detail       VARCHAR2(50),
    po_recipient        VARCHAR2(20),
    po_re_phone         VARCHAR2(20),
    po_item_count       NUMBER(3),
    po_sum_price        NUMBER(10),
    po_discount         NUMBER(10),
    po_delivery_price   NUMBER(10),
    po_tot_price        NUMBER(10),
    po_get_point        NUMBER(10),
    po_state            VARCHAR2(20),
    po_del_state        VARCHAR2(20),
    po_postal           VARCHAR2(10),
    po_base_addr        VARCHAR2(50),
    po_detail_addr      VARCHAR2(50),
    po_request_note     VARCHAR2(100),
    po_shipping_company VARCHAR2(20),
    po_tracking_number  VARCHAR2(20),
    po_etc              VARCHAR2(300)
)
LOGGING;

ALTER TABLE product_order ADD CONSTRAINT product_order_pk PRIMARY KEY ( po_no,
                                                                        po_p_pid );

CREATE TABLE product_review (
    pr_po_no               NUMBER NOT NULL,
    pr_p_pid               VARCHAR2(10) NOT NULL,
    pr_u_id                VARCHAR2(12) NOT NULL,
    pr_no                  NUMBER(10),
    po_star                NUMBER(2, 1),
    po_text                VARCHAR2(500),
    po_reg_date            TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp,
    product_order_po_p_pid VARCHAR2(20) NOT NULL
)
LOGGING;

ALTER TABLE product_review ADD CONSTRAINT product_review_pk PRIMARY KEY ( pr_po_no,
                                                                          pr_p_pid );

CREATE TABLE seller_info (
    s_u_id          VARCHAR2(12) NOT NULL,
    s_u_pw          VARCHAR2(20),
    s_seller_no     VARCHAR2(50),
    s_seller_type   VARCHAR2(20),
    s_sales_reg_num VARCHAR2(50),
    s_state         VARCHAR2(20),
    s_tel           VARCHAR2(20),
    s_fax           VARCHAR2(20),
    s_postal        VARCHAR2(10),
    s_base_addr     VARCHAR2(50),
    s_detail_addr   VARCHAR2(50),
    s_order_amount  NUMBER,
    s_sales_amount  NUMBER
)
LOGGING;

ALTER TABLE seller_info ADD CONSTRAINT seller_info_pk PRIMARY KEY ( s_u_id );

CREATE TABLE terms (
    t_name VARCHAR2(50) NOT NULL,
    t_body CLOB
)
LOGGING;

ALTER TABLE terms ADD CONSTRAINT terms_pk PRIMARY KEY ( t_name );

CREATE TABLE user_point (
    up_p_id         VARCHAR2(12) NOT NULL,
    up_no           NUMBER NOT NULL,
    up_point        NUMBER,
    up_now_point    NUMBER,
    up_activity_log VARCHAR2(200),
    up_get_date     TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp,
    up_dday         TIMESTAMP WITH LOCAL TIME ZONE
)
LOGGING;

ALTER TABLE user_point ADD CONSTRAINT user_point_pk PRIMARY KEY ( up_no,
                                                                  up_p_id );

CREATE TABLE version (
    v_no       NUMBER(100) NOT NULL,
    v_name     VARCHAR2(50),
    v_uploader VARCHAR2(12) NOT NULL,
    v_reg_date TIMESTAMP WITH LOCAL TIME ZONE DEFAULT systimestamp,
    v_body     VARCHAR2(500)
)
LOGGING;

ALTER TABLE version ADD CONSTRAINT version_pk PRIMARY KEY ( v_no );

ALTER TABLE faq
    ADD CONSTRAINT f_type
        FOREIGN KEY ( f_f_type )
            REFERENCES faq_type ( f_type )
            NOT DEFERRABLE;

ALTER TABLE product_links
    ADD CONSTRAINT p_pid
        FOREIGN KEY ( pl_p_pid )
            REFERENCES product_item ( p_pid )
            NOT DEFERRABLE;

ALTER TABLE product_review
    ADD CONSTRAINT "P_PID "
        FOREIGN KEY ( pr_u_id )
            REFERENCES m_user ( u_id )
            NOT DEFERRABLE;

ALTER TABLE product_order
    ADD CONSTRAINT "P_PID v1"
        FOREIGN KEY ( po_p_pid )
            REFERENCES product_item ( p_pid )
            NOT DEFERRABLE;

ALTER TABLE product_detail
    ADD CONSTRAINT "P_PID v2"
        FOREIGN KEY ( pd_p_pid )
            REFERENCES product_item ( p_pid )
                ON DELETE CASCADE
            NOT DEFERRABLE;

ALTER TABLE product_item
    ADD CONSTRAINT pc_id
        FOREIGN KEY ( p_pc_id )
            REFERENCES product_category ( pc_id )
            NOT DEFERRABLE;

ALTER TABLE order_items
    ADD CONSTRAINT po_no
        FOREIGN KEY ( oi_po_no,
                      oi_po_p_pid )
            REFERENCES product_order ( po_no,
                                       po_p_pid )
            NOT DEFERRABLE;

ALTER TABLE product_review
    ADD CONSTRAINT product_review_fk
        FOREIGN KEY ( pr_po_no,
                      product_order_po_p_pid )
            REFERENCES product_order ( po_no,
                                       po_p_pid )
            NOT DEFERRABLE;

ALTER TABLE product_links
    ADD CONSTRAINT pt_name
        FOREIGN KEY ( pl_pt_name )
            REFERENCES priduct_tags ( pt_name )
            NOT DEFERRABLE;

ALTER TABLE product_item
    ADD CONSTRAINT u_id
        FOREIGN KEY ( p_seller_id )
            REFERENCES m_user ( u_id )
            NOT DEFERRABLE;

ALTER TABLE seller_info
    ADD CONSTRAINT "U_ID "
        FOREIGN KEY ( s_u_id )
            REFERENCES m_user ( u_id )
            NOT DEFERRABLE;

ALTER TABLE coupon
    ADD CONSTRAINT "u_id. "
        FOREIGN KEY ( c_p_id )
            REFERENCES m_user ( u_id )
            NOT DEFERRABLE;

ALTER TABLE user_point
    ADD CONSTRAINT "u_id ."
        FOREIGN KEY ( up_p_id )
            REFERENCES m_user ( u_id )
            NOT DEFERRABLE;

ALTER TABLE version
    ADD CONSTRAINT "u_id  ."
        FOREIGN KEY ( v_uploader )
            REFERENCES m_user ( u_id )
            NOT DEFERRABLE;

ALTER TABLE board
    ADD CONSTRAINT "u_id   ."
        FOREIGN KEY ( b_u_id )
            REFERENCES m_user ( u_id )
            NOT DEFERRABLE;

ALTER TABLE product_order
    ADD CONSTRAINT "U_ID v1"
        FOREIGN KEY ( po_u_id )
            REFERENCES m_user ( u_id )
            NOT DEFERRABLE;

CREATE SEQUENCE faq_f_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER faq_f_id_trg BEFORE
    INSERT ON faq
    FOR EACH ROW
    WHEN ( new.f_id IS NULL )
BEGIN
    :new.f_id := faq_f_id_seq.nextval;
END;
/

CREATE SEQUENCE faq_type_f_type_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER faq_type_f_type_trg BEFORE
    INSERT ON faq_type
    FOR EACH ROW
    WHEN ( new.f_type IS NULL )
BEGIN
    :new.f_type := faq_type_f_type_seq.nextval;
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

CREATE OR REPLACE TRIGGER product_order_po_no_trg BEFORE
    INSERT ON product_order
    FOR EACH ROW
    WHEN ( new.po_no IS NULL )
BEGIN
    :new.po_no := product_order_po_no_seq.nextval;
END;
/

CREATE SEQUENCE user_point_up_no_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER user_point_up_no_trg BEFORE
    INSERT ON user_point
    FOR EACH ROW
    WHEN ( new.up_no IS NULL )
BEGIN
    :new.up_no := user_point_up_no_seq.nextval;
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



-- Oracle SQL Developer Data Modeler 요약 보고서: 
-- 
-- CREATE TABLE                            17
-- CREATE INDEX                             0
-- ALTER TABLE                             33
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           6
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
-- CREATE SEQUENCE                          6
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
