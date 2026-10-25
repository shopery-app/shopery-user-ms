package az.shopery.user_ms.service.impl;

import az.shopery.user_ms.handler.exception.ApplicationException;
import az.shopery.user_ms.model.dto.request.SupportTicketRequestDto;
import az.shopery.user_ms.model.entity.UserEntity;
import az.shopery.user_ms.model.entity.task.SupportTicketEntity;
import az.shopery.user_ms.repository.TaskRepository;
import az.shopery.user_ms.repository.UserRepository;
import az.shopery.user_ms.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void createSupportTicket(UserEntity createdBy, SupportTicketRequestDto supportTicketRequestDto) {
        UserEntity assignedAdmin = userRepository.findRandomActiveAdmin()
                .orElseThrow(() -> new ApplicationException("No admin available!"));

        SupportTicketEntity ticket = SupportTicketEntity.builder()
                .createdBy(createdBy)
                .assignedAdmin(assignedAdmin)
                .subject(supportTicketRequestDto.getSubject())
                .description(supportTicketRequestDto.getDescription())
                .build();

        taskRepository.save(ticket);
    }
}
