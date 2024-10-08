package com.pnu.sursim.domain.user.entity;

import com.pnu.sursim.domain.user.dto.JoinRequest;
import com.pnu.sursim.domain.user.dto.ProfileRequest;
import com.pnu.sursim.global.auth.dto.KakaoUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //이름
    @Column(nullable = false)
    private String name;

    //이메일
    @Column(nullable = false, unique = true)
    private String email;

    //비밀번호
    @Column(nullable = false)
    private String password;

    //생년월일
    @Column(name = "birth_date")
    private LocalDate birthDate;

    //성별
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private UserInfoStatus userInfoStatus;

    //유저의 포인트
    private int point;

    //유저의 핸드폰번호
    @Column(name = "phone_number")
    private String phoneNumber;

    public User(JoinRequest joinRequest) {
            this.name = joinRequest.name();
            this.email = joinRequest.email();
            this.password = joinRequest.password();
            this.birthDate = joinRequest.birthDate();
        this.gender = joinRequest.gender();
        this.phoneNumber = joinRequest.phoneNumber();
        this.userInfoStatus = UserInfoStatus.fromUser(this);
        this.point = 0;
    }

    public User(KakaoUser kakaoUser) {
        this.name = kakaoUser.nickname();
        this.email = kakaoUser.email();
        this.password = kakaoUser.password();
        this.userInfoStatus = UserInfoStatus.fromUser(this);
        this.point = 0;
    }

    public void accumulatePoint(int point) {
        this.point += point;
    }

    public void updateProfile(ProfileRequest profileRequest) {
        // 이름 업데이트
        if (profileRequest.existName()) {
            this.name = profileRequest.name();
        }

        // 생년월일 업데이트
        if (profileRequest.existBirthDate()) {
            this.birthDate = profileRequest.birthDate();
        }

        // 성별 업데이트
        if (profileRequest.existGender()) {
            this.gender = profileRequest.gender();
        }

        // 전화번호 업데이트
        if (profileRequest.existPhoneNumber()) {
            this.phoneNumber = profileRequest.phoneNumber();
        }

    }

    public void registerUserInfoFirst(LocalDate localDate, Gender gender, String phoneNumber) {
        this.birthDate = localDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.userInfoStatus = UserInfoStatus.COMPLETE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    public int getUserAge() {
        return  LocalDate.now().getYear() - this.getBirthDate().getYear();
    }

}
