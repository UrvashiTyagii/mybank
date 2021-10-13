package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import dto.*;
import models.Status;
import play.data.Form;
//import play.api.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.*;
import services.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    @Inject
    private RegistrationService registrationService;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    public Result admin(){

        return  ok(views.html.adminhomepage.render());
    }
    
    public Result bank(){
        return ok(views.html.myBank.render());
    }

//    public Result clist(){
//        return ok(views.html.customerlist.render());
//    }

    public  Result registration(){
        return ok(views.html.registration.render());
    }

    public Result uhome(){

        return ok(views.html.userhomepage.render());
    }

//    public Result userDetails(){
//        return ok(views.html.userDetails.render());
//    }

    public Result responsee()
    {
        return ok(Json.toJson("Welcome to Ajax!"));
    }

    @Inject
    private FormFactory formFactory;

    public Result saveData(Http.Request request){
        RegistrationRequest requestDto=new RegistrationRequest();
        RegistrationResponse responseDto=new RegistrationResponse();
        //JsonNode jsonNode=request.body().asJson();
        System.out.print("JSON" +request.body().toString());
        // requestDto= (RegistrationRequestDto) Json.fromJson(jsonNode, RegistrationRequestDto.class);
        Form<RegistrationRequest> registrationForm =
                formFactory.form(RegistrationRequest.class).bindFromRequest(request);
        System.out.println(registrationForm.get().getCustomerFirstName() + " FORM ");
        requestDto=registrationForm.get();
        registrationService.save(requestDto);
        responseDto.setStatus(200);
        responseDto.setMessage("successful");
        return ok(Json.toJson(responseDto));
    }




    @Inject
    UserDetailsService userDetailsService;

    public Result userDetails(Http.Request request){

        UserDetailsRequest userDetailsRequestdto=new UserDetailsRequest();
        UserDetailsResponse userDetailsResponsedto = new UserDetailsResponse();
        System.out.println("JSON"+ request.body().toString());
        Form<UserDetailsRequest> userDetailsRequestForm =
                formFactory.form(UserDetailsRequest.class).bindFromRequest(request);
        userDetailsRequestdto = userDetailsRequestForm.get();
        userDetailsResponsedto=userDetailsService.saveUserDetails(userDetailsRequestdto);

        return ok(Json.toJson(userDetailsResponsedto));


    }
//    @Inject
//    UserDetailsService useService;
//    public Result getUserDetails(Integer id){
//        useService.getUserDetails(id);
//        return ok(Json.toJson(user))
//    }



    public Result loginData(Http.Request request){
        LoginResponse loginResponseDto= new LoginResponse();
            LoginRequest loginRequestDto=new LoginRequest();


        Form<LoginRequest> loginRequestForm=
                formFactory.form(LoginRequest.class).bindFromRequest(request);

        loginRequestDto= loginRequestForm.get();
        loginResponseDto=LoginService.validateCredentials(loginRequestDto);
//
//        if (loginResponseDto.getStatus()== Status.statusEnum.ACTIVE.getId())
//        {
//
//            if(loginResponseDto.getRoleType()== 2)
//            {
//                return redirect("http://localhost:9000/uhome");
//            }
//            if (loginResponseDto.getRoleType()==1)
//            {
//                return redirect("http://localhost:9000/admin");
//            }
//        }
        return ok(Json.toJson(loginResponseDto));


    }

    @Inject
    TransferService transferService;

    public Result transferData(Http.Request request)
    {
        TransferMoneyRequest  transferMoneyRequestDto= new TransferMoneyRequest();
        TransferMoneyResponse transferMoneyResponseDto= new TransferMoneyResponse();
        System.out.print("JSON" +request.body().toString());

        Form<TransferMoneyRequest> transferMoneyRequestForm=
              formFactory.form(TransferMoneyRequest.class).bindFromRequest(request);

        transferMoneyRequestDto=transferMoneyRequestForm.get();
        transferService.transferSaveData(transferMoneyRequestDto);
        transferMoneyResponseDto.setStatus(200);
        transferMoneyResponseDto.setMessage("successful");

        return ok(Json.toJson(transferMoneyResponseDto));
    }

    @Inject
    private ActivateService activateService;

    public Result activate(Http.Request request)
    {
        ActivateRequest activateRequestDto = new ActivateRequest();
        ActivateResponse activateResponseDto = new ActivateResponse();
        System.out.print("JSON" +request.body().toString());
        Form<ActivateRequest> activateRequestForm =
                formFactory.form(ActivateRequest.class).bindFromRequest(request);
        activateRequestDto = activateRequestForm.get();
        activateResponseDto = activateService.activateAccount(activateRequestDto);

        return ok(Json.toJson(activateResponseDto));
    }

    @Inject
    DeActivateService deActivateService;
    public Result deActivate(Http.Request request)
    {
        DeactivateRequest deactivateRequestDto= new DeactivateRequest();
        DeactivateResponse deactivateResponseDto = new DeactivateResponse();

        System.out.print("JSON" +request.body().toString());
        Form<DeactivateRequest> deactivateRequestForm=
                formFactory.form(DeactivateRequest.class).bindFromRequest(request);
        deactivateRequestDto=deactivateRequestForm.get();
        deactivateResponseDto= deActivateService.deActivateAccount(deactivateRequestDto);
        return ok(Json.toJson(deactivateResponseDto));

    }



    public Result transfer(){
        return ok(views.html.depositpage.render());
    }

    public Result withdraw(){
        return ok(views.html.withdrawpage.render());
    }


   @Inject
   TransactionService transactionHistoryService;
    public Result tHistory(Http.Request request){
        TransactionHistoryRequest transactionHistoryRequestDto =new TransactionHistoryRequest();
        List<TransactionHistoryResponse> transactionHistoryResponseList = new ArrayList<>();
        System.out.print("JSON" +request.body().toString());
        Form<TransactionHistoryRequest> transactionHistoryRequestForm =
                formFactory.form(TransactionHistoryRequest.class).bindFromRequest(request);
        transactionHistoryRequestDto=transactionHistoryRequestForm.get();
        transactionHistoryResponseList=transactionHistoryService.transactionHistory(transactionHistoryRequestDto);
        return ok(Json.toJson(transactionHistoryResponseList));


    }

    @Inject
    private CustomerListService customerListService;

    public Result viewCustomerList(Integer roleId)
    {
        List<CustomerListResponse> customerListResponsesDto = customerListService.viewList(roleId);
        return ok(Json.toJson(customerListResponsesDto));

    }

    public Result viewPage(Integer roleId)
    {
        List<CustomerListResponse> customerListResponsesDto = customerListService.viewList(roleId);
        return ok(Json.toJson(customerListResponsesDto));
    }
}