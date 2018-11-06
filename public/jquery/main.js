$(document).ready(async function () {
    const data = await $.getJSON("/windengine/001/data/json")
    const table = $('#table')

    for(i in data) {
        table.append(tr([i, data[i]]))
    }
})

function tr(childs) {
    let ret = "<tr>"
    for(x of childs) {
        ret += "<td>"
        ret += x
        ret += "</td>"
    }
    return ret + "</tr>"
}