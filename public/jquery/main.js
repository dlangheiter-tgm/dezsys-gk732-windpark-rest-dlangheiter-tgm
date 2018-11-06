const exceptions = ["windengineID", "timestamp"]

$(document).ready(async function () {
    const data = await $.getJSON("/windengine/001/data/json")
    const table = $('#table')

    let units = {}

    for(i in data) {
        if(exceptions.includes(i)) {
            $("#" + i).text(data[i])
        } else if(i.startsWith("unit")) {
            units[i.replace("unit", "").toLowerCase()] = data[i]
        } else {
            table.append(`<tr><td>${i}</td><td id="${i}">${data[i]}</td></tr>`)
        }
    }

    for(i in units) {
        $(`#${i}`).append(" " + units[i])
    }
})