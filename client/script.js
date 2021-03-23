async function dummydata() {
    var data = await fetch("http://localhost:8080/api/lecturers")
    return await data.json()
}

async function renderDummy() {
    let lecturers = await dummydata();
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
}
renderDummy();