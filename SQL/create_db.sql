/* IOS_APPLICATIONS */
CREATE TABLE IOS_APPLICATIONS 
(
 /* INTERNAL ID (1,2,3..) used as foreign key on other tables */
 application_id int,
 /* APPLE KEY */
 app_id int,
 /* APPLE KEY */
 app_code text,
 /* INTERNAL IDENTIFIER ('ING' etc) */
 description text,
 PRIMARY KEY (app_id)
);


/* TABLE KEEPING TRACK OF THE IOS RELEASES */
CREATE TABLE IOS_RELEASES 
(
  log_time DATETIME DEFAULT CURRENT_TIMESTAMP,
  releasing_time DATETIME,	
  application_id int,
  release text,
  PRIMARY KEY (application_id, release),
  FOREIGN KEY(application_id) REFERENCES IOS_APPLICATIONS(application_id)
);

/** TABLE KEEPING TRACK OF THE NUMBER OF PAGES PER DATE */
CREATE TABLE COMMENTS_IOS_PAGES_LOG
(
	application_id int,
	log_time DATETIME DEFAULT CURRENT_TIMESTAMP,
	num_pages int,
	PRIMARY KEY (application_id),
	FOREIGN KEY(application_id) REFERENCES IOS_APPLICATIONS(application_id)
);

/** RATINGS BASED ON THE RELEASE (IOS)*/
CREATE TABLE IOS_RELEASE_RATINGS
(
  timestamp int,
  rating_time DATETIME DEFAULT CURRENT_TIMESTAMP,	
  app_id int,
  release text,
  five_stars int,
  four_stars int,
  three_stars int,
  two_stars int,
  one_star int,
  total int,
  var_total int,
  average int,
  var_average int,
  perc_five int,
  perc_four int,
  perc_three int,
  perc_two int,
  perc_one int,
  perc_five_var int,
  perc_four_var int,
  perc_three_var int,
  perc_two_var int,
  perc_one_var int,
  PRIMARY KEY (app_id,timestamp),
  FOREIGN KEY(app_id) REFERENCES IOS_APPLICATIONS(app_id)
);

CREATE INDEX ts_idx_release ON IOS_RELEASE_RATINGS(timestamp);

/** TOTAL RATINGS (IOS)*/
CREATE TABLE IOS_TOTAL_RATINGS
(
  timestamp int,
  rating_time DATETIME DEFAULT CURRENT_TIMESTAMP,	
  app_id int,
  five_stars int,
  four_stars int,
  three_stars int,
  two_stars int,
  one_star int,
  total int,
  var_total int,
  average int,
  var_average int,
  perc_five int,
  perc_four int,
  perc_three int,
  perc_two int,
  perc_one int,
  perc_five_var int,
  perc_four_var int,
  perc_three_var int,
  perc_two_var int,
  perc_one_var int,
  PRIMARY KEY (app_id,timestamp),
  FOREIGN KEY(app_id) REFERENCES IOS_APPLICATIONS(app_id)
);

CREATE INDEX ts_idx ON IOS_TOTAL_RATINGS(timestamp);

