package az.shopery.user_ms.repository;

import az.shopery.user_ms.model.entity.ShopEntity;
import az.shopery.user_ms.model.entity.UserEntity;
import az.shopery.user_ms.utils.enums.ShopStatus;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity, UUID> {
    Optional<ShopEntity> findByUserAndStatus(UserEntity userEntity, ShopStatus shopStatus);
}
