package com.bank.domain.enumeration;

public enum LockEnum {
    APPOINTMENT,//预约申请锁
    POOL_TASK,//预约池创建任务锁
    UPDATE_OVERDATE_TASK,//更新超时预约状态任务锁
}
