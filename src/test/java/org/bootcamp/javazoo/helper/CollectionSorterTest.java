package org.bootcamp.javazoo.helper;

import org.bootcamp.javazoo.dto.UserDto;
import org.bootcamp.javazoo.exception.BadRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CollectionSorterTest {

    @Test
    @DisplayName("T0003 sortTypeExists -> Sort type by name asc")
    void sortUserDtoCollectionByNameAscTest() {
        //Arrange
        List<UserDto> userDtoList = new ArrayList<>();
        UserDto userDto = new UserDto(1, "Luke Skywalker");
        UserDto userDto2 = new UserDto(2, "Leia Organa");
        userDtoList.add(userDto);
        userDtoList.add(userDto2);
        String order = "name_asc";
        List<UserDto> userDtoSortedList = userDtoList.stream().sorted(Comparator.comparing(UserDto::getUser_name)).toList();

        //Act
        List<UserDto> userDtoResult = CollectionSorter.sortUserDtoCollection(userDtoList, order);

        //Assert
        assertEquals(userDtoSortedList, userDtoResult);
    }

    @Test
    @DisplayName("T0003 sortTypeExists -> Sort type by name desc")
    void sortUserDtoCollectionByNameDescTest() {
        //Arrange
        List<UserDto> userDtoList = new ArrayList<>();
        UserDto userDto = new UserDto(1, "Luke Skywalker");
        UserDto userDto2 = new UserDto(2, "Leia Organa");
        userDtoList.add(userDto);
        userDtoList.add(userDto2);
        String order = "name_desc";
        List<UserDto> userDtoSortedList = userDtoList.stream().sorted(Comparator.comparing(UserDto::getUser_name).reversed()).toList();

        //Act
        List<UserDto> userDtoResult = CollectionSorter.sortUserDtoCollection(userDtoList, order);

        //Assert
        assertEquals(userDtoSortedList, userDtoResult);
    }

    @Test
    @DisplayName("T0003 sortTypeExists - Sort type Invalid")
    void sortUserDtoCollectionInvalidTest() {
        //Arrange
        List<UserDto> userDtoList = new ArrayList<>();
        UserDto userDto = new UserDto(1, "Luke Skywalker");
        UserDto userDto2 = new UserDto(2, "Leia Organa");
        userDtoList.add(userDto);
        userDtoList.add(userDto2);
        String order = "cadcad";

        //Act & Assert
        assertThrows(BadRequestException.class, () -> CollectionSorter.sortUserDtoCollection(userDtoList, order));
    }
}