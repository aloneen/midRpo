package kz.seisen.mid_rpo.service.impl;


import kz.seisen.mid_rpo.entity.Permission;
import kz.seisen.mid_rpo.entity.UserModel;
import kz.seisen.mid_rpo.repository.PermissionRepository;
import kz.seisen.mid_rpo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;



@Service
@RequiredArgsConstructor
public class MyUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionRepository permissionRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(username);

        if(Objects.nonNull(user)) {     // if user != null
            return user;
        }

        throw new UsernameNotFoundException("User Not Found");
    }

    public void registr(UserModel model){
        UserModel check = userRepository.findByEmail(model.getEmail());
        if (check == null){
            model.setPassword(passwordEncoder.encode(model.getPassword()));
            List<Permission> permissions = List.of(permissionRep.findByName("ROLE_USER"));

            model.setPermissions(permissions);
            userRepository.save(model);
        }
    }


}
