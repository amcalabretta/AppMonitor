	var MENU_REFERENCE = {
	  RD_IOS_RELEASE: {domain: "android", type: "rawData"}, 
	  RD_IOS_TOTAL:   {domain: "android", type: "rawData"}, 
	  RD_ANDROID:     {domain: "android", type: "rawData"},
	  RD_WINDOWS:     {domain: "android", type: "rawData"}
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
					var store = new Cache(JsonRest({target:"../data/rawData?domain=android&type=rawData"}), new Memory());
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
		//preparing the pane
		require(["dojo/dom-construct","dojo/dom-class"], function(domConstruct,domClass){
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
			case MENU_REFERENCE.RD_IOS_RELEASE:
				//adding the widgets
				require(["dijit/form/Button","dojo/dom-construct","dijit/form/DateTextBox","dojox/widget/Standby","dojo/domReady!"], function(Button,domConstruct,DateTextBox,Standby){
					console.log("Appended -2");
					var xhrGet = dojo.xhrGet({
				        url: "../data/applications?domain=ios",
				        handleAs: "json",
				        handle: function(response) {
				            console.info(2,'response',response);                
				            console.info(3,'xhrGet.results[0]',xhrGet.results[0]);
				        }
					});
				});
			break;
			default:
	 			 alert('Function not implelented (yet)');
		}
		currentView = menuReference; 
		//loadData(menuReference);
	}