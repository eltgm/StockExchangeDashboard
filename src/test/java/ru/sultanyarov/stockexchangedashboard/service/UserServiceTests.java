package ru.sultanyarov.stockexchangedashboard.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.sultanyarov.stockexchangedashboard.domain.User;
import ru.sultanyarov.stockexchangedashboard.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTests {
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final AuthenticationManager authManager = mock(AuthenticationManager.class);
    private final UserService userService = new UserService(userRepository, null, null, null, passwordEncoder, authManager);

    @Test
    @DisplayName("Должен зарегистрировать пользователя")
    void testRegisterUser() {
        userService.registrationUser("test", "test");
        verify(authManager).authenticate(any());
    }

    @Test
    @DisplayName("Должны получить ошибку при попытке зарегистрировать существующего пользователя")
    void testTryRegisterAlreadyExistedUser() {
        when(userRepository.findByUserLogin(anyString()))
                .thenAnswer((Answer<Optional<User>>) invocation -> Optional.of(new User("test", "test")));
        assertThrows(RuntimeException.class, () -> userService.registrationUser("test", "test"));
    }
}
