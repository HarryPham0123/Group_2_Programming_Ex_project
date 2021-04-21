var selectedArray = [];
var chartArray = {};
var classSize = 10;
$(function () {
    Chart.defaults.global.tooltips.enabled = false;

    //Load Academic year first
    getCodes("AYcode", ".sel-acad");
    //Academic year listener
    $(".sel-acad").change(function() {
        var selectedAcademicYear = $(".sel-acad option:selected").val();
        selectedArray.length = 1;
        selectedArray[0] = selectedAcademicYear;

        //Remove all options in the next selects
        $(".academic-box")
            .nextAll("div[class*='-box']")
            .children("select")
            .empty().append("<option value=null>--any--</option>");

        console.log(selectedArray);
        getCodes("Scode", ".sel-sem", ...selectedArray);

    });

    //Semester listener
    $(".sel-sem").change(function() {
        selectedArray.length = 2;
        selectedArray[1] = ($(".sel-sem option:selected").val());

        console.log(selectedArray);
        //Remove all options in the next selects
        $(".semester-box").nextAll("div[class*='-box']")
            .children("select")
            .empty().append("<option value=null>--any--</option>");
        getCodes("Fcode", ".sel-faculty", ...selectedArray);

    })

    //Faculty listener
    $(".sel-faculty").change(function() {
        selectedArray.length = 3;
        selectedArray[2] = $(".sel-faculty option:selected").val();

        console.log(selectedArray);
        //Remove all options in the next selects
        $(".faculty-box")
            .nextAll("div[class*='-box']")
            .children("select")
            .empty().append("<option value=null>--any--</option>");
        getCodes("Pcode", ".sel-pro", ...selectedArray);
    })

    //Program listener
    $(".sel-pro").change(function() {
        selectedArray.length = 4;
        selectedArray[3] = $(".sel-pro option:selected").val();

        console.log(selectedArray);
        //Remove all options in the next selects
        $(".program-box")
            .nextAll("div[class*='-box']")
            .children("select")
            .empty().append("<option value=null>--any--</option>");

        getCodes("Mcode", ".sel-module", ...selectedArray);
    })

    //Module listener
    $(".sel-module").change(function() {
        selectedArray.length = 5;
        selectedArray[4] = $(".sel-module option:selected").val();

        console.log(selectedArray);
        //Remove all options in the next selects
        $(".module-box")
            .nextAll("div[class*='-box']")
            .children("select")
            .empty().append("<option value=null>--any--</option>");
        getCodes("Ccode", ".sel-class", ...selectedArray);
    })

    //Class listener
    $(".sel-class").change(function() {
        selectedArray.length = 6;
        var selectedClass = selectedArray[5] = $(".sel-class option:selected").val();

        console.log(selectedArray);
        //Remove the next siblings select
        $(".class-box").nextAll("div[class*='-box']").children("select").empty().append("<option value=null>--any--</option>");
        //Append to lecturer select
        getCodes("Lcode", ".sel-lec", ...selectedArray);

        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: `http://localhost:8080/survey/api/classes/${encodeURI(selectedClass)}`,
            success: function(data) {
                classSize = data.size;
            }
        })
    })

    //Lecturer listener
    $(".sel-lec").change(function() {
        selectedArray.length = 7;
        selectedArray[6] = $(".sel-lec option:selected").val();

        console.log(selectedArray);
    })

    $(".visual").click(function (e) {
        e.preventDefault();
        fetchQuestionnaires("gender_question", ...selectedArray)("gender_chart");
        fetchQuestionnaires("attendance_question", ...selectedArray)('attendance_chart');
        for (index = 1; index < 18; index++) {
            fetchQuestionnaires(`question_${index}`, ...selectedArray)(`question_${index}_chart`);
        }
    });

    //Pre-loading all the charts
    preLoader();

});
/*Pre-loading all of the graph, title and description*/
function preLoader() {
    //Render the chart
    renderChart("gender_chart");
    renderChart("attendance_chart");

    //question_1_chart, question_2_chart, ...
    for (index = 1; index < 18; index++) {
        renderChart(`question_${index}_chart`);
    }

    //Render out the class size
    $(`<h3>Class size: ${classSize}</h3>`).insertAfter($("#question_17_chart"));
}
//Load the graph, title and description corresponding to chartName
function renderChart(chartName) {
    //Create charts
    createChart(chartName);
    //Create title
    displayTitle(chartName);
    //Create description
    displayDescription(chartName);
}
function updateChart(chart) {
    return (keys, values) => {
        //Check if the options exceed 5 (not include NOT APPLICABLE)
        if (values.length > 5) {
            values = values.slice(0, 5);
            keys = keys.slice(0, 5);
        };

        //Calculate the percentages
        var percentageArray = calculatePercentage(values);
        let calculateValues = refinedValues(values);

        console.log("Values: " + calculateValues);

        //Calculate mean and standard deviation
        let mean = jStat.mean(calculateValues).toFixed(1);
        let standardDeviation = jStat.stdev(calculateValues).toFixed(1);

        //Calculate the upper and lower bound
        let upperBound = parseFloat(mean) + parseFloat(standardDeviation);
        let lowerBound = parseFloat(mean) - standardDeviation;

        //Check if the lower and upper bound are exceed the x-Axis
        lowerBound = (lowerBound < 0) ? 0 : lowerBound;
        upperBound = (upperBound > 5) ? 5 : upperBound;

        //Update the results
        chart.data.labels = keys;
        chart.data.datasets[0].data = percentageArray;
        chart.data.datasets[1].data[0] = {x: mean, xMin: lowerBound, xMax: upperBound, y: Math.max(...percentageArray)+10}
        chart.update();
    }
}

function updateDescription(forChart) {
    return (values) => {
        //Check redundant response
        if (values.length > 5) {
            values = values.slice(0, 5);
        }
        //Refine the retrieved values
        let calculatedValues = refinedValues(values);
        console.log("Values: " + calculatedValues);

        //Calculate mean and standard deviation
        let mean = jStat.mean(calculatedValues).toFixed(1);
        let standardDeviation = jStat.stdev(calculatedValues).toFixed(1);
        let numberOfResponds = jStat.sum(values);
        let respondRate = ((numberOfResponds/classSize) * 100).toFixed(1) + "%";

        //Render the calculation
        $(`#${forChart}_numResp`).text(`Number of responses: ${numberOfResponds}`);
        $(`#${forChart}_respRate`).text(`Response rate: ${respondRate}`);
        $(`#${forChart}_staDev`).text(`Standard Deviation: ${standardDeviation}`);
        $(`#${forChart}_mean`).text(`Mean: ${mean}`);
    }
}
function fetchQuestionnaires(questionURL = "",
                             academic_year = null,
                             semester = null,
                             faculty = null,
                             program = null,
                             module = null,
                             lecturer = null,
                             clazz = null) {
    return function (chartName) {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: `http://localhost:8080/survey/api/questionnaire/${questionURL}?academic_year=${encodeURI(academic_year)}&semester=${encodeURI(semester)}&faculty=${encodeURI(faculty)}&program=${encodeURI(program)}&module=${encodeURI(module)}&lecturer=${encodeURI(lecturer)}&class=${encodeURI(clazz)}`,
            success: function(data) {
                let keys = [];
                let values = [];

                //Convert the keys and values into arrays
                for (const [thekey, thevalue] of Object.entries(data)) {
                    keys.push(String(thekey).toUpperCase());
                    values.push(parseInt(thevalue))
                }

                //Update chart's labels and data
                updateChart(chartArray[chartName])(keys, values);

                //Update the chart's description
                updateDescription(chartName)(values)
            }
        });
    }
}
function refinedValues(calculateValues) {
    if (calculateValues.length > 5) {
        calculateValues = calculateValues.slice(0, 5);
    }
    //Copy the current array
    var arrayValues = []
    //Loop through current array
    for (let index = 0; index < calculateValues.length; index++)
        for (let times = 1; times <= calculateValues[index]; times++) {
            arrayValues.push(index + 1);
        }

    return arrayValues;
};
function displayDescription(forChart) {
    //Create the blank description
    $(`<p id=${forChart}_numResp>Number of responses:</p>`).insertAfter($(`#${forChart}`));
    $(`<p id=${forChart}_respRate>Response rate:</p>`).insertAfter($(`#${forChart}`));
    $(`<p id=${forChart}_staDev>Standard deviation: </p>`).insertAfter($(`#${forChart}`));
    $(`<p id=${forChart}_mean>Mean: </p>`).insertAfter($(`#${forChart}`))
};

function displayTitle(forChart) {
    $.getJSON( "questions.json", function(questions) {
        let chartTitle = questions[forChart];
        //Set title for current chart
        $(`<h1 class="chart-title">${chartTitle}</h1>`).insertBefore($(`#${forChart}`));
    });
};

function createChart(chart) {
    var ctx = document.getElementById(chart).getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: [],
            datasets: [{
                label: 'Percentage',
                data: [],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }, {
                type: 'scatterWithErrorBars',
                label: 'Mean',
                xAxisID:  'mean_id',
                data: [],
                backgroundColor: 'rgb(255, 99, 132)',
            }]
        },
        options: {
            scales: {
                xAxes: [{
                    display: true,
                    stacked: true,
                    scaleLabel: {
                        display: true,
                    },
                },{
                    id: "mean_id",
                    type: 'linear',
                    display: false,
                    stacked: false,
                    scaleLabel: {
                        display: false,
                        labelString: 'Days',
                    },
                    ticks: {
                        beginAtZero: true,
                        stepSize: 1,
                        suggestedMin: 0,
                        suggestedMax: 5
                    }
                }],
                yAxes: [{
                    ticks: {
                        max: 100,
                        min: 0,
                        stepSize: 10,
                        callback: function (value) {
                            return value + "%"; // convert it to percentage
                        },
                    },
                    scaleLabel: {
                        display: true,
                        labelString: 'PERCENTAGE',
                    },
                }]

            },
            plugins: {
                datalabels: {
                    formatter: (value, ctx) => {
                        if(!isNaN(value)){
                            return parseInt(value)+"%";
                        }else{
                            return ctx.chart.data.datasets[1].data[0].x;
                        }
                    },
                    color: '#000000',
                    anchor: 'end',
                    align: 'end',
                }
            }
        }
    });
    chartArray[chart] = myChart;
}
function calculatePercentage(values) {
    //Calculate the sum
    let sumValues = jStat.sum(values);

    //Calculate the percentages array
    let percentageValues = values.map(value => {return ((value / sumValues) * 100).toFixed(2);});

    return percentageValues
};
function getCodes(
    code,
    appendMode,
    academic_year = null,
    semester = null,
    faculty = null,
    program = null,
    module = null,
    lecturer = null,
    clazz = null
) {
    var url = `http://localhost:8080/survey/api/general?academic_year=${encodeURI(academic_year)}&semester=${encodeURI(semester)}&faculty=${encodeURI(faculty)}&program=${encodeURI(program)}&module=${encodeURI(module)}&lecturer=${encodeURI(lecturer)}&class=${encodeURI(clazz)}`;
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url,
        success: function (data) {
            var codeArray = [];
            //Add to the available array
            for (index = 0; index < data.length; index++) {
                if (!codeArray.includes(data[index][code])) {
                    codeArray.push(data[index][code]);
                }
            }
            //Insert into DOM
            codeArray.map(code => {
                $(`<option value=${code}>${code}</option>`)
                    .appendTo($(appendMode));
            });
        },
    });
}