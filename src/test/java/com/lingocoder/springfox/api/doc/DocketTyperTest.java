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

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import static org.assertj.core.api.Assertions.*;
import org.springframework.plugin.metadata.SimplePluginMetadata ;
import static com.lingocoder.springfox.enums.DocketType.*;
import static springfox.documentation.spi.DocumentationType.*;
/* Class under test*/
import static com.lingocoder.springfox.api.doc.DocketTyper.*;

/**
 * Unit test for the {@link DockerTyper#of} static method.
 */
public class DocketTyperTest {
		
	private DocumentationType SF_SWAGGER2 = SWAGGER_2;
	private DocumentationType SF_SWAGGER1 = SWAGGER_12;
	private DocumentationType SF_OAS3 = OAS_30;
	private DocumentationType SF_SPRINGWEB = SPRING_WEB;
		
    @Test
    public void testOfVersion1( )
    {
		assertThat( of( SWAGGER1 ) ).hasValueSatisfying( dokt -> { assertThat( SF_SWAGGER1.getVersion( ) ).isEqualTo( dokt.getDocumentationType( ).getVersion( ) ); }  );
    }
		
	@Test
    public void testOfVersion2( )
    {
        assertThat( of( SWAGGER2 ) ).hasValueSatisfying( dokt -> { assertThat( SF_SWAGGER2.getVersion( ) ).isEqualTo( dokt.getDocumentationType( ).getVersion( ) ); }  );
    }
		
	@Test
    public void testOfVersion3( )
    {
        assertThat( of( OAS3 ) ).hasValueSatisfying( dokt -> { assertThat( SF_OAS3.getVersion( ) ).isEqualTo( dokt.getDocumentationType( ).getVersion( ) ); }  );
    }
		
	@Test
    public void testOfSpringWeb( )
    {
        assertThat( of( SPRINGWEB ) ).hasValueSatisfying( dokt -> { assertThat( SF_SPRINGWEB.getVersion( ) ).isEqualTo( dokt.getDocumentationType( ).getVersion( ) ); }  );
    }
		
	@Test
    public void testOfLatest( )
    {
        assertThat( of( DEFAULT ) ).hasValueSatisfying( dokt -> { assertThat( DocketTyper.LATEST.getDocumentationType( ).getVersion( ) ).isEqualTo( dokt.getDocumentationType( ).getVersion( ) ); }  );
    }
}