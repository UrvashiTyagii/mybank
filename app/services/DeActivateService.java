package services;

import dao.DeActivateDao;
import dto.DeactivateRequest;
import dto.DeactivateResponse;
import models.Status;
import models.User;

public class DeActivateService {

    public DeactivateResponse deActivateAccount(DeactivateRequest deactivateRequest){
        DeactivateResponse deactivateResponseDto=new DeactivateResponse();
        User user = User.find.byId(deactivateRequest.getUserId());
        if (user.getStatus().getId()== Status.statusEnum.ACTIVE.getId()){
            user.setStatus(Status.getInstanceOf(Status.statusEnum.INACTIVE.getId()));
            deactivateResponseDto.setStatus(200);
            deactivateResponseDto.setMessage("successful!");
            DeActivateDao.deActivateDao(user);
            return deactivateResponseDto;
        }
        deactivateResponseDto.setStatus(200);
        deactivateResponseDto.setMessage("User is already DeActivated!");
        return deactivateResponseDto;
    }
}
