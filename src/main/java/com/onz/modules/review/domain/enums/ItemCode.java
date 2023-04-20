package com.onz.modules.review.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Deprecated
@Getter
@AllArgsConstructor
public enum ItemCode {
    A100("계속근무",0, "KEEP_WORK", "KEEP_WORK"),
    A101("물론이죠",100, "KEEP_WORK", "KEEP_WORK"),
    A102("싫어요", 20, "KEEP_WORK", "KEEP_WORK"),
    A103("글쎄요", 60, "KEEP_WORK", "KEEP_WORK"),
    B100("행사횟수", 0, "EVENT_CNT_JUMSU", "WORKLOAD"),
    B101("연 4회 이하", 20, "EVENT_CNT_JUMSU", "WORKLOAD"),
    B102("연 5~10회", 100, "EVENT_CNT_JUMSU", "WORKLOAD"),
    B103("연 10~20회", 60, "EVENT_CNT_JUMSU", "WORKLOAD"),
    B104("연 20회 이상", 40, "EVENT_CNT_JUMSU", "WORKLOAD"),
    B200("서류업무", 0, "DOC_WORK_JUMSU", "WORKLOAD"),
    B201("많아요", 60, "DOC_WORK_JUMSU", "WORKLOAD"),
    B202("적당해요", 100, "DOC_WORK_JUMSU", "WORKLOAD"),
    B203("없어요", 20, "DOC_WORK_JUMSU", "WORKLOAD"),
    B300("수업준비시간", 0, "READY_CLASS_JUMSU", "WORKLOAD"),
    B301("1시간 미만 ", 20, "READY_CLASS_JUMSU", "WORKLOAD"),
    B302("1~2시간 미만", 40, "READY_CLASS_JUMSU", "WORKLOAD"),
    B303("2~3시간 미만", 60, "READY_CLASS_JUMSU", "WORKLOAD"),
    B304("5시간 이상", 100, "READY_CLASS_JUMSU", "WORKLOAD"),
    C100("개인PC", 0, "PERSONAL_PC_JUMSU", "JOB_SATISFACTION"),
    C101("있어요", 100, "PERSONAL_PC_JUMSU", "JOB_SATISFACTION"),
    C102("함께 써요", 60, "PERSONAL_PC_JUMSU", "JOB_SATISFACTION"),
    C103("없어요", 20, "PERSONAL_PC_JUMSU", "JOB_SATISFACTION"),
    C200("자기개발", 0, "SELF_DEV_JUMSU", "JOB_SATISFACTION"),
    C201("대학원 진학 허용해요", 60, "SELF_DEV_JUMSU", "JOB_SATISFACTION"),
    C202("교육비 지원/연수 3회 이상", 100, "SELF_DEV_JUMSU", "JOB_SATISFACTION"),
    C203("교육비 지원/연수 1회 이상", 60, "SELF_DEV_JUMSU", "JOB_SATISFACTION"),
    C204("없어요", 20, "SELF_DEV_JUMSU", "JOB_SATISFACTION"),
    C300("육아 휴직 허용 분위기", 0, "KIZ_REST_JUMSU", "JOB_SATISFACTION"),
    C301("육휴사용 교사 3명 이상", 100, "KIZ_REST_JUMSU", "JOB_SATISFACTION"),
    C302("육휴사용 교사 2명 이하", 60, "KIZ_REST_JUMSU", "JOB_SATISFACTION"),
    C303("육휴사용 교사 없어요", 20, "KIZ_REST_JUMSU", "JOB_SATISFACTION"),
    D100("동료관계", 0, "PARTNERSHIP_JUMSU", "WORK_ATMOSPHERE"),
    D101("경쟁적", 60, "PARTNERSHIP_JUMSU", "WORK_ATMOSPHERE"),
    D102("상호격려 및 협동", 100, "PARTNERSHIP_JUMSU", "WORK_ATMOSPHERE"),
    D103("개인주의", 40, "PARTNERSHIP_JUMSU", "WORK_ATMOSPHERE"),
    D200("원장리더쉽", 0, "OWNER_LEADERSHIP_JUMSU", "WORK_ATMOSPHERE"),
    D201("허용", 80, "OWNER_LEADERSHIP_JUMSU", "WORK_ATMOSPHERE"),
    D202("민주", 100, "OWNER_LEADERSHIP_JUMSU", "WORK_ATMOSPHERE"),
    D203("방임", 20, "OWNER_LEADERSHIP_JUMSU", "WORK_ATMOSPHERE"),
    D204("권위", 40, "OWNER_LEADERSHIP_JUMSU", "WORK_ATMOSPHERE"),
    ;

    String name;
    int score;
    String detailGroup;
    String group;
}
