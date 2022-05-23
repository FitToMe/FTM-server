package com.FitToMe.project.Entity;

import lombok.Getter;
import org.springframework.data.annotation.*;
import javax.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

/**
 * Time: 데이터 조작시 자동으로 날짜를 수정해주는 JPA의 Auditing 기능을 사용한다.
 이 Time Entity를 만들어 놓고 다른 Entity(Post,..)로부터 상속받아서 사용하게 된다.
 * */
@MappedSuperclass   // 클래스가 만들어지지 않는 기초 클래스라는 Annotaion
@EntityListeners(value = {AuditingEntityListener.class})    // Entity의 변화를 감지하는 리스너
@Getter
abstract class Time {

    @CreatedDate        // Entity가 생성되어 저장될 때, 시간이 자동 저장
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;      //등록일자

    @LastModifiedDate   // 조회한 Entity의 값을 변경할 때, 시간이 자동 저장
    @Column(name ="moddate" )
    private LocalDateTime modDate;      //수정일자
}