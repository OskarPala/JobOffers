package com.joboffers.domain.loginandregister;


import com.joboffers.domain.loginandregister.dto.RegisterUserDto;
import com.joboffers.domain.loginandregister.dto.RegistrationResultDto;
import com.joboffers.domain.loginandregister.dto.UserDto;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertAll;


class LoginAndRegisterFacadeTest {
    LoginAndRegisterFacade loginAndRegisterFacade = new LoginAndRegisterFacade(
            new InMemoryLoginRepository()
    );

    @Test
    public void should_register_user() {
        //    given
        RegisterUserDto registerUserDto = new RegisterUserDto("username", "password");
        //    when
        RegistrationResultDto register = loginAndRegisterFacade.register(registerUserDto);
        //    then
        assertAll(
                () -> assertThat(register.created()).isTrue(),
                () -> assertThat(register.username()).isEqualTo("username")
        );
    }

    @Test
    public void should_find_user_by_user_name() {
        //    given
        RegisterUserDto registerUserDto = new RegisterUserDto("username", "password");
        RegistrationResultDto register = loginAndRegisterFacade.register(registerUserDto);
        //    when
        UserDto userByName = loginAndRegisterFacade.findByUsername(register.username());
        //    then
        assertThat(userByName).isEqualTo(new UserDto(register.id(), "password", "username"));
    }

    @Test
    public void should_throw_exception_when_user_not_found() {
        //    given
        String username = "someUser";
        //    when
        Throwable thrown = catchThrowable(() -> loginAndRegisterFacade.findByUsername(username));
        //    then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(BadCredentialsException.class)
                .hasMessage("User not found");
    }
}