Ext.onReady(function(){

	Ext.BLANK_IMAGE_URL = 'ext-3.4.1/resources/images/default/s.gif';
	
    var Pet = Ext.data.Record.create([
    {
        name: 'id',
        type: 'string'
    }, {
        name: 'name',
        type: 'string'
    }, {
        name: 'species',
        type: 'string'
    }, {
        name: 'owner',
        type: 'string'
    }]);
    
    var proxy = new Ext.data.HttpProxy({
        api: {
            read : 'pet/view.action',
            create : 'pet/create.action',
            update: 'pet/update.action',
            destroy: 'pet/delete.action'
        }
    });
    
    var reader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: 'id',
        root: 'data',
        messageProperty: 'message'  // <-- New "messageProperty" meta-data
    }, 
    Pet);

 // The new DataWriter component.
    var writer = new Ext.data.JsonWriter({
        encode: true,
        writeAllFields: true
    });
    
 // Typical Store collecting the Proxy, Reader and Writer together.
    var store = new Ext.data.Store({
        id: 'user',
        proxy: proxy,
        reader: reader,
        writer: writer,  // <-- plug a DataWriter into the store just as you would a Reader
        autoSave: true // <-- false would delay executing create, update, destroy requests until specifically told to do so with some [save] buton.
    });

    //read the data from simple array
    store.load();
    
    Ext.data.DataProxy.addListener('exception', function(proxy, type, action, options, res) {
    	Ext.Msg.show({
    		title: 'Ошибка',
    		msg: res.message,
    		icon: Ext.MessageBox.ERROR,
    		buttons: Ext.Msg.OK
    	});
    });
	
    var editor = new Ext.ux.grid.RowEditor({
		saveText: 'Сохранить',
		cancelText: 'Отмена'		
    });
	
    // create grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        columns: [
            {header: "id",
             width: 50,
             sortable: true,
             dataIndex: 'id',
             editor: {
                xtype: 'textfield',
                allowBlank: true
            },
			 hidden: true,
			},
			{header: "Имя",
             width: 200,
             sortable: true,
             dataIndex: 'name',
             editor: {
                xtype: 'textfield',
                allowBlank: false
            }},
            {header: "Вид",
             width: 150,
             sortable: true,
             dataIndex: 'species',
             editor: {
                 xtype: 'textfield',
                 allowBlank: false
            }},
            {header: "Владелец",
             width: 200,
             sortable: true,
             dataIndex: 'owner',
             editor: {
                xtype: 'textfield',
                allowBlank: false
            }}
        ],
        plugins: [editor],
        title: 'Питомцы',
        height: 400,
        width:670,
		frame:true,
		tbar: [{
            iconCls: 'icon-add',
            text: 'Добавить',
            handler: function(){
                var e = new Pet({
                    name: 'Рекс',
					species: 'собака',
					owner: 'Иванов Александр'
                });
                editor.stopEditing();
                store.insert(0, e);
                grid.getView().refresh();
                grid.getSelectionModel().selectRow(0);
                editor.startEditing(0);
            }
        },{
            iconCls: 'icon-delete',
            text: 'Удалить',
            handler: function(){
                editor.stopEditing();
                var s = grid.getSelectionModel().getSelections();
                for(var i = 0, r; r = s[i]; i++){
                    store.remove(r);
                }
            }
        }]
    });

    //render to DIV
    grid.render('crud-grid');
});