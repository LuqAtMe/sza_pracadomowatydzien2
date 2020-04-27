package home.api.sza_pracadomowatydzien2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Api {

    private CounterService counterService;

    @Autowired
    public Api(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/forAll")
    public String forAll(Principal principal) {
        if (principal == null) {
            return "Hello anonymous";
        } else {
            return "Hello: " + principal.getName() + ". \t"
                    + "You have logged: " + counterService.getCounter(principal);
        }
    }

    @GetMapping("forAdmin")
    public String forAdmin(Principal principal) {
        return "Hello Admin: " + principal.getName() + ". \t"
                + "You have logged: " + counterService.getCounter(principal);
    }

    @GetMapping("forUser")
    public String forUser(Principal principal) {
        return "Hello User: " + principal.getName() + ". \t"
                + "You have logged: " + counterService.getCounter(principal);
    }

    @GetMapping("end")
    public String end() {
        return "Papa";
    }

}
