<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>scrap information</title>
<script src="../scripts/handlebars-v1.1.2.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />


<style>
	body{
		margin:0px;
	}
	.row{
		background-color: #dcdcdc;
	}
	.col{
		margin:10px;
		display:inline-block;
		width:15%;
	}

	.row:nth-child(1){
		background-color: #333;
		color:white;
	}
	.filter{
		text-align:center;
		padding:40px 0px;
		background-color: #dcdcdc;
	}
	.pagination{
		margin:40px 0px;
		text-align: center;
		color:white;
	}
	.navigationBtn{
		background: #4FA5DF;
		height: 40px;
		width: 140px;
		display: inline-block;
		vertical-align: middle;
		cursor: pointer;
		margin-top: -15px;
		line-height: 39px;
		border-radius: 5px;
		margin:0px 10px 0px 0px;
	}

</style>
</head>
<body>

	<div class="filter">
		<form id="filterContainer"  method="post" action="">
				<span>
					<label for="name">Name</label>
					<input type="text" id="name" name="name" >
				</span>

				<span>
					<label for="name">Location</label>
					<input type="text" id="location" name="location" >
				</span>

				<span>
					<label for="name">Source</label>
					<input type="text" id="source" name="source" >
				</span>

				<span>
					<label for="name">From</label>
					<input type="text" id="from" name="fromDate" >
				</span>

				<span>
					<label for="name">To</label>
					<input type="text" id="to" name="toDate">
				</span>

				
				<input type="submit" value="Apply Filters">
		</form>	
	</div>

	<div class="results">
		
		
	</div>

	<div class="pagination">
		
		<div class="navigationBtn textcenter"><span>Prev</span></div>
		<div class="navigationBtn textcenter"><span>Next</span></div>
		
	</div>	


	<div style="display:none">
	<span>${scrapData}</span>
	<span>${totalPages}</span>
	<span>${totalRecords}</span>
	<span>${pageNo}</span>
	</div>

	<script id="entry-template" type="text/x-handlebars-template">
  		<div>
  		  <div class="row">
			<div class='col'>Date</div>
  		  	<div class='col'>Name</div>
  		  	<div class='col'>Location</div>
  		  	<div class='col'>Type</div>
			<div class='col'>Source</div>
  		  </div>	
		  {{#each items}}
		  <div class='row'>{{agree_button}}</div>
		  {{/each}}
		</div>
	</script>

	<script>

	var context = {
	  items: ${scrapData}
	};

	Handlebars.registerHelper('agree_button', function() {
	  return new Handlebars.SafeString(
		"<div class='col'>"+this.date+"</div>" +
	    "<div class='col'>"+this.name+"</div>" +
	    "<div class='col'>"+this.location+"</div>" +
	    "<div class='col'>"+this.type+"</div>" +
	    "<div class='col'>"+this.source+"</div>"
	  );
	});

	var source   = $("#entry-template").html();
	var template = Handlebars.compile(source);

	//alert(html);
	var html    = template(context);
	$('.results').html(html);

	</script>
	

	<script>
		$(function() {
   			 $("#to,#from").datepicker();
  		});
	</script>

</body>
</html>