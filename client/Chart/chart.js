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
            "attendance_question",
            createChart('attendance_chart'),
            displayDescription('attendance_chart')
        )(...selectedArray);

        makeRequestTo("gender_question", createChart('gender_chart'))(...selectedArray);
        for (index = 1; index < 18; index++) {
            makeRequestTo(`question_${index}`, createChart(`question_${index}_chart`))(...selectedArray);
        }
    });
});
function makeRequestTo(questionURL = "", chartCreation, chartDescription) {
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

                //Display description for the charts
                chartDescription(values);
            }
        })
    }
}
function displayDescription(toChartAfter) {
    return (keys, values) => {
        //Array to be calculated the mean
        console.log(values);
        var refinedValues = values.map((value, index) => value * (index + 1));

        var mean = Math.round(refinedValues / 15);
        var standardDeviation = Math.round(jStat.stdev(refinedValues));

        $(`<p>Mean: ${mean}</p>`).insertAfter($(`#${toChartAfter}`))
        $(`<p>Standard Deviation: ${standardDeviation}</p>`).insertAfter($(`#${toChartAfter}`))
    }
}
function createChart(chart) {
    var ctx = document.getElementById(chart).getContext('2d');
    return (keys, values) => {
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: keys,
                datasets: [{
                    label: '# of choice',
                    data: values,
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
                            beginAtZero: true,
                            max: parseInt(Math.max(...values) + 1)

                        }
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
    console.log(url);
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