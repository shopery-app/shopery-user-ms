package az.shopery.user_ms.service.impl;

import static az.shopery.user_ms.utils.common.NameMapperHelper.first;
import static az.shopery.user_ms.utils.common.NameMapperHelper.last;

import az.shopery.user_ms.handler.exception.ResourceNotFoundException;
import az.shopery.user_ms.model.dto.request.UserProfileUpdateRequestDto;
import az.shopery.user_ms.model.dto.response.UserProfileResponseDto;
import az.shopery.user_ms.model.dto.shared.ShopSummaryDto;
import az.shopery.user_ms.model.dto.shared.SuccessResponse;
import az.shopery.user_ms.model.entity.UserEntity;
import az.shopery.user_ms.repository.ShopRepository;
import az.shopery.user_ms.repository.UserRepository;
import az.shopery.user_ms.service.UserService;
import az.shopery.user_ms.utils.common.FilenetClientHelper;
import az.shopery.user_ms.utils.enums.ShopStatus;
import az.shopery.user_ms.utils.enums.UserStatus;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final FilenetClientHelper filenetClientHelper;

    @Override
    @Transactional(readOnly = true)
    public SuccessResponse<UserProfileResponseDto> getMyProfile(String userEmail) {
        UserEntity userEntity = getUserByEmail(userEmail);
        return SuccessResponse.of(mapToDto(userEntity), "User profile retrieved successfully.");
    }

    @Override
    @Transactional
    public SuccessResponse<UserProfileResponseDto> updateMyProfile(String userEmail, UserProfileUpdateRequestDto userProfileUpdateRequestDto) {
        UserEntity userEntity = getUserByEmail(userEmail);
        userEntity.setName(userProfileUpdateRequestDto.getFirstName().trim() + " " + userProfileUpdateRequestDto.getLastName().trim());
        userEntity.setPhone(userProfileUpdateRequestDto.getPhone());
        userEntity.setDateOfBirth(userProfileUpdateRequestDto.getDateOfBirth());

        UserEntity updatedUserEntity = userRepository.save(userEntity);
        log.info("User profile updated successfully for user {}", userEmail);
        return SuccessResponse.of(mapToDto(updatedUserEntity), "User profile updated successfully!");
    }

    private UserEntity getUserByEmail(String userEmail) {
        return userRepository.findByEmailAndStatus(userEmail, UserStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + userEmail));
    }

    private UserProfileResponseDto mapToDto(UserEntity userEntity) {
        var userProfileResponseDto = UserProfileResponseDto.builder()
                .id(userEntity.getId())
                .firstName(first(userEntity.getName()))
                .lastName(last(userEntity.getName()))
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .dateOfBirth(userEntity.getDateOfBirth())
                .createdAt(userEntity.getCreatedAt())
                .shop(mapShop(userEntity))
                .build();

        if (Objects.nonNull(userEntity.getProfilePhotoId())) {
            userProfileResponseDto.setProfilePhoto(filenetClientHelper.getFile(userEntity.getProfilePhotoId()));
        }

        return userProfileResponseDto;
    }

    private ShopSummaryDto mapShop(UserEntity userEntity) {
        return shopRepository
                .findByUserAndStatus(userEntity, ShopStatus.ACTIVE)
                .map(shop -> ShopSummaryDto.builder()
                        .id(shop.getId())
                        .shopName(shop.getShopName())
                        .status(shop.getStatus())
                        .build())
                .orElse(null);
    }
}
