/**
 * A remedy for the headache of using SpringFox in JPMS-structured projects.
 *
 * Copyright (C) 2020 lingocoder <plugins@lingocoder.com>
 *
 * This work is licensed under the Creative Commons Attribution-NoDerivatives 4.0
 * International (CC BY-ND 4.0) License.
 *
 * This work is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Creative Commons Attribution-NoDerivatives 4.0 International (CC BY-ND 4.0)
 * License for more details.
 *
 * You should have received a copy of the Creative Commons
 * Attribution-NoDerivatives 4.0 International (CC BY-ND 4.0) License
 * along with this program. To view a copy of this license,
 * visit https://creativecommons.org/licenses/by-nd/4.0/.
 */
package com.lingocoder.springfox.api.doc;

import springfox.documentation.spring.web.plugins.Docket;
import static springfox.documentation.spi.DocumentationType.*;
import com.lingocoder.springfox.enums.DocketType;
import static com.lingocoder.springfox.enums.DocketType.*;

import java.util.Optional;
import static java.util.Optional.empty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>A factory for SpringFox <a href="https://springfox.github.io/springfox/javadoc/current/springfox/documentation/spring/web/plugins/Docket.html"><i><code>Docket</code></i>s</a>.</p>
 *
 * <p>{@code DocketTyper} implements a simple solution to the <a href="https://github.com/springfox/springfox"><i>SpringFox</i></a> RESTful API
 * documentation generation library's <a href="https://github.com/springfox/springfox/issues/2064">
 * <i>java 9 library compatibility issue #2064</i></a>.</p>
 * 
 * <p>This class is a remedy for the headache of using <a href="https://github.com/springfox/springfox"><i>SpringFox</i></a> in JPMS-structured projects.
 * It solves <a href="https://github.com/springfox/springfox"><i>SpringFox's</i></a> previously unsolvable â€ž<i><code>module com.example reads package
 * springfox.documentation.service from both springfox.spi and springfox.core</code></i>â€œ split
 * package problem.</p>
 *
 * <p>Use {@code DocketTyper} in your JDK 9<sup>+</sup> modularized projects when you 
 * need to include the SpringFox library to generate Swagger/OpenAPI documentation.</p>
 *
 * <p>For example:</p>
 * <pre>
 *   &commat;Bean
 *   public Docket customImplementation( ){
 *       return DocketTyper.of( OAS3 ).orElse( LATEST )
 *               .select( )
 *                   .apis( RequestHandlerSelectors.basePackage( "io.swagger.api" ) )
 *                   .build( )
 *               .directModelSubstitute( org.threeten.bp.LocalDate.class, java.sql.Date.class )
 *               .directModelSubstitute( org.threeten.bp.OffsetDateTime.class, java.util.Date.class )
 *               .apiInfo( apiInfo( ) );
 *   }
 * </pre>
 */
public class DocketTyper {

    private static final Logger LOG = LoggerFactory.getLogger( DocketTyper.class );
    
    /** 
     * A convenient reference to a pre-built <a href="https://springfox.github.io/springfox/javadoc/current/springfox/documentation/spring/web/plugins/Docket.html"><i><code>Docket</code></i></a>
     * that supports the current latest version of Swagger. If the actual latest can't be found
     * in the classpath, then this field will reference a reasonble default instead (<i>typically,
     * the version that immediately precedes the current latest</i>).
     */
	public static Docket LATEST =  new Docket( SWAGGER_2 );
		
	static { 
	    try{ LATEST = new Docket( OAS_30 ); }
		    catch( NoSuchFieldError nsfe ){
                LOG.warn( "The latest version of Swagger could not be found on the classpath. Using the default (version {})", SWAGGER_2.getVersion( ) );
		}
	}

    /**
     * No instances of this utility class are allowed to be created.
     */
    private DocketTyper( ){ }

    /**
     * <p>Constucts a <a href="https://springfox.github.io/springfox/javadoc/current/springfox/documentation/spring/web/plugins/Docket.html"><i><code>Docket</code></i></a>. â€ž<i>A builder which is intended to be the primary 
     * interface into the Springfox framework</i>.â€œ</p>
     *
     * @param whichType One of this library's supported {@link DocketType} enums that correspond to SpringFox's 
     * supported <a href="https://springfox.github.io/springfox/javadoc/current/springfox/documentation/spi/DocumentationType.html"><i><code>DocumentationType</code></i>s</a>
     * @return An {@link Optional} describing a <a href="https://springfox.github.io/springfox/javadoc/current/springfox/documentation/spring/web/plugins/Docket.html"><i><code>Docket</code></i></a>
     * that wraps the <a href="https://springfox.github.io/springfox/javadoc/current/springfox/documentation/spi/DocumentationType.html"><i><code>DocumentationType</code></i></a>
     * that corresponds to the given {@link DocketType}; never {@link Optional#empty}.
     */
    public static Optional< Docket > of( DocketType whichType ) {
		
		Optional< Docket > docket = empty( );
				 
	    switch( whichType ){ 
		    case SWAGGER1  :
			    docket = Optional.of( new Docket( SWAGGER_12 ) );
				break;
			case SWAGGER2 :
			    docket = Optional.of( new Docket( SWAGGER_2 ) );
				break;
			case OAS3 :						
			    docket = Optional.of( LATEST );
				break;
			case SPRINGWEB :						
			    docket = Optional.of( new Docket( SPRING_WEB ) );
				break;										
			case DEFAULT :
			    docket = Optional.of( LATEST );
		}
	    return docket;
	  }
}
