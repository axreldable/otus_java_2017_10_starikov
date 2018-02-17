package ru.otus.hw15;

import java.lang.ref.SoftReference;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;

public class CacheEngineImpl<K, V> extends EternalCacheEngine<K, V> {
    private static final int TIME_THRESHOLD_MS = 5;
    private final long lifeTimeMs;
    private final long idleTimeMs;
    private final Timer timer = new Timer();

    CacheEngineImpl(int maxElements, long lifeTimeMs, long idleTimeMs) {
        super(maxElements);
        this.lifeTimeMs = lifeTimeMs > 0 ? lifeTimeMs : 0;
        this.idleTimeMs = idleTimeMs > 0 ? idleTimeMs : 0;
    }

    @Override
    public void put(CacheElem<K, V> element) {
        super.put(element);

        if (lifeTimeMs != 0) {
            TimerTask lifeTimerTask = getTimerTask(element.getKey(), lifeElement -> lifeElement.getCreationTime() + lifeTimeMs);
            timer.schedule(lifeTimerTask, lifeTimeMs);
        }
        if (idleTimeMs != 0) {
            TimerTask idleTimerTask = getTimerTask(element.getKey(), idleElement -> idleElement.getLastAccessTime() + idleTimeMs);
            timer.schedule(idleTimerTask, idleTimeMs, idleTimeMs);
        }
    }

    @Override
    public CacheElem<K, V> get(K key) {
        if (elements.containsKey(key)) {
            CacheElem<K, V> elem = elements.get(key).get();
            if (elem != null) {
                hit++;
                elem.setAccessed();
                return elem;
            }
        }
        miss++;
        return null;
    }

    @Override
    public void dispose() {
        timer.cancel();
    }

    private TimerTask getTimerTask(final K key, Function<CacheElem<K, V>, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                SoftReference<CacheElem<K, V>> sr = elements.get(key);
                if (sr == null) {
                    elements.remove(key);
                    this.cancel();
                } else {
                    CacheElem<K, V> elem = sr.get();
                    if (elem == null || isT1BeforeT2(timeFunction.apply(elem), System.currentTimeMillis())) {
                        elements.remove(key);
                        this.cancel();
                    }
                }
            }
        };
    }

    private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }
}
