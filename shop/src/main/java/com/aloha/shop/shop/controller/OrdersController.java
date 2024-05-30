package com.aloha.shop.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aloha.shop.shop.model.OrderItems;
import com.aloha.shop.shop.model.Orders;
import com.aloha.shop.shop.model.Payments;
import com.aloha.shop.shop.model.PaymentsStatus;
import com.aloha.shop.shop.model.Shipments;
import com.aloha.shop.shop.service.OrderItemsService;
import com.aloha.shop.shop.service.OrdersService;
import com.aloha.shop.shop.service.PaymentsService;
import com.aloha.shop.shop.service.ShipmentsService;
import com.aloha.shop.user.model.Address;
import com.aloha.shop.user.model.Users;
import com.aloha.shop.user.service.AddressService;

import lombok.extern.slf4j.Slf4j;


/**
 * TODO: 전체적으로 권한 설정 및 본인 확인 처리 필요
 */
@Slf4j
@Controller("orders")
@RequestMapping("/orders")
public class OrdersController {


    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrderItemsService orderItemsService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ShipmentsService shipmentsService;

    @Autowired
    private PaymentsService paymentsService;


    /**
     * 주문하기
     * @param param
     * @return
     */
    @GetMapping("")
    public String orders() {


        return "/orders/index";
    }

    /**
     * 주문 등록
     * 1. product - id, quantity
     * 2. 
     * @param entity
     * @return
     * @throws Exception 
     */
    @PostMapping("")
    public String orderPost(Orders orders
                           ,HttpSession session
                           ,@RequestParam List<String> productId
                           ,@RequestParam List<Integer> quantity) throws Exception {
        
        log.info("::::::::: 주문 등록 - orderPost() ::::::::::");
        log.info("productId : " + productId);
        log.info("quantity : " + quantity);
        Users user = (Users) session.getAttribute("user");
        orders.setUserId(user.getId());
        orders.setProductId(productId);
        orders.setQuantity(quantity);

        // 주문 등록
        int result = ordersService.insert(orders);
        // TODO: 배송 등록

        log.info("신규 등록된 주문ID : " + orders.getId() );
        if( result > 0 ) {
            return "redirect:/orders/" + orders.getId();
        }
        // TODO : 주문 실패시 어디로 가는게 좋을지? - 장바구니? 주문내역? 상품목록?
        else {
            return "redirect:/orders";
        }
    }


    /**
     * 주문 완료
     * @param model
     * @param session
     * @param ordersId
     * @return
     * @throws Exception 
     */
    @GetMapping("/success")
    public String orderSuccess(Model model
                              ,Payments payments
                              ,HttpSession session
                              ,@RequestParam("ordersId") String ordersId) throws Exception {

        payments.setOrdersId(ordersId);
        payments.setStatus(PaymentsStatus.PAID);
        paymentsService.merge(payments);

        Shipments shipments = shipmentsService.selectByOrdersId(ordersId);
        log.info(":::::::::::::::::::: 주문 완료 - /order/success ::::::::::::::::::::");
        log.info(":::::::::::::::::::: shipments ::::::::::::::::::::");
        log.info(shipments.toString());
        
        payments = paymentsService.selectByOrdersId(ordersId);
        log.info(":::::::::::::::::::: payments ::::::::::::::::::::");
        log.info(payments.toString());

        Orders order = ordersService.select(ordersId);
        log.info(":::::::::::::::::::: orders ::::::::::::::::::::");
        log.info(payments.toString());

        model.addAttribute("shipments", shipments);
        model.addAttribute("payments", payments);
        model.addAttribute("order", order);
        return "/orders/success";
    }
    

    /**
     * 주문 실패 
     * @param model
     * @param session
     * @param ordersId
     * @return
     * @throws Exception 
     */
    @GetMapping("/fail")
    public String orderFail(Model model
                              ,Payments payments
                              ,HttpSession session
                              ,@RequestParam("ordersId") String ordersId
                              ,@ModelAttribute String errorMsg) throws Exception {

        payments.setOrdersId(ordersId);
        payments.setStatus(PaymentsStatus.PAID);
        paymentsService.insert(payments);

        Shipments shipments = shipmentsService.selectByOrdersId(ordersId);
        log.info(":::::::::::::::::::: 주문 완료 - /order/success ::::::::::::::::::::");
        log.info(":::::::::::::::::::: shipments ::::::::::::::::::::");
        log.info(shipments.toString());
        
        // ⭐ 결제 실패 시, 결제 상태 PENDING 으로 변경
        payments = paymentsService.selectByOrdersId(ordersId);
        payments.setStatus(PaymentsStatus.PENDING);
        paymentsService.merge(payments);
        log.info(":::::::::::::::::::: payments ::::::::::::::::::::");
        log.info(payments.toString());

        Orders order = ordersService.select(ordersId);
        log.info(":::::::::::::::::::: orders ::::::::::::::::::::");
        log.info(payments.toString());

        log.info("[결제 실패] 에러 메시지 : " + errorMsg);

        model.addAttribute("shipments", shipments);
        model.addAttribute("payments", payments);
        model.addAttribute("order", order);
        return "/orders/fail";
    }
    


    /**
     * 주문/결제  
     * - ➡ 결제하기
     * @param model
     * @param orderId
     * @return
     * @throws Exception
     */
    @GetMapping("/{orderId}")
    public String checkout(Model model
                          ,HttpSession session
                          ,@PathVariable("orderId") String orderId) throws Exception {
        
        // 로그인 사용자
        Users user = (Users) session.getAttribute("user");
        // 주문 정보
        Orders order = ordersService.select(orderId);
        // 주문 항목 정보
        List<OrderItems> orderItems = orderItemsService.listByOrderId(orderId);
        // 기본 배송지
        List<Address> addressList = addressService.listByUserId(user.getId());
        if( addressList == null || addressList.size() == 0) {
            return "redirect:/orders/checkout?noAddress";
        }
        Address address = addressList.stream().filter((add) -> {return add.getIsDefault();}).findFirst().get();
        log.info("기본 배송지 : " + address.getAddress());
        
        if( order == null ) return "redirect:/orders?error";
        log.info(":::::::::::::::::::: order ::::::::::::::::::::");
        log.info(order.toString());
        log.info(":::::::::::::::::::: order items ::::::::::::::::::::");
        log.info(orderItems.toString());


        model.addAttribute("order", order);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("address", address);
        return "/orders/checkout";
    }
    


    
    
    
}
