SET SERVEROUTPUT ON;

BEGIN
    -- 1. 모든 테이블에 대해 반복하며 삭제
    FOR tab IN (SELECT table_name FROM user_tables)
    LOOP
        BEGIN
            -- CASCADE CONSTRAINTS: 테이블을 참조하는 모든 외래 키 제약 조건을 같이 삭제합니다.
            -- PURGE: Recycle Bin(휴지통)에 넣지 않고 즉시 영구 삭제합니다.
            EXECUTE IMMEDIATE 'DROP TABLE ' || tab.table_name || ' CASCADE CONSTRAINTS PURGE';
            DBMS_OUTPUT.PUT_LINE('테이블 삭제 완료: ' || tab.table_name);
        EXCEPTION
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('테이블 삭제 실패: ' || tab.table_name || ' (' || SQLERRM || ')');
        END;
    END LOOP;
END;
/


SET SERVEROUTPUT ON;

BEGIN
    -- 1. 모든 트리거에 대해 반복하며 삭제
    FOR trg IN (SELECT trigger_name FROM user_triggers)
    LOOP
        BEGIN
            EXECUTE IMMEDIATE 'DROP TRIGGER ' || trg.trigger_name;
            DBMS_OUTPUT.PUT_LINE('트리거 삭제 완료: ' || trg.trigger_name);
        EXCEPTION
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('트리거 삭제 실패: ' || trg.trigger_name || ' (' || SQLERRM || ')');
        END;
    END LOOP;
END;
/

SET SERVEROUTPUT ON;

BEGIN
    -- 현재 스키마가 소유한 모든 시퀀스를 조회합니다.
    FOR seq IN (SELECT sequence_name FROM user_sequences)
    LOOP
        BEGIN
            -- 동적 SQL을 사용하여 시퀀스를 삭제합니다.
            EXECUTE IMMEDIATE 'DROP SEQUENCE ' || seq.sequence_name;
            DBMS_OUTPUT.PUT_LINE('시퀀스 삭제 완료: ' || seq.sequence_name);
        EXCEPTION
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('시퀀스 삭제 실패: ' || seq.sequence_name || ' (' || SQLERRM || ')');
        END;
    END LOOP;
END;
/