package services;

import dao.UserDao;
import dto.CustomerListResponse;
import models.User;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CustomerListService {

    @Inject
    private UserDao userDao;

    public List<CustomerListResponse> viewList(Integer roleId)
    {
        List<User> userList= userDao.getList(roleId);
        List<CustomerListResponse> customerListResponsesDto= new ArrayList<>();
        for (User user: userList)
        {
            CustomerListResponse customerListResponse= new CustomerListResponse();
            customerListResponse.setCustomerId(user.getId());
            customerListResponse.setCustomerName(user.getFname()+""+user.getLname());
            customerListResponse.setAadharNumber(user.getAdharNo());

            customerListResponse.setStatusId(user.getStatus().getId());

            customerListResponsesDto.add(customerListResponse);

        }
        return customerListResponsesDto;


    }


}
