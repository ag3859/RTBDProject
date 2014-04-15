<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" />
	<xsl:template match="ListRecords/record">  
    	<xsl:for-each select="metadata/creator">    		
    		<xsl:value-of select="."></xsl:value-of>
    		<xsl:text>, </xsl:text>
    	</xsl:for-each>
    	<!--xsl:text>
    			Group
    	</xsl:text-->
	</xsl:template>
</xsl:stylesheet>