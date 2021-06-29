webix.ui({
    rows: [
        { type: "header", template: "Click a film to open the form" },
        {
            id: "mymulti",
            height: 500,
            cells: [
                {
                    id: "listView",
                    view: "list",
                    template: "#rank#. #title# <div style='padding-left:18px'> Year:#year#, votes:#votes# </div>",
                    type: {
                        height: 60
                    },
                    select: true,
                    data: grid_data,
                    on: {
                        onAfterSelect: function (id) {
                            $$("mymulti").setValue("formView");
                        }
                    }
                },
                {
                    id: "formView",
                    view: "form",
                    elements: [
                        { view: "text", label: "Title", name: "title" },
                        { view: "text", label: "Year", name: "year" },
                        { view: "text", label: "Votes", name: "votes" },
                        {
                            view: "button", value: "Save",
                            click: () => {
                                $$("formView").save();
                                $$("mymulti").setValue("listView");
                            }
                        },
                        {}
                    ]
                },
                {
                    id: "aboutView",
                    template: "<i>Select an item in List to edit it in Form</i>",
                    padding: 5
                }
            ]
        },
        { view: "datatable", autoConfig: true, data: grid_data }
    ]
});

$$("formView").bind($$("listView"));