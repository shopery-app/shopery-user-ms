package az.shopery.user_ms.controller;

import az.shopery.user_ms.model.dto.shared.SuccessResponse;
import az.shopery.user_ms.service.UserPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/users/me/photo")
@RequiredArgsConstructor
public class UserPhotoController {

    private final UserPhotoService userPhotoService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<SuccessResponse<byte[]>> uploadMyProfilePhoto(@RequestParam String email, @RequestParam("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(userPhotoService.uploadProfilePhoto(email, multipartFile));
    }

    @DeleteMapping
    public ResponseEntity<SuccessResponse<Void>> deleteMyProfilePhoto(@RequestParam String email) {
        return ResponseEntity.ok(userPhotoService.deleteProfilePhoto(email));
    }
}
