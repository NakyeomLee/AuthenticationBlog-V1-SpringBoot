package com.metacoding.authblog.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor // 풀 생성자
@NoArgsConstructor // 디폴트(빈) 생성자
@Table(name = "user_tb") // 테이블명
@Getter
@Entity // 엔티티, 이거 달아놔야 테이블 생성 가능
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrement
    private Integer id; // Null일것도 감안해서 Integer

    @Column(unique = true, nullable = false) // 컬럼제약조건 unique(index 알아서 만들어질것), not null
    private String username; // 유저 아이디는 username으로 약속되어있음, id 아님
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;

    @CreationTimestamp
    private Timestamp createdAt;

    // 권한 설정 필터링 예시 코드 (선생님이 슬랙으로 줌 / SecutiryConfig 클래스 참고)
    // ROLE_ADMIN,ROLE_MANAGER
//    private String role; // 회원가입되면 기본 ROLE_USER

    // @getter 달아놨는데 getter 굳이 또 작성한 이유
    // 아이디랑 패스워드 필드명을 username, password 이외에 다른 걸 해놨을 수 도 있기 때문
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    // 사용자의 권한 반환 (사용자가 특정 리소스에 접근할 권한)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of();

        // 권한 설정 필터링 예시 코드 (선생님이 슬랙으로 줌 / SecutiryConfig 클래스 참고)
        // 프로젝트 참고용 - 관리자일때 특정 페이지 권한 허용, 회원가입 시 유저 권한 부여
//        String[] roles = role.split(",");
//
//        List<GrantedAuthority> gs = new ArrayList<GrantedAuthority>();
//        for (String roleName : roles) {
//            GrantedAuthority g = new SimpleGrantedAuthority("ROlE_USER");
//            gs.add(g);
//        }
//
//        return gs;
    }

    // 계정 만료되지 않았는지 확인
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨 있지 않은지 확인
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 인증 자격 증명(비밀번호 같은)이 만료되지 않았는지 확인
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화 상태인지 확인
    @Override
    public boolean isEnabled() {
        return true;
    }
}