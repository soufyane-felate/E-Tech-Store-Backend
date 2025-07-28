package com.E_Tech_Store_Backend.E_Tech_Store_Backend.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {


    @Query(value = """
      select t.* from Token t inner join User u      on t.user_id = u.id      where u.id = :id and (t.expired = false or t.revoked = false)
      """, nativeQuery = true)
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);
}