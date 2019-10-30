package com.rainarmy.tools.id;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 使用ThreadLocalRandm的UUID，比默认的UUID性能更优
 * @date 2019/10/30 10:03
 */
public class IdUtil {

    public static UUID fastUUID() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new UUID(random.nextLong(), random.nextLong());
    }
}
