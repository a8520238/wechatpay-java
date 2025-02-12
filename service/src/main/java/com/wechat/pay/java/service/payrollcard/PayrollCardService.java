// Copyright 2021 Tencent Inc. All rights reserved.
//
// 微工卡接口文档
//
// 服务商通过本API文档提供的接口，查询商户和微工卡的授权关系、生成预授权的token口令、核身预下单、核身结果的查询等。
//
// API version: 1.5.2

// Code generated by WechatPay APIv3 Generator based on [OpenAPI
// Generator](https://openapi-generator.tech); DO NOT EDIT.

package com.wechat.pay.java.service.payrollcard;

import static com.wechat.pay.java.core.http.UrlEncoder.urlEncode;
import static com.wechat.pay.java.core.util.GsonUtil.toJson;
import static java.util.Objects.requireNonNull;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.cipher.PrivacyEncryptor;
import com.wechat.pay.java.core.exception.HttpException;
import com.wechat.pay.java.core.exception.MalformedMessageException;
import com.wechat.pay.java.core.exception.ServiceException;
import com.wechat.pay.java.core.exception.ValidationException;
import com.wechat.pay.java.core.http.Constant;
import com.wechat.pay.java.core.http.DefaultHttpClientBuilder;
import com.wechat.pay.java.core.http.HostName;
import com.wechat.pay.java.core.http.HttpClient;
import com.wechat.pay.java.core.http.HttpHeaders;
import com.wechat.pay.java.core.http.HttpMethod;
import com.wechat.pay.java.core.http.HttpRequest;
import com.wechat.pay.java.core.http.HttpResponse;
import com.wechat.pay.java.core.http.JsonRequestBody;
import com.wechat.pay.java.core.http.MediaType;
import com.wechat.pay.java.core.http.QueryParameter;
import com.wechat.pay.java.core.http.RequestBody;
import com.wechat.pay.java.service.payrollcard.model.AuthenticationEntity;
import com.wechat.pay.java.service.payrollcard.model.CreateTokenRequest;
import com.wechat.pay.java.service.payrollcard.model.CreateTransferBatchRequest;
import com.wechat.pay.java.service.payrollcard.model.GetAuthenticationRequest;
import com.wechat.pay.java.service.payrollcard.model.GetRelationRequest;
import com.wechat.pay.java.service.payrollcard.model.ListAuthenticationsRequest;
import com.wechat.pay.java.service.payrollcard.model.ListAuthenticationsResponse;
import com.wechat.pay.java.service.payrollcard.model.PreOrderAuthenticationRequest;
import com.wechat.pay.java.service.payrollcard.model.PreOrderAuthenticationResponse;
import com.wechat.pay.java.service.payrollcard.model.PreOrderAuthenticationWithAuthRequest;
import com.wechat.pay.java.service.payrollcard.model.PreOrderAuthenticationWithAuthResponse;
import com.wechat.pay.java.service.payrollcard.model.RelationEntity;
import com.wechat.pay.java.service.payrollcard.model.TokenEntity;
import com.wechat.pay.java.service.payrollcard.model.TransferBatchEntity;

/** PayrollCardService服务 */
public class PayrollCardService {

  private final HttpClient httpClient;
  private final HostName hostName;
  private final PrivacyEncryptor encryptor;

  private PayrollCardService(HttpClient httpClient, HostName hostName, PrivacyEncryptor encryptor) {
    this.httpClient = requireNonNull(httpClient);
    this.hostName = hostName;
    this.encryptor = requireNonNull(encryptor);
  }
  /** PayrollCardService构造器 */
  public static class Builder {

    private HttpClient httpClient;
    private HostName hostName;
    private PrivacyEncryptor encryptor;

    public Builder config(Config config) {
      this.httpClient =
          new DefaultHttpClientBuilder()
              .credential(requireNonNull(config.createCredential()))
              .validator(requireNonNull(config.createValidator()))
              .build();
      this.encryptor = config.createEncryptor();

      return this;
    }

    public Builder hostName(HostName hostName) {
      this.hostName = hostName;
      return this;
    }

    public Builder httpClient(HttpClient httpClient) {
      this.httpClient = httpClient;
      return this;
    }

    public Builder encryptor(PrivacyEncryptor encryptor) {
      this.encryptor = encryptor;
      return this;
    }

    public PayrollCardService build() {
      return new PayrollCardService(httpClient, hostName, encryptor);
    }
  }

  /**
   * 获取核身结果
   *
   * @param request 请求参数
   * @return AuthenticationEntity
   * @throws HttpException 发送HTTP请求失败。例如构建请求参数失败、发送请求失败、I/O错误等。包含请求信息。
   * @throws ValidationException 发送HTTP请求成功，验证微信支付返回签名失败。
   * @throws ServiceException 发送HTTP请求成功，服务返回异常。例如返回状态码小于200或大于等于300。
   * @throws MalformedMessageException 服务返回成功，content-type不为application/json、解析返回体失败。
   */
  public AuthenticationEntity getAuthentication(GetAuthenticationRequest request) {
    String requestPath =
        "https://api.mch.weixin.qq.com/v3/payroll-card/authentications/{authenticate_number}";

    GetAuthenticationRequest realRequest = request;
    // 添加 path param
    requestPath =
        requestPath.replace(
            "{" + "authenticate_number" + "}", urlEncode(realRequest.getAuthenticateNumber()));

    // 添加 query param
    QueryParameter queryParameter = new QueryParameter();
    if (realRequest.getSubMchid() != null) {
      queryParameter.add("sub_mchid", urlEncode(realRequest.getSubMchid()));
    }
    requestPath += queryParameter.getQueryStr();
    if (this.hostName != null) {
      requestPath = requestPath.replaceFirst(HostName.API.getValue(), hostName.getValue());
    }
    HttpHeaders headers = new HttpHeaders();
    headers.addHeader(Constant.ACCEPT, MediaType.APPLICATION_JSON.getValue());
    headers.addHeader(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
    HttpRequest httpRequest =
        new HttpRequest.Builder()
            .httpMethod(HttpMethod.GET)
            .url(requestPath)
            .headers(headers)
            .build();
    HttpResponse<AuthenticationEntity> httpResponse =
        httpClient.execute(httpRequest, AuthenticationEntity.class);
    return httpResponse.getServiceResponse();
  }
  /**
   * 查询核身记录
   *
   * @param request 请求参数
   * @return ListAuthenticationsResponse
   * @throws HttpException 发送HTTP请求失败。例如构建请求参数失败、发送请求失败、I/O错误等。包含请求信息。
   * @throws ValidationException 发送HTTP请求成功，验证微信支付返回签名失败。
   * @throws ServiceException 发送HTTP请求成功，服务返回异常。例如返回状态码小于200或大于等于300。
   * @throws MalformedMessageException 服务返回成功，content-type不为application/json、解析返回体失败。
   */
  public ListAuthenticationsResponse listAuthentications(ListAuthenticationsRequest request) {
    String requestPath = "https://api.mch.weixin.qq.com/v3/payroll-card/authentications";

    ListAuthenticationsRequest realRequest = request;
    // 添加 query param
    QueryParameter queryParameter = new QueryParameter();
    if (realRequest.getOpenid() != null) {
      queryParameter.add("openid", urlEncode(realRequest.getOpenid()));
    }
    if (realRequest.getAppid() != null) {
      queryParameter.add("appid", urlEncode(realRequest.getAppid()));
    }
    if (realRequest.getSubAppid() != null) {
      queryParameter.add("sub_appid", urlEncode(realRequest.getSubAppid()));
    }
    if (realRequest.getSubMchid() != null) {
      queryParameter.add("sub_mchid", urlEncode(realRequest.getSubMchid()));
    }
    if (realRequest.getAuthenticateDate() != null) {
      queryParameter.add("authenticate_date", urlEncode(realRequest.getAuthenticateDate()));
    }
    if (realRequest.getAuthenticateState() != null) {
      queryParameter.add("authenticate_state", urlEncode(realRequest.getAuthenticateState()));
    }
    if (realRequest.getOffset() != null) {
      queryParameter.add("offset", urlEncode(realRequest.getOffset().toString()));
    }
    if (realRequest.getLimit() != null) {
      queryParameter.add("limit", urlEncode(realRequest.getLimit().toString()));
    }
    requestPath += queryParameter.getQueryStr();
    if (this.hostName != null) {
      requestPath = requestPath.replaceFirst(HostName.API.getValue(), hostName.getValue());
    }
    HttpHeaders headers = new HttpHeaders();
    headers.addHeader(Constant.ACCEPT, MediaType.APPLICATION_JSON.getValue());
    headers.addHeader(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
    HttpRequest httpRequest =
        new HttpRequest.Builder()
            .httpMethod(HttpMethod.GET)
            .url(requestPath)
            .headers(headers)
            .build();
    HttpResponse<ListAuthenticationsResponse> httpResponse =
        httpClient.execute(httpRequest, ListAuthenticationsResponse.class);
    return httpResponse.getServiceResponse();
  }
  /**
   * 微工卡核身预下单
   *
   * @param request 请求参数
   * @return PreOrderAuthenticationResponse
   * @throws HttpException 发送HTTP请求失败。例如构建请求参数失败、发送请求失败、I/O错误等。包含请求信息。
   * @throws ValidationException 发送HTTP请求成功，验证微信支付返回签名失败。
   * @throws ServiceException 发送HTTP请求成功，服务返回异常。例如返回状态码小于200或大于等于300。
   * @throws MalformedMessageException 服务返回成功，content-type不为application/json、解析返回体失败。
   */
  public PreOrderAuthenticationResponse preOrderAuthentication(
      PreOrderAuthenticationRequest request) {
    String requestPath = "https://api.mch.weixin.qq.com/v3/payroll-card/authentications/pre-order";
    PreOrderAuthenticationRequest realRequest = request;
    if (this.hostName != null) {
      requestPath = requestPath.replaceFirst(HostName.API.getValue(), hostName.getValue());
    }
    HttpHeaders headers = new HttpHeaders();
    headers.addHeader(Constant.ACCEPT, MediaType.APPLICATION_JSON.getValue());
    headers.addHeader(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
    HttpRequest httpRequest =
        new HttpRequest.Builder()
            .httpMethod(HttpMethod.POST)
            .url(requestPath)
            .headers(headers)
            .body(createRequestBody(realRequest))
            .build();
    HttpResponse<PreOrderAuthenticationResponse> httpResponse =
        httpClient.execute(httpRequest, PreOrderAuthenticationResponse.class);
    return httpResponse.getServiceResponse();
  }
  /**
   * 微工卡核身预下单（流程中完成授权）
   *
   * @param request 请求参数
   * @return PreOrderAuthenticationWithAuthResponse
   * @throws HttpException 发送HTTP请求失败。例如构建请求参数失败、发送请求失败、I/O错误等。包含请求信息。
   * @throws ValidationException 发送HTTP请求成功，验证微信支付返回签名失败。
   * @throws ServiceException 发送HTTP请求成功，服务返回异常。例如返回状态码小于200或大于等于300。
   * @throws MalformedMessageException 服务返回成功，content-type不为application/json、解析返回体失败。
   */
  public PreOrderAuthenticationWithAuthResponse preOrderAuthenticationWithAuth(
      PreOrderAuthenticationWithAuthRequest request) {
    String requestPath =
        "https://api.mch.weixin.qq.com/v3/payroll-card/authentications/pre-order-with-auth";
    // 加密敏感信息
    PreOrderAuthenticationWithAuthRequest realRequest = request.cloneWithCipher(encryptor::encrypt);
    if (this.hostName != null) {
      requestPath = requestPath.replaceFirst(HostName.API.getValue(), hostName.getValue());
    }
    HttpHeaders headers = new HttpHeaders();
    headers.addHeader(Constant.ACCEPT, MediaType.APPLICATION_JSON.getValue());
    headers.addHeader(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
    headers.addHeader(Constant.WECHAT_PAY_SERIAL, encryptor.getWechatpaySerial());
    HttpRequest httpRequest =
        new HttpRequest.Builder()
            .httpMethod(HttpMethod.POST)
            .url(requestPath)
            .headers(headers)
            .body(createRequestBody(realRequest))
            .build();
    HttpResponse<PreOrderAuthenticationWithAuthResponse> httpResponse =
        httpClient.execute(httpRequest, PreOrderAuthenticationWithAuthResponse.class);
    return httpResponse.getServiceResponse();
  }
  /**
   * 查询微工卡授权关系
   *
   * @param request 请求参数
   * @return RelationEntity
   * @throws HttpException 发送HTTP请求失败。例如构建请求参数失败、发送请求失败、I/O错误等。包含请求信息。
   * @throws ValidationException 发送HTTP请求成功，验证微信支付返回签名失败。
   * @throws ServiceException 发送HTTP请求成功，服务返回异常。例如返回状态码小于200或大于等于300。
   * @throws MalformedMessageException 服务返回成功，content-type不为application/json、解析返回体失败。
   */
  public RelationEntity getRelation(GetRelationRequest request) {
    String requestPath = "https://api.mch.weixin.qq.com/v3/payroll-card/relations/{openid}";

    GetRelationRequest realRequest = request;
    // 添加 path param
    requestPath = requestPath.replace("{" + "openid" + "}", urlEncode(realRequest.getOpenid()));

    // 添加 query param
    QueryParameter queryParameter = new QueryParameter();
    if (realRequest.getSubMchid() != null) {
      queryParameter.add("sub_mchid", urlEncode(realRequest.getSubMchid()));
    }
    if (realRequest.getAppid() != null) {
      queryParameter.add("appid", urlEncode(realRequest.getAppid()));
    }
    if (realRequest.getSubAppid() != null) {
      queryParameter.add("sub_appid", urlEncode(realRequest.getSubAppid()));
    }
    requestPath += queryParameter.getQueryStr();
    if (this.hostName != null) {
      requestPath = requestPath.replaceFirst(HostName.API.getValue(), hostName.getValue());
    }
    HttpHeaders headers = new HttpHeaders();
    headers.addHeader(Constant.ACCEPT, MediaType.APPLICATION_JSON.getValue());
    headers.addHeader(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
    HttpRequest httpRequest =
        new HttpRequest.Builder()
            .httpMethod(HttpMethod.GET)
            .url(requestPath)
            .headers(headers)
            .build();
    HttpResponse<RelationEntity> httpResponse =
        httpClient.execute(httpRequest, RelationEntity.class);
    return httpResponse.getServiceResponse();
  }
  /**
   * 生成授权token
   *
   * @param request 请求参数
   * @return TokenEntity
   * @throws HttpException 发送HTTP请求失败。例如构建请求参数失败、发送请求失败、I/O错误等。包含请求信息。
   * @throws ValidationException 发送HTTP请求成功，验证微信支付返回签名失败。
   * @throws ServiceException 发送HTTP请求成功，服务返回异常。例如返回状态码小于200或大于等于300。
   * @throws MalformedMessageException 服务返回成功，content-type不为application/json、解析返回体失败。
   */
  public TokenEntity createToken(CreateTokenRequest request) {
    String requestPath = "https://api.mch.weixin.qq.com/v3/payroll-card/tokens";
    // 加密敏感信息
    CreateTokenRequest realRequest = request.cloneWithCipher(encryptor::encrypt);
    if (this.hostName != null) {
      requestPath = requestPath.replaceFirst(HostName.API.getValue(), hostName.getValue());
    }
    HttpHeaders headers = new HttpHeaders();
    headers.addHeader(Constant.ACCEPT, MediaType.APPLICATION_JSON.getValue());
    headers.addHeader(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
    headers.addHeader(Constant.WECHAT_PAY_SERIAL, encryptor.getWechatpaySerial());
    HttpRequest httpRequest =
        new HttpRequest.Builder()
            .httpMethod(HttpMethod.POST)
            .url(requestPath)
            .headers(headers)
            .body(createRequestBody(realRequest))
            .build();
    HttpResponse<TokenEntity> httpResponse = httpClient.execute(httpRequest, TokenEntity.class);
    return httpResponse.getServiceResponse();
  }
  /**
   * 发起批量转账
   *
   * @param request 请求参数
   * @return TransferBatchEntity
   * @throws HttpException 发送HTTP请求失败。例如构建请求参数失败、发送请求失败、I/O错误等。包含请求信息。
   * @throws ValidationException 发送HTTP请求成功，验证微信支付返回签名失败。
   * @throws ServiceException 发送HTTP请求成功，服务返回异常。例如返回状态码小于200或大于等于300。
   * @throws MalformedMessageException 服务返回成功，content-type不为application/json、解析返回体失败。
   */
  public TransferBatchEntity createTransferBatch(CreateTransferBatchRequest request) {
    String requestPath = "https://api.mch.weixin.qq.com/v3/payroll-card/transfer-batches";
    // 加密敏感信息
    CreateTransferBatchRequest realRequest = request.cloneWithCipher(encryptor::encrypt);
    if (this.hostName != null) {
      requestPath = requestPath.replaceFirst(HostName.API.getValue(), hostName.getValue());
    }
    HttpHeaders headers = new HttpHeaders();
    headers.addHeader(Constant.ACCEPT, MediaType.APPLICATION_JSON.getValue());
    headers.addHeader(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
    headers.addHeader(Constant.WECHAT_PAY_SERIAL, encryptor.getWechatpaySerial());
    HttpRequest httpRequest =
        new HttpRequest.Builder()
            .httpMethod(HttpMethod.POST)
            .url(requestPath)
            .headers(headers)
            .body(createRequestBody(realRequest))
            .build();
    HttpResponse<TransferBatchEntity> httpResponse =
        httpClient.execute(httpRequest, TransferBatchEntity.class);
    return httpResponse.getServiceResponse();
  }

  private RequestBody createRequestBody(Object request) {
    return new JsonRequestBody.Builder().body(toJson(request)).build();
  }
}
