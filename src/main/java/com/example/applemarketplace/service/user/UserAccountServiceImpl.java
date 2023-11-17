package com.example.applemarketplace.service.user;

import com.example.applemarketplace.data.user.UserAccountRepository;
import com.example.applemarketplace.service.ServiceLayerMapper;
import com.example.applemarketplace.service.model.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;

    @Override
    public UserAccount getUserByEmail(String email) {
        return userAccountRepository.findByEmail(email)
                .map(ServiceLayerMapper.I::map)
                .get();
    }

}
