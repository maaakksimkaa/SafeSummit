package com.ssummit.repository;

import com.ssummit.model.Role;
import com.ssummit.model.User;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends GenericRepository<User> {

	Set<User> findAllByIdIn(Set<Long> ids); // Возвращает set Users по id

	Set<User> findAllByRole(Role role); // Возвращает set Users по role

	User findByEmail(String email); // Возвращает User по email

	User findByChangePasswordToken(String token); // Возвращает User по токену смены пароля.

    User findUserByLogin(String login); // Возвращает User по login

    Boolean existsByLogin(String login); // Проверка на наличие User с определенным login.

}
