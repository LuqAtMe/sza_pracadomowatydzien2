package home.api.sza_pracadomowatydzien2;


import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Service
public class CounterService {

    private Map<String, Integer> counterMap;

    public CounterService() {
        this.counterMap = new HashMap<String, Integer>();
        counterMap.put("Jan", 0);
        counterMap.put("Milosz", 0);
    }

    public Map<String, Integer> getCounterMap() {
        return counterMap;
    }

    @EventListener
    public void increaseCounter(AuthenticationSuccessEvent event) {
        String name = event.getAuthentication().getName();
        int count = counterMap.get(name);
        counterMap.replace(name, ++count);
    }

    public int getCounter(Principal principal) {
        return counterMap.get(principal.getName());
    }

}
