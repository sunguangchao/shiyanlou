MEETMANAGE = function(){
  var pagesize = 10;
  var type_data = [['0', '无效'], ['1', '有效']];
	/**
	 * 该方法用来获取选中元素的ID值
	 */
  function resultantNum(selects,id) {

	var ids = "";
	var len = selects.length;
	if(len > 0 && id!=''){
		for(var i=0 ; i<len; i++){
			ids = ids + selects[i].get(id) + ",";
		}
		ids = ids.substr(0,ids.length-1);
	}
	return ids;
  }

  //编号
  var ID = new Ext.form.TextField({
    fieldLabel: '编号',
    anchor: '97%',
    name: 'id',
    id: 'id',
    allowBlank: false,
    maxLength: 5
  });
  //开始时间
  var STIME = new Ext.form.TextField({
    fieldLabel: '服务开始时间11',
    anchor: '97%',
    name: 'startTime',
    id: 'startTime',
    allowBlank: false,
    maxLength: 50
  });
  //结束时间
  var ETIME = new Ext.form.TextField({
    fieldLabel: '服务结束时间',
    anchor: '97%',
    name: 'endTime',
    id:'endTime',
    allowBlank: false,
    maxLength: 50
  });
  //有效标志位
  var FLAG = new Ext.form.TextField({
    fieldLabel: '有效标志',
    anchor: '97%',
    name: 'flag',
    id: 'flag',
    allowBlank: false,
    maxLength: 2
  });
  //座席工号
  var VIPCODE = new Ext.form.TextField({
    fieldLabel: '座席工号',
    anchor: '97%',
    name: 'vipcode',
    id: 'vipcode',
    allowBlank: false,
    maxLength: 20,
    maxLengthText: '超过最大长度20'
  });
  //修改座席姓名
  var VIPNAME = new Ext.form.TextField({
    fieldLabel: '座席姓名',
    anchor: '97%',
    name: 'vipname',
    id: 'vipname',
    allowBlank: false,
    maxLength: 50,
    maxLengthText: '超过最大长度50'
  });
  //更新时的系统时间
  var UPDATETIME = new Ext.form.DateField({
    fieldLabel: '更新时间',
    anchor: '97%',
    name: 'updateTime',
    id: 'updateTime',
    format: 'Y-m-d H:i:s'
    
  });

  //查询模块
  var dataset = {
    layout: 'column',
    xtype: 'fieldset',
    autoWidth: true,
    buttonAlign: 'center',
    labelAlign: 'right',
    title: 'VIP座席服务时间检索',
    checkboxToggle: true,
    items:[{
      layout: 'form',
      columnWidth: .18
    },{
      columnWidth: .40,
      layout: 'form',
      items: [{
        xtype: 'textfield',
        fieldLabel:	'编号',
        allowBlank: true,
        mode: 'local',
        width: 185,
        name: 'VIP_NAME',
        id:	'VIP_ID',
        maxLength:	10,
        maxLengthText: '超过最大长度10'
      }]
    },{
     columnWidth: .2,
     layout: 'form',
     items: [{
    	  xtype: 'button',
    	  text: '查询',
    	  handler: loadData,
    	  anchor: '60%'
      }]
    }]
  };

  //数据
  var store = new Ext.data.Store({
    url: '../../VIPTimeSettingAction.do?type=selectFault',
    reader: new Ext.data.JsonReader({
      root:'rows',  //响应中包含记录数据的节点对应的根属性名称，返回数组名
      totalProperty:'total' //数据中的所有记录数属性名,条数
    },[
      {name:'ID'},
      {name:'BENTIME'},
      {name:'ENDTIME'},
      {name:'LOGINCODE'},
      {name:'AGENTNAME'},
      {name:'UPDATETIME'},
      {name:'FLAG'}
    ]),
    baseParams:{
      start: 0,
      limit: pagesize
    }
  });

  store.on('beforeload', function(store){
    var vip_id = "";
    try{
      vip_id = Ext.getCmp('VIP_ID').getValue().replace(/\"/g,"").replace(/\'/g,"");
    }catch(e){

    }
    var param = {ID:vip_id};
    Ext.apply(store.baseParams, param);
  });

  function loadData(){
    store.load();
  };

  function resetForm(){
    form.getForm().reset();
  };

  function addMeetManage(){
	  var meetform = new Ext.FormPanel({
		  border: false,
		  frame: true,
		  layout: 'column',
		  labelAlign: 'right',
		  items: [{
			  layout: 'form',
			  border: false,
			  style: 'padding 5xp',
			  items: [{
				  columnWidth: .9,
				  layout: 'form',
				  items: [{
					  xtype: 'textfield',
					  fieldLabel: '编号',
					  name:	'id',
					  id: 'id',
					  anchor: '48%',
					  allowBlank: false,
					  maxLength: 10,
					  maxLengthText: '超过最大长度10'
				  }]
			  },{
				  columnWidth : .9,
				  layout : 'form',
				  items : [{
					  xtype: 'textfield',
					  fieldLabel : '服务开始时间',
					  editable : true,
					  anchor: '48%',
					  name: 'startTime',
					  id: 'startTime',
					  maxLength:20
				  }]
			  },{
				  columnWidth : .9,
				  layout : 'form',
				  items : [{
					  xtype: 'textfield',
					  fieldLabel: '服务结束时间',
					  editable: true,
					  anchor: '48%',
					  name: 'endTime',
					  id: 'endTime',
					  maxLength:20
				  }]
			  },{
//				  columnWidth : .9,
//				  layout : 'form',
//				  items : [{
//					  xtype: 'textfield',
//					  fieldLabel: '有效标志',
//					  editable: true,
//					  anchor: '48%',
//					  name: 'flag',
//					  id: 'flag',
//					  maxLength:20
//				  }]
				  columnWidth : .9,
				  layout : 'form',
				  items : [{
					  xtype	: 'combo',
					  fieldLabel: '有效标志',
					  name: 'flag',
					  id: 'flag',
					  width: 150,
					  store: type_data,
					  editable: false,
					  emptyText: '请选择',
					  triggerAction : 'all',
					  anchor: '48%',
					  mode: 'local',
					  valueField: 'value', //?
					  displayField: 'name'	//?
				  }]
			  },{
				  columnWidth: .9,
				  layout: 'form',
				  items: [{
					  xtype: 'textfield',
					  fieldLabel: '座席工号',
					  name:	'vipcode',
					  id: 'vipcode',
					  anchor: '48%',
					  value: AGENTID,
					  allowBlank: false,
					  maxLength: 20,
					  maxLengthText: '超过最大长度20'
				  }]

			  },{
				  columnWidth: .9,
				  layout: 'form',
				  items: [{
					  xtype: 'textfield',
					  fieldLabel: '座席姓名',
					  name:	'vipname',
					  id: 'vipname',
					  anchor: '48%',
					  value: AGENTNAME,
					  allowBlank: false,
					  maxLength: 50,
					  maxLengthText: '超过最大长度50'
				  }]
			  },{
          columnWidth: .9,
          layout: 'form',
          items: [{
            xtype: 'hidden',
            fieldLabel: '更新时间',
            editable: false,
            readOnly: true,
            anchor: '48%',
            name: 'updateTime',
            id: 'updateTime',
            value: new Date(),
            format : 'Y-m-d H:i:s'
          }]
        }]
		  }]
	  });
	  var meetwin = null;
	  if(meetwin == null){
		  meetwin = new Ext.Window({
			  title: 'VIP座席服务时间新增',
			  closeable	:	true,
			  width	: 700,
			  height 	:	260,
			  plain	:	true,
			  resizable	:	false,
			  modal : true,
			  iconCls	:	'win',
			  border 	: 	false,
			  buttonAlign :	'center',
			  bodyStyle	:	'padding:5px',
			  items 	:	meetform,
			  buttons: [{
				  text: '新增',
				  handler: function(){
					  var ID = Ext.getCmp('id').getValue();
				      var STIME = Ext.getCmp('startTime').getValue();
				      var ETIME = Ext.getCmp('endTime').getValue();
				      var VIPCODE = Ext.getCmp('vipcode').getValue();
				      var VIPNAME = Ext.getCmp('vipname').getValue();
				      var UPDATETIME = Ext.getCmp('updateTime').getValue();
				      var FLAG = Ext.getCmp('flag').getValue();
//					  STIME=Ext.util.Format.date(STIME, 'H:i:s');
//					  ETIME=Ext.util.Format.date(ETIME, 'H:i:s');
                      UPDATETIME=Ext.util.Format.date(UPDATETIME, 'Y-m-d H:i:s');
					  if(ID == "" || ID == null){
						  Ext.Msg.alert('信息提示',"编号为必填项!");
						  return;
					  }
					  if(VIPCODE == "" || VIPCODE == null){
						  Ext.Msg.alert('信息提示',"座席编号为必填项!");
						  return;
					  }
					  if(VIPNAME == "" || VIPNAME == null){
						  Ext.Msg.alert('信息提示',"座席姓名为必填项!");
						  return;
					  }
					  var form = meetform.getForm();
					  form.submit({
						  url : '../../VIPTimeSettingAction.do?type=insertFault',
						  //后面的是要传入的参数，前面的是数据库对应的字段
						  params: {ID:ID, BENTIME:STIME, ENDTIME:ETIME, LOGINCODE:VIPCODE, AGENTNAME:VIPNAME, FLAG:FLAG, UPDATETIME:UPDATETIME},
						  scope: this,
						  success: function(form, action){
							  store.reload();
							  meetwin.destroy();
							  Ext.Msg.alert('信息提示', action.result.msg);
						  },
						  failure: function(form, action){
							  store.reload();
							  meetwin.destroy();
							  Ext.Msg.alert('信息提示', action.result.msg);
						  }
					  });
				  }
			  },{
				  text:'关闭',
				  handler: function(){
					  meetwin.destroy();
				  }
			  }]

		  });
	  }
	  meetwin.show();
  };




  //维护、修改信息
  function meetManage(id, startTime, endTime, vipcode, vipname, updateTime, flag){
	  var meetform = new Ext.FormPanel({
		  border: false,
		  frame: true,
		  layout: 'column',
		  labelAlign: 'right',
		  items: [{
			  layout: 'form',
			  border: false,
			  style: 'padding 5xp',
			  items: [{
				  columnWidth: .9,
				  layout: 'form',
				  items: [{
					  xtype: 'textfield',
					  fieldLabel: '编号',
					  name:	'id',
					  id: 'id',
					  anchor: '48%',
					  value: id,
					  allowBlank: false,
					  maxLength: 10,
					  maxLengthText: '超过最大长度10'
				  }]
			  },{
				  columnWidth : .9,
				  layout : 'form',
				  items : [{
					  xtype: 'textfield',
					  fieldLabel : '服务开始时间',
					  editable : true,
					  anchor: '48%',
					  name: 'startTime',
					  id: 'startTime',
					  maxLength:20,
					  value: startTime
				  }]
			  },{
				  columnWidth : .9,
				  layout : 'form',
				  items : [{
					  xtype: 'textfield',
					  fieldLabel: '服务结束时间',
					  editable: true,
					  anchor: '48%',
					  name: 'endTime',
					  id: 'endTime',
					  maxLength:20,
					  value: endTime
				  }]
			  },{
//				  columnWidth : .9,
//				  layout : 'form',
//				  items : [{
//					  xtype: 'textfield',
//					  fieldLabel: '有效标志',
//					  editable: true,
//					  anchor: '48%',
//					  name: 'flag',
//					  id: 'flag',
//					  maxLength:20,
//					  value: flag
//				  }]
			  columnWidth : .9,
			  layout : 'form',
			  items : [{
				  xtype	: 'combo',
				  fieldLabel: '有效标志',
				  name: 'flag',
				  id: 'flag',
				  width: 150,
				  value: flag,
				  store: type_data,
				  editable: false,
				  emptyText: '请选择',
				  triggerAction : 'all',
				  anchor: '48%',
				  mode: 'local',
				  valueField: 'value', //?
				  displayField: 'name'	//?
			  }]
			  },{
				  columnWidth: .9,
				  layout: 'form',
				  items: [{
					  xtype: 'textfield',
					  fieldLabel: '座席工号',
					  name:	'vipcode',
					  id: 'vipcode',
					  anchor: '48%',
					  allowBlank: false,
					  value: vipcode,
					  maxLength: 20,
					  maxLengthText: '超过最大长度20'
				  }]

			  },{
				  columnWidth: .9,
				  layout: 'form',
				  items: [{
					  xtype: 'textfield',
					  fieldLabel: '座席姓名',
					  name:	'vipname',
					  id: 'vipname',
					  anchor: '48%',
					  allowBlank: false,
					  value: vipname,
					  maxLength: 50,
					  maxLengthText: '超过最大长度50'
				  }]
			  },{
                  columnWidth: .9,
                  layout: 'form',
                  items: [{
                    xtype: 'hidden',
                    fieldLabel: '更新时间',
                    editable: false,
                    readOnly: true,
                    anchor: '48%',
                    name: 'updateTime',
                    id: 'updateTime',
                    value: new Date(),
                    format : 'Y-m-d H:i:s'
                  }]
				}]
		  }]

	  });
	  var meetwin = null;
	  if(meetwin == null){
		  meetwin = new Ext.Window({
			  title: 'VIP座席服务时间修改',
			  closeable: true,
			  width: 700,
			  height: 260,
			  plain: true,
			  resizable: false,
			  modal: true,
			  iconCls: 'win',
			  border: false,
			  buttonAlign: 'center',
			  bodyStyle: 'padding:5px',
			  items: meetform,
			  buttons: [{
				  text: '修改',
				  handler: function(){
					  var ID = Ext.getCmp('id').getValue();
				      var STIME = Ext.getCmp('startTime').getValue();
				      var ETIME = Ext.getCmp('endTime').getValue();
				      var VIPCODE = Ext.getCmp('vipcode').getValue();
				      var VIPNAME = Ext.getCmp('vipname').getValue();
				      var UPDATETIME = Ext.getCmp('updateTime').getValue();
				      var FLAG = Ext.getCmp('flag').getValue();
				      UPDATETIME=Ext.util.Format.date(UPDATETIME, 'Y-m-d H:i:s');
					  if(ID == "" || ID == null){
						  Ext.Msg.alert('信息提示',"编号为必填项!");
						  return;
					  }
					  if(VIPCODE == "" || VIPCODE == null){
						  Ext.Msg.alert('信息提示',"座席编号为必填项!");
						  return;
					  }
					  if(VIPNAME == "" || VIPNAME == null){
						  Ext.Msg.alert('信息提示',"座席姓名为必填项!");
						  return;
					  }
					  var form = meetform.getForm();
					  form.submit({
						  url : '../../VIPTimeSettingAction.do?type=updateFault',
						  params: {ID:ID, BENTIME:STIME, ENDTIME:ETIME, LOGINCODE:VIPCODE, AGENTNAME:VIPNAME, FLAG:FLAG, UPDATETIME:UPDATETIME}, 
						  scope: this,
						  success: function(form, action){
							  store.reload();
							  meetwin.destroy();
							  Ext.Msg.alert('信息提示', action.result.msg);
						  },
						  failure: function(form, action){
							  store.reload();
							  meetwin.destroy();
							  Ext.Msg.alert('信息提示', action.result.msg);
						  }
					  });
				  }
			  },{
				  text:'关闭',
				  handler: function(){
					  meetwin.destroy();
				  }
			  }]

		  });
	  }
	  meetwin.show();
  };
  smcheck = new Ext.grid.CheckboxSelectionModel({checkOnly:true});
  var taskGrid = new Ext.grid.GridPanel({
	  	id: 'grid_grid',
		sm: smcheck,
		store: store,
		height: Ext.get("form").getHeight()-202,
		loadMask: {msg:'正在加载数据，请稍侯......'},
		colModel: new Ext.grid.ColumnModel({
      columns: [
        smcheck,
        new Ext.grid.RowNumberer(),
        {header: '编号', dataIndex:'ID', width:50, sortable:true, align: 'center'},
        {header: '服务开始时间', dataIndex:'BENTIME', width:100, sortable:true, align: 'center'},
        {header: '服务结束时间', dataIndex:'ENDTIME', width:100, sortable:true, align: 'center'},
        {header: '座席工号', dataIndex:'LOGINCODE', width:100, sortable:true, align: 'center'},
        {header: '座席姓名', dataIndex:'AGENTNAME', width:100, sortable:true, align: 'center'},
        {header: '更新时间', dataIndex:'UPDATETIME', width:100, sortable:true, align: 'center'},
        {header: '有效标志', dataIndex:'FLAG', width:100, sortable:true, align: 'center'}
      ]
    }),
    listeners: {
      celldblclick: function(grid, rowIndex, columnIndex, e){
        var record = grid.getStore().getAt(rowIndex);
        var ID = record.get('ID');
        var STIME = record.get('BENTIME');
        var ETIME = record.get('ENDTIME');
        var VIPCODE = record.get('LOGINCODE');
        var VIPNAME = record.get('AGENTNAME');
        var UPDATETIME = record.get('UPDATETIME');
        var FLAG = record.get('FLAG');

        meetManage(ID, STIME, ETIME, VIPCODE, VIPNAME, UPDATETIME, FLAG);
      },
      afterrender: loadData
    },
    viewConfig:{
      forceFit: true,
      scrollOffset: 0
    },
    stripeRows: true,
    tbar: [{
      /*iconCls : 'icon-refresh',*/
			icon:'../../images/common/refresh.gif',
			text: '刷新',
			handler : function() {
				store.reload();
			}
    },'-',{
      text: '新增',
			/*iconCls : 	'icon-add',*/
			icon: '../../images/common/add.png',
			handler : function(){
				addMeetManage();
			}
    },'-',{
      text: '维护',
      iconCls: 'icon-edit',
      handler : function(){
        if (smcheck.getCount() == 0){
          Ext.Msg.alert('信息提示',	'请选择要修改的服务时间');
        }else if (smcheck.getCount() > 1){
          Ext.Msg.alert('信息提示',	'只能操作一条记录,请重新选择!');
        }else {
          var selects = smcheck.getSelections();
          var ID = resultantNum(selects, 'ID');
          var STIME = resultantNum(selects, 'BENTIME');
          var ETIME = resultantNum(selects, 'ENDTIME');
          var VIPCODE = resultantNum(selects, 'LOGINCODE');
          var VIPNAME = resultantNum(selects, 'AGENTNAME');
          var UPDATETIME = resultantNum(selects, 'UPDATETIME');
          var FLAG = resultantNum(selects, 'FLAG');
          meetManage(ID, STIME, ETIME, VIPCODE, VIPNAME, UPDATETIME, FLAG);
        }
      }
    },'-',{
      text: '删除',
      icon: '../../images/common/delete.png',
      handler : function(){
        if (smcheck.getCount() == 0){
          Ext.Msg.alert('信息提示',	'请选择要删除的服务时间');
        }else{
          var selects = smcheck.getSelections();
          var ID = resultantNum(selects, 'ID');
          Ext.MessageBox.confirm('VIP座席服务时间删除','请确认是否删除', function(btn){
            if (btn == 'yes'){
              Ext.Ajax.request({
                url: '../../VIPTimeSettingAction.do?type=deleteFault',
                params: {ID:ID},
                success: function(response){
                  var obj = Ext.decode(response.responseText);
                  var msg = obj.msg;
                  Ext.Msg.alert('提示', msg);
                  store.reload();
                },
                failure: function(response){
                  var obj = Ext.decode(response.responseText);
                  var msg = obj.msg;
                  Ext.Msg.alert('提示', msg);
                  store.reload();
                }
              });
            }
          });
        }
      }
    }],
    bbar: new Ext.PagingToolbar({
      pageSize: pagesize,
			store: store,
			displayInfo: true,
			displayMsg: '当前显示 {0}   条 到   {1}条记录 ,总记录为 {2} ',
			emptyMsg: '没有记录！'
    })
  });

  var form = new Ext.FormPanel({
    id: 'form_form',
    title:	'当前位置：综合管理 >> 参数设置>> VIP座席服务时间设置',
    bodyStyle:	'padding:10px',
    frame:	true,
    autoScroll:	true,
    labelWidth: 60,
    width: ymwidth,
    autoHeight:	true,
    items:	[dataset,taskGrid],
    html: 	'<div id="showdata"></div>',
    renderTo:	'form'
  });
  return form;

};
