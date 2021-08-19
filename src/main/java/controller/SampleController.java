package controller;


import domain.SampleVO;
import domain.Ticket;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@RequestMapping("/sample/*")
@Log4j
@Controller
public class SampleController {


    @GetMapping("/all")
    public void doAll() {
        log.info("do all can access everybody");
    }

    @GetMapping("/member")
    public void doMember() {
        log.info("logined member");
    }

    @GetMapping("/admin")
    public void doAdmin() {
        log.info("admin only");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
    @GetMapping("/annoMember")
    public void doMember2() {
        log.info("logined annotation member");
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/annoAdmin")
    public void doAdmin2() {
        log.info("admin annotation only");
    }
}
