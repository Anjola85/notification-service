package com.quiikmart.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.quiikmart.notificationservice.notification.Notification}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDto implements Serializable {
    private static final long serialVersionUID = 2067706827873481340L;
    private String channel;
    private String priority;
    private String recipient;
    private String content;
    private String type;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationRequestDto that = (NotificationRequestDto) o;
        return channel.equals(that.channel) && priority.equals(that.priority) && recipient.equals(that.recipient) && content.equals(that.content) && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channel, priority, recipient, content, type);
    }
}