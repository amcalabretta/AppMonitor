#!/bin/bash
echo " ************* Downloading IOS comments as for $DATE ************* "
echo "Loading applications from database $DATABASE_FILE"
if [ "${use_proxy}" == 'no' ]; then
    echo " - Not using any proxy"
fi
if [ "${use_proxy}" == 'yes' ]; then
    echo " - Using proxy ${proxy_ip}:${proxy_port}"
fi
$DATABASE_BIN $DATABASE_FILE "SELECT * FROM IOS_APPLICATIONS" | while read ROW; do\
	ID=$(echo "$ROW" |cut -d'|' -f 1)
	APPID=$(echo "$ROW" |cut -d'|' -f 2)
	APPNAME=$(echo "$ROW" |cut -d'|' -f 3)
	DESCRIPTION=$(echo "$ROW" |cut -d'|' -f 4)
	echo "Application: ${DESCRIPTION}, APPLE ID:${APPID}"
	echo " - Downloading raw data in $IOS_DIR/ios-${APPID}-${DATE}-unformatted.xml"
	if [ "${use_proxy}" == 'no' ]; then
        curl --silent -A ${APPSTORE_HEADER} -H ${APPSTORE_MARKET} 'https://itunes.apple.com/WebObjects/MZStore.woa/wa/viewContentsUserReviews?id='${APPID}'&pageNumber=0&sortOrdering=1&type=Purple+Software' >> $IOS_DIR/ios-${APPID}-${DATE}-unformatted.xml
	fi
	if [ "${use_proxy}" == 'yes' ]; then
        curl -x ${proxy_ip}:${proxy_port} -U ${proxy_usr}:${proxy_pass}  --silent -A ${APPSTORE_HEADER} -H ${APPSTORE_MARKET} 'https://itunes.apple.com/WebObjects/MZStore.woa/wa/viewContentsUserReviews?id='${APPID}'&pageNumber=0&sortOrdering=1&type=Purple+Software' >> $IOS_DIR/ios-${APPID}-${DATE}-unformatted.xml
	fi	
	echo " - Formatting raw data in $IOS_DIR/ios-${APPID}-${DATE}-formatted.xml"
	xmllint --format $IOS_DIR/ios-${APPID}-${DATE}-unformatted.xml --output $IOS_DIR/ios-${APPID}-${DATE}-formatted.xml
	echo " - Cleaning up in $IOS_DIR/ios-${APPID}-${DATE}-formatted-nons.xml"
	sed '2s/.*/<Document>/' $IOS_DIR/ios-${APPID}-${DATE}-formatted.xml > $IOS_DIR/ios-${APPID}-${DATE}-formatted-nons.xml
	echo " - Generating clean data into $IOS_DIR/ios-${APPID}-${DATE}-preprocessed-unformatted.xml"	
	xsltproc -o $IOS_DIR/ios-${APPID}-${DATE}-preprocessed-unformatted.xml $IOS_XSLT_FINAL_FILE $IOS_DIR/ios-${APPID}-${DATE}-formatted-nons.xml
	echo " - Formatting clean data into $IOS_DIR/ios-${APPID}-${DATE}-preprocessed-formatted.xml"	
	xmllint --format $IOS_DIR/ios-${APPID}-${DATE}-preprocessed-unformatted.xml --output $IOS_DIR/ios-${APPID}-${DATE}-preprocessed-formatted.xml
	echo " - Generating $IOS_DIR/ios-${APPID}-${DATE}.sql"	
	xsltproc --stringparam APP_ID "${APPID}" --stringparam TIMESTAMP "${DB_TIMESTAMP}" -o $IOS_DIR/ios-${APPID}-${DATE}.sql $IOS_XSLT_SQL_FILE $IOS_DIR/ios-${APPID}-${DATE}-preprocessed-formatted.xml
	echo " - executing $IOS_DIR/ios-${APPID}-${DATE}.sql"
	$DATABASE_BIN $DATABASE_FILE < $IOS_DIR/ios-${APPID}-${DATE}.sql
done