package org.bootcamp.javazoo.repository.impl;

import org.bootcamp.javazoo.entity.Seller;
import org.bootcamp.javazoo.entity.User;
import org.bootcamp.javazoo.repository.interfaces.ISellerRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SellerRepositoryImpl implements ISellerRepository {
    private List<Seller> sellers = new ArrayList<>();

    public SellerRepositoryImpl() {
        User user1 = new User(1, "User 1");
        User user2 = new User(2, "User 2");
        User user3 = new User(3, "User 3");
        Seller seller1 = new Seller(1, "Seller 1");
        seller1.addFollower(user1);
        seller1.addFollower(user2);
        seller1.addFollower(user3);
        sellers.add(seller1);

    }
    @Override
    public Seller findById(Integer id) {
        return sellers.stream().filter(seller -> seller.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void addFollow(User user, Seller seller) {
        seller.addFollower(user);
        user.addFollowed(seller);
    }

}
