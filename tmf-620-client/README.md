# tmf-620-client library
A TMF Client for Product Catalog Management.

## Exposed Client Providers
- ProductSpecificationClientProvider
- ProductOfferingClientProvider
- ProductOfferingPriceClientProvider
- CategoryClientProvider
- CatalogClientProvider

## Library Details 

This library provides a client for Tmf-620 ProductCatalog Management REST end-point. The library client uses TmForum tmf620-model library. It supports extensions if mixedIn with ObjectMapper properly.

## Usage
In order to use this client library add it to pom.xml. And if you have any extensions i.e. Dnext models add them to pom.xml and teach Jackson to consider the extended model classes:

```xml
<dependency>
    <groupId>com.pia.commons</groupId>
    <artifactId>tmf-620-client</artifactId>
    <version>${tmf-620-client.version}</version>
</dependency>
```
