package com.metacoding.authblog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;
    
    public User findByUsername(String username) {
        // createQuery 사용함 주의 / jpql
        // :이 ?역할
        Query q = em.createQuery("select u from User u where u.username = :username", User.class);
        q.setParameter("username", username);
        try {
            // 변수명 PS 붙인이유 Persistance
//            User userPS = (User) q.getSingleResult();
            // optional 리턴 (null 처리 때문에) / ofNullable : null일수도 있음
//            return Optional.ofNullable(userPS);

            // 테스트를 해보니 optinal을 리턴할 필요가 없어져서 리팩토링
            // 예외 처리를 try-catch로 하기로 바꿈
            return (User) q.getSingleResult();

        } catch (RuntimeException e) {
//            return Optional.ofNullable(null);
            throw new RuntimeException("아이디 혹은 패스워드가 일치하지 않습니다.");
        }
    }
}