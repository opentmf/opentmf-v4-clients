# tmf-622-client library
A TMF Client for Product Order Management.

## Exposed Client Providers
- ProductOrderClientProvider
- CancelProductOrderClientProvider

## Library Details 

This library provides a client for Tmf-622 ProductOrder Application Management REST end-point. The library client uses TmfForum tmf622-model library. It supports extensions if mixedIn with ObjectMapper properly.

i.e assume DnextProductOrder extension to ProductOrder.
If DnextProductOrder is provided as ProductOrder in ObjectMapper then, it will support DnextProductOrder properly.

```
getPrimaryObjectMapper().addMixIn(ProductOrder.class, DnextProductOrder.class);
```

The extended model definitions already provides their own extension overwrite methods. Thus instead of above line, we can simply use below code line: 

```
DnextTmf622JacksonConfig.registerExtensions(objectMapper);
```

This library
- exposes productOrderClient bean.
- uses tmf-622-model library.
- uses sh-web-client library.

## Usage
In order to use this client library add it to pom.xml. And if you have any extensions i.e. Dnext models add them to pom.xml and teach Jackson to consider the extended model classes:

### 1. Add Maven Dependency
```xml
<dependency>
    <groupId>com.pia.commons</groupId>
    <artifactId>tmf-622-client</artifactId>
    <version>${version}</version>
</dependency>
<dependency>
    <groupId>com.pia.commons</groupId>
    <artifactId>dnext-tmf-622-model</artifactId>
    <version>${version}</version>
</dependency>
```

### 2. Configure Product Order API server Url and sh-web-client configuration

- tmf-client service catalog url parameter

```yml
tmf-client:
  product-order-url: http://dummy-url.com
```

- sh-web-clients configuration

Please see sh-web-clients library for configuration. This is just a sample and may not be accurate:

```yml
solutions-hub:
  sh-client:
    fixed-headers:
      application: tmf641-soa
    token-config:
      base-url: https://oauth.vodafone.com
      token-path: /sh/auth2/token
      client-id: myClientId
      client-secret: mySecret
      username: myUsername
      password: myPassword
      grant-type: password
      scope: openid
```

### 2. Configure the ObjectMapper to register extended objects
Within your ObjectMapper java config code, you need to register the extended classes to Jackson so that ObjectMapper can recognize them.


```java
@Bean
@Primary
public ObjectMapper objectMapper() {
  var objectMapper = getDefaultObjectMapper();
  DnextTmf622JacksonConfig.registerExtensions(objectMapper);
  return objectMapper;
}
```

### 3. Cast response to extended objects if necessary

As the client library uses tmf622-model, to access extended object specific parameters, cast the return object to the extend object.

```java
...
var productOrderCreate = new DnextProductOrderCreate();
var response = this.productOrderClient
    .createProductOrder(productOrderCreate)
    .map(productOrder -> (DnextProductOrder) productOrder);
response.getOrderCharacteristics();
...
```

### Version History

- 1.0.0 1st release
