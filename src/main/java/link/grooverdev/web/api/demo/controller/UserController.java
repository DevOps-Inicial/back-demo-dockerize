package link.grooverdev.web.api.demo.controller;

import jakarta.validation.Valid;
import link.grooverdev.web.api.demo.dto.UserDto;
import link.grooverdev.web.api.demo.entity.User;
import link.grooverdev.web.api.demo.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

    private final IUserService userService;
    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        try {
            var users = userService.findAll().stream()
                    .map(allUsr->modelMapper.map(allUsr, UserDto.class)).collect(Collectors.toList());
            log.debug("Listado completo de registros");
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        catch (DataAccessException daex) {
            log.error("Error al obtener el listado de registros " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/enabled-users/{enabled}")
    public ResponseEntity<List<UserDto>> getAllEnabledUsers(@PathVariable boolean enabled) {

        try {
            var enabledUsers = userService.findAllEnabled(true).stream()
                    .map(eUsers-> modelMapper.map(eUsers, UserDto.class)).collect(Collectors.toList());
            log.debug("Listado de registros habilitados");
            return new ResponseEntity<>(enabledUsers, HttpStatus.OK);
        }
        catch (DataAccessException daex) {
            log.error("Error al obtener el listado de habilitados " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable @Valid String id) {

        try {
            var singleUser = userService.findByID(id);
            var singleUserResponse = modelMapper.map(singleUser, UserDto.class);
            log.debug("Un registro");
            return new ResponseEntity<>(singleUserResponse, HttpStatus.OK);
        }
        catch (DataAccessException daex) {
            log.error("Error al obtener un registro " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto newUserDto) {

        try {
            var requestUser = modelMapper.map(newUserDto, User.class);
            var savedUser = userService.save(requestUser);
            var responseUser = modelMapper.map(savedUser, UserDto.class);
            log.debug("Registro guardado exitosamente");
            return new ResponseEntity<>(responseUser, HttpStatus.CREATED);
        }
        catch (DataAccessException daex) {
            log.error("Error al guardar el registro " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable @Valid String id, @RequestBody @Valid UserDto updUserDto) {
        try {
            var requestUser = modelMapper.map(updUserDto, User.class);
            var updatedUser = userService.update(id, requestUser);
            var responseUser = modelMapper.map(updatedUser, UserDto.class);
            log.debug("Registro actualizado de forma exitosa");
            return new ResponseEntity<>(responseUser, HttpStatus.ACCEPTED);
        }
        catch (DataAccessException daex) {
            log.error("Error al actualizar el registro " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserDto> deleteUserByID(@PathVariable String id) {

        try {
            userService.delete(id);
            log.debug("Registro eliminado");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (DataAccessException daex) {
            log.error("Error al eliminar el registro " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
