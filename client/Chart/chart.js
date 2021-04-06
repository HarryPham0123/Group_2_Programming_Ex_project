$(function () {
    $(".visual").click(function (e) { 
        e.preventDefault();
        getTotalQuestion("attendance.txt")
        getTotalQuestion("gender.txt")
        getTotalQuestion("question1.txt")
        getTotalQuestion("question2.txt")
        getTotalQuestion("question3.txt")
        getTotalQuestion("question4.txt")
        getTotalQuestion("question5.txt")
        getTotalQuestion("question6.txt")
        getTotalQuestion("question7.txt")
        getTotalQuestion("question8.txt")
        getTotalQuestion("question9.txt")
        getTotalQuestion("question10.txt")
        getTotalQuestion("question11.txt")
        getTotalQuestion("question12.txt")
        getTotalQuestion("question13.txt")
        getTotalQuestion("question14.txt")
        getTotalQuestion("question15.txt")
        getTotalQuestion("question16.txt")
        getTotalQuestion("question17.txt")
        getTotalQuestion("question18.txt")
        
    });
});
function getTotalQuestion(api){
    const API=api.toString();  
    $.ajax({
        type: "GET",
        url: API,
        success: function (data) {
            const getChartName=api.split(".")
            console.log(getChartName[0])
            const obj=JSON.parse(data)
            const getValue=[]
            const getLabel=[]
            for (const property in obj) {
                getValue.push(parseInt(obj[property]))
                getLabel.push(property)
              }
              getForm(getLabel,getValue,getChartName[0])
        }
    });
}
function calPercent(arr,max){
   const percentage= arr.map((val)=>{
        return val*100/max
    })
    return percentage
}

function  getForm(getLabel,getData,getChart){
    //const getArr=JSON.parse(""+ getData +"");
    console.log(getData)
    const getPercentage=calPercent(getData,42);
    console.log(getPercentage)
    const mean=Math.round(jStat.mean(getData));
    const sd=Math.round(jStat.stdev(getData));
    $(`<canvas id="${getChart}">
       
    </canvas>
    <div class="stats">
        <p>${mean}</p>
        <p>${sd}</p>
    </div>
    `).appendTo(".chart-container");
    
    var ctx = document.getElementById(`${getChart}`).getContext('2d');
    var chart = new Chart(ctx, {
        // The type of chart we want to create
        
        type: 'bar',
        
        // The data for our dataset
        data: {
            labels: getLabel,
            datasets: [{
                label: getChart,
                backgroundColor: 'rgb(255, 99, 132)',
                borderColor: 'rgb(255, 99, 132)',
                data: getPercentage
            }]
        },

        // Configuration options go here
        options: {
            layout: {
                padding: {
                  left: 0,
                  right: 0,
                  top: 15,
                  bottom: 0
                }
              },
            responsive:true,
            scales: {
                yAxes: [{
                    ticks: {
                        min:0,
                        max:100,
                        beginAtZero: true,

                    }
                }]
            },
            tooltips: {
                "enabled": false
              },
            plugins: {
                datalabels: {
                    display: false
                  },
                labels: {
                    render: function (args) {  
                    let max = 42; //This is the default 100% that will be used if no Max value is found
                    

                    return Math.round(args.value)+"%"; //Calculate percent
                    }
                }
            }
            
        },  
    });
}
