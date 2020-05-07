package com.vetshop.util;

import com.vetshop.entities.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Active users store.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActiveUsersStore {

    private List<User> activeUsers = new ArrayList<>();

    /**
     * Add user.
     *
     * @param user the user
     */
    public void addUser(User user) {
        activeUsers.add(user);
    }

    /**
     * Check user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean checkUser(User user) {
        return activeUsers.stream().anyMatch(x -> user.getUsername().equals(x.getUsername()));
    }

    /**
     * Remove user boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean removeUser(String username) {
        User u = null;
        for (User it : activeUsers) {
            if (it.getUsername().equals(username))
                u = it;
        }
        if (u == null)
            return false;
        else {
            activeUsers.remove(u);
            return true;
        }
    }
}
