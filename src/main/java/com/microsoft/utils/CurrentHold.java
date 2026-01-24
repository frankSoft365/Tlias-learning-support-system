package com.microsoft.utils;

public class CurrentHold {
    // 这个工具对象存员工id
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    /**
     * 将员工id存入
     * @param id
     */
    public static void setCurrentId(Integer id) {
        threadLocal.set(id);
    }

    /**
     * 取员工id
     */
    public static Integer getCurrentId() {
        return threadLocal.get();
    }

    /**
     *  清理存储
     */
    public static void removeId() {
        threadLocal.remove();
    }
}
