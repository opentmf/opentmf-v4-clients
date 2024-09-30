# tmf-652-client library
A TMF Client for Resource Order Management.

## Exposed Client Providers
- ResourceOrderClientProvider
- CancelResourceOrderClientProvider

## Library Details

This library provides a client for tmf-652 Resource Inventory API REST end-point. The library client uses TmfForum tmf-652-model library. It supports extensions if mixedIn with ObjectMapper properly.

i.e assume AnyModelClass is an extension to resource order model.
If AnyModelClass is provided as ResourceOrder in ObjectMapper then, it will support deserialization to AnyModelClass.

```
getPrimaryObjectMapper().addMixIn(ResourceOrder.class, AnyModelClass.class);
```

The extended model definitions already provides their own extension overwrite methods.
`DnextTmfCommonJacksonConfig.registerExtensions` is a static method that is implemented in Dnext model library.

```
DnextTmfCommonJacksonConfig.registerExtensions(objectMapper);
```

This library

- exposes `tmfClientFactory` bean.
- uses `tmf-652-model` library.
- uses `sh-web-clients` library.

## Usage

In order to use this client library add it to pom.xml. And if you have any extensions i.e. Dnext models add them to
pom.xml and teach Jackson to consider the extended model classes:

### 1. Add Maven Dependency

```xml

<dependency>
    <groupId>com.pia.commons</groupId>
    <artifactId>tmf-652-client</artifactId>
    <version>${version}</version>
</dependency>
<dependency>
   <groupId>com.pia.commons</groupId>
   <artifactId>dep-tmf-652-model</artifactId>
   <version>${version}</version>
</dependency>
```

### 2. Add web client and tmf-client configuration

Please see [sh-web-clients](https://gitlabce.tools.aws.vodafone.com/iot-solutionhub/ninja/sh-web-clients) library for
configuration. This is just a sample and may not be accurate:

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


List the tmf 652 endpoints and the scope of each of the exposed endpoints.

```yml
solutions-hub:
  tmf-clients:
  sh-resource-ordering:
    paths:
      getResourceOrderById:
        path: /resourceOrder
        scope:
      getAllResourceOrders:
        path: /resourceOrder
        scope:
      createResourceOrder:
        path: /resourceOrder
        scope:
      patchResourceOrder:
        path: /resourceOrder
        scope:
      deleteResourceOrder:
        path: /resourceOrder
        scope:
  apix-resource-ordering:
    paths:
      getResourceOrderById:
        path: /resourceOrder
        scope: APIX_GET_PRODUCT_ORDER_BY_ID
```

In case the method name along with the path and the scope were listed in the configuration. The scope will be set to null and the following
defaults
paths will be used:

| Method Name            | Default endpoint    |
|------------------------|---------------------|
| `getAllResourceOrders` | /resourceOrder      |
| `getResourceOrderById` | /resourceOrder/{id} |
| `createResourceOrder`  | /resourceOrder      |
| `patchResourceOrder`   | /resourceOrder/{id} |
| `deleteResourceOrder`  | /resourceOrder/{id} |
| `registerListener`     | /hub                |
| `unRegisterListener`   | /hub/{id}           |


### 3. Get Tmf client

To get the tmfClient use the exposed **Bean** tmfClientFactory and call the getClient method.
The following are the method parameters:

| Parameter       | Usage                                                                                                                    |
|-----------------|--------------------------------------------------------------------------------------------------------------------------|
| TmfClientConfig | The tmf client configured above .i.e. **sh-resource-ordering**                                                           |
| WebClientType   | Choose an enum of the following: <br/> 1.WebClientType.**SH**<br/>2. WebClientType.**APIX** <br/>3.WebClientType.**DXL** |
| TmfClient       | Tmf client class type .i.e. **ResourceOrderClient.class**                                                                |

```java
     resourceOrderClient=
        tmfClientFactory.getTmfClient(
        tmfClientConfigurations.getTmfClients().get("sh-resource-ordering"),
        WebClientType.SH,
        ResourceOrderClient.class);
```

### 4. Configure the ObjectMapper to register extended objects

Within your ObjectMapper java config code, you can register the extended classes to Jackson so that ObjectMapper can
recognize them.

```java
@Bean
@Primary
public ObjectMapper objectMapper(){
        var objectMapper=getDefaultObjectMapper();
        DnextTmfCommonJacksonConfig.registerExtensions(objectMapper);
        return objectMapper;
    }
```

### Version History

- 1.0.0 1st release
