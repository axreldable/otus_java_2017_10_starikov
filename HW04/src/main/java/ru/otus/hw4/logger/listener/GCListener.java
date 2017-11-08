package ru.otus.hw4.logger.listener;

import com.sun.management.GarbageCollectionNotificationInfo;
import ru.otus.hw4.logger.info.GcInfo;

import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.util.logging.Logger;

public class GCListener implements NotificationListener {
    private Logger logger = Logger.getLogger(GCListener.class.getName());

    @Override
    public void handleNotification(Notification notification, Object handback) {
        if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
            GarbageCollectionNotificationInfo notificationInfo = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

            logger.info(GcInfo.from(notificationInfo).toString());
        }
    }
}
