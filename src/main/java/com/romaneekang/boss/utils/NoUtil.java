package com.romaneekang.boss.utils;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 编号工具类
 */
public class NoUtil {
    /**
     * 生成一个全局唯一的UUID。
     * <p>
     * 使用IdWorker的get32UUID方法来生成一个32位的唯一标识符。
     * 这个方法的目的是为了提供一种在分布式系统中生成唯一ID的方式，确保在所有节点上生成的ID都是唯一的。
     *
     * @return 一个32位的字符串，表示一个全局唯一的UUID。
     */
    public static String uuid() {
        return IdWorker.get32UUID();
    }

    /**
     * 生成一个用户编号。
     * <p>
     * 用户编号的生成规则是：8 + 雪花算法的值。
     *
     * @return 一个用户编号的字符串。
     */
    public static String getUserNo() {
        // 雪花算法的值
        return "8" + IdWorker.getId();
    }

    /**
     * 生成一个账户编号。
     * <p>
     * 账户编号的生成规则是：9 + 雪花算法的值。
     *
     * @return 一个账户编号的字符串。
     */
    public static String getAccountNo() {
        // 雪花算法的值
        return "9" + IdWorker.getId();
    }
}
