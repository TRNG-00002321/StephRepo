package com.revature.users;

import com.revature.users.dao.EmailClient;
import com.revature.users.dao.UserRepository;
import com.revature.users.dao.UserNotFoundException;
import com.revature.users.model.User;
import com.revature.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest
{
    @Mock // creates the mocked UserRepository
    private UserRepository ur;

    @Mock
    private EmailClient ec;

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
    void getUser_existingUser_returnsUser() {
        // Arrange: Configure the mock
        User eUser = new User(2L, "John", "joe@email.org");
        eUser.setId(1L);

        when(ur.findById(1L)).thenReturn(eUser);

        // Act: Call the method under test
        User actualUser = us.getUser(1L);

        // Assert: Verify the result
        assertEquals(eUser, actualUser);
        assertEquals("John", actualUser.getName());
    }

    @Test
    void getUser_nonExistentUser_throwsException() {
        // Arrange: Mock returns empty Optional
        when(ur.findById(999L)).thenReturn(null);

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            us.getUser(999L);
        });
    }

    @Test  //create user happy path
    public void testUserRegister_Pos(){

        //Arrange
        when(us.existsByEmail(nUser.getEmail())).thenReturn(false);
        when(ur.save(nUser)).thenReturn(nUser);

        //Act
        nUser.setId(2L);

        assertFalse(us.existsByEmail(nUser.getEmail()));
        assertEquals(2L,ur.save(nUser).getId());
        //Mock
        verify(ur,times(1)).save(nUser);


    }

    @Test  //create user duplicate path
    public void testUserRegister_Duplicate(){

        // Arrange
        when(us.register(eUser)).thenReturn(false);
        //Act
        boolean regUser = us.register(eUser);

        //Assert
        assertFalse(regUser);
        //Mock
        verify(ur,times(1)).save(eUser);


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
