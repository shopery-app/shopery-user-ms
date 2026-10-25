package az.shopery.user_ms.controller;

import az.shopery.user_ms.model.dto.request.AddressRequestDto;
import az.shopery.user_ms.model.dto.response.AddressResponseDto;
import az.shopery.user_ms.model.dto.shared.SuccessResponse;
import az.shopery.user_ms.service.UserAddressService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/me/addresses")
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressService userAddressService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<AddressResponseDto>>> getMyAddresses(@RequestParam String email) {
        return ResponseEntity.ok(userAddressService.getAll(email));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<AddressResponseDto>> addMyAddress(@RequestParam String email, @Valid @RequestBody AddressRequestDto addressRequestDto) {
        return ResponseEntity.ok(userAddressService.add(email, addressRequestDto));
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<SuccessResponse<AddressResponseDto>> updateMyAddress(@RequestParam String email, @PathVariable String addressId, @Valid @RequestBody AddressRequestDto addressRequestDto) {
        return ResponseEntity.ok(userAddressService.update(email, addressId, addressRequestDto));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<SuccessResponse<Void>> removeMyAddress(@RequestParam String email, @PathVariable String addressId) {
        return ResponseEntity.ok(userAddressService.remove(email, addressId));
    }

    @PutMapping("/{addressId}/default")
    public ResponseEntity<SuccessResponse<Void>> setMyDefaultAddress(@RequestParam String email, @PathVariable String addressId) {
        return ResponseEntity.ok(userAddressService.setDefault(email, addressId));
    }
}
