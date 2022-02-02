package com.example.restaurant.naver;


import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void searchLocalTest(){
        var search = new SearchLocalReq();
        search.setQuery("갈비집");
        //search.setDisplay(1);
        //search.setStart(1);
        //search.setSort("random");

        var result = naverClient.searchLocal(search);
        Assertions.assertNotNull(result.getItems().stream().findFirst().get().getCategory());
        System.out.println(result);

    }

    @Test
    public void searchImageTest(){
        var search = new SearchImageReq();
        search.setQuery("갈비집");
        //search.setDisplay(1);
        //search.setStart(1);
        //search.setSort("random");

        var result = naverClient.searchImage(search);
        System.out.println(result);

    }


}
