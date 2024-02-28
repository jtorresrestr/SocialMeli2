package org.bootcamp.javazoo.util;

import org.bootcamp.javazoo.entity.Seller;
import org.bootcamp.javazoo.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockBuilder {
    public static List<User> usersBuilder() {
        User user1 = new User(1, "Homero Simpson");
        user1.addFollowed(6);
        user1.addFollowed(7);
        user1.addFollowed(8);

        User user2 = new User(2, "Jack Sparrow");
        user2.addFollowed(6);
        user2.addFollowed(9);
        user2.addFollowed(8);

        User user3 = new User(3, "Mario Bross");
        user3.addFollowed(6);
        user3.addFollowed(8);

        User user4 = new User(4, "Bender Doblador Rodriguez");
        user4.addFollowed(6);
        user4.addFollowed(9);

        User user5 = new User(5, "Sheldon Cooper");
        return new ArrayList<>(Arrays.asList(user1, user2, user3, user4, user5));
    }

    public static List<Seller> sellersBuilder() {
        Seller seller6 = new Seller(6, "Willy Wonka", Arrays.asList(1,2,3,4), Arrays.asList(0, 2));
        Seller seller7 = new Seller(7, "Indiana Jones", List.of(1), Arrays.asList(1, 3, 4));
        Seller seller8 = new Seller(8, "Tony Stark", Arrays.asList(3, 1, 2), Arrays.asList(7, 8));
        Seller seller9 = new Seller(9, "Bruce Wayne", Arrays.asList(4, 2), List.of(6));
        Seller seller10 = new Seller(10, "Peter Parker", List.of(), List.of(5));

        return new ArrayList<>(Arrays.asList(seller6, seller7, seller8, seller9, seller10));
    }
}
