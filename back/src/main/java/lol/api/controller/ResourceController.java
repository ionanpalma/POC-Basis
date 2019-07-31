package lol.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lol.api.exception.BadRequestException;
import lol.api.model.UserModel;
import lol.api.payload.AddUser;
import lol.api.payload.BaseResponse;
import lol.api.service.ResourceService;

@RestController("/api")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    private static final Logger log = LoggerFactory.getLogger(ResourceController.class);

    @PostMapping("/users")
    public ResponseEntity<BaseResponse> add(@Valid @RequestBody AddUser user, HttpServletRequest req)
            throws BadRequestException {
        log.info("--- Creating new user ---");

        UserModel response = resourceService.add(user);

        return new ResponseEntity<BaseResponse>(new BaseResponse(response, HttpStatus.CREATED, req.getRequestURI()),
                HttpStatus.CREATED);
    }

    @GetMapping("/users/{userid}")
    public ResponseEntity<BaseResponse> find(@PathVariable("userid") Long userid, HttpServletRequest req) {
        log.info("--- Returning user ---");

        if (userid == null) {
            throw new BadRequestException("The request params values are invalid");
        }

        UserModel au = resourceService.find(userid);

        return new ResponseEntity<BaseResponse>(new BaseResponse(au, HttpStatus.OK, req.getRequestURI()),
                HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<BaseResponse> findAll(HttpServletRequest req) {
        log.info("--- Returning all users ---");

        List<UserModel> au = resourceService.findAll();

        return new ResponseEntity<BaseResponse>(new BaseResponse(au, HttpStatus.OK, req.getRequestURI()),
                HttpStatus.OK);
    }

}
