DROP TABLE USERS CASCADE CONSTRAINTS;
DROP TABLE STUDYGROUP CASCADE CONSTRAINTS;
DROP TABLE GROUPMEMBERS CASCADE CONSTRAINTS;

CREATE TABLE USERS (
    USER_ID    VARCHAR2(18 BYTE) PRIMARY KEY,
    PASSWORD   VARCHAR2(100 BYTE) NOT NULL,
    USERNAME   VARCHAR2(100 BYTE) NOT NULL,
    EMAIL      VARCHAR2(100 BYTE),
    PHONE      VARCHAR2(15 BYTE)
);

CREATE TABLE STUDYGROUP (
	GROUP_ID	CHAR(200 BYTE) PRIMARY KEY,
	GROUPNAME	VARCHAR2(200 BYTE) NOT NULL,
	GROUPDESCRIPTION	VARCHAR2(500 BYTE),
	GOAL		VARCHAR2(200 BYTE),
	CATEGORY	VARCHAR2(100 BYTE),
	CURRMEMBER	NUMBER(10 BYTE),
	MAXMEMBER	NUMBER(10 BYTE),
	LEADER		VARCHAR2(18 BYTE)
);

CREATE TABLE GROUPMEMBER (
    GROUP_ID CHAR(200 BYTE),
    USER_ID CHAR(18 BYTE),
    ROLE VARCHAR2(50 BYTE),
    ATTENDANCE_RATE NUMBER(3, 1) DEFAULT 0,
    QUIZ_RATE NUMBER(3, 1) DEFAULT 0,
    ASSIGN_RATE NUMBER(3, 1) DEFAULT 0,
    PRIMARY KEY (GROUP_ID, USER_ID),
    FOREIGN KEY (GROUP_ID) REFERENCES STUDYGROUP(GROUP_ID) ON DELETE CASCADE,
    FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID) ON DELETE CASCADE
);
COMMIT;

