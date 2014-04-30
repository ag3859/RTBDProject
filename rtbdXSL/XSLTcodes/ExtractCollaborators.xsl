<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes"/>

	<xsl:template match="dblp">
		<xsl:for-each select="article[contains(@key,'conf/')]">
			<xsl:copy-of select="."/>					
		</xsl:for-each>	
			<!--xsl:element name="www">
				<xsl:attribute name="mdate">
    				<xsl:value-of select="@mdate"></xsl:value-of>
    			</xsl:attribute>
				<xsl:attribute name="key">    	
    				<xsl:value-of select="@key"></xsl:value-of>
    			</xsl:attribute>
    			<xsl:copy-of select="/title"></xsl:copy-of>
			</xsl:element-->
		
	</xsl:template>
</xsl:stylesheet>