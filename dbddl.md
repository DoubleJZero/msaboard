CREATE TABLE TB_BOARD_INFO (
  BOARD_ID INT(11) NOT NULL AUTO_INCREMENT COMMENT '게시판아이디',
  BOARD_TITLE varchar(100) NOT NULL COMMENT '게시판제목',
  BOARD_CONTENTS varchar(2000) NOT NULL COMMENT '게시판내용',
  BOARD_COUNT INT(11) NOT NULL COMMENT '게시판조회수',
  RGST_ID varchar(20) NOT NULL COMMENT '등록자아이디',
  RGST_DTTM datetime NOT NULL COMMENT '등록일시',
  UPDT_ID varchar(20) NOT NULL COMMENT '수정자아이디',
  UPDT_DTTM datetime NOT NULL COMMENT '수정일시',
  PRIMARY KEY (BOARD_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci COMMENT='게시판정보';



CREATE SEQUENCE TB_BOARD_INFO_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
MAXVALUE 4000000000;