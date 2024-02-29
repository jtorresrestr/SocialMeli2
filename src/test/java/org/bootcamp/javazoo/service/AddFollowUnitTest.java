package org.bootcamp.javazoo.service;

import org.bootcamp.javazoo.dto.response.MessageDto;
import org.bootcamp.javazoo.entity.Seller;
import org.bootcamp.javazoo.entity.User;
import org.bootcamp.javazoo.exception.NotFoundException;
import org.bootcamp.javazoo.repository.impl.SellerRepositoryImpl;
import org.bootcamp.javazoo.repository.impl.UserRepositoryImpl;
import org.bootcamp.javazoo.service.impl.FindServiceImpl;
import org.bootcamp.javazoo.service.impl.SellerServiceImpl;
import org.bootcamp.javazoo.util.MockBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddFollowUnitTest {
    @Mock
    private UserRepositoryImpl userRepository;
    @Mock
    private SellerRepositoryImpl sellerRepository;
    @Mock
    private FindServiceImpl findService;
    @InjectMocks
    private SellerServiceImpl sellerService;


    @Test
    @DisplayName("T0001 getSellerById -> when seller is found then return seller")
    public void getSellerByIdTest() {
        //Arrange
        Seller seller = MockBuilder.sellersBuilder().get(1);
        User user = MockBuilder.usersBuilder().get(1);
        Integer userId = user.getId();
        Integer sellerId = seller.getId();

        when(findService.getUserById(userId)).thenReturn(user);
        when(findService.getSellerById(any(Integer.class))).thenAnswer(invocation -> {
            int sId = invocation.getArgument(0);
            Seller s = new Seller();
            s.setId(sId);

            return s;
        });

        //act
        MessageDto result = sellerService.addFollow(userId, sellerId);

        //assert
        verify(findService, times(1)).getUserById(userId);
        verify(findService, times(1)).getSellerById(sellerId);
        assertNotNull(result);
    }

    @Test
    @DisplayName("T0001 getSellerById -> when seller is not found then throw NotFoundException")
    public void getSellerByIdNotFoundTest() {
        // Arrange
        int sellerId = 1;
        User user = MockBuilder.usersBuilder().get(1);
        Integer userId = user.getId();

        when(findService.getUserById(userId)).thenReturn(user);
        when(findService.getSellerById(eq(sellerId))).thenThrow(new NotFoundException("Seller not found"));

        // Act and Assert
        assertThrows(NotFoundException.class, () -> {
            sellerService.addFollow(userId, sellerId);
        });

        // Verify
        verify(findService, times(1)).getUserById(userId);
        verify(findService, times(1)).getSellerById(sellerId);
    }
}
