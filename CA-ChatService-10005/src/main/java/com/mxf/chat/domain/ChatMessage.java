package com.mxf.chat.domain;

import java.util.Date;

/**
 * @author manxingfu
 * @date 2023/5/23 18:50
 * @desc 聊天消息对象
 */
public class ChatMessage {

    // 消息编号
    private String messageId;

    // 聊天内容
    private String content;
    // 发送人
    private String userId;
    private String username;

    // 发送时间
    private Date createTime;

    public ChatMessage(String messageId, String content, String userId, String username, Date createTime) {
        this.messageId = messageId;
        this.content = content;
        this.userId = userId;
        this.username = username;
        this.createTime = createTime;
    }

    public ChatMessage() {
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
