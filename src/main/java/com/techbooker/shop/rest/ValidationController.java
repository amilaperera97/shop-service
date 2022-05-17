package com.techbooker.shop.rest;

import com.techbooker.shop.dto.ValidateBranchInfoDto;
import com.techbooker.shop.service.ValidationService;
import com.techbooker.sm.util.dto.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.techbooker.shop.util.contance.ControllerConstance.*;

@RequestMapping(API_V1)
@RestController
@Valid
@AllArgsConstructor
public class ValidationController {

    private final ValidationService validationService;

    @PostMapping(value = BRANCH_VALIDATION)
    public ResponseDto<Boolean> validBranch(@RequestBody @Valid ValidateBranchInfoDto validateBranchInfo) {
        return new ResponseDto<Boolean>().buildSuccessMsgWithData(validationService.isValid(validateBranchInfo));
    }
}
