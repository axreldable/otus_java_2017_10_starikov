package ru.otus.hw15;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.hw15.data.UserDataSet;
import ru.otus.hw15.messageSystem.Address;
import ru.otus.hw15.messageSystem.MessageSystem;
import ru.otus.hw15.messageSystem.MessageSystemContext;
import ru.otus.hw15.service.HibernateServiceImpl;

import javax.annotation.PostConstruct;
import java.util.List;

import static ru.otus.hw15.Constants.CACHE_LIFE_TIME;
import static ru.otus.hw15.Constants.CACHE_SIZE;

@Component
public class HibernateCacheService extends HibernateServiceImpl implements CacheService {
    @Getter private final CacheEngine<Long, UserDataSet> cache = CacheEngineFactory.create(CACHE_SIZE, CACHE_LIFE_TIME, 0);

    @Autowired
    private Address dbAddress;

    private final MessageSystemContext context;

    public HibernateCacheService(MessageSystemContext context) {
        this.context = context;
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

    @Override
    public Address getAddress() {
        return this.dbAddress;
    }

    @Override
    @PostConstruct
    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public MessageSystem getMS() {
        return context.getMessageSystem();
    }
}
