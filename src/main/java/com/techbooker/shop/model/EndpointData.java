package com.techbooker.shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "endpoint_data", schema = "shop_info", catalog = "scanmyway")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EndpointData extends CommonModel {

    @Column(name = "host")
    private String host;

    @Column(name = "endpoint_url")
    private String endpointUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    @JsonBackReference
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

    @Enumerated(EnumType.STRING)
    @Column(name = "endpoint_operation", nullable = false)
    private Operations operation;

    @OneToMany(mappedBy = "id",cascade = CascadeType.PERSIST)
    private List<ExternalParamModel> endPointParams;
}
