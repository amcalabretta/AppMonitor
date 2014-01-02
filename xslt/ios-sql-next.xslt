<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:param name="app_id"/>
	<xsl:template match="/">
			<xsl:variable name="version" select="comments/data/version" />
<xsl:for-each select="comments/data">
DELETE FROM IOS_RELEASES WHERE application_id = <xsl:value-of select="$app_id"/> and release = '<xsl:value-of select="version"/>';
INSERT INTO IOS_RELEASES(releasing_time,app_id,release) VALUES ('<xsl:value-of select="normalize-space(updated)"/>',<xsl:value-of select="$app_id"/>,'<xsl:value-of select="version"/>');
</xsl:for-each>
<xsl:for-each select="comments/page/pagetotal">
DELETE FROM COMMENTS_IOS_PAGES_LOG WHERE application_id = <xsl:value-of select="$app_id"/>;
INSERT INTO COMMENTS_IOS_PAGES_LOG(APPLICATION_ID,NUM_PAGES) VALUES (<xsl:value-of select="$app_id"/>,<xsl:value-of select="current()" />);
</xsl:for-each>
<xsl:for-each select="comments/ratings">
<xsl:if test="data-stars-5-release">
INSERT INTO IOS_RELEASE_RATINGS (timestamp,app_id,release,five_stars,four_stars,three_stars,two_stars,one_star) VALUES (<xsl:value-of select="$timestamp"/>,<xsl:value-of select="$app_id"/>,'<xsl:copy-of select="$version" />',<xsl:value-of select="data-stars-5-release"/>,<xsl:value-of select="data-stars-4-release"/>,<xsl:value-of select="data-stars-3-release"/>,<xsl:value-of select="data-stars-2-release"/>,<xsl:value-of select="data-stars-1-release"/>);
</xsl:if>
INSERT INTO IOS_TOTAL_RATINGS (timestamp,app_id,release,five_stars,four_stars,three_stars,two_stars,one_star) VALUES (<xsl:value-of select="$timestamp"/>,<xsl:value-of select="$app_id"/>,'<xsl:copy-of select="$version" />',<xsl:value-of select="data-stars-5-total"/>,<xsl:value-of select="data-stars-4-total"/>,<xsl:value-of select="data-stars-3-total"/>,<xsl:value-of select="data-stars-2-total"/>,<xsl:value-of select="data-stars-1-total"/>);
</xsl:for-each>
</xsl:template>
</xsl:stylesheet>