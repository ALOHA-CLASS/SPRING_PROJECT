package com.aloha.batch.batch.item.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aloha.batch.batch.item.reader.BoardItemReader;
import com.aloha.batch.dto.Board;
import com.aloha.batch.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BoardItemWriter implements ItemWriter<Board> {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public void write(List<? extends Board> items) throws Exception {
        log.info("::::::::::::::::::::::::::::::::::::::");
        log.info("BoardItemWriter - write()");
        for (Board board : items) {
            log.info("board : " + board);
            boardMapper.update(board);
        }
    }
}