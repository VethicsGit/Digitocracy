
package com.example.kanika.digitocracy.APIResponse.DebatesList;

import java.util.List;

import com.example.kanika.digitocracy.APIResponse.DebatesList.DebateParticipant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DebateList {

    @SerializedName("debate_id")
    @Expose
    private String debateId;
    @SerializedName("topic_title")
    @Expose
    private String topicTitle;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("schedule_date_time")
    @Expose
    private String scheduleDateTime;
    @SerializedName("duration_minutes")
    @Expose
    private String durationMinutes;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("im_debate_participant")
    @Expose
    private String imDebateParticipant;
    @SerializedName("debate_participants")
    @Expose
    private List<DebateParticipant> debateParticipants = null;

    public String getDebateId() {
        return debateId;
    }

    public void setDebateId(String debateId) {
        this.debateId = debateId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScheduleDateTime() {
        return scheduleDateTime;
    }

    public void setScheduleDateTime(String scheduleDateTime) {
        this.scheduleDateTime = scheduleDateTime;
    }

    public String getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(String durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getImDebateParticipant() {
        return imDebateParticipant;
    }

    public void setImDebateParticipant(String imDebateParticipant) {
        this.imDebateParticipant = imDebateParticipant;
    }

    public List<DebateParticipant> getDebateParticipants() {
        return debateParticipants;
    }

    public void setDebateParticipants(List<DebateParticipant> debateParticipants) {
        this.debateParticipants = debateParticipants;
    }

}
