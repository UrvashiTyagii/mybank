package services;

import dao.ActivateDao;
import dto.ActivateRequest;
import dto.ActivateResponse;
import models.Status;
import models.User;

public class ActivateService {

    public ActivateResponse activateAccount(ActivateRequest activateRequest)
    {
        ActivateResponse activateResponseDto= new ActivateResponse();
        User user = User.find.byId(activateRequest.getUserId());
        System.out.println("Found user with Id "  + user.getId());
        if (user.getStatus().getId()==Status.statusEnum.INACTIVE.getId()){
            user.setStatus(Status.getInstanceOf(Status.statusEnum.ACTIVE.getId()));
            activateResponseDto.setStatus(200);
            activateResponseDto.setMessage("Successful");
            ActivateDao.activateDao(user);
            return activateResponseDto;
        }

        activateResponseDto.setStatus(200);
        activateResponseDto.setMessage("User is already activated!");
        return activateResponseDto;


    }
}
