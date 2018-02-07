package ru.otus.hw13;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.otus.hw13.data.UserDataSet;
import ru.otus.hw13.service.HibernateServiceImpl;

import java.util.List;

import static ru.otus.hw13.Constants.CACHE_LIFE_TIME;
import static ru.otus.hw13.Constants.CACHE_SIZE;

@Component
public class HibernateCacheService extends HibernateServiceImpl {
    @Getter private final CacheEngine<Long, UserDataSet> cache = CacheEngineFactory.create(CACHE_SIZE, CACHE_LIFE_TIME, 0);

    public HibernateCacheService() {
    }

    @Override
    public UserDataSet load(long id) {
        CacheElem<Long, UserDataSet> cached = cache.get(id);
        if (cached != null) {
            return cached.getValue();
        }
        UserDataSet user = super.load(id);
        if (user != null) {
            cache.put(new CacheElem<>(id, user));
        }
        return user;
    }

    @Override
    public void save(UserDataSet dataSet) {
        super.save(dataSet);
        cache.put(new CacheElem<>(dataSet.getId(), dataSet));
    }

    @Override
    public List<UserDataSet> readAll() {
        List<UserDataSet> allUsers = super.readAll();
        if (allUsers != null) {
            allUsers.forEach(user -> cache.put(new CacheElem<>(user.getId(), user)));
        }
        return allUsers;
    }
}
