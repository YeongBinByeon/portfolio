package com.fastcampus.javaallinone.project3.demo.repository;

import com.fastcampus.javaallinone.project3.demo.domain.Block;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlockRepositoryTest {

    @Autowired
    private BlockRepository blockRepository;

    @Test
    void crud(){
        Block block = new Block();

        block.setName("martin");
        block.setReason("귀찮아서");
        block.setStartDate(LocalDate.now());
        block.setEndDate(LocalDate.now());

        blockRepository.save(block);

        List<Block> blocks = blockRepository.findAll();
        Assert.assertEquals(blocks.size(), 3);
        Assert.assertEquals(blocks.get(0).getName(), "dennis");
        Assert.assertEquals(blocks.get(1).getName(), "sophia");
        Assert.assertEquals(blocks.get(2).getName(), "martin");
    }
}