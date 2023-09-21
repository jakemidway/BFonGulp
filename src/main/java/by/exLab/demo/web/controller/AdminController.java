package by.exLab.demo.web.controller;


import by.exLab.demo.core.dto.PageDTO;
import by.exLab.demo.core.dto.user.*;
import by.exLab.demo.service.api.IAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class AdminController {

    private final IAdminService adminService;

    public AdminController(IAdminService userService) {
        this.adminService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addNewUser(@RequestBody UserCreateDTO userCreateDTO) {
        adminService.addNewUser(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //Постраничный вывод пользователей
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDTO<UserDTO>> getPage(
            @RequestParam(name = "page", required = false, defaultValue = "0") int numberOfPage,
            @RequestParam(name = "size", required = false, defaultValue = "20") int size){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getPage(numberOfPage, size));
    }

    //Получить информацию о пользователе
    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getCard(@PathVariable("uuid") UUID uuid){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getCard(uuid));
    }

    //Редактировать информацию о пользователе
    @RequestMapping(path = "/{uuid}/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("uuid") UUID uuid,
                                    @PathVariable("dt_update") LocalDateTime dtUpdate,
                                    @RequestBody UserCreateDTO userCreateDTO ) {
        adminService.update(uuid, dtUpdate, userCreateDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
