package kr.co.fastcampus.eatgo.domain;


import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.*;

public class RestaurantTests {

    @Test
    public void creation(){
        Restaurant restaurant = new Restaurant(1004L,"Bob zip", "Seoul");
        assertThat(restaurant.getId(), Is.is(1004L));
        assertThat(restaurant.getName(), Is.is("Bob zip"));
        assertThat(restaurant.getAddress(), Is.is("Seoul"));
    }
    @Test
    public void information(){
        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
        assertThat(restaurant.getInformation(),Is.is("Bob zip in Seoul"));
    }

}