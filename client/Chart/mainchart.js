
$(function () {
    getSelection();
    let newAcad=getAcad()
        let newSem=getSem()
        let newFaculty=getFaculty()
        let newPro= getPro()
        let newModule=getModule()
        let newClass=getClass()
        let newLec= getLec()
        const finalSelection=updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec)
        $(".visual").click(function (e) { 
            //console.log(finalSelection)
            
        });
    $(".sel-acad, .sel-sem, .sel-faculty, .sel-pro, .sel-module, .sel-class, .sel-lec").change(function (e) { 
        let newAcad=getAcad()
        let newSem=getSem()
        let newFaculty=getFaculty()
        let newPro= getPro()
        let newModule=getModule()
        let newClass=getClass()
        let newLec= getLec()
        //const finalSelection=updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec)
        $(".visual").click(function (e) { 
            //console.log(finalSelection)
            getAttendanceGen(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"attendance_question"),"attendance_question")
            getAttendanceGen(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"gender_question"),"gender_question")
            getTotalQuestion(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"question_1"),"question_1")
            getTotalQuestion(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"question_2"),"question_2")
            getTotalQuestion(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"question_3"),"question_3")
            getTotalQuestion(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"question_4"),"question_4")
            getTotalQuestion(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"question_5"),"question_5")
            getTotalQuestion(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"question_6"),"question_6")
            getTotalQuestion(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"question_7"),"question_7")
            getTotalQuestion(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"question_8"),"question_8")
            getTotalQuestion(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"question_9"),"question_9")
            getTotalQuestion(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"question_10"),"question_10")
            getTotalQuestion(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"question_11"),"question_11")
            getTotalQuestion(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"question_12"),"question_12")
            getTotalQuestion(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"question_13"),"question_13")
            getTotalQuestion(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"question_14"),"question_14")
            getTotalQuestion(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"question_15"),"question_15")
            getTotalQuestion(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"question_16"),"question_16")
            getTotalQuestion(updateSelection(newAcad,newSem,newFaculty,newPro,newModule,newClass,newLec,"question_17"),"question_17")
            
            
        });
        getAPI(finalSelection)
    });
   
    
    
    
    
});
function apendTitle(title){
    //console.log(title)
    if(title==="attendance"){
        console.log("a")
        $("<h1>Percentage of respondent by attendance</h1>").appendTo("."+`${title}`);
    }
    else if(title==="gender"){
        console.log("a")
        $("<h1>Percentage of respondent by gender</h1>").appendTo("."+`${title}`);
    }
    else if(title==="question1"){
        $("<h1>Clearance of module objectives</h1>").appendTo("."+`${title}`);
    }
    else{return}
    
}
function getAPI(arr){
    let stringAPI="http://localhost:8080/survey/api/general?"
    // const newAPIString=""
    // stringAPI.concat("aaaaa")
    // for(let i=0;i<arr.length;i++){
    //     if(arr[i]==="--any--"){
    //         console.log(arr[i])
    //         console.log("a "+stringAPI)
    //         const newString=stringAPI.concat("null").concat("&&")
    //         console.log(newString)
    //     }
    //     else if(arr[i]+1===null){
    //         console.log(arr[i])
    //         console.log("b "+stringAPI)
    //         const newString=stringAPI.concat(arr[i])
    //         console.log(newString)
    //     }
    //     else{
    //         console.log(arr[i])
    //         console.log("c "+stringAPI)
            
    //         const newString=stringAPI.concat(arr[i]).concat("&&")
    //         console.log(newString)
    //     }
    // }
    // console.log(newAPIString)
}
function updateSelection(getAcad,getSem,getFaculty,getPro,getModule,getClass,getLec,key){
    const selectArr=[]
    let API="http://localhost:8080/survey/api/questionnaire/"+`${key}`+"?"+"academic_year="+`${replaceAny(getAcad)}`+"&"+"semester="+
   `${replaceAny(getSem)}`+"&"+"faculty="+`${replaceAny(getFaculty)}`+"&"+"program="+`${replaceAny(getPro)}`+"&"+"module="+`${replaceAny(getModule)}`+
   "&"+"lecturer="+`${replaceAny(getLec)}`+"&"+"class="+`${replaceAny(getClass)}`
    // console.log(getAcad)
    // if(checkSelection){
    //     selectArr.push(getAcad)
    //     selectArr.push(getSem)
    //     selectArr.push(getFaculty)
    //     selectArr.push(getPro)
    //     selectArr.push(getModule)
    //     selectArr.push(getClass)
    //     selectArr.push(getLec)
    // }
    //console.log(API)
    return API
    //console.log(selectArr)
}
function replaceAny(string){
    if(string==="--any--"){
        return "null"
    }
    else{
        return string
    }
}
function checkSelections(){
    if(checkSelection("sel-acad")||checkSelection("sel-sem")||checkSelection("sel-faculty")||checkSelection("sel-pro")
    ||checkSelection("sel-module")||checkSelection("sel-class")||checkSelection("sel-lec"))    
    {
        return true
    }
}
function checkSelection(getClass){
    if(!($("."+`${getClass}`+" option:selected").val()=="starter")){
        //console.log("true")
        return true
    }
}
function getAcad(){
	const selectedAcad=$('.sel-acad').find(":selected").text();
    //console.log(selectedAcad)
    return selectedAcad
}
function getSem(){
	
    const selectedSem=$('.sel-sem').find(":selected").text();
    //console.log(selectedSem)
    return selectedSem
}
function getFaculty(){

    const selecteFaculty=$('.sel-faculty').find(":selected").text();
    //console.log(selecteFaculty)
    return selecteFaculty
}
function getPro(){

    const selectedPro=$('.sel-pro').find(":selected").text();
    //console.log(selectedPro)
    return selectedPro
}
function getModule(){
	
    const selectedModule=$('.sel-module').find(":selected").text();
    //console.log(selectedModule)
    return selectedModule
}
function getClass(){
	
    const selectedClass=$('.sel-class').find(":selected").text();
    //console.log(selectedClass)
    return selectedClass
}
function getLec(){

    const selectedLec=$('.sel-lec').find(":selected").text();
    //console.log(selectedLec) 
    return selectedLec        
}

function getSelection(){
    $.ajax({
		type: 'GET',
		url: 'http://localhost:8080/survey/api/general',
		success: function(data) {
			const uniqueAcad=new Set(data.map(val=>{
                return val.AYcode
            }))
            const acadArr=[...uniqueAcad]
            acadArr.map(val=>{
            $(` 
                <option>${val}</option>       
            `).appendTo(".sel-acad");
            })
            const uniqueSem=new Set(data.map(val=>{
                return val.Scode
            }))
            const semArr=[...uniqueSem]
            semArr.map(val=>{
            $(` 
                <option>${val}</option>       
            `).appendTo(".sel-sem");
            })
            const uniqueFaculty=new Set(data.map(val=>{
                return val.Fcode
            }))
            const fnameArr=[...uniqueFaculty]
            fnameArr.map(val=>{
            $(` 
                <option>${val}</option>       
            `).appendTo(".sel-faculty");
            })
            const uniquePname=new Set(data.map(val=>{
                return val.Pcode
            }))
            const pnameArr=[...uniquePname]
            pnameArr.map(val=>{
            $(` 
                <option>${val}</option>       
            `).appendTo(".sel-pro");
            })
            const uniqueMname=new Set(data.map(val=>{
                return val.Mcode
            }))
            const mnameArr=[...uniqueMname]
            mnameArr.map(val=>{
            $(` 
                <option>${val}</option>       
            `).appendTo(".sel-module");
            })
            const uniqueClass=new Set(data.map(val=>{
                return val.Ccode
            }))
            const classArr=[...uniqueClass]
            classArr.map(val=>{
            $(` 
                <option>${val}</option>       
            `).appendTo(".sel-class");
            })
            const uniqueLname=new Set(data.map(val=>{
                return val.Lcode
            }))
            const lnameArr=[...uniqueLname]
            lnameArr.map(val=>{
                $(` 
                <option>${val}</option>       
            `).appendTo(".sel-lec");
            })
            
            
		},
        error:function () { 
            alert("Error loading order");
        }
	});
}
function getTotalQuestion(api,key){
    const API=api.toString(); 
    console.log(API) 
    $.ajax({
        type: "GET",
        url: API,
        
        success: function (data) {
           
            const getChartName=key.split(".")
            //console.log(getChartName[0])
            const obj=data
            const getValue=[]
            const getLabel=[]
            
            for (const property in obj) {
                getValue.push(parseInt(obj[property]))
                if(isNaN(parseInt(property))){
                    getLabel.push(property)
                    
                }else{
                    getLabel.push(parseInt(property))
                }
                
              }
              //console.log(getValue)
              //console.log(getLabel)
              
              getForm(getLabel,getValue,getChartName[0],Freq(obj))
              
              //drawCircle(getChartName[0])
              
        }
    });
}
function Freq(obj){
    const arr=[];
    for (const property in obj) {
        const length=(parseInt(obj[property]))
        for(let i =0;i<length;i++){
            arr.push(parseInt(property))
        }
    }
    //console.log(arr)
    return arr;
}
function getMean(data,label){
    ;
    const total=data.reduce((curr,val)=>{
        return curr+=val;
    })
    //console.log(total)
    let sum=0;
    let value=0
    for(let i=0;i<data.length;i++){
        value=data[i]*label[i]/total
        sum+=value
    }
    //console.log(sum)
    return sum
}
function calPercent(arr,max){
   const percentage= arr.map((val)=>{
        return Math.round(val*100/max)
    })
    return percentage
}

function  getForm(getLabel,getData,getChart,meanArr){
    // const newLabel=getLabel.map(val=>{
    //     console.log(parseInt(val))
    //     if(parseInt(val)==NaN){
    //         return val
            
    //     }else{
    //         return parseInt(val)
    //     }
       
    // })
    //console.log(getChart)
    //console.log(newLabel)
    //const getArr=JSON.parse(""+ getData +"");
    //console.log(getData)
    const getPercentage=calPercent(getData,42);
    //console.log(getPercentage)
    //console.log(getPercentage)
    //const mean=Math.round(getMean(getData,getLabel))
    const mean=Math.round(jStat.mean(meanArr));
    const sd=Math.round(jStat.stdev(meanArr));
    //console.log(mean)
    //console.log(sd)
    
        $(`<div class=${getChart}></div>
        <canvas id="${getChart}">
        
        </canvas>
        <div class="stats">
            <p>Mean:${mean}</p>
            <p>Sd:${sd}</p>
            <p>size:42</p>
        </div>
        `).appendTo(".chart-container");
        //console.log("a")
    apendTitle(getChart)
    Chart.pluginService.register({
        afterDraw: function(chart) {
            if (typeof chart.config.options.lineAt != 'undefined') {
                var lineAt = chart.config.options.lineAt;
                var ctxPlugin = chart.chart.ctx;
                var xAxe = chart.scales[chart.config.options.scales.xAxes[0].id];
                ctxPlugin.strokeStyle = "green";
                ctxPlugin.beginPath();
                lineAt = 100;
                ctx.arc(3*215,315,15,0,2*Math.PI);
                ctxPlugin.moveTo(xAxe.left, lineAt);
                ctxPlugin.stroke();
                ctxPlugin.closePath();
                ctxPlugin.beginPath();
                ctxPlugin.fillStyle = "black";
                ctx.fill();
                ctxPlugin.font = "30px Arial";
                ctxPlugin.fillText(mean, 3*213,280); 
               
            }
        }
    });
    
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
                data: getPercentage,
                // errorBars: {
                //     '1': {plus: 0},
                    
                 },
                {
                    type: 'bubble',data: [
                        {
                          x: 0,
                          y: 0,
                          r: 0
                        }
                      ],
                      label:'mean',
                    backgroundColor: "rgb(0, 0, 0)",
                    
                    
                  
            }]
        },

        // Configuration options go here
        options: {
            animation: {
                duration: 0
            },
            lineAt: 15,
            annotation: {
                annotations: [{
                    type: 'box',
                    
                    xScaleID: 'x-axis-0',

                    
                    yScaleID: 'y-axis-0',
                    xMin: mean-1,
                    xMax: mean+1,
                    yMax: 50,
                    yMin: 50,
                    borderColor: 'tomato',
                    borderWidth: 1
                }],
                drawTime: "afterDraw" // (default)
            },
            data: getPercentage,
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
                
                chartJsPluginErrorBars: {
                    width: 400,
                    
                    
                    //color: 'darkgray'
                  },
                datalabels: {
                    display: false
                  },
                labels: {
                    
                    render: function (args) {  
                    if(!isNaN(args)){
                        let max = 42; //This is the default 100% that will be used if no Max value is found
                    

                        return Math.round(args.value)+"%"; //Calculate percent
                        }
                        else{
                            return 
                        }
                    }
                }
            }
            
        },  
    });
    
   

    
}
function getAttendanceGen(api,key){
    const API=api.toString();  
    $.ajax({
        type: "GET",
        url: API,
        success: function (data) {
            const getChartName=key.split(".")
            //console.log(getChartName[0])
            const obj=data
            const getValue=[]
            const getLabel=[]
            
            for (const property in obj) {
                getValue.push(parseInt(obj[property]))
                if(isNaN(parseInt(property))){
                    getLabel.push(property)
                    
                }else{
                    getLabel.push(parseInt(property))
                }
                
              }
              //console.log(getValue)
              //console.log(getLabel)
              
              getNewForm(getLabel,getValue,getChartName[0],Freq(obj))
              
              //drawCircle(getChartName[0])
              
        }
    });
}
function Freq(obj){
    const arr=[];
    for (const property in obj) {
        const length=(parseInt(obj[property]))
        for(let i =0;i<length;i++){
            arr.push(parseInt(property))
        }
    }
    //console.log(arr)
    return arr;
}
function getMean(data,label){
    ;
    const total=data.reduce((curr,val)=>{
        return curr+=val;
    })
    //console.log(total)
    let sum=0;
    let value=0
    for(let i=0;i<data.length;i++){
        value=data[i]*label[i]/total
        sum+=value
    }
    //console.log(sum)
    return sum
}
function calPercent(arr,max){
   const percentage= arr.map((val)=>{
        return Math.round(val*100/max)
    })
    return percentage
}

function  getNewForm(getLabel,getData,getChart,meanArr){
    //console.log(getChart)
    const getPercentage=calPercent(getData,42);
    //console.log(getPercentage)
    const mean=Math.round(jStat.mean(meanArr));
    const sd=Math.round(jStat.stdev(meanArr));
    
    console.log(getChart)
        $(`<div class=${getChart}></div>
            
        <canvas id="${getChart}">
       
    </canvas>
    `).appendTo(".chart-container");
    apendTitle(getChart)
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
                data: getPercentage,
                
                  
            }]
        },

        // Configuration options go here
        options: {
            
            data: getPercentage,
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
                
                chartJsPluginErrorBars: {
                    width: 400,
                    
                    
                    //color: 'darkgray'
                  },
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

