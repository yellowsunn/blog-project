package com.yellowsunn.springblog.domain.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @CreatedDate
    public LocalDateTime date;

    private String name;
    private String password;
    private String content;

    private String orderNumber; // 메인댓글Id_서브댓글Id [ ex) 68_70 ]

    private String ipAddr; // 아이피 주소

    private boolean isManager; // 매니저인지 확인

    // 댓글의 댓글인 경우 기준이 되는 댓글
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_comment_id")
    private Comment mainComment;

    // 댓글의 댓글
    @OneToMany(mappedBy = "mainComment", cascade = CascadeType.REMOVE)
    List<Comment> subComment = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    public void updateOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
