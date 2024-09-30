# tmf-638-client library
A TMF Client for Service Inventory backend services.

## Exposed Client Providers
- ServiceClientProvider

## Library Details 

This library provides a client for Tmf-638 Service Inventory API REST end-point. The library client uses TmfForum tmf638-model library. It supports extensions if mixedIn with ObjectMapper properly.

i.e assume DnextService extension to Service model.
If DnextService is provided as Service in ObjectMapper then, it will support DnextService properly.

```
getPrimaryObjectMapper().addMixIn(Service.class, DnextService.class);
```

The extended model definitions already provides their own extension overwrite methods. Thus instead of above line, we can simply use below code line: 

```
DnextTmf638JacksonConfig.registerExtensions(objectMapper);
```

This library
- exposes serviceInventoryClient bean.
- uses tmf-638-model library.
- uses sh-web-client library.

## Usage
In order to use this client library add it to pom.xml. And if you have any extensions i.e. Dnext models add them to pom.xml and teach Jackson to consider the extended model classes:

### 1. Add Maven Dependency
```xml
<dependency>
    <groupId>com.pia.commons</groupId>
    <artifactId>tmf-638-client</artifactId>
    <version>${version}</version>
</dependency>
<dependency>
    <groupId>com.pia.commons</groupId>
    <artifactId>dnext-tmf-638-model</artifactId>
    <version>${version}</version>
</dependency>
```
### 2. Configure Service Inventory API server Url and sh-web-client configuration

- tmf-client service inventory url parameter

```yml
tmf-client:
  service-inventory-url: http://dummy-url.com
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

### 3. Configure the ObjectMapper to register extended objects
Within your ObjectMapper java config code, you need to register the extended classes to Jackson so that ObjectMapper can recognize them.


```java
@Bean
@Primary
public ObjectMapper objectMapper() {
  var objectMapper = getDefaultObjectMapper();
  DnextTmf638JacksonConfig.registerExtensions(objectMapper);
  return objectMapper;
}
```

### 4. Cast response to extended objects if necessary

As the client library uses tmf638-model, to access extended object specific parameters, cast the return object to the extend object.

```java
...
var serviceCreate = new DnextServiceCreate();
var response = this.productOrderClient
    .createServiceInventory(serviceCreate)
    .map(service -> (DnextService) service);
response.getCreatedOn();
...
```

### Version History

- 1.0.0 1st release
