package kodlamaio.hrms.api.controller;

import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.model.concretes.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/confirm/{uuid}/{code}")
    public ResponseEntity<Result> confirm(@PathVariable("uuid") UUID uuid, @PathVariable("code") String verificationCode){
        ResponseEntity<DataResult<VerificationCode>> result = verificationCodeService.findByUserUuid(uuid);
        if(!result.getBody().isSuccess()){
            return ResponseEntity.badRequest().body(new ErrorResult(result.getBody().getMessage()));
        } else if(result.getBody().getData().isConfirmed()){
            return ResponseEntity.badRequest().body(new ErrorResult("Your account already confirmed."));
        } else{
            return verificationCodeService.confirm(result.getBody().getData(), verificationCode);
        }
    }
}
