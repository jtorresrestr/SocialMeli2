package org.bootcamp.javazoo.service.impl;

import org.bootcamp.javazoo.dto.response.CountFollowersDto;
import org.bootcamp.javazoo.entity.Seller;
import org.bootcamp.javazoo.service.interfaces.IFindService;
import org.bootcamp.javazoo.util.MockBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SellerServiceImplTest {

    @Mock
    IFindService findService;

    @InjectMocks
    SellerServiceImpl sellerService;
    @Test
    @DisplayName("T0007 getFollowersListCount -> Number of followers is correct")
    void getFollowersListCount() {
        //Arrange
        int userId = 1;
        Seller seller = MockBuilder.sellersBuilder().get(0);
        when(findService.getSellerById(userId)).thenReturn(seller);

        //Act
        CountFollowersDto countResult = sellerService.getFollowersListCount(userId);

        //Assert
        assertEquals(seller.getFollowers().size(), countResult.getFollowers_count());
    }
}