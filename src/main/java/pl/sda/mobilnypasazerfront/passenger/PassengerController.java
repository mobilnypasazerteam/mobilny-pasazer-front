package pl.sda.mobilnypasazerfront.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class PassengerController {

    private final PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("/new-form")
    String registerTicket (Model model) {
        model.addAttribute( "ticket", new PassengerTicket() );
        return "passengerForm";
    }

    @PostMapping(value="/new-ticket")
    public String saveTicket(@ModelAttribute(name = "ticket") PassengerTicket ticket, Model model){

        passengerService.save(ticket);
        model.addAttribute( "ticketList", passengerService.getTicketList() );
        return "ticketList";

        //return "index";

    }

    @GetMapping("/get-list")
    String lista (Model model) {
       model.addAttribute( "ticket", new PassengerTicket() );

        //get data mock
        ArrayList listaDowyswieltnia =  new ArrayList<PassengerTicket>(); //PassangerService.PassengerService();

        //sample data
        PassengerTicket P1 =new PassengerTicket();
        P1.setEmail("aaa@a.pl");
        listaDowyswieltnia.add(P1);
        PassengerTicket P2 =new PassengerTicket();
        P1.setEmail("bbbb@a.pl");
        listaDowyswieltnia.add(P2);

        model.addAttribute( "lista", listaDowyswieltnia );
        return "orderList";
    }






}