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
package com.lingocoder.springfox.enums; 

/**
 * Supported enums that correspond to SpringFox's supported <a href="https://springfox.github.io/springfox/javadoc/current/springfox/documentation/spi/DocumentationType.html"><i><code>DocumentationType</code></i></a>s.
 */
public enum DocketType{ 

    /** Corresponds to <a href="https://springfox.github.io/springfox/javadoc/current/springfox/documentation/spi/DocumentationType.html#SWAGGER_12"><i><code>DocumentationType.SWAGGER_12</code></i></a>. */
    SWAGGER1( "1.2" ),

    /** Corresponds to <a href="https://springfox.github.io/springfox/javadoc/current/springfox/documentation/spi/DocumentationType.html#SWAGGER_2"><i><code>DocumentationType.SWAGGER_2</code></i></a>. */
    SWAGGER2( "2.0" ),

    /** Corresponds to <a href="https://springfox.github.io/springfox/javadoc/current/springfox/documentation/spi/DocumentationType.html#OAS_30"><i><code>DocumentationType.OAS_30</code></i></a>. */
    OAS3("3.0"),

    /** Corresponds to <a href="https://springfox.github.io/springfox/javadoc/current/springfox/documentation/spi/DocumentationType.html#SPRING_WEB"><i><code>DocumentationType.SPRING_WEB</code></i></a>. */
    @Deprecated SPRINGWEB( "5.2"),

    /** Corresponds to <a href="https://springfox.github.io/springfox/javadoc/current/springfox/documentation/spi/DocumentationType.html#OAS_30"><i><code>DocumentationType.OAS_30</code></i></a>. */
    DEFAULT( "3.0" );
		
	private String version;
		
	DocketType( String version ){ 
        this.version = version;
	}	
}

