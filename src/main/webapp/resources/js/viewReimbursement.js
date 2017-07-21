/**
 * 
 */

window.onload = function(){
	document.getElementById("pending").addEventListener('click', loadPendingReimb, false);
	document.getElementById("resolved").addEventListener('click', loadResolvedReimb, false);
}

function loadPendingReimb(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			var json = JSON.parse(xhr.responseText);
			var tbl = $.makePendingTable(json);
			document.getElementById("view").innerHTML = tbl;
			$("#view").html($(tbl));
		}
	}
	xhr.open("GET", "getPendingReimb", true);
	xhr.send();
}

function loadResolvedReimb(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status ==200){
			var json = JSON.parse(xhr.responseText);
			var tbl = $.makeResolvedTable(json);
			document.getElementById("view").innerHTML = tbl;
			$("#view").html($(tbl));
		}
	}
	xhr.open("GET", "getResolvedReimb", true);
	xhr.send();
}

//src: https://stackoverflow.com/a/27814032
$.makePendingTable = function (mydata) {
    var table = $('<table border=1 class=\"table table-striped table-bordered\">');
    var tblHeader = "<thead><tr>";
    tblHeader += "<th style=\"text-align:center\">Reimbursement_Id</th>";
    tblHeader += "<th style=\"text-align:center\">Reimbursement_Type</th>";
    tblHeader += "<th style=\"text-align:center\">Amount</th>";
    tblHeader += "<th style=\"text-align:center\">Submitted</th>";
    tblHeader += "<th style=\"text-align:center\">Description</th>";
    tblHeader += "</tr></thead><tbody>";
    $(tblHeader).appendTo(table);

    for(i = 0; i < Object.keys(mydata).length; i++){
    	var TableRow = "<tr>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['reimbursement_Id'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['reimbursement_Type'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['amount'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['submitted'] + "</td>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['description'] + "</td>";
    	TableRow += "</tr>";
    	$(table).append(TableRow);
    }
    $(table).append("</tbody>");
    return ($(table));
};

$.makeResolvedTable = function (mydata) {
    var table = $('<table border=1 class=\"table table-striped table-bordered\">');
    var tblHeader = "<thead><tr>";
    tblHeader += "<th style=\"text-align:center\">Reimbursement_Id</th>";
    tblHeader += "<th style=\"text-align:center\">Reimbursement_Type</th>";
    tblHeader += "<th style=\"text-align:center\">Amount</th>";
    tblHeader += "<th style=\"text-align:center\">Submitted</th>";
    tblHeader += "<th style=\"text-align:center\">Description</th>";
    tblHeader += "<th style=\"text-align:center\">Resolver_Name</th>";
    tblHeader += "<th style=\"text-align:center\">Status</th>";
    tblHeader += "<th style=\"text-align:center\">Resolved</th>";
    tblHeader += "</tr></thead><tbody>";
    $(tblHeader).appendTo(table);

    for(i = 0; i < Object.keys(mydata).length; i++){
    	var TableRow = "<tr>";
    	TableRow += "<td style=\"text-align:center\">" + mydata[i]['reimbursement_Id'] + "</td>";
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
    $(table).append("</tbody>");
    return ($(table));
};