package org.bootcamp.javazoo.service.impl;


import org.bootcamp.javazoo.dto.response.MessageDto;
import org.bootcamp.javazoo.entity.User;
import org.bootcamp.javazoo.exception.BadRequestException;
import org.bootcamp.javazoo.helper.Mapper;
import org.bootcamp.javazoo.repository.interfaces.IUserRepository;
import org.bootcamp.javazoo.dto.response.CountFollowersDto;
import org.bootcamp.javazoo.service.interfaces.IUserService;
import org.springframework.stereotype.Service;
import org.bootcamp.javazoo.dto.UserDto;
import org.bootcamp.javazoo.dto.response.FollowersListDto;
import org.bootcamp.javazoo.entity.Seller;
import org.bootcamp.javazoo.exception.NotFoundException;
import org.bootcamp.javazoo.repository.interfaces.ISellerRepository;
import org.bootcamp.javazoo.service.interfaces.ISellerService;

import java.util.List;

@Service
public class SellerServiceImpl implements ISellerService {
    private final ISellerRepository sellerRepository;
    private final IUserService userService;

    public SellerServiceImpl(ISellerRepository sellerRepository, IUserService userService) {
        this.sellerRepository = sellerRepository;
        this.userService = userService;
    }
    @Override
    public FollowersListDto getFollowersListService(Integer userId, String order) {
        Seller seller = this.getById(userId);
        List<UserDto> followers;
        if (order == null || order.equals("name_asc")) {
            followers = seller.getFollowers().stream()
                    .map(userService::getUserById)
                    .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                    .map(Mapper::convertUserToUserDto)
                    .toList();
        } else if (order.equals("name_desc")) {
            followers = seller.getFollowers().stream()
                    .map(userService::getUserById)
                    .sorted((o1, o2) -> o2.getName().compareTo(o1.getName()))
                    .map(Mapper::convertUserToUserDto)
                    .toList();
        } else {
            throw new BadRequestException("'order' parameter in endpoint path is invalid");
        }

        return new FollowersListDto(userId, seller.getName(), followers);
    }

    @Override
    public CountFollowersDto getFollowersListCount(Integer userId) {
        Seller seller = this.getById(userId);

        int followersCount = seller.getFollowers().size();
        return new CountFollowersDto(userId, seller.getName(), followersCount);
    }

    @Override
    public Seller getById(int sellerId){
        Seller seller = sellerRepository.findById(sellerId);
        if (seller == null) throw new NotFoundException("Seller not found");
        return seller;
    }
    @Override
    public MessageDto addFollow(Integer userId, Integer userToFollowId) {
        if (userId.equals(userToFollowId)) {
            throw new BadRequestException("A user cannot follow themselves.");
        }
        User user = userService.getUserById(userId);
        Seller seller = this.getById(userToFollowId);

        boolean alreadyFollowing = user.getFollowed().stream()
                .map(this::getById)
                .anyMatch(s -> s.getId().equals(seller.getId()));

        if (alreadyFollowing) {
            throw new BadRequestException("The user is already following the seller.");
        }

        seller.addFollower(userId);
        user.addFollowed(userToFollowId);

        return new MessageDto("Ok");
    }
}
