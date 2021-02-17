package com.epam.tcfp.phonestore.service.factory;

import com.epam.tcfp.phonestore.service.*;
import com.epam.tcfp.phonestore.service.admin.*;
import com.epam.tcfp.phonestore.service.user.EditUserPersonalDataService;
import com.epam.tcfp.phonestore.service.user.UserHomeService;
import com.epam.tcfp.phonestore.service.user.UserPersonalDataFormService;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


public class ServiceFactory {
    private static final Map<String, Service> SERVICE_MAP = new HashMap<>();
    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();
    private static final Logger log = Logger.getLogger(ServiceFactory.class.getName());

    private ServiceFactory() {
    }

    static {
        SERVICE_MAP.put("/PHONESTORE", new HomeService());
        SERVICE_MAP.put("/PHONESTORE/LOGIN/FORM", new LoginFormService());
        SERVICE_MAP.put("/PHONESTORE/LOGIN", new LoginService());
        SERVICE_MAP.put("/PHONESTORE/REGISTER/FORM", new RegisterFormService());
        SERVICE_MAP.put("/PHONESTORE/REGISTER", new RegisterService());
        SERVICE_MAP.put("/PHONESTORE/LOGOUT", new LogoutService());
        SERVICE_MAP.put("/PHONESTORE/CART/ADD", new AddToCartService());
        SERVICE_MAP.put("/PHONESTORE/CART/DELETE", new DeleteFromCartService());
        SERVICE_MAP.put("/PHONESTORE/CART", new CartService());
        SERVICE_MAP.put("/PHONESTORE/CART/CHANGE-QUANTITY", new ChangeQuantityService());
        SERVICE_MAP.put("/PHONESTORE/CHECKOUT", new CheckoutService());
        SERVICE_MAP.put("/PHONESTORE/ORDER-CONFIRMATION", new OrderConfirmationService());
        SERVICE_MAP.put("/PHONESTORE/BRAND", new BrandSearchService());
        SERVICE_MAP.put("/PHONESTORE/SEARCH", new SearchService());
        SERVICE_MAP.put("/PHONESTORE/CONTACTS", new ContactsService());

        SERVICE_MAP.put("/PHONESTORE/VIEW", new ViewProductService());

        SERVICE_MAP.put("/PHONESTORE/ADMIN/HOME", new AdminHomeService());

        SERVICE_MAP.put("/PHONESTORE/ADMIN/USER-MANAGEMENT", new AdminUserManagementService());
        SERVICE_MAP.put("/PHONESTORE/ADMIN/USER-MANAGEMENT/EDIT/FORM", new AdminEditUserFormService());
        SERVICE_MAP.put("/PHONESTORE/ADMIN/USER-MANAGEMENT/EDIT", new AdminEditUserService());
        SERVICE_MAP.put("/PHONESTORE/ADMIN/USER-MANAGEMENT/DELETE", new AdminDeleteUserService());
        SERVICE_MAP.put("/PHONESTORE/ADMIN/USER-MANAGEMENT/CREATE/FORM", new AdminCreateUserFormService());
        SERVICE_MAP.put("/PHONESTORE/ADMIN/USER-MANAGEMENT/CREATE", new AdminCreateUserService());

        SERVICE_MAP.put("/PHONESTORE/ADMIN/PRODUCT-MANAGEMENT", new AdminProductManagementService());
        SERVICE_MAP.put("/PHONESTORE/ADMIN/PRODUCT-MANAGEMENT/EDIT-FORM", new AdminProductEditFormService());
        SERVICE_MAP.put("/PHONESTORE/ADMIN/PRODUCT-MANAGEMENT/EDIT", new AdminProductEditService());
        SERVICE_MAP.put("/PHONESTORE/ADMIN/PRODUCT-MANAGEMENT/ADD", new AdminProductAddService());
        SERVICE_MAP.put("/PHONESTORE/ADMIN/PRODUCT-MANAGEMENT/ADD-FORM", new AdminProductAddFormService());
        SERVICE_MAP.put("/PHONESTORE/ADMIN/PRODUCT-MANAGEMENT/DELETE", new AdminProductDeleteService());

        SERVICE_MAP.put("/PHONESTORE/ADMIN/ORDER-MANAGEMENT", new AdminOrderService());
        SERVICE_MAP.put("/PHONESTORE/ADMIN/ORDER-MANAGEMENT/EDIT/FORM", new AdminOrderEditFormService());
        SERVICE_MAP.put("/PHONESTORE/ADMIN/ORDER-MANAGEMENT/EDIT", new AdminOrderEditService());
        SERVICE_MAP.put("/PHONESTORE/ADMIN/ORDER-MANAGEMENT/DELETE", new AdminOrderDeleteService());

        SERVICE_MAP.put("/PHONESTORE/USER/HOME", new UserHomeService());
        SERVICE_MAP.put("/PHONESTORE/USER/PERSONAL-DATA-FORM", new UserPersonalDataFormService());
        SERVICE_MAP.put("/PHONESTORE/USER/EDIT/PERSONAL-DATA", new EditUserPersonalDataService());
        SERVICE_MAP.put("/ERROR", new ErrorService());
    }

    public Service getService(String request) {
        Service service;
        service = SERVICE_MAP.get(request.toUpperCase());
        if(service == null){
            service = SERVICE_MAP.get("/ERROR");
        }
        log.info("current service is " + service.toString());
        return service;
    }

    public static ServiceFactory getInstance() {
        return SERVICE_FACTORY;
    }
}
