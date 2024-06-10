-- board 테이블 정의
CREATE TABLE `board` (
  `no` int NOT NULL AUTO_INCREMENT COMMENT '번호',
  `title` varchar(100) NOT NULL COMMENT '제목',
  `writer` varchar(100) NOT NULL COMMENT '작성자',
  `content` text COMMENT '내용',
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  `upd_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자',
  `views` int NOT NULL DEFAULT '0' COMMENT '조회수',
  PRIMARY KEY (`no`)
) COMMENT='게시판';

-- 샘플 데이터 등록
TRUNCATE board;

INSERT INTO board( title, writer, content ) 
VALUES ('제목01','작성자01','내용01')
      ,('제목02','작성자02','내용02')
      ,('제목03','작성자03','내용03')
      ,('제목04','작성자04','내용04')
      ,('제목05','작성자05','내용05')