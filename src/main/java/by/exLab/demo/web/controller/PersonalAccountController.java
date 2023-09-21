package by.exLab.demo.web.controller;

import by.exLab.demo.core.dto.user.PasswordDTO;
import by.exLab.demo.core.dto.user.UserLoginDTO;
import by.exLab.demo.core.dto.user.UserRegistrationDTO;
import by.exLab.demo.service.api.IPersonalAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class PersonalAccountController {

    private final IPersonalAccountService service;

    @PostMapping(path = "/registration")
    public ResponseEntity<?> register(  @Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
         service.save(userRegistrationDTO);
         return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(path = "/login")
    public  ResponseEntity<?> login(  @Valid @RequestBody UserLoginDTO userLoginDTO) {
        // UserDTO userDTO = service.login(userLoginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(service.login(userLoginDTO));
    }

    //Активация аккаунта
    @GetMapping(path = "/login/mail_confirm/{mail}/{uuid}")
    public  ResponseEntity<?> mailConfirmAfterRegistration(@PathVariable("uuid") UUID uuid,
                                                           @Valid @PathVariable("mail") String mail){
        service.mailConfirm(uuid, mail);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(path = "/reset_password")
    public ResponseEntity<?> recoverPassword(@RequestParam(name = "mail") String mail){
         service.sendMailAboutRecoveryPassword(mail);
         return ResponseEntity.status(HttpStatus.OK).build();
    }

//    @PostMapping(path = "/reset_password")
//    public ResponseEntity<?> recoverPassword( @RequestBody String mail){
//        service.sendMailAboutRecoveryPassword(mail);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

    @PostMapping(path = "/reset_password/{mail}/{uuid}")
    public ResponseEntity<?> enterNewPassword(@PathVariable("uuid") UUID uuid,
                                           @Valid @RequestBody PasswordDTO dto){
        service.saveNewPassword(uuid, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //Получить информацию о себе
//    @RequestMapping(path = "/me", method = RequestMethod.GET)
//    public ResponseEntity<?> getCard(){
//        return ResponseEntity.status(HttpStatus.OK).body(holder.getUser());
//    }

}