var selectedArray = [];
var chartArray = [];
var classSize = 10;
var lookupTable = ["AYcode", "Scode", "Fcode", "Pcode", "Mcode", "Ccode", "Lcode"];

$(function () {
    Chart.defaults.global.tooltips.enabled = false;
    //Hides the comment when not using it
    $(".comment-table").hide();

    $(".visual").click(function (e) {
        //Hides the comment when not using it
        $(".comment-table").hide();
        e.preventDefault();

        let academic_year = $(".sel-acad :selected").val();
        let semester = $(".sel-sem :selected").val();
        let faculty = $(".sel-faculty :selected").val();
        let program = $(".sel-pro :selected").val();
        let module = $(".sel-module :selected").val();
        let lecturer = $(".sel-lec :selected").val();
        let clazz = $(".sel-class :selected").val();

        //Checks if both class and lecturer are selected
        if ((lecturer !== "null") && (clazz !== "null")) {
            $(".comment-table").show();
            displayComments();

        }

        //Encapsulates the filter, to send to back-end
        var arrayOfValues = [academic_year, semester, faculty, program, module, lecturer, clazz];
        console.log(arrayOfValues);
        fetchQuestionnaires("gender_question", ...arrayOfValues)("gender_chart");
        fetchQuestionnaires("attendance_question", ...arrayOfValues)('attendance_chart');
        for (index = 1; index < 18; index++) {
            fetchQuestionnaires(`question_${index}`, ...arrayOfValues)(`question_${index}_chart`);
        }
    });

    //Attach listener to the selects
    $(".sel-acad").change(function() {
        watchSelect(0);
    })
    $(".sel-sem").change(function() {
        watchSelect(1);
    })
    $(".sel-faculty").change(function() {
        watchSelect(2);
    })
    $(".sel-pro").change(function() {
        watchSelect(3);
    })
    $(".sel-module").change(function() {
        watchSelect(4);
    })
    $(".sel-class").change(function() {
        watchSelect(5);
    })
    $(".sel-lec").change(function() {
        watchSelect(6);
    })

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

    //Loads all of the select
    getCodes("AYcode", ".sel-acad");
    getCodes("Scode", ".sel-sem");
    getCodes("Fcode", ".sel-faculty");
    getCodes("Pcode", ".sel-pro");
    getCodes("Mcode", ".sel-module");
    getCodes("Ccode", ".sel-class");
    getCodes("Lcode", ".sel-lec");

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

        //Calculate the mean and standard deviation
        let [mean, standardDeviation] = calculateStats(values);

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
function calculateStats(values) {
    //Calculate the percentages
    var percentageArray = calculatePercentage(values);
    let calculateValues = refinedValues(values);

    //Calculate mean and standard deviation
    let mean = jStat.mean(calculateValues).toFixed(1);
    let standardDeviation = jStat.stdev(calculateValues).toFixed(1);

    //Return a pair of mean and SD
    return [mean, standardDeviation];
}
function updateDescription(forChart) {
    return (values) => {
        //Check redundant response
        if (values.length > 5) {
            values = values.slice(0, 5);
        }
        //Refine the retrieved values
        let calculatedValues = refinedValues(values);

        //Calculate the mean and standard deviation
        let [mean, standardDeviation] = calculateStats(values);
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
function displayComments() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "http://localhost:8080/survey/api/questionnaire/question_18",
        success: function(comments) {
            //Insert comments to table's DOM
            comments.map((comment, index) => {
                $(`<tr>
                <td>${index + 1}</td>
                <td>${comment}</td>
                </tr>`).appendTo(".comment-table");
            })
        }
    })
}
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
function watchSelect(selectIndex) {
    var selects = $("select[class^='sel-']");
    //Check the changed index
    if (selectedArray.includes(selectIndex)) {
        //Check lower-bound index
        let startIndex = (selectedArray.length - selectIndex) < 2 ? 1 : (selectedArray.length - selectIndex);
        var toDeleteSelects = selectedArray.splice(startIndex, selectIndex);
        //Deletes the selects base on indexes
        toDeleteSelects.forEach(deleteIndex => {
            $(selects.get(deleteIndex)).empty().append("<option value=null>--any--<option/>");
        })

    } else {
        //Add to the selected array -> prevents update on that specific select
        selectedArray.push(selectIndex);
    }

    //Get code from selects
    var academic_year = $(".sel-acad :selected").val();
    var semester = $(".sel-sem :selected").val();
    var faculty = $(".sel-faculty :selected").val();
    var program = $(".sel-pro :selected").val();
    var module = $(".sel-module :selected").val();
    var lecturer = $(".sel-lec :selected").val();
    var clazz = $(".sel-class :selected").val();

    //Call APIs
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url : `http://localhost:8080/survey/api/general?academic_year=${encodeURI(academic_year)}&semester=${encodeURI(semester)}&faculty=${encodeURI(faculty)}&program=${encodeURI(program)}&module=${encodeURI(module)}&lecturer=${encodeURI(lecturer)}&class=${encodeURI(clazz)}`,
        success: function(data) {
            for (index = 0; index < selects.length; index++) {
                if (!selectedArray.includes(index)) {
                    var indexToCode = lookupTable[index];

                    //Clear the select
                    $(selects[index]).children().remove().end().append("<option value=null>--any--</option>")

                    //Update the select
                    var uniqueCodes = new Set(data.map(entity => entity[indexToCode]));
                    var codesArray = Array.from(uniqueCodes);

                    //Add new options to that select
                    codesArray.map(code => {
                        $(selects.get(index)).append(`<option value=${code}>${code}</option>`);
                    })
                }
            }
            console.log("Filter state: " + selectedArray);
        }
    })
}