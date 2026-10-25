package az.shopery.user_ms.service;

import az.shopery.user_ms.model.dto.request.SupportTicketRequestDto;
import az.shopery.user_ms.model.entity.UserEntity;

public interface TaskService {
    void createSupportTicket(UserEntity createdBy, SupportTicketRequestDto supportTicketRequestDto);
}
