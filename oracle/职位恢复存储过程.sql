create or replace procedure RECOVER_RECRUIT(   --   职位回收站恢复职位存储过程   添加是否发送到邮箱   
  P_RECRUIT_NO VARCHAR2
)
as
begin
  INSERT INTO RECRUIT (
    FUNC,
    FUNC_2ND,
    FUNC_3RD,
    STATION,
    FIELD_1ST,
    FIELD_2ND,
    FIELD_3RD,
    DEGREE,
    DEGREE_UP,
    FOREIGN,
    FOREIGN_LEVEL,
    CANTON_LEVEL,
    SEX,
    MARRIAGE,
    MIN_AGE,
    MAX_AGE,
    LOC_NOW,
    LOC_REG,
    TALENT_TYPE,
    WORK_LOC_1ST,
    WORK_LOC_2ND,
    WORK_LOC_3RD,
    WORK_MODE,
    WORK_YEARS,
    HIRE_NUM,
    HIRE_NUM_HIDE,
    SALARY,
    SALARY1,
    SALARY2,
    EXTRA_SALARY,
    QUARTER,
    REQUIRMENT,
    NOTE,
    STATION_CONTACTOR,
    STATION_TEL,
    STATION_EMAIL,
    IS_SEND_EMAIL,
    USEFUL_LIFE,
    VALIDITY,
    STATE,
    REG_DATE,
    REG_OPERATOR,
    VALIDITY_DATE,
    VALIDITY_OPERATOR,
    VISIT,
    EXP_DATE,
    ORDER_ID,
    APPLY_FILTER,
    EMAIL_SECURITY,
    UNIT_NO,
    SUB_UNIT_NO,
    RECRUIT_NO)
  select
    FUNC,
    FUNC_2ND,
    FUNC_3RD,
    STATION,
    FIELD_1ST,
    FIELD_2ND,
    FIELD_3RD,
    DEGREE,
    DEGREE_UP,
    FOREIGN,
    FOREIGN_LEVEL,
    CANTON_LEVEL,
    SEX,
    MARRIAGE,
    MIN_AGE,
    MAX_AGE,
    LOC_NOW,
    LOC_REG,
    TALENT_TYPE,
    WORK_LOC_1ST,
    WORK_LOC_2ND,
    WORK_LOC_3RD,
    WORK_MODE,
    WORK_YEARS,
    HIRE_NUM,
    HIRE_NUM_HIDE,
    SALARY,
    SALARY1,
    SALARY2,
    EXTRA_SALARY,
    QUARTER,
    REQUIRMENT,
    NOTE,
    STATION_CONTACTOR,
    STATION_TEL,
    STATION_EMAIL,
    IS_SEND_EMAIL,
    USEFUL_LIFE,
    VALIDITY,
    STATE,
    REG_DATE,
    REG_OPERATOR,
    VALIDITY_DATE,
    VALIDITY_OPERATOR,
    VISIT,
    EXP_DATE,
    ORDER_ID,
    APPLY_FILTER,
    EMAIL_SECURITY,
    UNIT_NO,
    SUB_UNIT_NO,
    RECRUIT_NO
  from
    RECRUIT_RECYCLED
  where
    RECRUIT_NO = P_RECRUIT_NO;
  delete from
    RECRUIT_RECYCLED
  where
    RECRUIT_NO = P_RECRUIT_NO;
  commit;
end;