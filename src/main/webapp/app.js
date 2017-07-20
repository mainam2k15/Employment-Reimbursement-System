/**
 * 
 */

window.onload = function(){
	console.log("html loaded");
	loadNavbar();
}

function loadNavbar(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			document.getElementById("navbar").innerHTML = xhr.responseText;
//			document.getElementById('home').addEventListener('click', loadTxView, false);
//			document.getElementById('home2').addEventListener('click', loadTxView, false);
			document.getElementById('employeeView').addEventListener('click', loadEmployeeView, false);
			document.getElementById('reimbursementView').addEventListener('click', loadReimbursementView, false);
		}
	}
	
	xhr.open("GET", 'getNavbarMngr', true);
	xhr.send();
}

function loadEmployeeView(){
	console.log("loading all employees");
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
			document.getElementById("home2").classList.remove("active");
			document.getElementById("reimbursementView").classList.remove("active");
			document.getElementById("employeeView").classList.add("active");
			
			var json = JSON.parse(xhr.responseText);
			var tbl = $.makeEmployeeTable(json);
			document.getElementById("view").innerHTML = tbl;
			$("#view").html($(tbl));
		}
	}
	
	xhr.open("GET", 'getAllEmployees', true);
	xhr.send();
	
}

function loadReimbursementView(){
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
			document.getElementById("home2").classList.remove("active");
			document.getElementById("reimbursementView").classList.add("active");
			document.getElementById("employeeView").classList.remove("active");
			
			document.getElementById('view').innerHTML = xhr.responseText;
			
			document.getElementById("pending").addEventListener('click', loadPendingReimbMngrView, false);
			document.getElementById("resolved").addEventListener('click', loadResolvedReimbMngrView, false);
			document.getElementById("empReimb").addEventListener('click', loadEmployeeReimbMngrView, false);
			document.getElementById("approve").addEventListener('click', approveReimbursement, false);
			document.getElementById("deny").addEventListener('click', denyReimbursement, false);
			
			document.getElementById("approve").disabled = true;
			document.getElementById("deny").disabled = true;
			
			populateEmployeeDroplist();
		}
	}
	
	xhr.open("GET", 'getReimbursementViewMngr', true);
	xhr.send();
}

function loadPendingReimbMngrView(){
	
	document.getElementById("approve").disabled = false;
	document.getElementById("deny").disabled = false;
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			if(xhr.responseText != ""){
				var json = JSON.parse(xhr.responseText);
				var tbl = $.makePendingTable(json);
				document.getElementById("reimbTable").innerHTML = tbl;
				$("#reimbTable").html($(tbl));
			}
		}
	}
	
	xhr.open("GET", 'getPendingReimbMngr', true);
	xhr.send();
}

function loadResolvedReimbMngrView(){
	
	document.getElementById("approve").disabled = true;
	document.getElementById("deny").disabled = true;
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			if(xhr.responseText != ""){
				var json = JSON.parse(xhr.responseText);
				var tbl = $.makeResolvedTable(json);
				document.getElementById("reimbTable").innerHTML = tbl;
				$("#reimbTable").html($(tbl));
			}
		}
	}
	
	xhr.open("GET", 'getResolvedReimbMngr', true);
	xhr.send();
}

function loadEmployeeReimbMngrView(){
	
	document.getElementById("approve").disabled = true;
	document.getElementById("deny").disabled = true;
	
	var selBox = document.getElementById("employeeName");
	var empName = selBox.options[selBox.selectedIndex].text;
	
	var emp = {
		employeeName: empName
	}
	emp = JSON.stringify(emp);
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			if(xhr.responseText != ""){
				var json = JSON.parse(xhr.responseText);
				var tbl = $.makeResolvedTable(json);
				document.getElementById("reimbTable").innerHTML = tbl;
				$("#reimbTable").html($(tbl));
			}
		}
	}
	
	xhr.open("POST", 'getEmpReimb', true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(emp);
}

function populateEmployeeDroplist(){
	
	var select = document.getElementById('employeeName');
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
			var json = JSON.parse(xhr.responseText);
			
		    for(i = 0; i < Object.keys(json).length; i++){
		    	var name = json[i]['fName'] + " " + json[i]['lName'];
		    	select.options[select.options.length] = new Option(name, '');
		    }
		}
	}
	
	xhr.open("GET", 'getAllEmployees', true);
	xhr.send();
	
}

function approveReimbursement(){
	
	var reimb = $('input:checked');
	
	var reimbIndex = [];
	
	if(reimb.length == 0){
		return;
	}
	
	for(i = 0; i < reimb.length; i++){
		reimbIndex[i] = reimb[i].name;
	}
	
	var jsonIndex = {
		index: reimbIndex
	}
	jsonIndex = JSON.stringify(jsonIndex);
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var msg = document.getElementById("message");
			msg.classList.add("alert");
			msg.classList.add("alert-success");
			msg.innerHTML = "<strong> Successfully approved reimbursements! </strong>";
			//refresh view
			loadPendingReimbMngrView();
		}
	}
	
	xhr.open("POST", 'approveReimb', true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(jsonIndex);
	
}

function denyReimbursement(){
	
	var reimb = $('input:checked');
	
	var reimbIndex = [];
	
	if(reimb.length == 0){
		return;
	}
	
	for(i = 0; i < reimb.length; i++){
		reimbIndex[i] = reimb[i].name;
	}
	
	var jsonIndex = {
		index: reimbIndex
	}
	jsonIndex = JSON.stringify(jsonIndex);
	
	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var msg = document.getElementById("message");
			msg.classList.add("alert");
			msg.classList.add("alert-success");
			msg.innerHTML = "<strong> Successfully denied reimbursements! </strong>";
			//refresh view
			loadPendingReimbMngrView();
		}
	}
	
	xhr.open("POST", 'denyReimb', true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(jsonIndex);
	
}


$.makeEmployeeTable = function (mydata) {
    var table = $('<table border=1 style=\"margin:auto;\">');
    var tblHeader = "<tr>";
    tblHeader += "<th style=\"text-align:center\">Employee_Id</th>";
    tblHeader += "<th style=\"text-align:center\">Email</th>";
    tblHeader += "<th style=\"text-align:center\">First Name</th>";
    tblHeader += "<th style=\"text-align:center\">Last Name</th>";
    tblHeader += "</tr>";
    $(tblHeader).appendTo(table);

    for(i = 0; i < Object.keys(mydata).length; i++){
    	var TableRow = "<tr>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['user_id'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['email'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['fName'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['lName'] + "</td>";
    	TableRow += "</tr>";
    	$(table).append(TableRow);
    }

    return ($(table));
};

$.makePendingTable = function (mydata) {
    var table = $('<table border=1 style=\"margin: auto;\">');
    var tblHeader = "<tr>";
    tblHeader += "<th> </th>"
    tblHeader += "<th style=\"text-align:center\">Reimb_Id</th>";
    tblHeader += "<th style=\"text-align:center\">Author</th>";
    tblHeader += "<th style=\"text-align:center\">Reimb_Type</th>";
    tblHeader += "<th style=\"text-align:center\">Amount</th>";
    tblHeader += "<th style=\"text-align:center\">Submitted</th>";
    tblHeader += "<th style=\"text-align:center; width: 55%;\">Description</th>";
    tblHeader += "</tr>";
    $(tblHeader).appendTo(table);

    for(i = 0; i < Object.keys(mydata).length; i++){
    	var TableRow = "<tr>";
    	TableRow += "<td><input type=\"checkbox\" name=\"" + i + "\" /></td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['reimbursement_Id'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['author'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['reimbursement_Type'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['amount'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['submitted'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['description'] + "</td>";
    	TableRow += "</tr>";
    	$(table).append(TableRow);
    }

    return ($(table));
};

$.makeResolvedTable = function (mydata) {
    var table = $('<table border=1 style=\"margin: auto;\">');
    var tblHeader = "<tr>";
    tblHeader += "<th style=\"text-align:center\">Reimb_Id</th>";
    tblHeader += "<th style=\"text-align:center\">Author</th>";
    tblHeader += "<th style=\"text-align:center\">Reimb_Type</th>";
    tblHeader += "<th style=\"text-align:center\">Amount</th>";
    tblHeader += "<th style=\"text-align:center\">Submitted</th>";
    tblHeader += "<th style=\"text-align:center\">Description</th>";
    tblHeader += "<th style=\"text-align:center\">Resolver</th>";
    tblHeader += "<th style=\"text-align:center\">Status</th>";
    tblHeader += "<th style=\"text-align:center\">Resolved</th>";
    tblHeader += "</tr>";
    $(tblHeader).appendTo(table);

    for(i = 0; i < Object.keys(mydata).length; i++){
    	var TableRow = "<tr>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['reimbursement_Id'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['author'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['reimbursement_type'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['amount'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['submitted'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['description'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['resolver_name'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['status_name'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['resolved'] + "</td>";
    	TableRow += "</tr>";
    	$(table).append(TableRow);
    }

    return ($(table));
};