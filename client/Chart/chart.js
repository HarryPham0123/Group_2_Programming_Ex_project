var selectedArray = [];
var chartContainer;
var classSize = 10;
$(function () {
    Chart.defaults.global.tooltips.enabled = false;
    chartContainer = $(".chart-container").detach();

    //Load Academic year first
    getCodes("AYcode", ".sel-acad");
    //Academic year listener
    $(".sel-acad").change(function() {
        selectedArray.push($(".sel-acad option:selected").val());
        $(".sel-sem option").not(":first").remove();
        getCodes("Scode", ".sel-sem", ...selectedArray);

    });

    //Semester listener
    $(".sel-sem").change(function() {
        selectedArray.push($(".sel-sem option:selected").val());
        $(".sel-faculty option").not(":first").remove();
        getCodes("Fcode", ".sel-faculty", ...selectedArray);

    })

    //Faculty listener
    $(".sel-faculty").change(function() {
        selectedArray.push($(".sel-faculty option:selected").val());
        $(".sel-pro option").not(":first").remove();
        getCodes("Pcode", ".sel-pro", ...selectedArray);
    })

    //Program listener
    $(".sel-pro").change(function() {
        selectedArray.push($(".sel-pro option:selected").val());
        $(".sel-module option").not(":first").remove();
        getCodes("Mcode", ".sel-module", ...selectedArray);
    })

    //Module listener
    $(".sel-module").change(function() {
        selectedArray.push($(".sel-module option:selected").val());

        //Remove the next select
        $(".sel-class option").not(":first").remove();
        getCodes("Ccode", ".sel-class", ...selectedArray);
    })

    //Class listener
    $(".sel-class").change(function() {
        selectedArray.push($(".sel-class option:selected").val());
        var selectedClass = $(".sel-class option:selected").val();
        //Remove the next siblings select
        $(".sel-lec option").not(":first").remove();
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
        selectedArray.push($(".sel-lec option:selected").val());
    })

    $(".visual").click(function (e) {
        chartContainer.appendTo("body");
        e.preventDefault();
        //Attendance question
        makeRequestTo("gender_question", "gender_chart", )(...selectedArray);
        makeRequestTo("attendance_question",'attendance_chart', )(...selectedArray);

        for (index = 1; index < 18; index++) {
            makeRequestTo(`question_${index}`,`question_${index}_chart`)(...selectedArray);
        }
        //Class size
        $(`<h3>Class size: ${classSize}</h3>`).insertAfter($("#question_17_chart"));

    });
});
function makeRequestTo(questionURL = "", chartName) {
    return function addParameters(
        academic_year = null,
        semester = null,
        faculty = null,
        program = null,
        module = null,
        lecturer = null,
        clazz = null) {
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

                //Add the keys and values to create charts
                createChart(chartName)(keys, values);

                //Display description for the current chart
                displayDescription(chartName)(values);

                //Display title for the current chart
                displayTitle(chartName);
            }
        })
    }
}
function refinedValues(calculateValues) {
    //Copy the current array
    var arrayValues = [...calculateValues];
    //Loop through current array
    for (let index = 0; index < calculateValues.length; index++)
        for (let times = 1; times <= (index + 1); times++) {
            arrayValues.push(calculateValues[index]);
        }

    return arrayValues;
};
function displayDescription(forChart) {
    return (values) => {
        //Refine the retrieved values
        let calculatedValues = refinedValues(values);


        //Calculate mean and standard deviation
        let mean = jStat.mean(calculatedValues).toFixed(1);
        let standardDeviation = jStat.stdev(calculatedValues).toFixed(1);
        let numberOfResponds = jStat.sum(values.slice(0, values.length - 1));
        let respondRate = ((numberOfResponds/classSize) * 100).toFixed(1) + "%";

        //Display the calculation
        $(`<p>Number of responses: ${numberOfResponds}</p>`).insertAfter($(`#${forChart}`));
        $(`<p>Response rate: ${respondRate}</p>`).insertAfter($(`#${forChart}`));
        $(`<p>Standard Deviation: ${standardDeviation}</p>`).insertAfter($(`#${forChart}`));
        $(`<p>Mean: ${mean}</p>`).insertAfter($(`#${forChart}`))
};



//return arrayValues;
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

    return (keys, values) => {
        var percentageArray = calculatePercentage(values);
        let calculateValues=refinedValues(values);
        let mean = jStat.mean(calculateValues).toFixed(1);
        let meanDot=mean-0.5
        let upperBound=parseInt(mean)+1.5
        let lowerBound=Math.ceil(parseFloat(mean))-1.5
        if(lowerBound<1){
            lowerBound=0

        }
        if(upperBound>=5){
            upperBound=5
        }
        if(mean==5){
            lowerBound=Math.ceil(parseFloat(mean))-1
        }
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: keys,
                datasets: [{
                    label: 'Percentage',
                    data: percentageArray,
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
                         // yAxisID: 'invoice-amount',

                         data: [
                             {x: meanDot,
                             xMin: lowerBound,
                             xMax:upperBound,
                             y: Math.max(...percentageArray)+10
                              }],
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
                            let sum = 0;
                            let dataArr = ctx.chart.data.datasets[0].data;
                            console.log(dataArr);
                            dataArr.map(data => {
                                sum += parseFloat(data);
                            });
                            let percentage = Math.round(value * 100 / sum)
                                if(!isNaN(percentage)){
                                    console.log(isNaN(percentage))
                                    return percentage+"%";
                                }else{
                                    return mean;

                                }
                        },
                        color: '#000000',
                        anchor: 'end',
                        align: 'end',
                    }
                }
            }
        });
    }
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
