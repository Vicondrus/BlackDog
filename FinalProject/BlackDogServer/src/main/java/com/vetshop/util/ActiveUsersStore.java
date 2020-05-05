package com.vetshop.util;

import com.vetshop.entities.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActiveUsersStore {

    private List<User> activeUsers = new ArrayList<>();

    public void addUser(User user){
        activeUsers.add(user);
    }

    public boolean checkUser(User user){
        return activeUsers.stream().anyMatch(x -> user.getUsername().equals(x.getUsername()));
    }

    public boolean removeUser(String username){
        User u = null;
        for(User it : activeUsers){
            if(it.getUsername().equals(username))
                u = it;
        }
        if(u == null)
            return false;
        else{
            activeUsers.remove(u);
            return true;
        }
    }
}
