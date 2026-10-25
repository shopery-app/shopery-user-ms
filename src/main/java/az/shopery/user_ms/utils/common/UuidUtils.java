package az.shopery.user_ms.utils.common;

import az.shopery.user_ms.handler.exception.ApplicationException;
import java.util.UUID;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UuidUtils {

    public static UUID parse(String uuidString) {
        try {
            return UUID.fromString(uuidString.trim());
        } catch (IllegalArgumentException exception) {
            throw new ApplicationException("It is not a valid UUID format!");
        }
    }
}
