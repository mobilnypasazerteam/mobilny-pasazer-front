package pl.sda.mobilnypasazerfront.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PassengerController {

    private final PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @Autowired
    private PassengerRegistrationService passengerRegistrationService;

    @GetMapping("/new-form")
    String passengerForm (Model model) {
        model.addAttribute( "ticket", new PassengerTicket() );
        return "passengerForm";
    }

    @PostMapping(value="/new-ticket")
    public String newTicket(@ModelAttribute(name = "ticket") PassengerTicket ticket,
                           BindingResult result, Model model){

        System.out.println("New ticket : " + ticket.toString() );
        passengerService.save(ticket);

        return "index";
    }

    @GetMapping(value = "/users/index")
    public String showUserIndex() {
        return "userIndex";
    }

    @GetMapping(value = "/register")
    public String showForm(Model model){
        PassengerRegistrationDTO passengerRegistrationDTO = new PassengerRegistrationDTO();
        model.addAttribute("passengerDto", passengerRegistrationDTO);
        model.addAttribute("sex", Sex.values());
        return "registerForm";
    }

    @PostMapping(value="/register")
    public String register(@ModelAttribute(name = "passengerDto") @Valid PassengerRegistrationDTO passengerDto,
                           BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("sex", Sex.values());
            return "registerForm";
        }
        passengerRegistrationService.registerUser(passengerDto);
        return "index";
    }
}
