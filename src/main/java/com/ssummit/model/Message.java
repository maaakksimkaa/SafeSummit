package com.ssummit.model;

import javax.persistence.*;

import com.ssummit.dto.MessageDto;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "message_seq", allocationSize = 1)
public class Message {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_generator")
    private Long id;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_timestamp")
    private LocalDateTime createdDateTime;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition="TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "message_type_id",
            foreignKey = @ForeignKey(name = "FK_MESSAGE_MESSAGETYPE")
    )
    private MessageType messageType;

    @Builder
    public Message(MessageDto messageDto) {
        this.title = messageDto.getTitle();
        this.description = messageDto.getDescription();
        this.messageType = messageDto.getMessageType();
    }
}
