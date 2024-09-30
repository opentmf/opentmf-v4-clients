package com.pia.tmf.v4.tmf666.config;

import com.pia.client.common.service.api.TokenService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Gokhan Demir
 */
@AutoConfiguration(after = TokenService.class)
public class Tmf666ClientAutoConfiguration {

  @Bean
  public BillFormatClientProvider billFormatClientProvider(ApplicationContext ctx) {
    return new BillFormatClientProvider(ctx);
  }

  @Bean
  public BillingAccountClientProvider billAccountClientProvider(ApplicationContext ctx) {
    return new BillingAccountClientProvider(ctx);
  }

  @Bean
  public BillingCycleSpecificationClientProvider billCycleSpecificationClientProvider(ApplicationContext ctx) {
    return new BillingCycleSpecificationClientProvider(ctx);
  }

  @Bean
  public BillPresentationMediaClientProvider billPresentationMediaClientProvider(ApplicationContext ctx) {
    return new BillPresentationMediaClientProvider(ctx);
  }

  @Bean
  public FinancialAccountClientProvider financialAccountClientProvider(ApplicationContext ctx) {
    return new FinancialAccountClientProvider(ctx);
  }

  @Bean
  public PartyAccountClientProvider partyAccountClientProvider(ApplicationContext ctx) {
    return new PartyAccountClientProvider(ctx);
  }

  @Bean
  public SettlementAccountClientProvider settlementAccountClientProvider(ApplicationContext ctx) {
    return new SettlementAccountClientProvider(ctx);
  }
}
