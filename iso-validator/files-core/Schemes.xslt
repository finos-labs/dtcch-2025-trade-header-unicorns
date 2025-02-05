<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:hand="urn:HandCoded:Releases" xmlns:fpml="urn:HandCoded:FpML-Releases">
	<xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes"/>
	
	<xsl:variable name="spec" select="//hand:specification[hand:name = 'FpML']"/>
	<xsl:variable name="uniq" select="$spec//fpml:schemeDefault[not(fpml:attribute = preceding::fpml:schemeDefault/fpml:attribute)]"/>
	
	<xsl:variable name="v10" select="$spec/hand:dtdRelease[hand:version='1-0']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v20" select="$spec/hand:dtdRelease[hand:version='2-0']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v30" select="$spec/hand:dtdRelease[hand:version='3-0']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v40" select="$spec/hand:schemaRelease[hand:version='4-0']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v41" select="$spec/hand:schemaRelease[hand:version='4-1']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v42" select="$spec/hand:schemaRelease[hand:version='4-2']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v43" select="$spec/hand:schemaRelease[hand:version='4-3']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v44" select="$spec/hand:schemaRelease[hand:version='4-4']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v45" select="$spec/hand:schemaRelease[hand:version='4-5']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v46" select="$spec/hand:schemaRelease[hand:version='4-6']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v47" select="$spec/hand:schemaRelease[hand:version='4-7']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v48" select="$spec/hand:schemaRelease[hand:version='4-8']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v49" select="$spec/hand:schemaRelease[hand:version='4-9']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v50" select="$spec/hand:schemaRelease[hand:version='5-0']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v51" select="$spec/hand:schemaRelease[hand:version='5-1']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v52" select="$spec/hand:schemaRelease[hand:version='5-2']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v53" select="$spec/hand:schemaRelease[hand:version='5-3']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v54" select="$spec/hand:schemaRelease[hand:version='5-4']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v55" select="$spec/hand:schemaRelease[hand:version='5-5']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>
	<xsl:variable name="v56" select="$spec/hand:schemaRelease[hand:version='5-6']/fpml:schemeDefault[not(fpml:attribute = preceding-sibling::fpml:schemeDefault/fpmlAttribute)]"/>

	<xsl:template match="/">
		<html>
			<head>
				<title>FpML Schemes</title>
			</head>
			<body>
				<table border="1">
					<tbody>
						<tr>
							<th>Attribute</th>
							<th>1-0</th>
							<th>2-0</th>
							<th>3-0</th>
							<th>4-0</th>
							<th>4-1</th>
							<th>4-2</th>
							<th>4-3</th>
							<th>4-4</th>
							<th>4-5</th>
							<th>4-6</th>
							<th>4-7</th>
							<th>4-8</th>
							<th>4-9</th>
							<th>5-0</th>
							<th>5-1</th>
							<th>5-2</th>
							<th>5-3</th>
							<th>5-4</th>
							<th>5-5</th>
							<th>5-6</th>
						</tr>
						<xsl:for-each select="$uniq">
							<xsl:sort select="fpml:attribute"/>
							<xsl:variable name="attr" select="fpml:attribute"/>
							<tr>
								<td><xsl:value-of select="fpml:attribute"/></td>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v10"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v20"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v30"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v40"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v41"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v42"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v43"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v44"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v45"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v46"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v47"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v48"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v49"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v50"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v51"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v52"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v53"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v54"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v55"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
								<xsl:call-template name="Check">
									<xsl:with-param name="data" select="$v56"/>
									<xsl:with-param name="attr" select="$attr"/>
								</xsl:call-template>
							</tr>
						</xsl:for-each>
					</tbody>
				</table>
			</body>
		</html>
	</xsl:template>

	<xsl:template name="Check">
		<xsl:param name="data"/>
		<xsl:param name="attr"/>
		<xsl:choose>
			<xsl:when test="$data[fpml:attribute = $attr]">
				<td>*</td>
			</xsl:when>
			<xsl:otherwise>
				<td>.</td>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
