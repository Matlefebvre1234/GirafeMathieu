webix.ready(function() {
    var queryString = decodeURIComponent(window.location.search);
    queryString = queryString.substring(1);

    var queries = queryString.split("?");
    if (webix.CustomScroll)
        webix.CustomScroll.init();

    webix.ui({
        view: "scrollview",
        id: "scrollview",
        scroll: "y",
        height: "80%",
        width: "80%", body: {
            rows: [{
                view: "label",
                id: "agegTitre",
                height: 200,
                label: queries,
                css: "titre",
                align: "center"
                //label: "Bas De texte"
            }]
        }
    });
})


