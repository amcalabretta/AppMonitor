	var MENU_REFERENCE = {
	  DATA_APPLICATIONS:      {domain: "android", type: "rawData"}, 
	  DATA_INTERNAL:          {domain: "android", type: "rawData"}, 
	  REPORTS_AVG_COMMENTS:   {domain: "android", type: "rawData"},
	  REPORTS_RELEASES:       {domain: "android", type: "rawData"},
	  REPORTS_SNAPSHOTS:      {domain: "android", type: "rawData"}
	};
	
	var currentView = null;
	
	/**
	 * Returns the date formatted as "d MMM y" starting from the timestamp in milliseconds.
	 * */
	function formatTimestamp(datum){
	    dojo.require( "dojo.date.locale" );
	    var d = new Date(datum*1000);
	    return dojo.date.locale.format( d, {selector:"date", datePattern:"d MMMM y" } );
	}
	
	/**
	 * Returns today formatted dojo-like i.e. as "yyyy-MM-dd"
	 * */
	function today(daysAgo) {
		var d = new Date();
		d.setDate(d.getDate()-daysAgo);
	    return dojo.date.locale.format( d, {selector:"date", datePattern:"yyyy-MM-dd" } );
	}
	
	
	function loadData(menuReference) {
		require([
					"dojo/store/JsonRest",
					"dojo/store/Memory",
					"dojo/store/Cache",
					"dojox/grid/DataGrid",
					"dojo/data/ObjectStore",
					"dojo/query",
					"dojo/domReady!"
				], function(JsonRest, Memory, Cache, DataGrid, ObjectStore, query){
					var store = new Cache(JsonRest({target:"../data/rawData?domain=android"}), new Memory());
					var grid = new DataGrid({
						store: dataStore = new ObjectStore({objectStore: store}),
						autoWidth:false,
						structure: [
					                { name: "Date", field: "timestamp", width:"150px",formatter: formatTimestamp},
		            			    { name: "5 stars", field: "fiveStars"},
					                { name: "4 stars", field: "fourStars"},
		            			    { name: "3 stars", field: "threeStars"},
					                { name: "2 stars", field: "twoStars"},
					                { name: "1 star", field: "oneStar"},
					                { name: "total", field: "total"}
		            			]
					});
					grid.placeAt("dataView");
					grid.startup();
					grid.resize();
				});
	}
	
	/**
	 * Function loading the raw data in the content div
	 */
	function menuController(menuReference) {
		//preparing the pane (end remove the widgets)
		require(["dojo/dom-construct","dojo/dom-class","dijit/registry","dojo/dom"], function(domConstruct,domClass,registry,dom){
			  //deleting the checkboxes
			  for (var i=0;i<getMaxNumApp();i++) {
				  registry.remove("checkBox"+i);
			  }
			  //deleting the dates:
			  registry.remove("fromDate");
			  registry.remove("toDate");
			  //and the select for the platform
			  registry.remove("platForm");
			  //and the button
			  registry.remove("submitButton");
			  domConstruct.empty("content");
			  //adding the header
			  var header = domConstruct.create("div",{id: "header"}); 
			  domClass.add(header, "header");
			  domConstruct.place(header, "content", 0);
			  //adding the dataView
			  var dataView = domConstruct.create("div",{id: "dataView"}); 
			  domClass.add(dataView, "dataView");
			  domConstruct.place(dataView, "content", 1);
			  //adding the controls
			  var controls = domConstruct.create("div",{id: "controls"}); 
			  domClass.add(controls, "controls");
			  domConstruct.place(controls, "content", 2);
		});  
		switch(menuReference) {
			case MENU_REFERENCE.DATA_APPLICATIONS:
				//adding the widgets
				require(["dijit/form/Button","dojo/dom-construct","dojo/dom","dojo/dom-style","dijit/form/DateTextBox","dijit/form/Select","dijit/form/CheckBox","dijit/registry",], function(Button,domConstruct,dom,domStyle,DateTextBox,Select,CheckBox,registry){
				    var submitButtonTag = domConstruct.create("button",{id: "submitButton"});
				    var fromDateTag = domConstruct.create("input",{id: "fromDate"});
				    var toDateTag = domConstruct.create("input",{id: "toDate"});
				    var selectPlatformTag = domConstruct.create("select",{id: "platForm"});
				    var checkBoxContainer = domConstruct.create("div",{id: "checks"});
				    domConstruct.place(fromDateTag, "controls", 0);
				    domConstruct.place(domConstruct.create("br",{id: "firstSeparator"}), "controls", 1);
				    domConstruct.place(toDateTag, "controls", 2);
				    domConstruct.place(domConstruct.create("br",{id: "secondSeparator"}), "controls", 3);
				    domConstruct.place(selectPlatformTag, "controls", 4);
				    domConstruct.place(domConstruct.create("br",{id: "thirdSeparator"}), "controls", 5);
				    domConstruct.place(checkBoxContainer, "controls", 6);
				    domConstruct.place(submitButtonTag, "controls", 7);
				    //labels (for some reason they do not take the style from the css)
				    domConstruct.place('<label for="fromDate">From:</label>', dojo.byId('fromDate'), 'before');
				    domConstruct.place('<label for="fromDate">To:</label>', dojo.byId('toDate'), 'before');
				    domConstruct.place('<label for="platForm">Platform:</label>', dojo.byId('platForm'), 'before');
				    var submitButton = new Button({
				        label: "Load Data",
				        id: "submitButton",
				        onClick: function(){
				            // Do something:
				        	console.log('Loading data');
				        	var dtb = dijit.byId('fromDate');
				        	// get value
				        	var startD = dojo.date.locale.format(dtb.value, {datePattern: "yyyy-MM-dd", selector: "date"});
				        	console.log(startD);
				            //dom.byId("result1").innerHTML += "Thank you! ";
				        }
				    }, "submitButton");
				    var fromDate = new DateTextBox({
				    			constraints: { datePattern : 'd MMMM y' },
				                id: "fromDate",
				                name: "fromDate",
				                value: today(7)
				            }, "fromDate");
				    var toDate = new DateTextBox({
				    	constraints: { datePattern : 'd MMMM y' },
		                id: "toDate",
		                name: "toDate",
		                value:today(0)
		            }, "toDate");
				    
				    var platformSelect = new Select({
				        name: "platForm",
				        id: "platForm",
				        options: [
				            { label: "Select a platform..", value: -1, selected: true }
				        ],
				        onChange: function(){
				        	//deleting the checkboxes
							for (var i=0;i<getMaxNumApp();i++) {
								 console.log('removing select '+i); 
								 registry.remove("checkBox"+i);
							}
				        	domConstruct.empty("checks");
				            var selectedPlatformId = this._getValueFromOpts();
						    var applications = getApplications(selectedPlatformId);
						    if (applications.length !=0) {
						    	domStyle.set(submitButton.domNode, 'display', 'inline');
						    } else {
						    	domStyle.set(submitButton.domNode, 'display', 'none');
						    }
						    for (var i = 0; i < applications.length; i++) {
						    	var currentId = applications[i].id;
						    	var currentDescription = applications[i].description;						    	
						        var chk = domConstruct.create("input", {id:"cbox"+i, type:"checkbox"});
						        var lbl = domConstruct.create("label", {innerHTML:currentDescription, "for":"cbox"});
						        var br  = domConstruct.create("br");
						        domConstruct.place(chk, "checks", i+2*i);
						        domConstruct.place(lbl, "checks", i+2*i+1);
						        domConstruct.place(br, "checks", i+2*i+2);
						        var checkBoxWidget = new CheckBox({
						            name: "checkBox"+i,
						            id: "checkBox"+i,
						            value: "id_"+currentId,
						            checked: false,						        
						    	},"cbox"+i);
						    }
				        }
				    },"platForm");
				    for (var i = 0; i < DOMAINS.length; i++) {
				        platformSelect.addOption({disabled:false,label:DOMAINS[i].description,selected:false,value:DOMAINS[i].id});
				    };
				    domStyle.set(submitButton.domNode, 'display', 'none');
				});
			break;
			default:
	 			 alert('Function not implelented (yet)');
		}
		currentView = menuReference; 
		//loadData(menuReference);
	}