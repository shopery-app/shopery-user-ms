package az.shopery.user_ms.controller;

import az.shopery.user_ms.model.dto.request.UserProfileUpdateRequestDto;
import az.shopery.user_ms.model.dto.response.UserProfileResponseDto;
import az.shopery.user_ms.model.dto.shared.SuccessResponse;
import az.shopery.user_ms.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/me")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<SuccessResponse<UserProfileResponseDto>> getMyProfile(@RequestParam String email) {
        return ResponseEntity.ok(userService.getMyProfile(email));
    }

    @PutMapping("/profile")
    public ResponseEntity<SuccessResponse<UserProfileResponseDto>> updateMyProfile(@RequestParam String email, @Valid @RequestBody UserProfileUpdateRequestDto userProfileUpdateRequestDto) {
        return ResponseEntity.ok(userService.updateMyProfile(email, userProfileUpdateRequestDto));
    }
}
