 package com.usermanagement.service;

 import com.usermanagement.entity.User;
 import com.usermanagement.repository.UserRepository;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.mockito.InjectMocks;
 import org.mockito.Mock;
 import org.mockito.junit.jupiter.MockitoExtension;

 import java.util.Optional;
 import java.util.UUID;

 import static org.junit.jupiter.api.Assertions.assertEquals;
 import static org.junit.jupiter.api.Assertions.assertNotNull;
 import static org.mockito.Mockito.verify;
 import static org.mockito.Mockito.when;

 @ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void doitTrouverUnUtilisateur() {
        UUID id = UUID.randomUUID();

        User user = new User();
        user.setId(id);
        user.setName("Youssef");

        when(userRepository.findById(id))
                .thenReturn(Optional.of(user));

        User resultat = userService.getUserById(id);

        assertNotNull(resultat);
        assertEquals("Youssef", resultat.getName());

        verify(userRepository).findById(id);
    }
}