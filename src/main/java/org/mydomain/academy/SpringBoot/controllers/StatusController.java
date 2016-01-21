package org.mydomain.academy.SpringBoot.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.mydomain.academy.SpringBoot.view.View;
import org.mydomain.academy.db.entities.Status;
import org.mydomain.academy.db.utils.formatters.StringDateFormatter;
import org.mydomain.academy.services.impls.JPAServiceImpl.JPAStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/status")
public class StatusController {

    @Autowired
    JPAStatusService jpaStatusService;

    @Autowired
    private StringDateFormatter sdf;

    @JsonView(View.Summary.class)
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String findLast() {
        Status status = jpaStatusService.findLast();
        if (status != null) {
            String response = "Connection info<br><br>IP address: " + status.getIpAddress() + "<br>Last update: " +
                    status.getLastUpdate();
            return response;
        }
        return "Nothing to show";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public boolean updateStatus(@RequestParam(value = "msg") String msg, HttpServletRequest request) {

        //todo move to properties
        if ("change-this".equals(msg)) {
            String ip = request.getRemoteAddr();
            Status status = new Status(ip);
            System.out.println("Updating latest ip address: " + ip);
            return jpaStatusService.saveService(status);
        }
        return false;
    }
}