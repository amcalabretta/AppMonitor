#!/bin/bash
echo " ************* Downloading IOS comments as for $DATE ************* "
echo "Loading applications from database $DATABASE_FILE"
i=1
$DATABASE_BIN $DATABASE_FILE "SELECT * FROM IOS_APPLICATIONS" | while read ROW; do\
	ID=$(echo "$ROW" |cut -d'|' -f 1)
	APPID=$(echo "$ROW" |cut -d'|' -f 2)
	APPNAME=$(echo "$ROW" |cut -d'|' -f 3)
	DESCRIPTION=$(echo "$ROW" |cut -d'|' -f 4)
	echo "Application: ${DESCRIPTION}, APPLE ID:${APPID} - ${i}"
	curl -x ${ing_proxy_ip}:${ing_proxy_port} -U ${ing_proxy_usr}:${ing_proxy_pass}  --silent -A "iTunes/9.2 (Macintosh; U; PPC Mac OS X 10.6)" -H "X-Apple-Store-Front: 143452-1" 'https://itunes.apple.com/WebObjects/MZStore.woa/wa/viewContentsUserReviews?id='${APPID}'&pageNumber=0&sortOrdering=1&type=Purple+Software' >> $COMMENTS_DIR/ios-${APPID}-${DATE}-unformatted.xml
	xmllint --format $COMMENTS_DIR/ios-${APPID}-${DATE}-unformatted.xml --output $COMMENTS_DIR/ios-${APPID}-${DATE}-formatted.xml
	sed '2s/.*/<Document>/' $COMMENTS_DIR/ios-${APPID}-${DATE}-formatted.xml > $COMMENTS_DIR/ios-${APPID}-${DATE}-formatted-nons.xml
	xsltproc -o $COMMENTS_DIR/ios-${APPID}-${DATE}-preprocessed-unformatted.xml $IOS_XSLT_FINAL_FILE $COMMENTS_DIR/ios-${APPID}-${DATE}-formatted-nons.xml
	xmllint --format $COMMENTS_DIR/ios-${APPID}-${DATE}-preprocessed-unformatted.xml --output $COMMENTS_DIR/ios-${APPID}-${DATE}-preprocessed-formatted.xml
	xsltproc --stringparam APP_ID "${APPID}" --stringparam TIMESTAMP "${DB_TIMESTAMP}" -o $COMMENTS_DIR/ios-${APPID}-${DATE}.sql $IOS_XSLT_SQL_FILE $COMMENTS_DIR/ios-${APPID}-${DATE}-preprocessed-formatted.xml
	i=`expr $i + 1`
done