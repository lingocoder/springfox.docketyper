# springfox.docketyper

### A remedy for the headache of using SpringFox in JPMS-structured projects
This project implements a simple solution to the [*SpringFox*](https://github.com/springfox/springfox) RESTful API documentation generation library's [*java 9 library compatibility issue #2064*](https://github.com/springfox/springfox/issues/2064).

It solves [*SpringFox's*](https://github.com/springfox/springfox) previously unsolvable „*`module com.example reads package springfox.documentation.service from both springfox.spi and springfox.core`*“ split package problem.

Use this library in your JDK 9<sup>+</sup> modularized projects when you need to include the SpringFox library to generate Swagger/OpenAPI documentation.

#### Example usage

```
   @Bean
   public Docket customImplementation( ){
       return DocketTyper.of( OAS3 ).orElse( LATEST )
               .select( )
                   .apis( RequestHandlerSelectors.basePackage( "io.swagger.api" ) )
                   .build( )
               .directModelSubstitute( org.threeten.bp.LocalDate.class, java.sql.Date.class )
               .directModelSubstitute( org.threeten.bp.OffsetDateTime.class, java.util.Date.class )
               .apiInfo( apiInfo( ) );
   }
```