package com.workflex.user.service;

import com.workflex.user.dto.request.RegisterRequest;

public interface UserService {
    void register(RegisterRequest request);
}
