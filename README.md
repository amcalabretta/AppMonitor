AppMonitor
==========

AppMonitor is a complete system allowing to download stats (in terms of reviews done by users) of mobile applications.
It supports the following platforms:

 -- iOs
 -- Android
 -- Windows Phone
 -- Windows RT
 
AppMonitor uses a bounce of utilities (commonly used in *nix world) to download and elaborate the data from the various markets
(appstore, google play and so on), namely they are:

 - curl
 - zip
 - xmllint
 - xsltproc 
 
The system is based on a set of scripts whose responsabilities are defined as follows:

 - main.sh
   This script defines all the variables used by other scripts, and it starts them in sequence, this is probably the one you shall 
   define as an entry in the crontab.
   
   The variables defined are:
   
   - use_proxy: (possible values:yes/no) instruct the scripts wheter using a proxy to download the data from internet.
   - proxy_ip: The ip address of the proxy used.
   - proxy_port: The port used by the proxy.
   - proxy_usr: The user name used to connect trought the proxy.
   - proxy_pass: The password used to connect trought the proxy.
   - MAIN_DIR: The directory where the scritps are
   - DATABASE_BIN: The path of the sqlite3 binary, it has been defined because you might want to compile it by yourself (see above)
   - DATABASE_FILE: The path of the sqlite3 databse file.
   - IOS_DIR: The directory where the ios files are generated (you might define another cron tab to delete or tar these files)
   - LOGS_DIR: The directory where the logs are wrote (you might define another cron tab to delete or tar the logs)
   - IOS_XSLT_FINAL_FILE: This should not be changed, it is set to $MAIN_DIR/xslt/ios-comments.xslt.
   - IOS_XSLT_SQL_FILE: This should not be changed, it is set to $MAIN_DIR/xslt/ios-sql-comments.xslt
   - DATE: The current date (used to update/insert data into the database) should not be changed, it is set to $(date +"%Y%m%d")
   - DB_TIMESTAMP=The current timestamp in seconds, used as key into the database should not be changed, is it set to $(date +%s)
 
 - ios.sh
   This file downloads and update the data for ios for the current date, it actually generates a bounce of .sql files that 
   are later executed to insert/update the data in the database.
   
