package ru.otus.hw15;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw15.data.AddressDataSet;
import ru.otus.hw15.data.UserDataSet;

import javax.annotation.PostConstruct;

@Service
public class DBStarter {

    @Autowired
    private HibernateCacheService cacheService;

    @PostConstruct
    @SuppressWarnings("InfiniteLoopStatement")
    private void startDbServiceWork() {
//        new Thread(() -> {
//            try {
//                while (true) {
//                    cacheService.save(new UserDataSet("name1", 26, new AddressDataSet("some street")));
//                    cacheService.load(1);
//                    Thread.sleep(100);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

        try {
            int i = 0;
            while (i < 5) {
                i++;
                cacheService.save(new UserDataSet("name1", 26, new AddressDataSet("some street")));
                cacheService.load(1);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}