package org.acme;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import org.acme.security.model.User;

import java.util.List;

@Singleton
public class StartUp {
    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        // reset and load all test users
        User.deleteAll();
        User.add("admin", "admin", "admin", "Yiwei Ge", "yg@test.com");
        User.add("user", "user", "user", "Yang Feng", "fy@test.com");

        List<User> userList = User.listAll();
        userList.forEach(System.out::println);
    }
}
