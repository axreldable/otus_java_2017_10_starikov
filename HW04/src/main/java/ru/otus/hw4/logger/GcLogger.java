package ru.otus.hw4.logger;

import ru.otus.hw4.logger.listener.GCListener;

import javax.management.NotificationEmitter;
import java.lang.management.GarbageCollectorMXBean;
import java.util.List;

public class GcLogger {
    public void start() {
        List<GarbageCollectorMXBean> gcBeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcBean;
            emitter.addNotificationListener(new GCListener(), null, null);
        }
    }
}
