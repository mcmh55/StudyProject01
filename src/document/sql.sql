-- 휴지통 비우기 --

PURGE RECYCLEBIN;

-- 회원 --

CREATE TABLE MY_MEMBER(
    M_ID VARCHAR2(20),
    M_PW VARCHAR2(40) NOT NULL,
    M_NICK VARCHAR2(40) NOT NULL,
    M_AGE NUMBER(3) NOT NULL,
    M_EMAIL VARCHAR2(100) NOT NULL,
    CONSTRAINT MY_MEMBER_PK PRIMARY KEY(M_ID),
    CONSTRAINT MY_MEMBER_UK_EMAIL UNIQUE(M_EMAIL)
);

SELECT * FROM MY_MEMBER;

DROP TABLE MY_MEMBER
CASCADE CONSTRAINTS;


-- /////////////////////////////////////////// --

-- 게시판 --

SELECT * FROM MY_BOARD;

SELECT DBMS_LOB.SUBSTR(B_CONTENT, DBMS_LOB.GETLENGTH(B_CONTENT), 1) FROM MY_BOARD
WHERE B_SEQ = 1;

SELECT * FROM MY_BOARD
WHERE B_DEL = 0
AND B_TITLE LIKE '%안녕%'
OR B_CONTENT LIKE '%안녕%';

SELECT REPLACE(B_CONTENT, CHR(10), '<br/>') FROM MY_BOARD;

CREATE TABLE MY_BOARD(
    B_SEQ       NUMBER(8),
    B_ID        VARCHAR2(20) NOT NULL,
    B_PW        NUMBER(4) NOT NULL,
    B_TITLE     VARCHAR2(100) NOT NULL,
    B_CONTENT   NCLOB,
    B_FILENAME  VARCHAR2(40),
    B_READCOUNT NUMBER(8) DEFAULT 0,
    B_WRITEDATE DATE NOT NULL,
    B_NOTICE    NUMBER(1) DEFAULT 0,
    B_DEL       NUMBER(1) DEFAULT 0,
    CONSTRAINT MY_BOARD_PK PRIMARY KEY(B_SEQ)
);

CREATE SEQUENCE SEQ_MY_BOARD
START WITH 1 INCREMENT BY 1;

DROP TABLE MY_BOARD
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_MY_BOARD;

INSERT INTO MY_BOARD
VALUES(SEQ_MY_BOARD.NEXTVAL, 'test', 2, '우아아아', '캬캬', NULL, 0, SYSDATE-1, 0, 0);