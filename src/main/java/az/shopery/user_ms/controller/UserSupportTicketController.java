package az.shopery.user_ms.controller;

import az.shopery.user_ms.model.dto.request.SupportTicketRequestDto;
import az.shopery.user_ms.model.dto.shared.SuccessResponse;
import az.shopery.user_ms.model.dto.response.UserSupportTicketResponseDto;
import az.shopery.user_ms.service.SupportTicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/api/v1/users/me/support-tickets")
@RequiredArgsConstructor
public class UserSupportTicketController {

    private final SupportTicketService supportTicketService;

    @PostMapping
    public ResponseEntity<SuccessResponse<Void>> createMySupportTicket(@Valid @RequestBody SupportTicketRequestDto supportTicketRequestDto, @RequestParam String email) {
        return ResponseEntity.ok(supportTicketService.createMySupportTicket(supportTicketRequestDto, email));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<Page<UserSupportTicketResponseDto>>> getMySupportTicket(@RequestParam String email, Pageable pageable) {
        return ResponseEntity.ok(supportTicketService.getMySupportTickets(email, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> deleteMySupportTicket(@PathVariable String id, @RequestParam String email) {
        return ResponseEntity.ok(supportTicketService.deleteMySupportTicket(id, email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<UserSupportTicketResponseDto>> updateMySupportTicket(@Valid @RequestBody SupportTicketRequestDto supportTicketRequestDto, @PathVariable String id, @RequestParam String email) {
        return ResponseEntity.ok(supportTicketService.updateMySupportTicket(supportTicketRequestDto, id, email));
    }
}
