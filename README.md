# tmf-v4-clients
TMF-630 compliant clients for TMF v4 backends.

This library is intended to provide clients for TMF v4 models.

For the provided TmfClient methods and behaviour, please see [tmf-clients-base](https://github.com/pia-commons/tmf-clients-base) project and its [README.md](https://github.com/pia-commons/tmf-clients-base/blob/develop/README.md) file. 

## Exposed Client Providers

In addition to the GenericClientProvider (which uses String as Create, Update and Result objects), the following providers are exposed as beans:

| TMF | Description        | Endpoint                   | Provider                                |
|-----|:-------------------|:---------------------------|:----------------------------------------|
| ALL |                    | /hub                       | HubClientProvider                       |
| ALL | All TMF APIs       | Configured Endpoints       | GenericClientProvider                   |
| 620 | Product Catalog    | /category                  | CategoryClientProvider                  |
| 620 | Product Catalog    | /catalog                   | CatalogClientProvider                   |
| 620 | Product Catalog    | /productSpecification      | ProductSpecificationClientProvider      |
| 620 | Product Catalog    | /productOffering           | ProductOfferingClientProvider           |
| 620 | Product Catalog    | /productOfferingPrice      | ProductOfferingPriceClientProvider      |
| 622 | Product Ordering   | /productOrder              | ProductOrderClientProvider              |
| 622 | Product Ordering   | /cancelProductOrder        | CancelProductOrderClientProvider        |
| 629 | Customer           | /customer                  | CustomerClientProvider                  |
| 632 | Party              | /individual                | IndividualClientProvider                |
| 632 | Party              | /organization              | OrganizationClientProvider              |
| 633 | Service Catalog    | /serviceCategory           | ServiceCategoryClientProvider           |
| 633 | Service Catalog    | /serviceCandidate          | ServiceCandidateClientProvider          |
| 633 | Service Catalog    | /serviceCatalog            | ServiceCatalogClientProvider            |
| 633 | Service Catalog    | /serviceSpecification      | ServiceSpecificationClientProvider      |
| 634 | Resource Catalog   | /resourceCategory          | ResourceCategoryClientProvider          |
| 634 | Resource Catalog   | /resourceCandidate         | ResourceCandidateClientProvider         |
| 634 | Resource Catalog   | /resourceCatalog           | ResourceCatalogClientProvider           |
| 634 | Resource Catalog   | /resourceSpecification     | ResourceSpecificationClientProvider     |
| 637 | Product Inventory  | /product                   | ProductClientProvider                   |
| 638 | Service Inventory  | /service                   | ServiceClientProvider                   |
| 639 | Resource Inventory | /resource                  | ResourceClientProvider                  |
| 641 | Service Ordering   | /serviceOrder              | ServiceOrderClientProvider              |
| 641 | Service Ordering   | /cancelServiceOrder        | CancelServiceOrderClientProvider        |
| 648 | Quote Management   | /quote                     | QuoteClientProvider                     |
| 652 | Resource Ordering  | /resourceOrder             | ResourceOrderClientProvider             |
| 652 | Resource Ordering  | /cancelResourceOrder       | CancelResourceOrderClientProvider       |
| 663 | Shopping Cart      | /shoppingCart              | ShoppingCartClientProvider              |
| 666 | Account            | /partyAccount              | PartyAccountClientProvider              |
| 666 | Account            | /billingAccount            | BillingAccountClientProvider            |
| 666 | Account            | /settlementAccount         | SettlementAccountClientProvider         |
| 666 | Account            | /financialAccount          | FinancialAccountClientProvider          |
| 666 | Account            | /billingCycleSpecification | BillingCycleSpecificationClientProvider |
| 666 | Account            | /billFormat                | BillFormatClientProvider                |
| 666 | Account            | /billPresentationMedia     | BillPresentationMediaClientProvider     |
| 669 | Party Role         | /partyRole                 | PartyRoleClientProvider                 |


## Usage
Let's imagine a scenario, where a microservice needs to communicate with three different TMF-622 Product Ordering Management backends, from over APIX, DXL and SH at the same time.

### Specify Dependencies
```xml
<project>

  <dependencyManagement>
    <dependency>
      <groupId>com.pia.commons</groupId>
      <artifactId>pia-commons-versions</artifactId>
      <version>RELEASE</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencyManagement>

  <!-- This automatically picks up tmf-622-model -->
  <dependency>
    <groupId>com.pia.commons</groupId>
    <artifactId>tmf-622-v4-client</artifactId>
  </dependency>

  <!-- Also be able to use DNext extensions to the ProductOrderManagement -->
  <dependency>
    <groupId>com.pia.commons</groupId>
    <artifactId>dnext-tmf-622-v4-model</artifactId>
  </dependency>
  
  <!-- Note: Using at least one PiA web client provider is mandatory. -->
  <!-- We will use openid provider in this example -->
  <dependency>
    <groupId>com.pia.commons</groupId>
    <artifactId>pia-openid-webclient-provider</artifactId>
  </dependency>

</project>
```
### Configuration Properties

#### application.yaml
```yaml
pia:
  webclient:
    openid:
      dnext:
        connection-provider-name: dnext-provider
        max-connections: 100
        request-timeout-millis: 50_000
        response-timeout-millis: 50_000
        num-retries: 3
        retry-wait-millis: 5_000
        fixed-headers:
          Accept: application/json
          AnotherHeader: AnotherValue
        token-config:
          token-url: http://localhost:1080/token
          basic-auth-username: user
          basic-auth-password: pass
          cache-name: dnext-token-cache
          cache-expiry-seconds: 3600
          token-field: access_token
          form-data:
            username: user
            password: pass
            scope: openid
            grant_type: password
      other:
        connection-provider-name: other-provider
        max-connections: 100
        request-timeout-millis: 50_000
        response-timeout-millis: 50_000
        num-retries: 3
        retry-wait-millis: 5_000
        fixed-headers:
          Accept: application/json
          caller: myApplicationName
        proxy-config:
          proxy-host: http://localhost
          proxy-port: 1234
          non-proxy-hosts:
            - mockserver
            - camunda7
        token-config:
          token-url: http://other-keycloak:8080/token
          cache-name: other-token-cache
          cache-expiry-seconds: 1800
          token-field: access_token
          form-data:
            client_id: client_id
            client_secret: client_secret
            scope: openid
            grant_type: client_credentials

  tmf-clients:

    dnext-tmf622:
      base-url: http://dpom-api-svc
      context-path: /tmf-api/productOrderingManagement/v4
      endpoint: /productOrder
      fixed-headers:
        application: MyMicroService

    other-tmf622:
      base-url: http://other-product-ordering-backend
      context-path: /tmf-api/productOrderingManagement/v4
      endpoint: /productOrder
      scopes:
        get: OTHER_GET_PRODUCT_ORDER_BY_ID
        list: OTHER_GET_PRODUCT_ORDER_LIST
        post: OTHER_POST_PRODUCT_ORDER
        patch: OTHER_PATCH_PRODUCT_ORDER
        delete: OTHER_DELETE_PRODUCT_ORDER_BY_ID
```
For more detailed configuration options, please consult the documentation on the pia-web-clients library README document.

### WebClient, TokenService and ClientProperties Bean Configurations
The PiA TMF v4 Clients Library requires that for each configured connection provider, 3 beans to be exposed prefixed by the connection id

For example, for a configured connection named dnext, there should be the ffollowing three beans exposed by the caller application:

- dnextWebClient
- dnextTokenService
- dnextClientConfiguration

Here is how to expose the required beans:

```java
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(OpenidClients.class)
public class OpenidAuthClientsConfig {

  private final OpenidWebClientProvider openidWebClientProvider;
  private final OpenidClients openidClients;

  @Bean
  public OpenidClientProperties dnextClientProperties() {
    return openidClients.getOpenid().get("dnext");
  }

  @Bean
  public WebClient dnextWebClient(
      @Qualifier("dnextClientProperties") OpenidClientProperties dnextClientProperties) {
    return openidWebClientProvider.buildWebClient(dnextClientProperties);
  }

  @Bean
  public OpenidTokenService dnextTokenService(
      @Qualifier("dnextClientProperties") OpenidClientProperties dnextClientProperties) {
    return openidWebClientProvider.buildTokenService(dnextClientProperties);
  }

  @Bean
  public OpenidClientProperties otherClientProperties() {
    return openidClients.getOpenid().get("other");
  }

  @Bean
  public WebClient otherWebClient(
      @Qualifier("otherClientProperties") OpenidClientProperties otherClientProperties) {
    return openidWebClientProvider.buildWebClient(otherClientProperties);
  }

  @Bean
  public OpenidTokenService otherTokenService(
      @Qualifier("otherClientProperties") OpenidClientProperties otherClientProperties) {
    return openidWebClientProvider.buildTokenService(otherClientProperties);
  }
}
```
### TMF v4 Client Bean Configuration
TMF Client library provides a ClientProvider bean per endpoint to construct the implementation dynamically using the preferred web client.

In our microservice, we need to expose a customized ObjectMapper and the requested TMF client implementations as beans in a configuration class like this:

```java

@Configuration
@RequiredArgsConstructor
public class TmfClientConfig {

  private final TmfClientConfigurations tmfClientConfigurations;

  @Bean
  @Primary
  public ObjectMapper objectMapper() {
    var objectMapper = JacksonUtil.getDefaultObjectMapper();
    DnextTmf622JacksonConfig.registerExtensions(objectMapper);
    return objectMapper;
  }

  @Bean
  public ProductOrderClient dnextProductOrderClient(ProductOrderClientProvider productOrderClientProvider) {
    return productOrderClientProvider.getTmfClient(
        tmfClientConfigurations.getTmfClients().get("dnext-tmf622"),
        "dnext");
  }

  @Bean
  public ProductOrderClient otherProductOrderClient(ProductOrderClientProvider productOrderClientProvider) {
    return productOrderClientProvider.getTmfClient(
        tmfClientConfigurations.getTmfClients().get("other-tmf622"),
        "other");
  }
}
```

Then in our service implementation, we can use any ProductOrderClient bean that we exposed in the previous step. Example:

```java
@Service
@RequiredArgsConstructor
public class SomeServiceImpl implements SomeService {
  
  private final ProductOrderClient dnextProductOrderClient;
  private final ProductOrderClient otherProductOrderClient;
  
  // ...
}
```
Voila! Simple! And we have a dozen of methods to communicate with any TMF backend in a TMF-630 compliant fashion.

## Version History
### 1.0.0
- Initial release
### 1.0.1
- Adds GenericClient
### 1.0.2
- renames modules by appending v4
- starts using the separate tmf-clients-base project
- simplifies test dependencies
### 1.0.3
- updates tmf-clients-base to 1.0.1
- updates pia-web-clients to 1.0.4
