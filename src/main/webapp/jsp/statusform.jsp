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
	<script id="entry-template" type="text/x-handlebars-template">
  		<div>
  		  <div class="row">
  		  	<div class='col'>Name</div>
  		  	<div class='col'>Week</div>
  		  	<div class='col'>Time</div>
  		  	<div class='col'>Date</div>
  		  </div>	
		  {{#each items}}
		  <div class='row'>{{agree_button}}</div>
		  {{/each}}
		</div>
	</script>

	<script>

	var data = [{"week":"10","date":"Nov 10","time":"4:05 PM ET","name":"Panthers"},{"week":"10","date":"Nov 10","time":"4:05 PM ET","name":"Panthers"},{"week":"11","date":"Nov 17","time":"4:25 PM ET","name":"Saints"},{"week":"12","date":"Nov 25","time":"8:40 PM ET","name":"Redskins"},{"week":"13","date":"Dec 01","time":"4:05 PM ET","name":"Rams"},{"week":"14","date":"Dec 08","time":"4:25 PM ET","name":"Seahawks"},{"week":"15","date":"Dec 15","time":"1:00 PM ET","name":"Buccaneers"},{"week":"16","date":"Dec 23","time":"8:40 PM ET","name":"Falcons"},{"week":"17","date":"Dec 29","time":"4:25 PM ET","name":"Cardinals"}];

	var context = {
	  items: data
	};

	Handlebars.registerHelper('agree_button', function() {
	  return new Handlebars.SafeString(
	    "<div class='col'>"+this.name+"</div>" +
	    "<div class='col'>"+this.week+"</div>" +
	    "<div class='col'>"+this.time+"</div>" + 
	    "<div class='col'>"+this.date+"</div>" 
	  );
	});


	var source   = $("#entry-template").html();
	var template = Handlebars.compile(source);

	//alert(html);
	var html    = template(context);

	document.body.innerHTML = html;

	</script>


<%
	String scrapData = (String)session.getAttribute("scrapData");
	%>
	
	<table align="center" border="1" cellspacing="0" cellpadding="0">
		<caption>
			<strong>Scrap Response Information</strong>
		</caption>
			<tr>
				<td>
					<h4><%=scrapData %></h4>
				</td>
			</tr>
	</table>



</body>
</html>