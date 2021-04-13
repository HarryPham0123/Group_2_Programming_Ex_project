var selectedArray = [];
$(function () {
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
        $(".sel-pro option").not(":first").remove();
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

        //Remove the next siblings select
        $(".sel-lec option").not(":first").remove();
        //Append to lecturer select
        getCodes("Lcode", ".sel-lec", ...selectedArray);
    })

    //Module listener
    $(".sel-lec").change(function() {
        selectedArray.push($(".sel-lec option:selected").val());
        console.log(selectedArray);
    })

    $(".visual").click(function (e) {
        e.preventDefault();
        //Attendance question
        makeRequestTo(
            "gender_question",
            createChart('gender_chart'),
            displayDescription('gender_chart'),
            displayTitle('gender_chart', 'Percentage of respondents by gender'))(...selectedArray);
        makeRequestTo(
            "attendance_question",
            createChart('attendance_chart'),
            displayDescription('attendance_chart'),
            displayTitle('attendance_chart', 'Percentage of respondents by class attendance')
        )(...selectedArray);
        /*
        for (index = 1; index < 18; index++) {
            makeRequestTo(`question_${index}`, createChart(`question_${index}_chart`))(...selectedArray);
        }
         */
    });
});
function makeRequestTo(questionURL = "", chartCreation, chartDescription, chartTitle) {
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
                chartCreation(keys, values);

                //Display description for the current chart
                chartDescription(values);

                //Display title for the current chart
                chartTitle();
            }
        })
    }
}
function displayDescription(forChart) {
    return (values) => {
        //Refine the retrieved values
        var calculatedValues = refinedValues(values);

        //Calculate mean and standard deviation
        var mean = Math.round(jStat.mean(calculatedValues));
        var standardDeviation = Math.round(jStat.stdev(calculatedValues));

        //Display the calculation
        $(`<p>Mean: ${mean}</p>`).insertAfter($(`#${forChart}`))
        $(`<p>Standard Deviation: ${standardDeviation}</p>`).insertAfter($(`#${forChart}`));
};

function refinedValues (calculateValues) {
    //Copy the current array
    var arrayValues = [...calculateValues];
    //Loop through current array
    for (let index = 0; index < calculateValues.length; index++)
        for (let times = 1; times <= (index + 1); times++) {
            arrayValues.push(calculateValues[index]);
        }
};

return arrayValues;
};

function displayTitle(forChart, chartTitle) {
    //Set title for current chart
    $(`<h1 class="chart-title">${chartTitle}</h1>`).insertBefore($(`#${forChart}`));

};

function createChart(chart) {
    var ctx = document.getElementById(chart).getContext('2d');
    return (keys, values) => {
        var percentageArray = calculatePercentage(values);
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
                }]
            },
            options: {
                scales: {
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

                            dataArr.map(data => {
                                sum += data;
                            });
                            let percentage = Math.round(value * 100 / sum) + "%";
                            return percentage;
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
    let percentageValues = values.map(value => {return (value / sumValues) * 100;});

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
