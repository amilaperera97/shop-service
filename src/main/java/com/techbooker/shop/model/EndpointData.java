package com.techbooker.shop.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "endpoint_data", schema = "shop_info", catalog = "scanmyway")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EndpointData extends CommonModel {

    @Column(name = "feature_name")
    private String featureName;

    @Column(name = "endpoint")
    private String endpoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Enumerated(EnumType.STRING)
    @Column(name = "ws_type")
    private WebserviceType wsType;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_body_type")
    private ContentType requestBodyType;

    @Column(name = "request_body", length = 3000, columnDefinition = "TEXT")
    private String requestBody;

    @Enumerated(EnumType.STRING)
    @Column(name = "response_body_type")
    private ContentType responseBodyType;

    @Column(name = "response_body", length = 3000, columnDefinition = "TEXT")
    private String responseBody;

    @Enumerated(EnumType.STRING)
    @Column(name = "http_method", nullable = false)
    private HttpMethod httpMethod;

    @OneToMany(mappedBy = "endpointData", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ParamData> paramData;
}
