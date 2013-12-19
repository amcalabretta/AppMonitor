#!/bin/sh
#####################################################
# Main script whose main responsabilities are:
#  - Starting the scripts collecting the stats per platform (iOs, Android etc)
#  - Defining the variables used by these scripts (see below).
# This script should be the one invoked every time you need to collect the stats (ideally once a day).
# You might use cron with 
#####################################################
# Variables
export use_proxy=yes
export proxy_ip=SET_PROXY_IP_HERE (if the above propery is set to yes)
export proxy_port=SET_PROXY_PORT_HERE
export proxy_usr=SET_PROXY_USER_HERE
export proxy_pass=SET_PROXY_PASSWORD_HERE
export MAIN_DIR=SET_THE_DIRECTORY_WHERE_ALL_SCRITPS_ARE
export DATABASE_BIN=$MAIN_DIR/database/sqlite3 
export DATABASE_FILE=$MAIN_DIR/database/mobile_ratings
# other variables that are not necessary to be changed.
export COMMENTS_DIR=$MAIN_DIR/comments
export LOGS_DIR=$MAIN_DIR/logs
export SCRIPTS_DIR=$MAIN_DIR/scripts
export IOS_XSLT_FINAL_FILE=$MAIN_DIR/xslt/ios-comments.xslt
export IOS_XSLT_SQL_FILE=$MAIN_DIR/xslt/ios-sql-comments.xslt
export DATE=$(date +"%Y%m%d")
export DATE_PW=$(date -v-1w +"%Y%m%d")
export DB_TIMESTAMP=$(date +%s)
# Downloading the snapshot for today (ios)
$SCRIPTS_DIR/ios.sh >> $LOGS_DIR/ios-ratings-$DATE.log
