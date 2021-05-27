package kodlamaio.hrms.api.controller;

import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.model.concretes.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/verification-code")
public class VerificationCodesController {

    private VerificationCodeService verificationCodeService;

    @Autowired
    public VerificationCodesController(VerificationCodeService verificationCodeService) {
        this.verificationCodeService = verificationCodeService;
    }

    @GetMapping("/verify-candidate/{uuid}/{code}")
    public Result confirmCandidate(@PathVariable("uuid") UUID uuid, @PathVariable("code") String verificationCode){
        DataResult<VerificationCode> result = verificationCodeService.findByUserUuid(uuid);
        if(!result.isSuccess()){
            return new ErrorResult(result.getMessage());
        } else if(result.getData().isConfirmed()){
            return new ErrorResult("Your account already confirmed.");
        } else{
            return verificationCodeService.update(result.getData(), verificationCode);
        }
    }
}
