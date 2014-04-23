<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   <xsl:output omit-xml-declaration="yes" indent="yes"/>
   <xsl:strip-space elements="*"/>

   <xsl:template match="authors">
   <xsl:for-each select="author">
   <xsl:element name="author">
   	<xsl:attribute name="id">
   	<xsl:value-of select="@id"></xsl:value-of>
   	</xsl:attribute>
   </xsl:element>         
   </xsl:for-each>
   </xsl:template>

<xsl:template match="citations">
   <xsl:for-each select="citation">
   <xsl:element name="citation">
   	<xsl:attribute name="id">
   	<xsl:value-of select="@id"></xsl:value-of>
   	</xsl:attribute>
   	<xsl:attribute name="paperid">
   	<xsl:value-of select="./paperid">
   	</xsl:value-of>
   	</xsl:attribute>
   </xsl:element>
   
   </xsl:for-each>
   </xsl:template>

   <xsl:template match="document">   
   <xsl:element name="document">
   	<xsl:attribute name="id">
   	<xsl:value-of select="@id"></xsl:value-of>   
   </xsl:attribute>
   </xsl:element>      
   <xsl:apply-templates select="authors"></xsl:apply-templates>   
   <xsl:apply-templates select="citations"></xsl:apply-templates>
   </xsl:template>
   
   
   <xsl:template match="@*|node()">
            <xsl:copy>
                <xsl:apply-templates select="@*|node()"/>
            </xsl:copy>
        </xsl:template>      
</xsl:stylesheet>