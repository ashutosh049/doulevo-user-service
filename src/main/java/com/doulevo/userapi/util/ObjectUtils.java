package com.doulevo.userapi.util;

import static org.springframework.web.util.UriUtils.encode;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Slf4j
@UtilityClass
public class ObjectUtils {

    /**
     * UTF_8 encoding of uri variables. URIs as defined by RFC 3986 (see Section 2: Characters)
     * ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~:/?#[]@!$&'()*+,;=
     *
     * @param uriVariables
     * @return
     */
    public static String[] encodeUriVariables(String... uriVariables) {
        return Arrays.stream(uriVariables).map(value -> {
            String stringValue = (value != null ? value.toString() : "");
            String encoded = encode(stringValue, StandardCharsets.UTF_8);
            log.debug("Encrypting 1st time : " + stringValue + " -> " + encoded);
            log.debug("Encrypting 2nd time : " + encoded + " -> " + encode(encoded, StandardCharsets.UTF_8));
            return encoded;
        }).toArray(String[]::new);
    }

    /**
     * Get Enums as array of String
     *
     * @param e
     * @return
     */
    public static String[] getEnumAsArrayOfString(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }

    /**
     * Check if the provided object is null, blank, empty, zero length, if yes then returns true
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(@Nullable Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof Optional) {
            return !((Optional) obj).isPresent();
        }
        if (obj instanceof CharSequence) {
            return !StringUtils.hasText((CharSequence) obj);
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        return false;
    }

    public static boolean isEmpty(@Nullable Object[] array) {
        return (array == null || array.length == 0);
    }

    public static boolean isEmpty(@Nullable Collection<?> c) {
        // Assert.isAssignable(c.getClass(), Collection.class, "Invalid type");
        return (c == null || c.size() == 0);
    }

  /*public static <T> T requireValueNoThrow(T obj, String message) {

      if (obj == null){
          log.error(message);
      }

      if(obj instanceof String && !hasValue((String) obj)) {
          log.error(message);
      }
      return obj;
  }*/

}
