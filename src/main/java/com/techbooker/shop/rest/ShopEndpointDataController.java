package com.techbooker.shop.rest;

import com.techbooker.shop.dto.EndpointDataDto;
import com.techbooker.shop.service.ShopEndpointDataService;
import com.techbooker.sm.util.dto.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.techbooker.shop.util.contance.ControllerConstance.*;

@RequestMapping(API_V1)
@RestController
@Valid
@AllArgsConstructor
@EnableOAuth2Sso
public class ShopEndpointDataController {
//public class ShopEndpointDataController extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/v1/public/endpoint")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    private final ShopEndpointDataService shopEndpointDataService;

    @GetMapping(value = PUBLIC + ENDPOINT_DATA, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<List<EndpointDataDto>> findAll() {
        return new ResponseDto<List<EndpointDataDto>>().buildSuccessMsgWithData(shopEndpointDataService.findAll());
    }

    @PostMapping(value = ENDPOINT_DATA, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<EndpointDataDto> save(@RequestBody EndpointDataDto endpointData) {
        return new ResponseDto<EndpointDataDto>().buildSuccessMsgWithData(shopEndpointDataService.save(endpointData));
    }
}
