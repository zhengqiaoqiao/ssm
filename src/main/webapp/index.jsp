<html>
<body>
<h2>Hello World!</h2>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	
	var list = new Array();
	
	list.push("aa");
	list.push("bb");
	var data = JSON.stringify(list)
	
	$.ajax({
		type : "POST",
		contentType : 'application/json',  
		data : data,
		url : 'http://localhost:8080/ssm/class/a.do',
		success: function(msg){
			alert( "Data Saved: " + msg );
		}
	});


</script>
</body>
</html>
