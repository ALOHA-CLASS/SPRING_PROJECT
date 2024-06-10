package com.aloha.batch.batch.item.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aloha.batch.dto.Board;
import com.aloha.batch.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class BoardItemReader implements ItemReader<Board> {

    @Autowired
    private BoardMapper boardMapper;

    public static int index = 1;

    @Override
    public Board read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        
        log.info("::::::::::::::::::::::::::::::::::::::");
        log.info("read() - board");
        Board board = boardMapper.select(index++);
        return board;
    }


    
}