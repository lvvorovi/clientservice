package com.roadmap.clientservice.web.controller;

import com.roadmap.clientservice.business.service.ClientService;
import com.roadmap.clientservice.model.ClientCreateRequest;
import com.roadmap.clientservice.model.ClientResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.roadmap.clientservice.swagger.ApiResponseStore.*;
import static com.roadmap.clientservice.swagger.SwagerTagStore.CLIENT_CONTROLLER_TAG;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class MnVClientController {

    private final ClientService service;

    @GetMapping("/{id}")
    public ModelAndView findById(@PathVariable Long id) {
        ClientResponse dto = service.findById(id);
        ModelAndView view = new ModelAndView();
        view.setViewName("getClient");
        view.addObject(dto);
        return view;
    }

    @GetMapping
    public String form() {
        return "saveClient";
    }

    @PostMapping
    public ModelAndView save(@ModelAttribute ClientCreateRequest request) {
        ClientResponse response = service.save(request);
        ModelAndView view = new ModelAndView();
        view.addObject(response);
        view.setViewName("getClient");
        return view;
    }
}


