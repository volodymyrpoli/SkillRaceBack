package com.volodymyrpoli.skillrace.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SubtopicWithDone extends Subtopic {

    private Boolean done;

    public static SubtopicWithDone getSubtopicWithDone(Subtopic subtopic, Boolean done) {
        SubtopicWithDone subtopicWithDone = new SubtopicWithDone();
        subtopicWithDone.setDone(done);
        map(subtopic, subtopicWithDone);
        return subtopicWithDone;
    }

    private static void map(Subtopic subtopic, SubtopicWithDone subtopicWithDone) {
        subtopicWithDone.setId(subtopic.getId());
        subtopicWithDone.setName(subtopic.getName());
        subtopicWithDone.setTopic(subtopic.getTopic());
        subtopicWithDone.setLevel(subtopic.getLevel());
        subtopicWithDone.setAttachments(subtopic.getAttachments());
    }
}
