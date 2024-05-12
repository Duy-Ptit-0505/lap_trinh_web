package com.htdweb.controller.admin;

import com.htdweb.model.dto.AccountDTO;
import com.htdweb.model.dto.BuildingDTO;
import com.htdweb.model.dto.OrderDTO;
import com.htdweb.model.dto.TransactionDTO;
import com.htdweb.service.BuildingService;
import com.htdweb.service.OrderService;
import com.htdweb.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private OrderService orderService;
    @GetMapping("/admin/home")
    public ModelAndView homePage(){
        ModelAndView mav = new ModelAndView("/admin/index");
//        ModelAndView mav = new ModelAndView("/admin/test");
        return mav;
    }
    @GetMapping("/admin/add-building")
    public ModelAndView addBuilding(@ModelAttribute BuildingDTO buildingDTO){
        ModelAndView mav = new ModelAndView("/admin/add-building");
        mav.addObject("modelAdd", buildingDTO);
        return mav;
    }
    @GetMapping("/admin/hihi")
    public void addBuilding(@RequestBody(required = false) AccountDTO accountDTO){

        System.out.println("hihi");
    }
    @GetMapping("/admin/update-building")
    public ModelAndView updateBuilding(@ModelAttribute("modelAddBuilding") BuildingDTO buildingDTO){
        ModelAndView mav = new ModelAndView("/admin/update-building");
//        ModelAndView mav = new ModelAndView("/admin/update-building");
        List<BuildingDTO> buildingDTOList = buildingService.getAllBuildingDTO();
        mav.addObject("buildingDTOList", buildingDTOList);
        return mav;
    }
    @PostMapping("/admin/update-building")
    public String UpdateBuilding(@ModelAttribute("modelAdd") BuildingDTO buildingDTO){
        buildingService.addOrUpdateBuilding(buildingDTO);
        if(buildingDTO.getId() != null){
            return "redirect:/admin/update-building";
        }
        else{
            return "redirect:/admin/home";
        }
    }
    @PostMapping("/admin/add-building")
    public String addBuilding1(@ModelAttribute("modelAddBuilding") BuildingDTO buildingDTO){
        buildingService.addOrUpdateBuilding(buildingDTO);
        if(buildingDTO.getId() != null){
            return "redirect:/admin/update-building";
        }
        else{
            return "redirect:/admin/home";
        }
    }
    @GetMapping("/admin/test")
    public ModelAndView test(){
        return new ModelAndView("/web/rent");
    }
    @GetMapping("/admin/check-order")
    public ModelAndView adminCheckOrder(@ModelAttribute("modelAddImage") TransactionDTO transactionDTO){
        ModelAndView mav = new ModelAndView("/admin/check-order");
        List<OrderDTO> orderDTOList = orderService.getAllOrderByAdmin();
        mav.addObject("orderDTOList", orderDTOList);
        return mav;
    }
    @GetMapping("/admin/check-transaction")
    public ModelAndView adminCheckTransaction(){
        ModelAndView mav = new ModelAndView("/admin/check-transaction");
        List<TransactionDTO> transactionDTOList = transactionService.getAllTransacactionForAdmin();
        mav.addObject("transactionDTOList", transactionDTOList);
        return mav;
    }

    @GetMapping("/admin/update/{id}")
    public ModelAndView checkUpdateBuilding(@PathVariable Long id, @ModelAttribute("modelAdd") BuildingDTO buildingDTO){
        ModelAndView mav = new ModelAndView("/admin/update-templates");
        buildingDTO = buildingService.getBuildingDTOById(id);
        mav.addObject("modelAdd", buildingDTO);
        return mav;
    }
    @GetMapping("/admin/delete/{id}")
    public String deleteBuilding(@PathVariable Long id){
        buildingService.deleteBuilding(id);
        return "redirect:/admin/update-building";
    }
    @GetMapping("/admin/failed-order/{id}")
    public String failedOrder(@PathVariable Long id){
        orderService.failedOrder(id);
        return "redirect:/admin/check-order";
    }
    @PostMapping("/admin/done-order/")
    public String doneOrder(@ModelAttribute("modelAddImage") TransactionDTO transactionDTO){
        orderService.doneOrder(transactionDTO);
        return "redirect:/admin/check-order";
    }
//    @GetMapping("/admin/done-order/{id}")
//    public String doneOrder(@PathVariable Long id){
//        orderService.doneOrder(id);
//        return "redirect:/admin/check-order";
//    }
}
