package com.example.smartbeautymirror.AppUser;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String nickname;

    @Builder
    public UserDto(Long id, String email,String password , String name, String phone ,String nickname){
        this.id = id;
        this.password=password;
        this.email=email;
        this.name=name;
        this.phone=phone;
        this.nickname=nickname;
    }
}
