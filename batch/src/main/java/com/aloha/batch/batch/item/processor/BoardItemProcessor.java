package com.aloha.batch.batch.item.processor;

import java.util.Date;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.aloha.batch.dto.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BoardItemProcessor implements ItemProcessor<Board, Board> {

    @Override
    public Board process(Board board) throws Exception {
        log.info("::::::::::::::::::::::::::::::::::::::::");
        log.info("BoardItemProcessor - process()");
        board.setTitle("수정함!!" + new Date().getSeconds());
        return board;
    }
}