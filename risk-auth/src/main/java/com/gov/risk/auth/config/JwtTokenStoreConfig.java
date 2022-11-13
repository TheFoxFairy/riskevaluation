//package com.gov.risk.auth.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//import javax.sql.DataSource;
//import java.util.Collections;
//
//@Configuration
//public class JwtTokenStoreConfig {
//    /**
//     *  秘钥字符串
//     */
//    private static final String SIGNING_KEY = "";
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }
//
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(SIGNING_KEY);
//        return converter;
//    }
//
//    /**
//     *  配置令牌管理
//     */
//    @Bean
//    public AuthorizationServerTokenServices tokenServices(
//            ClientDetailsService clientDetailsService,
//            TokenStore tokenStore,
//            JwtAccessTokenConverter accessTokenConverter){
//        DefaultTokenServices services = new DefaultTokenServices();
//        services.setClientDetailsService(clientDetailsService);
//        services.setSupportRefreshToken(true);
//        services.setTokenStore(tokenStore);
//
//        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(accessTokenConverter));
//        services.setTokenEnhancer(tokenEnhancerChain);
//        return services;
//    }
//
//    /**
//     * 授权码存储方式
//     */
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource){
//        return new JdbcAuthorizationCodeServices(dataSource);
//    }
//}
