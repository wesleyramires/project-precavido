<script th:inline="javascript">
	var real_chartData = /*[[${chartData}]]*/'noValue';
	var real_chartPizza = /*[[${chartPizza}]]*/'noValue';

	
	$(document).ready(function() {
	    google.charts.load('current', {
	        packages : [ 'corechart', 'bar' ]
	    });
	    google.charts.setOnLoadCallback(drawColumnChart);
	    google.charts.setOnLoadCallback(drawPieChart);
	    google.charts.setOnLoadCallback(drawChart);
	});
	
	function drawColumnChart() {
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Mês');
		data.addColumn('number', 'Valores');
		Object.keys(real_chartData).forEach(function(key) {
		    data.addRow([ key, real_chartData[key] ]);
		});
		var options = {
			title : '',
		    hAxis : {
		        title : 'Mês',
		    },
		    vAxis : {
		        title : 'Valores',
		        format:'R$ '
		    }
		};
		var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
		chart.draw(data, options);
		
	   }
	
	function drawPieChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Year');
        data.addColumn('number', 'Views');
        Object.keys(real_chartPizza).forEach(function(key) {
            data.addRow([ key, real_chartPizza[key] ]);
        });
        var options = {
            title : ''
        };
        var chart = new google.visualization.PieChart(document
                .getElementById('piechart'));
        chart.draw(data, options);
    }
</script>