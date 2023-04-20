package com.onz.modules.admin.event.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onz.modules.account.domain.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class EventItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Size(max=20)
    private String phone;

    @Size(max=200)
    private String answer;

    @Size(max=50)
    private String chu;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private ZonedDateTime createDt;

    @Size(max=60)
    private String temp;


    @Builder
    public EventItem(Event event, Account account, String phone, String answer, String chu, String temp) {
        this.event = event;
        this.account = account;
        this.phone = phone;
        this.answer = answer;
        this.chu = chu;
        this.temp = temp;
    }
}
