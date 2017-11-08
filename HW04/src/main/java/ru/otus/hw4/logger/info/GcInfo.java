package ru.otus.hw4.logger.info;

import com.sun.management.GarbageCollectionNotificationInfo;
import lombok.Builder;

@Builder
public class GcInfo {
    private Generation generation;
    private String gcName;
    private String duration;

    public static GcInfo from(GarbageCollectionNotificationInfo notificationInfo) {
        return GcInfo.builder()
                .generation(Generation.parseGeneration(notificationInfo.getGcAction()))
                .gcName(notificationInfo.getGcName())
                .duration(String.valueOf(notificationInfo.getGcInfo().getDuration()))
                .build();
    }

    @Override
    public String toString() {
        return "Generation ='" + generation + '\'' +
                ", GC name ='" + gcName + '\'' +
                ", GC duration='" + duration + '\'';
    }
}
