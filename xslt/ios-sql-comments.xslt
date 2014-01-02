<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:param name="APP_ID"/>
<xsl:param name="TIMESTAMP"/>
  <xsl:strip-space elements="*"/>
	<xsl:template match="/">
			<xsl:variable name="version" select="comments/data/version" />
			<xsl:for-each select="comments/data">
DELETE FROM IOS_RELEASES WHERE application_id = <xsl:value-of select="$APP_ID"/> and release = '<xsl:value-of select="version"/>';			
INSERT INTO IOS_RELEASES(releasing_time,app_id,release) VALUES ('<xsl:value-of select="updated"/>',<xsl:value-of select="$APP_ID"/>,'<xsl:value-of select="version"/>');
			</xsl:for-each>
			<xsl:for-each select="comments/page/pagetotal">
DELETE FROM COMMENTS_IOS_PAGES_LOG WHERE application_id = <xsl:value-of select="$APP_ID"/>;			
INSERT INTO COMMENTS_IOS_PAGES_LOG(APP_ID,NUMBER) VALUES (<xsl:value-of select="$APP_ID"/>,<xsl:value-of select="current()" />);
			</xsl:for-each>
			<xsl:for-each select="comments/ratings">
			<xsl:if test="data-stars-5-release">
INSERT INTO IOS_RELEASE_RATINGS (timestamp,app_id,release,five_stars,four_stars,three_stars,two_stars,one_star,total) VALUES (<xsl:value-of select="$TIMESTAMP"/>,<xsl:value-of select="$APP_ID"/>,'<xsl:copy-of select="$version" />',<xsl:value-of select="data-stars-5-release"/>,<xsl:value-of select="data-stars-4-release"/>,<xsl:value-of select="data-stars-3-release"/>,<xsl:value-of select="data-stars-2-release"/>,<xsl:value-of select="data-stars-1-release"/>,0);
			</xsl:if>
INSERT INTO IOS_TOTAL_RATINGS (timestamp,app_id,release,five_stars,four_stars,three_stars,two_stars,one_star,total) VALUES (<xsl:value-of select="$TIMESTAMP"/>,<xsl:value-of select="$APP_ID"/>,'<xsl:copy-of select="$version" />',<xsl:value-of select="data-stars-5-total"/>,<xsl:value-of select="data-stars-4-total"/>,<xsl:value-of select="data-stars-3-total"/>,<xsl:value-of select="data-stars-2-total"/>,<xsl:value-of select="data-stars-1-total"/>,0);
			</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>