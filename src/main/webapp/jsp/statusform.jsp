<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>scrap information</title>
<script src="../scripts/handlebars-v1.1.2.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<style>
	.row{
		background-color: #dcdcdc;
	}
	.col{
		margin:10px;
		display:inline-block;
		width:22%;
	}

	.row:nth-child(1){
		background-color: #333;
		color:white;
	}
</style>
</head>
<body>
<% String scrapData = (String)session.getAttribute("scrapData"); %>
	
	<script id="entry-template" type="text/x-handlebars-template">
  		<div>
  		  <div class="row">
			<div class='col'>Date</div>
  		  	<div class='col'>Name</div>
  		  	<div class='col'>Location</div>
  		  	<div class='col'>Type</div>
  		  </div>	
		  {{#each items}}
		  <div class='row'>{{agree_button}}</div>
		  {{/each}}
		</div>
	</script>

	<script>

	var context = {
	  items: <%=scrapData %>
	};

	Handlebars.registerHelper('agree_button', function() {
	  return new Handlebars.SafeString(
		"<div class='col'>"+this.date+"</div>" +
	    "<div class='col'>"+this.name+"</div>" +
	    "<div class='col'>"+this.location+"</div>" +
	    "<div class='col'>"+this.type+"</div>" 
	  );
	});

	var source   = $("#entry-template").html();
	var template = Handlebars.compile(source);

	//alert(html);
	var html    = template(context);
	document.body.innerHTML = html;

	</script>
</body>
</html>