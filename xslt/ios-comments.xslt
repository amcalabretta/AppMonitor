<?xml version="1.0" encoding="UTF-8"?>
<!-- XSLT TRANSFORMATION FOR IOS COMMENTS -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" indent="yes"/>
<xsl:variable name="release-hour" select='" 10:00:00"' /> 
<xsl:template match="/">
	<comments>
		<data>
			<xsl:for-each select="Document/View/ScrollView/VBoxView/View/MatrixView/VBoxView/HBoxView/VBoxView/VBoxView/MatrixView/VBoxView/TextView/SetFontStyle">
				
				<xsl:if test="position() = 3">
						<!-- yyyy-MM-dd HH:mm:ss -->
						<updated>
							<xsl:variable name="month" select="normalize-space( substring-before(normalize-space(substring-before(substring-after(current(),'Updated'),',') ),' '))" />
							<xsl:variable name="year" select=" normalize-space(   substring-after(current(),',')) " />
							<xsl:variable name="day" select="  substring-after( substring-before(  normalize-space( substring-after(current(),'Updated') ),','), ' ')" />
							<xsl:if test="$month = 'Dec'">
								<xsl:value-of select="$year"/>-12-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
							<xsl:if test="$month = 'Nov'">
								<xsl:value-of select="$year"/>-11-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
							<xsl:if test="$month = 'Oct'">
								<xsl:value-of select="$year"/>-10-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
							<xsl:if test="$month = 'Sep'">
								<xsl:value-of select="$year"/>-09-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
							<xsl:if test="$month = 'Aug'">
								<xsl:value-of select="$year"/>-08-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
							<xsl:if test="$month = 'Jul'">
								<xsl:value-of select="$year"/>-07-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
							<xsl:if test="$month = 'Jun'">
								<xsl:value-of select="$year"/>-06-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
							<xsl:if test="$month = 'May'">
								<xsl:value-of select="$year"/>-05-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
							<xsl:if test="$month = 'Apr'">
								<xsl:value-of select="$year"/>-04-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
							<xsl:if test="$month = 'Mar'">
								<xsl:value-of select="$year"/>-03-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
							<xsl:if test="$month = 'Feb'">
								<xsl:value-of select="$year"/>-02-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
							<xsl:if test="$month = 'Jan'">
								<xsl:value-of select="$year"/>-01-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
					  	</updated>
				</xsl:if>
				<xsl:if test="position() = 4">
						<version>
		    				<xsl:value-of select="normalize-space(substring-after(current(),':'))"/>
					  	</version>
					</xsl:if>
			</xsl:for-each>
		</data>
		
	
		<ratings>
			<xsl:for-each select="Document/View/ScrollView/VBoxView/View/MatrixView/VBoxView/HBoxView/VBoxView/VBoxView/View/View/View/VBoxView/Test/VBoxView/MatrixView/VBoxView/HBoxView">
				<xsl:if test="position() &lt; 6">
					<xsl:if test="position() = 1">
						<data-stars-5-total>
		    				<xsl:value-of select="normalize-space(substring-before(substring-after(attribute::alt,','),'rating'))" />
					  	</data-stars-5-total>
					</xsl:if>
					<xsl:if test="position() = 2">
						<data-stars-4-total>
		    				<xsl:value-of select="normalize-space(substring-before(substring-after(attribute::alt,','),'rating'))" />
					  	</data-stars-4-total>
					</xsl:if>
					<xsl:if test="position() = 3">
						<data-stars-3-total>
		    				<xsl:value-of select="normalize-space(substring-before(substring-after(attribute::alt,','),'rating'))" />
					  	</data-stars-3-total>
					</xsl:if>
					<xsl:if test="position() = 4">
						<data-stars-2-total>
		    				<xsl:value-of select="normalize-space(substring-before(substring-after(attribute::alt,','),'rating'))" />
					  	</data-stars-2-total>
					</xsl:if>
					<xsl:if test="position() = 5">
						<data-stars-1-total>
		    				<xsl:value-of select="normalize-space(substring-before(substring-after(attribute::alt,','),'rating'))" />
					  	</data-stars-1-total>
					</xsl:if>
				</xsl:if>	
				<xsl:if test="position() = 6">
					<data-stars-5-release>
	    				<xsl:value-of select="normalize-space(substring-before(substring-after(attribute::alt,','),'rating'))" />
				  	</data-stars-5-release>
				</xsl:if>
				<xsl:if test="position() = 7">
					<data-stars-4-release>
	    				<xsl:value-of select="normalize-space(substring-before(substring-after(attribute::alt,','),'rating'))" />
				  	</data-stars-4-release>
				</xsl:if>
				<xsl:if test="position() = 8">
					<data-stars-3-release>
	    				<xsl:value-of select="normalize-space(substring-before(substring-after(attribute::alt,','),'rating'))" />
				  	</data-stars-3-release>
				</xsl:if>
				<xsl:if test="position() = 9">
					<data-stars-2-release>
	    				<xsl:value-of select="normalize-space(substring-before(substring-after(attribute::alt,','),'rating'))" />
				  	</data-stars-2-release>
				</xsl:if>
				<xsl:if test="position() = 10">
					<data-stars-1-release>
	    				<xsl:value-of select="normalize-space(substring-before(substring-after(attribute::alt,','),'rating'))" />
				  	</data-stars-1-release>
				</xsl:if>
			</xsl:for-each>
		</ratings>
		<xsl:for-each select="Document/View/ScrollView/VBoxView/View/MatrixView/VBoxView/VBoxView/HBoxView/TextView">
					<xsl:choose>
      					<xsl:when test="contains(current(), 'Page')">
				    		<page>
								<pagenum><xsl:value-of select="normalize-space(substring-after(substring-before(current(),'of'), 'Page'))"/></pagenum>
								<pagetotal><xsl:value-of select="normalize-space(substring-after(current(),'of'))"/></pagetotal>				    			
							</page>    
      					</xsl:when>
    				</xsl:choose>
		</xsl:for-each>		
		<xsl:for-each select="Document/View/ScrollView/VBoxView/View/MatrixView/VBoxView/VBoxView/VBoxView">
			<comment>
				<!-- Comment headers and user data (plus version and date) -->
				<xsl:for-each select="HBoxView/TextView">
					<xsl:choose>
      					<xsl:when test="contains(current(), 'by')">
				    		<username>	
								<xsl:value-of select="normalize-space(substring-after(substring-before(current(),'-'), 'by'))"/>
							</username>
							<version>
								<xsl:value-of select="normalize-space(substring-before(substring-after(current(),'Version'), '-'))"/>
							</version>
							<date>
								<xsl:variable name="year" select="normalize-space(substring-after(normalize-space(substring-after(substring-after(current(),'-'), '-')),','))" />
								<xsl:variable name="day" select="normalize-space(   substring-before(    substring-after(  normalize-space(substring-after(substring-after(current(),'-'), '-')), ' ')  ,','))    " />
								<xsl:variable name="month" select=" substring-before(  normalize-space(substring-after(substring-after(current(),'-'), '-')), ' ')   " />
								<xsl:if test="$month = 'Dec'">
									<xsl:value-of select="$year"/>-12-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
								<xsl:if test="$month = 'Nov'">
									<xsl:value-of select="$year"/>-11-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
								<xsl:if test="$month = 'Oct'">
									<xsl:value-of select="$year"/>-10-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
								<xsl:if test="$month = 'Sep'">
									<xsl:value-of select="$year"/>-09-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
								<xsl:if test="$month = 'Aug'">
									<xsl:value-of select="$year"/>-08-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
								<xsl:if test="$month = 'Jul'">
									<xsl:value-of select="$year"/>-07-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
								<xsl:if test="$month = 'Jun'">
									<xsl:value-of select="$year"/>-06-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
								<xsl:if test="$month = 'May'">
									<xsl:value-of select="$year"/>-05-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
								<xsl:if test="$month = 'Apr'">
									<xsl:value-of select="$year"/>-04-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
								<xsl:if test="$month = 'Mar'">
									<xsl:value-of select="$year"/>-03-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
								<xsl:if test="$month = 'Feb'">
									<xsl:value-of select="$year"/>-02-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>
								<xsl:if test="$month = 'Jan'">
									<xsl:value-of select="$year"/>-01-<xsl:value-of select="$day"/><xsl:value-of select="$release-hour" /></xsl:if>								
							</date>
							<xsl:for-each select="SetFontStyle/GotoURL">
								<userid>
									<xsl:value-of select="substring-after(attribute::url, '=')" />
								</userid>
							</xsl:for-each>
      					</xsl:when>
      					<xsl:when test="not(contains(current(), 'by'))">
				    		<commentheader>	
								<xsl:value-of select="normalize-space(current())"/>
							</commentheader>    
				        </xsl:when>
    				</xsl:choose>
				</xsl:for-each>
				<xsl:for-each select="HBoxView/HBoxView/HBoxView">
					<xsl:choose>
      					<xsl:when test="contains(attribute::alt, 'star')">
							<stars>	
								<xsl:value-of select="normalize-space(substring-before(attribute::alt, 'star'))" />
							</stars>
						</xsl:when>
					</xsl:choose>	
				</xsl:for-each>
				<xsl:for-each select="TextView">
					<text><xsl:value-of select="normalize-space(current())"/></text>
				</xsl:for-each>
    		</comment>	
    	</xsl:for-each>
	</comments>
</xsl:template>
</xsl:stylesheet>