var debug = console.log.bind(console); //Debuger

async function dummydata() {
    var data = await fetch("http://localhost:8080/api/lecturers")
    return await data.json();
}

async function renderDummy() {
    let lecturers = await dummydata();
    lecturers.map(lecturer => {
        var lecturerSelect = document.createElement("option")
        lecturerSelect.innerHTML = [lecturer.name, lecturer.code].join(" - ");
        lecturerSelect.value = lecturer.name;
        document.getElementById("lecturers").append(lecturerSelect)
    })

    lecturers.map(lecturer => {
            var dummyRender = document.getElementById("dummy");
            var header = document.createElement("h2");
            header.innerText = lecturer.code;
            header.className = "lecturer_header";
            var content = document.createElement("p");
            content.innerText = lecturer.name;
            content.className = "lecturer_name";
            var divCover = document.createElement("div");
            divCover.className = "lecturer_container";
            divCover.append(header);
            divCover.append(content);
            dummyRender.append(divCover);
        }
    )
    return lecturers
}
renderDummy()

var ctx = document.getElementById('myChart').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2, 3],
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
                    beginAtZero: true
                }
            }]
        }
    }
});

