package com.revature.users;

import com.revature.users.dao.UserRepository;
import com.revature.users.model.User;
import com.revature.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest
{
    @Mock // creates the mocked UserRepository
    private UserRepository ur;

    @InjectMocks //injects UserRepo into userService
    private UserService us;


    private User eUser;
    private User nUser;


    @BeforeEach
    public void setUp(){
        eUser=new User(1L,"Bob","joe@email.org");
        nUser=new User(null,"Cat","Meowdy@email.org");
    }

    @Test
    public void testUserById_Pos(){
        //Stub - Arrange
        when(ur.findById(1L)).thenReturn(eUser);

        //Act
        User fUser = us.getUserById(1L);

        //Assert
        assertEquals("Bob",fUser.getName());

        //Mock
        verify(ur,times(1)).findById(1L);


    }

    @Test
    public void testUserById_NotFound(){
        //Stub - Arrange
        when(ur.findById(1L)).thenReturn(null);

        //Act
        User fUser = us.getUserById(1L);

        //Assert
        assertNull(fUser);

        //Mock
        verify(ur,times(1)).findById(1L);


    }
    @Test
    public void testUserRegister_Pos(){

        //Act
        us.register(nUser);

        //Mock
        verify(ur,times(1)).save(nUser);


    }

    @Test
    public void testUserRegister_ExistsAlready(){
        //Stub - Arrange
        when(ur.findByEmail("joe@email.org")).thenReturn(eUser);

        //Act
        us.register(eUser); //false means exists
        boolean exist = us.register(eUser);

                //Assert
        assertFalse(exist, "they exist already");

        //Mock
        verify(ur,times(2)).findByEmail("joe@email.org");


    }

}
