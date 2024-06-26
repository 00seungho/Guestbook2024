package kr.ac.kopo.kr.guestbook2024.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Guestbook extends BaseEnitity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gno;
    @Column(length = 100,nullable = false)
    private String title;
    @Column(length = 1500,nullable = false)//length는 byte의 길이
    private String content;
    @Column(length = 100,nullable = false)
    private String writer;
    @Column()


    public void changeTitle(String title){
        this.title = title;
    }
    public void changecontent(String content){
        this.content = content;
    }


}
