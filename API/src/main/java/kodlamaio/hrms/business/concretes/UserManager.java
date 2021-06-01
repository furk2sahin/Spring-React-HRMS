package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }
}
