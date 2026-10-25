package az.shopery.user_ms.service;

import az.shopery.user_ms.model.dto.request.UserProfileUpdateRequestDto;
import az.shopery.user_ms.model.dto.response.UserProfileResponseDto;
import az.shopery.user_ms.model.dto.shared.SuccessResponse;

public interface UserService {
    SuccessResponse<UserProfileResponseDto> getMyProfile(String userEmail);
    SuccessResponse<UserProfileResponseDto> updateMyProfile(String userEmail, UserProfileUpdateRequestDto dto);
}
