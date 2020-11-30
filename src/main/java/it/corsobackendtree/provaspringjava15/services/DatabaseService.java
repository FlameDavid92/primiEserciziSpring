package it.corsobackendtree.provaspringjava15.services;
import it.corsobackendtree.provaspringjava15.models.UserCriptato;
import it.treebackend.exercisespring.view.User;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.UUID;

@Service
public class DatabaseService {
    private static final HashMap<String, UserCriptato> users = new HashMap<>(); //key:username, value:user


    public boolean registrazione(User user){
        UserCriptato newUser = new UserCriptato(user.getUsername(), user.getPassword());
        if(users.containsKey(user.getUsername())) return false;
        users.put(newUser.getUsername(), newUser);
        return true;
    }

    public String login(User user){
        if(users.containsKey(user.getUsername())){
            if(user.getPassword().hashCode() == users.get(user.getUsername()).getPasswordCriptata()){
                return user.getUsername();
            }
            return null;
        }
        return null;
    }


}
