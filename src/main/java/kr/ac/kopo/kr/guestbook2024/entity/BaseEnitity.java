package kr.ac.kopo.kr.guestbook2024.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class BaseEnitity {
    @CreatedDate
    @Column(name = "regdate",updatable = false)
    private LocalDateTime regDate;
    @CreatedDate
    @Column(name = "moddate",updatable = false)
    private LocalDateTime modDate;
}
