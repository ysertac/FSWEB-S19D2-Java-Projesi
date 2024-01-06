package com.workintech.service;

import com.workintech.entity.Member;
import com.workintech.entity.Role;
import com.workintech.repository.MemberRepository;
import com.workintech.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthenticationService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public Member register(String email, String password) {

        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if (optionalMember.isPresent()) {
            throw new RuntimeException("User with given email is already exist! " + email);
        }
        String encodedPassword = passwordEncoder.encode(password);

        List<Role> roleList = new ArrayList<>();

//        Optional<Role> roleAdmin = roleRepository.findByAuthority("ADMIN");
//        if (!roleAdmin.isPresent()) {
//            Role roleAdminEntity = new Role();
//            roleAdminEntity.setAuthority("ADMIN");
//            roleList.add(roleRepository.save(roleAdminEntity));
//        } else {
//            roleList.add(roleAdmin.get());
//        }

        Optional<Role> roleUser = roleRepository.findByAuthority("USER");
        if (!roleUser.isPresent()) {
            Role roleUserEntity = new Role();
            roleUserEntity.setAuthority("USER");
            roleList.add(roleRepository.save(roleUserEntity));
        } else {
            roleList.add(roleUser.get());
        }


        Member member = new Member();
        member.setEmail(email);
        member.setPassword(encodedPassword);
        member.setRoles(roleList);
        return memberRepository.save(member);
    }
}
