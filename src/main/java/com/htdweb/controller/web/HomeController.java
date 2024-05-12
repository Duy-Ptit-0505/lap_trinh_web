package com.htdweb.controller.web;


import com.htdweb.converter.RegisterDTOtoUserEntity;
import com.htdweb.entity.BuildingEntity;
import com.htdweb.entity.UserEntity;
import com.htdweb.model.dto.*;
import com.htdweb.repository.BuildingRepository;
import com.htdweb.repository.UserRepository;
import com.htdweb.service.BuildingService;
import com.htdweb.service.OrderService;
import com.htdweb.service.RoleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping()
public class HomeController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingService buildingService;
    public BuildingRepository buildingRepository;
    @Autowired
    private RegisterDTOtoUserEntity registerDTOtoUserEntity;

    @Autowired
    private OrderService orderService;
    @GetMapping("/web/")
    public String home(){
        return "redirect:/web/home";
    }

    @GetMapping("/web/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("/web/login");
        return mav;

    }
    @RequestMapping(value = "/web/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ModelAndView("redirect:/web/home");
    }
    @GetMapping("/web/register")
    public String getRegister(@ModelAttribute("registerDTO") RegisterDTO registerDTO){

        return "web/register";
    }

    @PostMapping("/web/register")
    public String postRegister(@ModelAttribute("registerDTO") RegisterDTO registerDTO){
        UserEntity userEntity = registerDTOtoUserEntity.toUserEntity(registerDTO);
        userRepository.save(userEntity);
        return "redirect:/web/login";
    }

    @GetMapping("/web/home")
    public ModelAndView homePage(@ModelAttribute SearchDTO searchDTO){
        ModelAndView mav = new ModelAndView("web/index");
        List<BuildingDTO> buildingDTOList = buildingService.getAllBuildingDTOByCustomer(searchDTO);
        List<BuildingDTO> top5BuildingList = buildingService.getTop5BuildingDTOByView();
        mav.addObject("listBuildingDTO", buildingDTOList);
        mav.addObject("modelSearch", searchDTO);
        mav.addObject("top5BuildingList", top5BuildingList);
        return mav;

    }

    @GetMapping("/web/building/{id}")
    public ModelAndView detailBuilding(@PathVariable Long id, @ModelAttribute OrderDTO orderDTO){
        ModelAndView mav = new ModelAndView("/web/detail-building");
        BuildingDTO buildingDTO = buildingService.getBuildingDTOById(id);
        orderDTO.setIdBuilding(id);
        Date date = new Date();

        // Create a SimpleDateFormat object with the desired format
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        // Convert the Date object to a String

        orderDTO.setOrderDay(formatter.format(date));
        mav.addObject("objectAddOrder", orderDTO);
        mav.addObject("buildingDTO", buildingDTO);

        return mav;
    }
//    @PostMapping("/authenticateTheUser")
//    public ModelAndView successfulLogin() {
//        return new ModelAndView("/web/index");
//    }
//    @GetMapping("/web/account/")
//    public String getString(@RequestParam(name = "hihi") String hihi){
//        return hihi;
//    }
    @PostMapping("/web/add-order")
    public String addBuilding(@ModelAttribute("objectAddOrder") OrderDTO orderDTO) throws ParseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = null;
        if(authentication != null && authentication.isAuthenticated()){
            name = authentication.getName();

        }
        if(name.equals("anonymousUser")){
            return "/web/login";
        }
        orderDTO.setIdCustomer(userRepository.findOneByUserName(name).getId());

        orderService.saveOrder(orderDTO);
        return "redirect:/web/home";
    }
    @GetMapping("/web/for-customer")
    private ModelAndView forCustomer(){
        ModelAndView mav = new ModelAndView("/web/for-customer");
        return mav;
    }

}
